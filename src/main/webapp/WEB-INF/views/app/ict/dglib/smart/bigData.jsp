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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/smart/css/bigdata.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="/resources/ict/dglib/smart/js/bigdata.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>big data recommendation</div>
                <div>빅데이터추천</div>
            </div>
            <div class="content">
                <div class="title">
                    <div>빅데이터는 어떤 책을 추천했을까요?</div>
                    <div>전국 공공도서관 연령대별 추천도서를 소개합니다</div>
                </div>
                <div class="filter">
                    <button class="active">아동</button>
                    <button>청소년</button>
                    <button>20~30대</button>
                    <button>40~50대</button>
                    <button>60대 이상</button>
                </div>
                <div class="bookSlide">
					<%-- 반복문으로 a 출력 --%>
                    <a href="/ict/dglib/smart/detail.do" class="book">
                        <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="">
                        <div>로미오와 줄리엣</div>
                        <div>윌리엄 셰익스피어</div>
                    </a>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/smart/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>