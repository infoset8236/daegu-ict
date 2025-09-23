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
    <div class="navigation">
        <div class="library">대구도서관</div>
        <div class="todayWrap">
            <div class="caption">TODAY SCHEDULE</div>
            <div class="roomClock">
                <canvas id="gc"></canvas>
            </div>
            <div class="dtWrap">
                <div class="dtDate">2025년 11월 05일(수)</div>
                <div class="dtTime">11:08</div>
            </div>
        </div>
        <a href="/ict/dglib/roomPad/index.do" class="btnNav">메인</a>
    </div>

    <div class="rpContent">
        <!-- 사용자 정보 -->
        <div class="rpUser">
            <a href="/ict/dglib/roomPad/account.do" class="usrProfileLink">
                <img src="/resources/ict/dglib/roompad/img/user.svg" alt="프로필이미지">
            </a>
        </div>

        <div class="rpStatusItem">
            <!-- 날짜 및 룸 정보 -->
            <div class="rpStatusInfo">
                <div class="rpStatusDate">2025. 07. 21. 월</div>
                <div class="rpStatusRoom">그룹 스터디룸 01</div>
            </div>

            <!-- 현재 이용자 정보 -->
            <div class="rpStatusList">
                <div class="rpUserInfo">
                    <div class="rpUserStatus">이용중</div>
                    <div class="rpUserName">이*현</div>
                    <div class="rpUserTime">11:00 ~ 12:00</div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>