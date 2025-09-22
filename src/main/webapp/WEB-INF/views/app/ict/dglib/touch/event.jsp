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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/touch/css/event.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="/resources/ict/dglib/touch/js/event.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>EVENT</div>
                <div>행사안내</div>
            </div>
            <div class="content">
                <div class="decoration">TODAY’S<br>LIBRARY<br>EVENT</div>
                <ul class="eventList">
                    <%-- 반복문으로 li 출력 --%>
                    <li class="event">
                        <div>01</div>
                        <div>
                            글과 함께 힐링하기 : 캘리그라피 체험 어디까지 할 수 있나 볼까요
                        </div>
                        <div>
                            <span>시간</span>
                            <p>14:00 ~ 16:00</p>
                        </div>
                        <div>
                            <span>장소</span>
                            <p>2층 평생학습실(배움1실)</p>
                        </div>
                    </li>
                </ul>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/touch/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>