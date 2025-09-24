jQuery(document).ready(function ($) {
    const slider = {
        $container: $(".myResList"), // 슬라이더 컨테이너
        $items: $(".myResList").children(".myResItem"), // 슬라이드 아이템
        groupSize: 3 // 한 그룹당 아이템 수
    };

    // myResItem이 3개 이상일 때만 그룹화 및 슬라이더 활성화
    if (slider.$items.length >= 4) {
        // 그룹화: 슬라이드 아이템을 groupSize(3개) 단위로 묶음
        for (let i = 0; i < slider.$items.length; i += slider.groupSize) {
            const $groupItems = slider.$items.slice(i, i + slider.groupSize);
            $groupItems.wrapAll('<div class="slideGroup"></div>');
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
        // myResItem이 3개 미만일 경우, 슬라이더와 dots 숨김
        slider.$container.removeClass("slick-initialized slick-slider"); // Slick 클래스 제거
        $(".slick-dots").hide(); // dots 숨김
    }

});
