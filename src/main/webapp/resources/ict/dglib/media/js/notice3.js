document.addEventListener("DOMContentLoaded", () => {
  const imgs = document.querySelectorAll(".infoImg");
  imgs.forEach(img => {
    img.src = "/resources/ict/dglib/media/img/common/dummy.png";
  });
});




$(document).ready(function () {
  const $infoList = $(".infoList");
  const $items = $infoList.children(".infoItem");
  const $current = $(".panelPagination .panelCurrent");
  const $total = $(".panelPagination .panelTotal");

  const groupSize = 3;   // infoItem 묶음 단위
  const wrapperSize = 3; // infoGroup 묶음 단위

  // infoItem -> infoGroup -> infoGroupWrapper 생성
  $infoList.empty();
  for (let i = 0; i < $items.length; i += groupSize * wrapperSize) {
    const $wrapper = $('<div class="infoGroupWrapper"></div>');
    for (let j = i; j < i + groupSize * wrapperSize && j < $items.length; j += groupSize) {
      const $group = $('<div class="infoGroup"></div>');
      $items.slice(j, j + groupSize).appendTo($group);
      $wrapper.append($group);
    }
    $infoList.append($wrapper);
  }

  // 총 아이템 수 표시
  $total.text("/" + $items.length);
  $current.text($infoList.find(".infoGroupWrapper").first().find(".infoItem").length);

  // Slick 초기화
  $infoList.slick({
    slidesToShow: 1,
    slidesToScroll: 1,
    arrows: false,
    autoplay: true,
    autoplaySpeed: 8000,
    dots: false,
    infinite: true
  });

  // 슬라이드 변경 시 현재 아이템 수 업데이트
  $infoList.on("afterChange", function (_, slick, currentSlide) {
    let count = 0;
    $infoList.find(".infoGroupWrapper").slice(0, currentSlide + 1).each(function () {
      count += $(this).find(".infoItem").length;
    });
    $current.text(count);
  });
});

