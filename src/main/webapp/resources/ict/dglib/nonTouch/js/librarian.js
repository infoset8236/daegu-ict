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

    for (let i = 0; i < $children.length; i += 5) {
      const $group = $('<div class="bookItem"></div>');
      $children.slice(i, i + 5).appendTo($group);
      grouped.push($group);
    }

    $slider.empty().append(grouped).slick({
      slidesToShow: 1,
      arrows: false,
      autoplay: false,
      dots: grouped.length > 1,
      infinite: true
    });

    let currentBookIndex = 0, bookTimer;

    const highlightBook = ($books, slideIndex) => {
      $books.find('img').css('border', 'none');
      $books.eq(currentBookIndex).find('img').css('border', '2px solid #191F28');
      $mainSlider.slick('slickGoTo', slideIndex * 5 + currentBookIndex);

      bookTimer = setTimeout(() => {
        currentBookIndex++;
        if (currentBookIndex < $books.length) {
          highlightBook($books, slideIndex);
        } else {
          $slider.slick('slickNext');
        }
      }, 8000);
    };

    const startBookCycle = (slideIndex) => {
      clearTimeout(bookTimer);
      const $books = $slider.find(`.slick-slide[data-slick-index="${slideIndex}"] .book`);
      currentBookIndex = 0;
      highlightBook($books, slideIndex);
    };

    $slider.on('afterChange', (e, slick, currentSlide) => startBookCycle(currentSlide));
    startBookCycle(0);
  });
});
