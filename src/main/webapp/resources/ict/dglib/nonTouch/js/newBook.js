$(document).ready(function () {
  $('.newBookList').each(function () {
    const $slider = $(this);
    const $items = $slider.children();
    const groupedItems = [];

    for (let i = 0; i < $items.length; i += 9) {
      const $group = $('<div class="newBookListItem"></div>');
      $items.slice(i, i + 9).appendTo($group);
      groupedItems.push($group);
    }

    $slider.empty().append(groupedItems);

    $slider.slick({
      slidesToShow: 1,
      arrows: false,
      autoplay: true,
      autoplaySpeed: 8000,
      dots: groupedItems.length > 1,
      infinite: true
    });
  });
});
