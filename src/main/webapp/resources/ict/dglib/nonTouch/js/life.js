$(document).ready(function () {
    $('.newsList').each(function () {
        const $slider = $(this);

        $slider.slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: false,
            infinite: true,
            vertical: true,
        });
    });
});
