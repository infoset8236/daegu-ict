$(document).ready(function () {
    const $mainSlider = $('.mainBookList').slick({
        slidesToShow: 1,
        arrows: false,
        autoplay: false,
        dots: false,
        infinite: true,
        fade: true,
        cssEase: 'linear',
    });

    $('.bookList').each(function () {
        const $slider = $(this);
        const $children = $slider.children();
        const grouped = [];

        for (let i = 0; i < $children.length; i += 6) {
            const $group = $('<div class="bookItem"></div>');
            $children.slice(i, i + 6).appendTo($group);
            grouped.push($group);
        }

        $slider.empty().append(grouped).slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: false,
            dots: grouped.length > 1,
            infinite: true
        });

        let currentBookIndex = 0;

        const highlightBook = ($books, slideIndex) => {
            $books.find('img').css('border', 'none');
            $books.eq(currentBookIndex).find('img').css('border', '2px solid #191F28');
            $mainSlider.slick('slickGoTo', slideIndex * 6 + currentBookIndex);
        };

        const initHighlight = (slideIndex) => {
            const $books = $slider.find(`.slick-slide[data-slick-index="${slideIndex}"] .book`);
            currentBookIndex = 0;
            highlightBook($books, slideIndex);
        };

        $slider.on('click', '.book', function () {
            const $clicked = $(this);
            const slideIndex = $clicked.closest('.slick-slide').data('slick-index');
            const $books = $clicked.closest('.bookItem').find('.book');

            currentBookIndex = $books.index($clicked);
            highlightBook($books, slideIndex);
        });

        initHighlight(0);
    });

    $('.trigger').on('click', function (e) {
        e.preventDefault();
        $('#popup').fadeIn();
    });

    $('#popup .close, #popup').on('click', function (e) {
        if (e.target !== this) return;
        $('#popup').fadeOut();
    });
});
