$(document).ready(function () {
    $('.informationList').each(function () {
        const $slider = $(this);
        const slideCount = $slider.children().length;

        $slider.slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: slideCount > 1,
            infinite: true
        });
    });
});
