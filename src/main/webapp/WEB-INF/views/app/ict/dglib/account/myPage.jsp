<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/font.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/header.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/navigation.css">
    <link rel="stylesheet" href="/resources/ict/common/css/slick.css"/>
    <link rel="stylesheet" href="/resources/ict/common/css/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/account/css/myPage.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="/resources/ict/dglib/account/js/myPage.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>my library</div>
                <div>나의서재</div>
            </div>
            <div class="content">
                <div class="loanSummary">
                    <div>대출 중 권수<span>1</span></div>
                    <div>예약 중 권수<span>2</span></div>
                </div>
                <div class="tabMenu">
                    <div class="active">대출도서</div>
                    <div>대출이력</div>
                    <div>예약현황</div>
                </div>
                <div class="filterArea">
                    <div class="recentMonthFilter">
                        <label class="filterItem">
                            <input type="radio" name="monthFilter" value="1" checked>
                            <span class="customRadio"></span>
                            전체
                        </label>
                        <label class="filterItem">
                            <input type="radio" name="monthFilter" value="2">
                            <span class="customRadio"></span>
                            1개월
                        </label>
                        <label class="filterItem">
                            <input type="radio" name="monthFilter" value="3">
                            <span class="customRadio"></span>
                            3개월
                        </label>
                    </div>
                    <div class="customDateFilter">
                        <div class="filterLabel">기간</div>
                        <div class="filterInput">
                            <label>
                                <input type="date">
                            </label>
                            <span class="dateSeparator">~</span>
                            <label>
                                <input type="date" >
                            </label>
                        </div>
                    </div>
                </div>
                <ul class="resultList">
                    <li class="resultItem">
                        <div class="bookInfo">
                            <img src="/resources/ict/common/img/common/dummy.png" alt="" class="bookThumbnail">
                            <div class="bookDetail">
                                <div class="bookTitle">물고기는 존재하지 않는다</div>
                                <div class="bookMeta">
                                    <div class="dataRoom">자료실명<span>2층 일반자료실</span></div>
                                    <div class="loanDate">대출일자<span>2025-07-06</span></div>
                                    <div class="libraryName">도서관명<span>대구도서관</span></div>
                                    <div class="returnDate">반납일자<span>2025-07-06</span></div>
                                </div>
                            </div>
                        </div>
                        <%--    <div class="loanStatus code1">대출중</div>    --%>
                        <%--    <div class="loanStatus code2">반납<br>연기</div>    --%>
                        <%--    <div class="loanStatus code3">예약중</div>    --%>
                        <%--    <div class="loanStatus code4">예약<br>취소</div>    --%>
                        <%--    <div class="loanStatus code5">반납<br>완료</div>    --%>
                        <div class="loanStatus code1">대출중</div>
                    </li>
                </ul>
                <div class="pagination">
                    <a class="first" href=""></a>
                    <a class="prev" href=""></a>
                    <ul>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                    </ul>
                    <a class="next" href=""></a>
                    <a class="last" href=""></a>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/${sessionScope.mode}/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>
