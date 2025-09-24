$(document).ready(function(){
    $(".topEventSlide").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        dots: false,
        autoplay: true,
        autoplaySpeed: 8000,
        infinite: true,
        asNavFor: ".bottomEventSlide"
    });

    $(".bottomEventSlide").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        dots: false,
        autoplay: true,
        autoplaySpeed: 8000,
        infinite: true,
        asNavFor: ".topEventSlide",
    });
});
