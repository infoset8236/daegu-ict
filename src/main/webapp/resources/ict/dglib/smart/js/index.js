$(document).ready(function () {
    $(".bookFilter div").on("click", function () {
        const $clicked = $(this);

        if ($clicked.hasClass("active")) return;

        gsap.to(".bookFilter div.active", {
            duration: 0.3,
            scale: 1,
            backgroundColor: "transparent",
            color: "#000",
            onComplete: function () {
                $(".bookFilter div.active").removeClass("active");

                $clicked.addClass("active");
                gsap.fromTo(
                    $clicked,
                    { scale: 0.8, backgroundColor: "#007acc", color: "#fff" },
                    { duration: 0.3, scale: 1, ease: "back.out(1.7)" }
                );
            },
        });
    });

    const $input = $("#searchInput");

    const placeholderTexts = [
        "책 이름? 작가? 출판사?",
        "기억나는 것만 써요, 나머진 우리가!",
        "도서관 속 보물찾기 시작!",
        "기억이 가물가물해도 걱정말아요",
        "키워드만 주면 척척!",
        "떠오르는 단어 한마디면 검색 OK!",
        "작가 이름만 기억나도 OK!",
        "출판사 이름만 입력해도 OK!",
        "저자 이름만 톡, 검색을 술술~",
        "출판사 힌트만 줘도",
        "검색을 빠르게 척척!"
    ];

    $(".searchForm").on("submit", function (e) {
        e.preventDefault();

        let searchType = $(".bookFilter div.active").attr("id");
        let search_text = $("#searchInput").val().trim();

        let manageCode = 'DG';
        let pageno = '1';
        let display = '10';
        let search_type = 'detail';

        if (search_text === "") {
            showCommonPopup("검색어를 입력해주세요.");
            return;
        }

        let url = "/ict/dglib/smart/result.do";
        let params = [];

        if (manageCode) params.push("manageCode=" + encodeURIComponent(manageCode));
        if (pageno) params.push("pageno=" + encodeURIComponent(pageno));
        if (display) params.push("display=" + encodeURIComponent(display));
        if (searchType) params.push("searchType=" + encodeURIComponent(searchType));
        if (search_text) params.push("search_text=" + encodeURIComponent(search_text));
        if (search_type) params.push("search_type=" + encodeURIComponent(search_type));

        if (params.length > 0) {
            url += "?" + params.join("&");
        }

        location.href = url;
    });

    let currentIndex = 0;

    function animatePlaceholder() {
        const text = placeholderTexts[currentIndex];
        const tl = gsap.timeline({
            onComplete: function () {
                currentIndex = (currentIndex + 1) % placeholderTexts.length;
                animatePlaceholder();
            }
        });

        let index = 0;

        tl.to({}, {
            duration: 0.08,
            repeat: text.length,
            onRepeat: function () {
                $input.attr("placeholder", text.substring(0, index + 1));
                index++;
            },
            onStart: function () {
                index = 0;
                $input.attr("placeholder", "");
            }
        });

        tl.to({}, { duration: 1.5 });

        tl.to({}, {
            duration: 0.05,
            repeat: text.length,
            onRepeat: function () {
                index--;
                $input.attr("placeholder", text.substring(0, index));
            }
        });
    }

    animatePlaceholder();
});
