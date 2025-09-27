$(document).ready(function () {
    $('.bookSlide').each(function () {
        const $slider = $(this);
        const $items = $slider.children();
        const groupedItems = [];

        for (let i = 0; i < $items.length; i += 12) {
            const $group = $('<div class="wrapper"></div>');
            $items.slice(i, i + 12).appendTo($group);
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

function goDetail(regNo, manageCode) {
    let url = "/ict/dglib/smart/detail.do";
    let params = [];

    if (regNo) params.push("regNo=" + encodeURIComponent(regNo));
    if (manageCode) params.push("manageCode=" + encodeURIComponent(manageCode));

    if (params.length > 0) {
        url += "?" + params.join("&");
    }

    location.href = url;
}
