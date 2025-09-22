$(document).ready(function () {
    $('.noticeList').each(function () {
        const $slider = $(this);
        const slideCount = $slider.children().length;

        $slider.slick({
            centerMode: true,
            slidesToShow: 1,
            variableWidth: true,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: slideCount > 1,
            infinite: true
        });
    });
});
