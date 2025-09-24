<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/font.css">
    <link rel="stylesheet" href="/resources/ict/common/css/slick.css"/>
    <link rel="stylesheet" href="/resources/ict/common/css/slick-theme.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/schedule.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/roompad/js/schedule.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/views/app/ict/dglib/roomPad/nav.jsp" />

    <div class="roomPadCont">
        <div class="userInfo">
            <a href="/ict/dglib/roomPad/account.do" class="usrProfileLink">
                <img src="/resources/ict/dglib/roompad/img/user.svg" alt="프로필이미지">
            </a>
        </div>

        <div class="roomStatusCont">
            <!-- 날짜 및 룸 정보 -->
            <div class="roomStatusInfo">
                <div class="roomStatusDate">2025. 07. 21. 월</div>
                <div class="roomStatusRoom">그룹 스터디룸 01</div>
            </div>

            <!-- 현재 이용자 정보 -->
            <div class="roomStatusList">
                <!-- roomUserInfo 부분 반복문 -->
                <div class="roomUserInfo">
                    <div class="roomUserStatus">이용중</div>
                    <div class="roomUserName">이*현</div>
                    <div class="roomUserTime">11:00 ~ 12:00</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>