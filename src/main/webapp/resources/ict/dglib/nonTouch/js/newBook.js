$(document).ready(function() {
    var manageCode = "DG";

    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var firstDay = new Date(year, month, 1);
    var start_date = firstDay.toISOString().split("T")[0];
    var lastDay = new Date(year, month + 1, 0);
    var end_date = lastDay.toISOString().split("T")[0];

    var pageno = "1";
    var display = "90";

    var apiUrl = "/api/klas/newBook.do?manageCode=" + manageCode + "&search_start_date=" + start_date + "&search_end_date=" + end_date + "&pageno=" + pageno + "&display=" + display;

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function(data) {
            if (data.RESULT_INFO !== "SUCCESS") {
                console.warn("서버 결과 실패");
                return;
            }

            var $slider = $("#newBookList");
            $slider.empty();

            var books = data.LIST_DATA.filter(function(item) {
              return item.RNUM !== undefined && item.RNUM !== null;
            });

            $.each(books, function(index, book) {
            var imgUrl = (book.IMAGE && book.IMAGE.trim() !== "" && book.IMAGE.indexOf("noimg3.gif") === -1) ? book.IMAGE : "/resources/ict/dglib/nonTouch/img/common/dummy.png";

            var item = `
                <div class="book">
                    <img src="${imgUrl}" alt="${book.TITLE_INFO || ''}">
                    <div class="title">${book.TITLE_INFO || ''}</div>
                    <div class="author">${book.AUTHOR || ''}</div>
                </div>
            `;
            $slider.append(item);
        });

            const $items = $slider.children();
            const groupedItems = [];

            for (let i = 0; i < $items.length; i += 9) {
                const $group = $('<div class="newBookListItem"></div>');
                $items.slice(i, i + 9).appendTo($group);
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
