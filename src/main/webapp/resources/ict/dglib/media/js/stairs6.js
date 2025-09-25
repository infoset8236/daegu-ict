$(document).ready(function(){
    $(".bookTopList").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        dots: false,
        autoplay: true,
        autoplaySpeed: 8000,
        infinite: true,
        asNavFor: ".bookBottomList",
        rtl: true
    });

    $(".bookBottomList").slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        dots: false,
        autoplay: true,
        autoplaySpeed: 8000,
        infinite: true,
        asNavFor: ".bookTopList",
    });
});