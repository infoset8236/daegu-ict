jQuery(document).ready(function ($) {
    const slider = {
        $container: $(".queueSlideWrap"), // 슬라이더 컨테이너
        $items: $(".queueSlideWrap").children(".queueSlideItem"), // 슬라이드 아이템
        groupSize: 3 // 한 그룹당 아이템 수
    };

    // 슬라이드 아이템이 4개 이상일 때만 슬라이더, 게이지, 페이지네이션 활성화
    if (slider.$items.length >= 4) {
        // 그룹화: 슬라이드 아이템을 groupSize(3개) 단위로 묶음
        if (slider.$items.length >= slider.groupSize) {
            for (let i = 0; i < slider.$items.length; i += slider.groupSize) {
                const $groupItems = slider.$items.slice(i, i + slider.groupSize);
                $groupItems.wrapAll('<div class="slideGroup"></div>');
            }
        }

        const $groups = slider.$container.children(".slideGroup"); // 그룹화된 슬라이드
        const totalGroups = $groups.length; // 전체 그룹 수
        let $gauge = $(".queueGauge"); // 게이지 요소
        let $paging = $(".queuePaging"); // 페이지네이션 바 배경 요소

        // Slick 슬라이더 초기화
        slider.$container.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: true,
            prevArrow: $(".queuePrev"),
            nextArrow: $(".queueNext"),
            autoplay: true,
            autoplaySpeed: 8000,
            dots: false,
            variableWidth: true,
            infinite: true
        }).on('init', function(event, slick) {
            // 슬라이더stru: .queueGauge, .queuePaging, .queueArrow 표시
            $gauge.show();
            $paging.show();
            $(".queueArrow").show();
            // 초기 게이지 설정
            updateGauge(0);
        }).on('afterChange', function(event, slick, currentSlide) {
            // 슬라이드 변경 시 게이지 업데이트
            updateGauge(currentSlide);
        });

        // 게이지 업데이트 함수
        function updateGauge(currentIndex) {
            const totalGroups = $groups.length;
            const currentGroupsPassed = Math.min(currentIndex + 1, totalGroups);
            const percent = (currentGroupsPassed / totalGroups) * 100;
            $gauge.css("width", percent + "%"); // 게이지 너비 설정
        }

        // 페이지 로드 시 즉시 게이지 설정
        updateGauge(0);
    } else {
        // 슬라이드 아이템이 4개 미만일 경우, 슬라이더, 게이지, 페이지네이션, 화살표 숨김
        $(".queueGauge").hide();
        $(".queuePaging").hide(); // 페이지네이션 바 배경 숨김
        $(".queueArrow").hide();
        slider.$container.removeClass("slick-initialized slick-slider"); // Slick 클래스 제거
    }

    // 화살표 클릭 시 기본 동작 방지
    $(".queueArrow").on("click", function(e) {
        e.preventDefault();
    });
});
