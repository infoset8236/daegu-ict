<%@ page language="java" pageEncoding="utf-8"%>
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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/smart/css/chart.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://d3js.org/d3.v7.min.js"></script>
    <script src="/resources/ict/dglib/smart/js/chart.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>keyword recommendation</div>
                <div>키워드추천</div>
            </div>
            <div class="content">
                <div class="title">
                    <div>회원님의 관심사는 무엇인가요?</div>
                    <div>관심 키워드를 분석해 알맞은 책을 추천해드려요</div>
                </div>
                <div class="bubbleBox"></div>
                <button class="change">키워드 바꾸기</button>
                <div class="selectedKeyword"></div>
                <div class="selectBox">
                    <div>
                        <label for="sex">성별</label>
                        <select id="sex">
                            <option value="">전체</option>
                            <option value="0">여성</option>
                            <option value="1">남성</option>
                        </select>
                    </div>
                    <div>
                        <label for="book_keyword_age">나이</label>
                        <select id="book_keyword_age">
                            <option value="">전체</option>
                            <option value="4">영유아</option>
                            <option value="5">유아</option>
                            <option value="8">초등</option>
                            <option value="14">청소년</option>
                            <option value="25">20대 이상</option>
                            <option value="30">30대 이상</option>
                            <option value="40">40대 이상</option>
                            <option value="50">50대 이상</option>
                            <option value="60">60대 이상</option>
                        </select>
                    </div>
                </div>
                <button class="submit" onclick="recomend();">도서추천받기</button>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/smart/nav.jsp"/>
        </div>

        <div id="commonPopup" class="commonPopup" style="display:none;">
            <div class="commonPopupContent">
                <div id="commonPopupMessage"></div>
                <button id="commonPopupClose">확인</button>
            </div>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>
