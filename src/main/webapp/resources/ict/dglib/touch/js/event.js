$(document).ready(function() {
    var homepage_id = "h102";
    var date_type = "2";

    //한달
    /*var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var firstDay = new Date(year, month, 1);
    var start_date = firstDay.toISOString().split("T")[0];
    var lastDay = new Date(year, month + 1, 0);
    var end_date = lastDay.toISOString().split("T")[0];*/

    var today = new Date();
    var year = today.getFullYear();
    var month = (today.getMonth() + 1).toString().padStart(2, '0');
    var day = today.getDate().toString().padStart(2, '0');

    var todayStr = year + "-" + month + "-" + day;

    var start_date = todayStr;
    var end_date = todayStr;

    var apiUrl = "/api/homepage/eventList.do?homepage_id=" + homepage_id + "&date_type=" + date_type + "&start_date=" + start_date + "&end_date=" + end_date;

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
            var $slider = $("#eventList");
            $slider.empty();

            $.each(list, function(index, event) {
                var num = (index + 1).toString().padStart(2, '0');

                var item = `
                    <li class="event">
                        <div>${num}</div>
                        <div>${event.title}</div>
                        <div>
                            <span>시간</span>
                            <p>${event.start_time && event.end_time ? event.start_time + " ~ " + event.end_time : (event.start_time || event.end_time || "")}</p>
                        </div>
                        <div>
                            <span>장소</span>
                            <p>${event.place_name || ""}</p>
                        </div>
                    </li>
                `;
                $slider.append(item);
            });

            var slideCount = $slider.children().length;

            if (slideCount > 0) {
                $slider.slick({
                    slidesToShow: 1,
                    arrows: false,
                    autoplay: true,
                    autoplaySpeed: 8000,
                    dots: slideCount > 1,
                    infinite: true
                });
            }
        },
        error: function(xhr, status, error) {
            console.error("서버 API 호출 실패:", error);
        }
    });
});