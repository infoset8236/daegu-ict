<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/smart/css/index.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="/resources/ict/dglib/smart/js/index.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>SMART BOOK RECOMMENDATION</div>
                <div>스마트도서추천</div>
            </div>
            <div class="content">
                <div class="searchIntro">
                    <div>회원님, 어떤 책을 <br>찾고 계신가요?</div>
                    <div>책 제목이나 작가 정보 등 <br>원하시는 책을 검색해보세요</div>
                </div>

                <div class="searchBox">
                    <img class="decoration" src="/resources/ict/dglib/smart/img/index/character.apng" alt="">
                    <div class="bookFilter">
                        <div id="title" class="active">도서명</div>
                        <div id="author">저자명</div>
                        <div id="publisher">발행처</div>
                    </div>
                    <form class="searchForm">
                        <label>
                            <input id="searchInput" type="text" placeholder="">
                        </label>
                        <button class="submit">
                            <img src="/resources/ict/dglib/smart/img/index/search.svg" alt="">
                        </button>
                    </form>
                </div>

                <div class="recommendIntro">
                    <img class="decoration1" src="/resources/ict/dglib/smart/img/index/book.png" alt="">
                    <img class="decoration2" src="/resources/ict/dglib/smart/img/index/heart.apng" alt="">
                    <div>나에게 딱 맞는 책을<br>만나보세요!</div>
                    <div>어떤 책을 읽어야 할지 고민하는<br>회원님을 위해 도서 추천 콘텐츠를<br>제공해드려요</div>
                    <a class="recommendLink" href="/ict/dglib/smart/chart.do">스마트하게 도서 추천 받기</a>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/smart/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>

    <div id="commonPopup" class="commonPopup" style="display:none;">
        <div class="commonPopupContent">
            <div id="commonPopupMessage"></div>
            <button id="commonPopupClose">확인</button>
        </div>
    </div>
</div>
</body>
</html>
