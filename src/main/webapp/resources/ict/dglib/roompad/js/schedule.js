jQuery(document).ready(function ($) {
    const slider = {
        $container: $(".roomStatusList"), // 슬라이더 컨테이너
        $items: $(".roomStatusList").children(".roomUserInfo"), // 슬라이드 아이템
        groupSize: 4 // 기본 그룹당 아이템 수
    };

    // rpUserInfo 개수에 따라 동적 그룹 클래스 이름 설정
    const itemCount = slider.$items.length;
    let groupClass = itemCount >= 4 ? "rpStatusWrap" : `rpUserGroup${itemCount}`; // 4개 이상: rpStatusWrap, 그 이하: rpUserGroup2, rpUserGroup3

    // rpUserInfo가 5개 이상일 때만 슬라이더와 dots 활성화
    if (itemCount >= 5) {
        // 그룹화: 슬라이드 아이템을 groupSize(4개) 단위로 묶음
        if (itemCount >= slider.groupSize) {
            for (let i = 0; i < itemCount; i += slider.groupSize) {
                const $groupItems = slider.$items.slice(i, i + slider.groupSize);
                $groupItems.wrapAll(`<div class="${groupClass}"></div>`);
            }
        }

        // Slick 슬라이더 초기화
        slider.$container.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: true, // dots 활성화
            infinite: true,
            variableWidth: true
        }).on('init', function(event, slick) {
            // 슬라이더 초기화 시 dots 표시
            $(".slick-dots").show();
        });
    } else {
        // rpUserInfo가 5개 미만일 경우, 그룹화만 진행하고 슬라이더와 dots 숨김
        if (itemCount >= 2) { // 최소 2개 이상일 때 그룹화
            for (let i = 0; i < itemCount; i += itemCount) { // 단일 그룹으로 묶음
                const $groupItems = slider.$items.slice(i, i + itemCount);
                $groupItems.wrapAll(`<div class="${groupClass}"></div>`);
            }
        }
        slider.$container.removeClass("slick-initialized slick-slider"); // Slick 클래스 제거
        $(".slick-dots").hide(); // dots 숨김
    }
});


