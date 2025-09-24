$(document).ready(function() {
    var manage_idx = "1306";
    var board_type = "NOTICE";

    var apiUrl = "/api/homepage/boardList.do?manage_idx=" + manage_idx + "&board_type=" + board_type + "&kiosk_yn=Y";

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function(data) {
            if (data.result !== "SUCCESS") {
                console.warn("서버 결과 실패");
                return;
            }

            var list = data["result-data"]["result-list"];
            var $slider = $("#noticeList");
            $slider.empty();

            $.each(list, function(index, board) {
                var item = `
                    <li>
                        <img src="${board.image_url}" alt="">
                        <div class="title"><p>${board.title}</p></div>
                    </li>
                `;
                $slider.append(item);
            });

            $('#noticeList').each(function () {
                const $slider = $(this);
                const slideCount = $slider.children().length;

                $slider.slick({
                    centerMode: true,
                    slidesToShow: 1,
                    variableWidth: true,
                    arrows: false,
                    autoplay: true,
                    autoplaySpeed: 8000,
                    dots: slideCount > 1,
                    infinite: true
                });
            });
        },
        error: function(xhr, status, error) {
            console.error("서버 API 호출 실패:", error);
        }
    });
});