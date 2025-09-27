$(document).ready(function () {

    // 페이지네이션 클릭 이벤트 (번호, 이전/다음, 처음/끝)
    $("#pagination").on("click", "a, li", function (e) {
        e.preventDefault();

        const urlParams = new URLSearchParams(window.location.search);

        let pageno = "";

        if ($(this).is("a")) {
            pageno = $(this).attr("keyValue");
        } else if ($(this).is("li")) {
            pageno = $(this).text();
        }

        let searchType = urlParams.get('searchType');
        let search_text = urlParams.get('search_text');

        let manageCode = urlParams.get('manageCode');
        let display = '10';
        let search_type = 'detail';

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

        $("html, body").animate({scrollTop: 0}, "fast");
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
