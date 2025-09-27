$(document).ready(function () {
    const defaultManageCode = "DG";
    const urlParams = new URLSearchParams(window.location.search);

    const regNo = urlParams.get('regNo');
    const manageCode = urlParams.get('manageCode') || defaultManageCode;

    // 이번달 1일 ~ 말일
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);

    const start_date = firstDay.toISOString().split("T")[0];
    const end_date = lastDay.toISOString().split("T")[0];

    const pageno = "1";
    const display = "90";

    const newBookApiUrl =
        "/api/klas/newBook.do?manageCode=" + manageCode +
        "&search_start_date=" + start_date +
        "&search_end_date=" + end_date +
        "&pageno=" + pageno +
        "&display=" + display;

    let bookDetailApiUrl = "/api/klas/bookDetail.do";
    const params = [];
    if (regNo) params.push("regNo=" + encodeURIComponent(regNo));
    if (manageCode) params.push("manageCode=" + encodeURIComponent(manageCode));
    if (params.length > 0) {
        bookDetailApiUrl += "?" + params.join("&");
    }

    let detailBooks = [];

    // 1. detail API 호출
    $.getJSON(bookDetailApiUrl, function (detailData) {
        if (detailData.RESULT_INFO === "SUCCESS" && detailData.LIST_DATA.length > 1) {
            detailBooks = detailData.LIST_DATA.filter(b => !b.SEARCH_COUNT);
        }

        // 2. newBook API 호출
        $.getJSON(newBookApiUrl, function (newBookData) {
            let newBooks = [];
            if (newBookData.RESULT_INFO === "SUCCESS" && newBookData.LIST_DATA.length > 1) {
                newBooks = newBookData.LIST_DATA.filter(b => !b.SEARCH_COUNT);
            }

            // 3. mainBookList → detail + newBook
            renderBooks(detailBooks, '.mainBookList');
            renderBooks(newBooks, '.mainBookList');

            // 4. bookList → detail + newBook
            renderSimpleBooks(detailBooks, '.bookList');
            renderSimpleBooks(newBooks, '.bookList');

            // 5. 슬릭 초기화 (append 끝난 후 1번만)
            initMainSlider();
            initBookListSlider();
        });
    });

    // ==============================
    // 슬릭 초기화 (메인)
    // ==============================
    function initMainSlider() {
        const $mainSlider = $('.mainBookList');
        if (!$mainSlider.hasClass('slick-initialized')) {
            $mainSlider.slick({
                slidesToShow: 1,
                arrows: false,
                autoplay: false,
                dots: false,
                infinite: true,
                fade: true,
                cssEase: 'linear',
            });
        } else {
            $mainSlider.slick('refresh');
        }
    }

    // ==============================
    // 슬릭 초기화 (하단 썸네일 + sync)
    // ==============================
    function initBookListSlider() {
        const $slider = $('.bookList');
        const $children = $slider.children();
        const grouped = [];

        // 6개씩 묶기
        for (let i = 0; i < $children.length; i += 6) {
            const $group = $('<div class="bookItem"></div>');
            $children.slice(i, i + 6).appendTo($group);
            grouped.push($group);
        }

        $slider.empty().append(grouped);

        $slider.slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: false,
            dots: grouped.length > 1,
            infinite: true
        });

        // ==========================
        // bookList ↔ mainBookList sync
        // ==========================
        const $mainSlider = $('.mainBookList');
        let currentBookIndex = 0;

        const highlightBook = ($books) => {
            $books.find('img').css('border', 'none');
            $books.eq(currentBookIndex).find('img').css('border', '2px solid #191F28');

            // 전체 index = 현재 슬라이드 내 index + 이전 그룹 offset
            const groupIndex = $slider.slick('slickCurrentSlide');
            const globalIndex = groupIndex * 6 + currentBookIndex;

            $mainSlider.slick('slickGoTo', globalIndex);
        };

        const initHighlight = () => {
            const $books = $slider.find('.slick-current .book');
            currentBookIndex = 0;
            highlightBook($books);
        };

        // 썸네일 클릭 시 sync
        $slider.on('click', '.book', function () {
            const $clicked = $(this);
            const $books = $clicked.closest('.bookItem').find('.book');
            currentBookIndex = $books.index($clicked);
            highlightBook($books);
        });

        initHighlight();
    }

    // ==============================
    // 팝업 열기/닫기
    // ==============================
    $(document).on('click', '.trigger', function (e) {
        e.preventDefault();
        $('#popup').fadeIn();
    });
    $('#popup .close').on('click', () => $('#popup').fadeOut());
    $('#popup').on('click', function (e) {
        if ($(e.target).is('#popup')) {
            $('#popup').fadeOut();
        }
    });
});


// ==============================
// Render 함수
// ==============================
function renderBooks(data, targetSelector) {
    const $target = $(targetSelector);

    data.forEach(function (book) {
        const html = `
        <div class="mainBookListItem">
            <div class="bookInformation">
                <div class="mainBookImage">
                    <div>${book.BOOK_STATUS === "1" ? "대출 가능" : "대출 불가"}</div>
                    <img src="${book.IMAGE || '/resources/ict/dglib/smart/img/common/dummy.png'}" alt="">
                </div>
                <div>
                    <div class="mainBookTitle">${book.TITLE_INFO}</div>
                    <div class="mainBookAuthor">${book.AUTHOR || ''} ${book.PUBLISHER ? '/ ' + book.PUBLISHER : ''}</div>
                    <div class="mainBookMeta">
                        <div>발행년도 <span>${book.PUB_YEAR || ''}</span></div>
                        <div>ISBN <span>${book.ISBN || ''}</span></div>
                        <div>소장위치 <span>${book.SHELF_LOC_NAME || ''}</span></div>
                        <div>청구기호 <span>${book.CALL_NO || ''}</span></div>
                    </div>
                    <a href="" class="trigger">서가위치보기</a>
                </div>
            </div>
            <div class="mainBookDesc">
                <div>책소개</div>
                <div>${book.DESCRIPTION || ''}</div>
            </div>
        </div>
        `;
        $target.append(html);
    });
}

function renderSimpleBooks(data, targetSelector) {
    const $target = $(targetSelector);

    data.forEach(function (book) {
        const html = `
        <div class="book">
            <img src="${book.IMAGE || '/resources/ict/dglib/smart/img/common/dummy.png'}" alt="" class="bookImage">
            <div class="bookTitle">${book.TITLE_INFO}</div>
            <div class="bookAuthor">${book.AUTHOR || ''}</div>
        </div>
        `;
        $target.append(html);
    });
}
