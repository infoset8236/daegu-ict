$(document).ready(function() {
    var manageCode = "DG";
    var apiUrl = "/api/klas/bestBook.do?manageCode=" + manageCode + "&display=10";

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function(data) {
            if (data.RESULT_INFO !== "SUCCESS") {
                console.warn("서버 결과 실패");
                return;
            }

            var $slider = $("#bestBookList");
            $slider.empty();

            // 리스트 아이템 추가
            $.each(data.LIST_DATA, function(index, book) {
                var imgUrl = book.IMAGE && book.IMAGE.trim() !== ""
                    ? book.IMAGE
                    : "/resources/ict/dglib/nonTouch/img/common/dummy.png";

                var item = `
                    <div class="book">
                        <img src="${imgUrl}" alt="${book.TITLE}">
                        <div>${book.RANK}위</div>
                        <div>${book.TITLE}</div>
                        <div>${book.AUTHOR} · ${book.PUBLISHER}</div>
                        <div><span>발행년도</span> ${book.PUBLISH_YEAR || "-"}</div>
                        <div><span>소장위치</span> ${book.SHELF_LOC_NAME || "-"}</div>
                    </div>
                `;
                $slider.append(item);
            });

            // 5개씩 묶어서 슬라이드 그룹화
            const $items = $slider.children();
            const groupedItems = [];

            for (let i = 0; i < $items.length; i += 5) {
                const $group = $('<div class="bestBookListItem"></div>');
                $items.slice(i, i + 5).appendTo($group);
                groupedItems.push($group);
            }

            $slider.empty().append(groupedItems);

            // slick 초기화
            $slider.slick({
                slidesToShow: 1,
                arrows: false,
                autoplay: true,
                autoplaySpeed: 8000,
                dots: groupedItems.length > 1,
                infinite: true
            });
        },
        error: function(xhr, status, error) {
            console.error("서버 API 호출 실패:", error);
        }
    });
});