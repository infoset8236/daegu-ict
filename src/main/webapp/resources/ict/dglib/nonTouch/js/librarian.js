$(document).ready(function () {
    var manage_idx = "1302";
    var board_type = "BOOK";
    var endRowNum = "30";

    var apiUrl = "/api/homepage/boardList.do?manage_idx=" + manage_idx + "&board_type=" + board_type + "&endRowNum=" + endRowNum;

    $.ajax({
        url: apiUrl,
        type: "GET",
        dataType: "json",
        success: function (data) {
            if (data.result !== "SUCCESS") {
                console.warn("도서 API 실패");
                return;
            }

            var list = data["result-data"]["result-list"];
            var $main = $(".mainBookList");
            var $thumb = $(".bookList");
            $main.empty();
            $thumb.empty();

            // 📌 메인 & 썸네일 동적 생성
            $.each(list, function (i, book) {
                // 메인 영역
                var mainItem = `
                    <div class="mainBookListItem">
                        <div class="mainBookImage">
                            <img src="${book.image_url || '/resources/ict/dglib/nonTouch/img/common/dummy.png'}" alt="">
                        </div>
                        <div class="mainBookTitle">${book.title}</div>
                        <div class="mainBookMeta">
                            <div>저자명<span>${book.imsi_v_3 || '-'}</span></div>
                            <div>발행년도<span>${book.imsi_v_2 || '-'}</span></div>
                            <div>발행처<span>${book.imsi_v_4 || '-'}</span></div>
                            <div>소장위치<span>${book.imsi_v_6 || '-'}</span></div>
                        </div>
                        <div class="mainBookDesc">${book.contents || ''}</div>
                    </div>`;
                $main.append(mainItem);

                // 썸네일 리스트
                var thumbItem = `
                    <div class="book">
                        <img src="${book.image_url || '/resources/ict/dglib/nonTouch/img/common/dummy.png'}" alt="" class="bookImage">
                        <div class="bookTitle">${book.title}</div>
                        <div class="bookAuthor">${book.imsi_v_3 || '-'}</div>
                    </div>`;
                $thumb.append(thumbItem);
            });

            // 📌 슬라이더/하이라이트 초기화 실행
            initBookSlider();
        },
        error: function (xhr, status, error) {
            console.error("도서 API 호출 실패:", error);
        }
    });

    function initBookSlider() {
        // 메인 슬라이더 (큰 화면용)
        const $mainSlider = $('.mainBookList').slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: false,
            dots: false,
            infinite: true,
            fade: true,
            cssEase: 'linear',
        });

        // 썸네일 슬라이더 (5개씩 그룹)
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
    }
});