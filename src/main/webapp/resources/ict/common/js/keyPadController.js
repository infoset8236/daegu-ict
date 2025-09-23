/**
 * keyPadController.js
 *
 * Version: 1.0.1
 * Author: [jbh]
 * Last Updated: 2025-01-08
 * Description: 소켓 통신 기반 키패드 컨트롤러로, 웹 접근성을 고려하여 포커스 이동, 모달 관리,
 *              TTS 및 볼륨 제어, 이어폰 상태 등을 처리합니다.
 *
 * Change Log:
 * - [1.0.1] 2025-01-08: 소켓 이벤트는 keyup 이후 발생하므로 실시간 DOM 이벤트 흐름과 동기화되지 않음.
 * - [1.0.0] 2024-11-26: 초기 구현 완료 (TTS, 볼륨 제어, 모달 관리, 키 이벤트 처리)
 * - [0.9.0] 2024-11-20: 키보드 이벤트와 소켓 통신 로직 통합
 * - [0.8.0] 2024-11-18: 기본 구조 설계 (포커스 이동 및 TTS 처리)
 *
 * Usage:
 * keyPadController.init(jQuery, { debugMode: true });
 * keyPadController.openModal($('#modal'));
 * keyPadController.closeModal();
 */

const keyPadController = (function () {
    let $; // 전달받은 jQuery 객체를 저장

    const KEYS = {
        TAB: 'Tab',
        LEFT: 'ArrowLeft',
        RIGHT: 'ArrowRight',
        ENTER: 'Enter',
        UP: 'ArrowUp', // ArrowUp
        DOWN: 'ArrowDown', // ArrowDown
        F17: 'F17', // 홈
        F15: 'F15', // 이어폰 IN
        F16: 'F16', // 이어폰 OUT
    };

    /*const KEYS = {
        TAB: 'Tab',
        LEFT: '[LEFT]',
        RIGHT: '[RIGHT]',
        ENTER: '[ENTER]',
        UP: '[UP]', // ArrowUp
        DOWN: '[DOWN]', // ArrowDown
        F17: '[F17]', // 홈
        F15: '[F15]', // 이어폰 IN
        F16: '[F16]', // 이어폰 OUT
    };*/

    const state = {
        isModalActive: false, // 모달 활성화 여부
        $currentModal: null, // 현재 활성화된 모달
        focusElems: [], // 포커스 가능한 요소들
        focusIdx: -1, // 현재 포커스된 요소 인덱스
        $prevFocus: null, // 이전 포커스된 요소
        overlaySelector: '#overlay, .overlay', // 기본 overlay 선택자

        homeKeyTimer: null, // 홈 키 타이머
        homeKeyCount: 0, // 홈 키 연속 입력 횟수
        volume: 50, // 기본 볼륨 (0~100 사이 값)
        volumeStep: 5, // 볼륨 조정 단계

        socket: null, // 소켓 인스턴스
        latestRequestId: null, // 최신 TTS 요청 ID
        isHeadphoneConnected: false, // 이어폰 연결 상태

        debugMode: false, // 디버그 모드 설정
    };

    const methods = {
        /**
         * 디버그 모드에서만 로그 출력.
         * @param {string} message - 출력할 메시지
         * @param {*} data - 추가 데이터 (선택)
         */
        log: (message, data = '') => {
            if (state.debugMode) {
                console.log(`[keyPadController] ${message}`, data);
            }
        },

        /**
         * 오디오 중지 및 초기화
         */
        stopAndResetAudio: () => {
            if (typeof audio !== 'undefined' && audio instanceof Audio) {
                try {
                    audio.pause(); // 오디오 재생 멈춤
                    audio.currentTime = 0; // 오디오 위치 초기화
                    audio.src = ''; // 버퍼 초기화
                    methods.log('오디오 중지 및 버퍼 초기화 완료');
                } catch (error) {
                    methods.log('오디오 처리 중 오류 발생', error);
                }
            } else {
                methods.log('오디오 객체가 정의되지 않음');
            }
        },

        /**
         * 이어폰 상태 확인 및 동기화.
         * 세션 값 기준으로 상태 업데이트 후 반환.
         * @returns {boolean} 이어폰 연결 여부
         */
        isHeadphoneConnected: () => {
            const isTestMode = sessionStorage.getItem('test_mode') === 'true';

            if (isTestMode) {
                state.isHeadphoneConnected = true;
                methods.log('테스트 모드에서 이어폰 연결 상태 강제 활성화');
            } else {
                const sessionValue = sessionStorage.getItem('g_earphone');
                state.isHeadphoneConnected = sessionValue === 'Y'; // 실제 환경에서 동기화
            }

            return state.isHeadphoneConnected;
        },

        /**
         * TTS로 텍스트를 읽음.
         * @param {string} text - TTS로 출력할 텍스트
         * @param {number} speed - 음성 속도
         * @param {number} pitch - 음성 톤
         * @param {string} lang - 언어 (기본값: 한국어)
         */
        speak: (text, speed = 0, pitch = 0, lang = 'ko-KR') => {
            if (!text) return;

            window.speechSynthesis.cancel();
            const utterance = new SpeechSynthesisUtterance(text);
            utterance.lang = lang;
            utterance.rate = speed;
            utterance.pitch = pitch;

            window.speechSynthesis.speak(utterance);
            methods.log('TTS 실행', text);
        },

        /**
         * Overlay를 숨김 처리.
         */
        hideOverlay: () => {
            const $overlay = $(state.overlaySelector);
            if ($overlay.length) {
                $overlay.hide();
                methods.log('Overlay 숨김 처리 완료', $overlay);
            } else {
                methods.log('Overlay를 찾을 수 없음');
            }
        },

        /**
         * 포커스 가능한 요소를 초기화.
         * 모달 활성화 여부에 따라 검색 범위를 다르게 설정.
         * @param {jQuery} $container - 검색 범위의 컨테이너 요소
         */
        resetFocusElems: ($container) => {
            const $allModals = methods.detectAllModals();

            // 활성화된 모달 내에서만 검색
            if (state.isModalActive) {
                state.focusElems = state.$currentModal
                    .find('[tabindex]:not([tabindex="-1"])')
                    .filter((_, el) => $(el).is(':visible')) // display: none 상태 제외
                    .sort((a, b) => $(a).attr('tabindex') - $(b).attr('tabindex'));
            } else {
                // 페이지 전체에서 모든 모달 요소 제외
                state.focusElems = $container
                    .find('[tabindex]:not([tabindex="-1"])')
                    .not($allModals.find('[tabindex]'))
                    .filter((_, el) => $(el).is(':visible')) // display: none 상태 제외
                    .sort((a, b) => $(a).attr('tabindex') - $(b).attr('tabindex'));
            }

            methods.log(
                state.isModalActive
                    ? '모달 포커스 요소 초기화'
                    : '페이지 포커스 요소 초기화',
                state.focusElems
            );
        },

        /**
         * 특정 인덱스로 포커스를 이동하고, TTS로 내용을 읽음.
         * @param {number} idx - 이동할 포커스 인덱스
         */
        setFocus: (idx, readTTS = true) => {
            if (!state.isHeadphoneConnected) {
                methods.log('이어폰 연결 상태 아님');
                return;
            }

            if (idx < 0 || idx >= state.focusElems.length) return;

            // 기존 포커스 제거
            $('.focused').removeClass('focused').blur();
            state.focusIdx = idx;

            // 새 포커스 설정
            const $elem = state.focusElems.eq(idx);
            $elem.addClass('focused').focus();

            // readTTS가 true일 경우에만 TTS 실행
            if (readTTS) {
                methods.log(`TTS 실행 대상: 태그=${$elem.prop('tagName')}, 클래스=${$elem.attr('class') || '없음'}, 데이터 ID=${$elem.data('ttsId')}`);
                socketService.sendTTSMessage($elem.data('ttsId'), $elem.data('ttsValues'));
            } else {
                methods.log('TTS 실행 생략 (readTTS=false)');
            }
        },

        /**
         * 모든 모달을 탐지하여 반환.
         * 활성화 여부와 관계없이 모든 모달 요소를 찾음.
         * @returns {jQuery} - 모든 모달 요소
         */
        detectAllModals: () => $('[role="dialog"], .modal'),

        /**
         * 활성화된 모달을 탐지하여 반환.
         * aria-hidden="false" 또는 visible 상태의 모달을 검색.
         * @returns {jQuery|null} - 활성화된 모달 또는 null
         */
        detectActiveModal: () => {
            const $activeModal = $(
                '[role="dialog"][aria-hidden="false"], .modal:visible'
            ).first();
            if ($activeModal.length) {
                state.isModalActive = true;
                state.$currentModal = $activeModal;
                methods.log('활성화된 모달 탐지', $activeModal);
                return $activeModal;
            }
            state.isModalActive = false;
            state.$currentModal = null;
            return null;
        },

        /**
         * 모달 활성화및 포커스를 초기화.
         * @param {jQuery} $modal - 활성화할 모달 요소
         */
        openModal: ($modal) => {
            state.$prevFocus = $('.focused').not($modal.find('*'));
            methods.log('모달 활성화 전 포커스 요소 저장', state.$prevFocus);

            state.$currentModal = $modal;
            state.isModalActive = true;

            methods.resetFocusElems($modal);
            methods.setFocus(0, false); // 모달 열기 시 readTTS=false로 설정

            $modal.attr('aria-hidden', 'false').show();
            methods.log('모달 활성화');
        },

        /**
         * 모달 비활성화 및 상태 초기화.
         * 모달을 닫고 이전 포커스를 복원.
         */
        closeModal: () => {
            state.isModalActive = false;

            methods.resetFocusElems($(document));

            if (state.$prevFocus && state.$prevFocus.length) {
                // state.$prevFocus.focus();
                state.$prevFocus.addClass('focused').focus();
                methods.log('이전 포커스 요소 복원', state.$prevFocus);

                state.focusIdx = state.focusElems.index(state.$prevFocus);
            } else {
                state.focusIdx = -1;
            }

            if (state.$currentModal) {
                state.$currentModal.attr('aria-hidden', 'true').hide();
                state.$currentModal = null;
            }

            methods.log('모달 비활성화 후 상태 초기화 완료');
        },

        /**
         * Tab 키로 포커스를 이동.
         * 다음 또는 이전 포커스로 이동하고 TTS 실행.
         * @param {number} direction - 이동 방향 (1: 다음, -1: 이전)
         */
        handleTab: (direction) => {
            if (!state.focusElems.length) return;

            const nextIdx =
                (state.focusIdx + direction + state.focusElems.length) %
                state.focusElems.length;

            // Tab 이동 시 readTTS=true로 설정하여 TTS 실행
            methods.setFocus(nextIdx, true);
        },

        /**
         * Enter 키로 현재 포커스된 요소를 클릭.
         */
        handleEnter: () => {
            if (!state.isHeadphoneConnected) {
                methods.log('이어폰 연결 상태 아님');
                return;
            }

            const $focusedElem = state.focusElems.eq(state.focusIdx);

            if (!$focusedElem.length) {
                methods.log('포커스된 요소 없음');
                return;
            }

            if ($focusedElem.hasClass('key')) {
                $focusedElem.trigger('click'); // 가상 키보드 클릭 처리
                methods.log('Enter 키로 가상 키보드 키 클릭 발생', $focusedElem);
            } else if ($focusedElem.is('button, a, span, label, input[type="button"], input[type="submit"], input[type="checkbox"]')) {
                $focusedElem.trigger('click'); // 일반 클릭 처리
                methods.log('Enter 키로 클릭 처리 완료', $focusedElem);
            } else {
                methods.log('Enter 키 처리되지 않은 요소', $focusedElem);
            }
        },

        /**
         * UP 키 핸들러.
         * 볼륨 증가.
         */
        handleUpKey: () => {
            const newVolume = Math.min(state.volume + state.volumeStep, 100); // 100을 초과하지 않도록 제한
            if (newVolume !== state.volume) {
                state.volume = newVolume;
                socketService.sendVolumeMessage(state.volume);
                methods.log('UP 키 입력 처리 - 볼륨 증가', state.volume);
            }
        },

        /**
         * DOWN 키 핸들러.
         * 볼륨 감소.
         */
        handleDownKey: () => {
            const newVolume = Math.max(state.volume - state.volumeStep, 0); // 0 미만으로 내려가지 않도록 제한
            if (newVolume !== state.volume) {
                state.volume = newVolume;
                socketService.sendVolumeMessage(state.volume);
                methods.log('DOWN 키 입력 처리 - 볼륨 감소', state.volume);
            }
        },
        /**
         * Home 키로 동작 수행.
         * - 첫 번째 입력: 현재 포커스된 요소 TTS 다시 듣기
         * - 두 번째 입력: 홈으로 이동
         */
        handleHomeKey: () => {
            if (state.homeKeyTimer) {
                clearTimeout(state.homeKeyTimer);
            }

            state.homeKeyCount += 1;

            if (state.homeKeyCount === 1) {
                methods.log('홈 키 첫 번째 입력: TTS 다시 듣기');
                if (state.focusIdx >= 0 && state.focusIdx < state.focusElems.length) {
                    const $elem = state.focusElems.eq(state.focusIdx);
                    socketService.sendTTSMessage($elem.data('ttsId'), $elem.data('ttsValues'));
                }

                state.homeKeyTimer = setTimeout(() => {
                    state.homeKeyCount = 0;
                    methods.log('홈 키 입력 초기화');
                }, 1000);
            } else if (state.homeKeyCount === 2) {
                socketService.sendTTSMessage('');
                clearTimeout(state.homeKeyTimer);
                methods.log('홈 키 두 번째 입력: 홈으로 이동');
                Router.navigate('/');
                state.homeKeyCount = 0;
            }
        },

        /**
         * 이어폰 연결 처리.
         */
        handleHeadphoneIn: () => {
            state.isHeadphoneConnected = true;
            sessionStorage.setItem('g_earphone', 'Y'); // 세션 스토리지에 상태 저장
            socketService.sendChangeSoundOutput('usb advanced');
            methods.log('이어폰 IN - 사운드 장치 변경 요청');

            // 오디오 엘리먼트 중지 및 초기화
            methods.stopAndResetAudio();
            // 페이지 초기 로드시 첫 번째 요소로 이동 readTTS=false로 설정
            methods.setFocus(0, false);
        },

        /**
         * 이어폰 해제 처리.
         */
        handleHeadphoneOut: () => {
            state.isHeadphoneConnected = false;
            sessionStorage.setItem('g_earphone', 'N'); // 세션 스토리지에 상태 저장
            socketService.sendChangeSoundOutput('realtek');
            methods.log('이어폰 OUT - 사운드 장치 변경 요청');
            methods.log('이어폰 해제됨 (상태 초기화)');
            // 모달 상태에 따른 초기화 처리
            if (state.isModalActive) {
                methods.log('모달 상태: 초기화 처리');
                methods.closeModal(); // 모달 닫기
                methods.hideOverlay(); // Overlay 숨김
            } else {
                methods.log('모달 없음: 페이지 초기화 처리');
                methods.resetFocusElems($(document)); // 포커스 초기화
            }

            // 현재 포커스된 요소 blur 처리
            const $focused = $('.focused');
            if ($focused.length) {
                $focused.removeClass('focused').blur();
                methods.log('포커스 초기화 완료', $focused);
            }

            state.focusIdx = -1; // 포커스 인덱스 초기화
        },

        /**
         * 키와 동작을 매핑한 객체.
         * 특정 키 입력 시 해당 핸들러를 호출.
         */
        keyHandlers: {
            /**
             * Tab 키 핸들러.
             * Shift 키와 함께 눌린 경우 이전 요소로 이동, 아니면 다음 요소로 이동.
             * @param {KeyboardEvent} e - Tab 키 이벤트 객체
             */
            [KEYS.TAB]: (e) => methods.handleTab(e.shiftKey ? -1 : 1),

            /**
             * Tab 키 핸들러.
             * Shift 키와 함께 눌린 경우 이전 요소로 이동, 아니면 다음 요소로 이동.
             * @param {KeyboardEvent} e - Tab 키 이벤트 객체
             */
            [KEYS.LEFT]: (e) => methods.handleTab(-1),

            /**
             * Tab 키 핸들러.
             * Shift 키와 함께 눌린 경우 이전 요소로 이동, 아니면 다음 요소로 이동.
             * @param {KeyboardEvent} e - Tab 키 이벤트 객체
             */
            [KEYS.RIGHT]: (e) => methods.handleTab(1),

            /**
             * Enter 키 핸들러.
             * 현재 포커스된 요소를 클릭 처리.
             */

            [KEYS.ENTER]: () => methods.handleEnter(),

            /**
             * UP 키 핸들러.
             * 오디오 볼륨 업 처리.
             */
            [KEYS.UP]: () => methods.handleUpKey(),

            /**
             * DOWN 키 핸들러.
             * 오디오 볼륨 다운 처리.
             */
            [KEYS.DOWN]: () => methods.handleDownKey(),

            /**
             * Home 키 핸들러.
             * 첫 번째 입력: 현재 포커스된 요소의 텍스트 TTS로 읽기.
             * 두 번째 입력: 홈으로 페이지 이동.
             */
            [KEYS.F17]: () => methods.handleHomeKey(),

            /**
             * F15 키 핸들러.
             * 이어폰 IN.
             */
            [KEYS.F15]: () => methods.handleHeadphoneIn(true), // 이어폰 IN

            /**
             * F16 키 핸들러.
             * 이어폰 OUT.
             */
            [KEYS.F16]: () => methods.handleHeadphoneOut(false), // 이어폰 OUT
        },

        /**
         * 키보드 이벤트 핸들러.
         * keyHandlers에 매핑된 키가 입력되면 해당 핸들러 호출.
         * @param {KeyboardEvent} e - 키보드 이벤트 객체
         */
        handleKeyEvent: (e) => {
            if (!e.key) {
                methods.log('키 입력 값이 없습니다.');
                return;
            }

            const testMode = sessionStorage.getItem('test_mode') === 'true';

            if (!testMode && !state.isHeadphoneConnected) {
                // 이어폰 비활성화 상태에서 F15, F16 키는 동작하도록 예외 처리
                if (e.key === KEYS.F15 || e.key === KEYS.F16) {
                    const handler = methods.keyHandlers[e.key];
                    if (handler) {
                        e.preventDefault(); // 기본 동작 방지
                        handler(e); // 이어폰 상태 변경 처리
                    }
                    return; // 이후 동작 무시
                }

                // 이어폰 미연결 상태에서 다른 키는 차단
                if (Object.values(KEYS).includes(e.key)) {
                    e.preventDefault(); // 기본 동작 방지
                    methods.log(`키 차단됨 (이어폰 비활성화): ${e.key}`);
                }
                return; // 일반 키보드 동작은 그대로 유지
            }

            // 테스트 모드이거나 이어폰 활성화 상태에서 커스텀 키 처리
            const handler = methods.keyHandlers[e.key];
            if (handler) {
                e.preventDefault(); // 기본 동작 방지
                handler(e);
                methods.log(`키 핸들러 실행 완료: ${e.key}`);
            } else {
                methods.log(`정의되지 않은 키 입력: ${e.key}`);
            }
        },

        /**
         * 소켓 key up 처리
         * @param {string} key - 입력된 키 값
         * 사용하지 않는 이유:
         * - 소켓 이벤트는 키 입력 이후 발생하며, DOM 이벤트 흐름과 다름.
         * - 키 입력은 실시간으로 DOM의 keydown 이벤트에서 처리하는 것이 더 적합.
         * - 소켓 이벤트는 부가적인 동작이나 상태 확인 용도로 활용 가능.
         */
        handleKeyInput: (key) => {
            if (!key) {
                methods.log('빈 키 값이 수신되었습니다.');
                return;
            }

            const testMode = sessionStorage.getItem('test_mode') === 'true';
            if (!testMode && !state.isHeadphoneConnected) {
                if (key === KEYS.F15 || key === KEYS.F16) {
                    const handler = methods.keyHandlers[key];
                    if (handler) {
                        handler();
                        methods.log(`키 핸들러 실행 완료: ${key}`);
                    }
                    return; // 이후 동작 무시
                }

                // 이어폰 미연결 상태에서 키 입력 차단
                if (Object.values(KEYS).includes(key)) {
                    methods.log(`키 차단됨 (이어폰 비활성화): ${key}`);
                }
                return; // 이어폰이 연결되지 않은 경우 아무 동작도 하지 않음
            }

            // 이어폰 활성화 상태이거나 테스트 모드에서 커스텀 키 처리
            const handler = methods.keyHandlers[key];
            if (handler) {
                handler();
                methods.log(`키 핸들러 실행 완료: ${key}`);
            } else {
                methods.log(`정의되지 않은 키 입력: ${key}`);
            }
        },

        /**
         * 키보드 이벤트 핸들러 등록.
         * 중복 등록 방지 후 키 이벤트 연결.
         * Tab, Enter, ArrowUP, ArowDown, Home(F17), F15(jack in), F16(jack out) 키 이벤트를 처리.
         */
        attachEvents: () => {
            // 키보드 이벤트 중복 등록 방지
            $(document)
                .off('keydown.keyPadController')
                .on('keydown.keyPadController', methods.handleKeyEvent);
        },

        /**
         * 키패드 컨트롤러 업데이트.
         * 새 페이지나 모달에 포커스를 초기화하고 이어폰 상태를 동기화.
         */
        update: () => {
            state.focusIdx = -1; // 포커스 인덱스 초기화

            // 새로 로드된 페이지의 포커스 요소 설정
            const $initialModal = methods.detectActiveModal();
            if ($initialModal) {
                methods.resetFocusElems($initialModal);
                methods.setFocus(0, false); // 모달의 초기 로드시 첫 번째 요소로 이동 readTTS=false로 설정
            } else {
                methods.resetFocusElems($(document));
                methods.setFocus(0, false); // 새 페이지의 초기 로드시 첫 번째 요소로 이동 readTTS=false로 설정
            }

            methods.isHeadphoneConnected(); // 이어폰 상태 동기화
            methods.log('keyPadController Update 완료');
        },
    };

    /**
     * 컨트롤러 초기화.
     * @param {Object} jqueryInstance - jQuery 인스턴스
     * @param {Object} [options={}] - 설정 옵션
     * @param {boolean} [options.debugMode=false] - 디버그 모드 활성화 여부
     * @param {boolean} [options.testMode=false] - 테스트 모드 활성화 여부(스톰키패드없이 키보드로 테스트시)
     */
    const init = (jqueryInstance, options = {}) => {
        if (!jqueryInstance) {
            console.error('jQuery 인스턴스가 전달되지 않았습니다.');
            return;
        }

        $ = jqueryInstance; // 전달받은 jQuery 객체 저장

        // 디버그 모드 설정
        if (typeof options.debugMode === 'boolean' && options.debugMode) {
            state.debugMode = options.debugMode;
            methods.log('디버그 모드 활성화');
        }

        // 테스트 모드 설정
        const isTestMode = typeof options.testMode === 'boolean' && options.testMode;
        if (isTestMode) {
            sessionStorage.setItem('test_mode', 'true');
            sessionStorage.setItem('g_earphone', 'Y'); // 테스트 모드에서 이어폰 연결 상태 설정
            state.isHeadphoneConnected = true; // 내부 상태 업데이트
            methods.log('테스트 모드 활성화 - 이어폰 상태 설정: Y');
        } else {
            sessionStorage.setItem('test_mode', 'false');

            // 이어폰 상태 초기화
            if (!sessionStorage.getItem('g_earphone')) {
                sessionStorage.setItem('g_earphone', 'N');
            }
            // 일반 환경에서는 이어폰 상태를 세션값에 따라 설정
            state.isHeadphoneConnected = methods.isHeadphoneConnected(); // 기존 값 동기화
            methods.log('일반 모드 활성화 - 이어폰 상태 동기화');
        }

        methods.attachEvents();
        methods.log('keyPadController 초기화 완료');
    };

    return {
        init,
        update: methods.update,
        openModal: methods.openModal,
        closeModal: methods.closeModal,
        handleKeyInput: methods.handleKeyInput,
        isHeadphoneConnected: methods.isHeadphoneConnected,
        setDebugMode: (isEnabled) => {
            state.debugMode = isEnabled;
            methods.log(`디버그 모드 ${isEnabled ? '활성화' : '비활성화'}`);
        },
    };
})(jQuery);
