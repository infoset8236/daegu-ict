<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/font.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/auth.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/dglib/roompad/js/auth.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/views/app/ict/dglib/roomPad/nav.jsp" />

    <div class="rpContent">
        <!-- 사용자 정보 -->
        <div class="rpUser">
            <a href="" class="usrProfileLink">
                <img src="/resources/ict/dglib/roompad/img/user.svg" alt="프로필이미지">
            </a>
        </div>
        <div class="resContainer">
            <div class="resHeader">
                <div class="resTitEn">Reservation verification</div>
                <div class="resTitKo">예약자 인증</div>
            </div>
            <div class="decoration">
                <img class="signal1" src="/resources/ict/dglib/roompad/img/auth/signal1.svg" alt="">
                <img class="signal2" src="/resources/ict/dglib/roompad/img/auth/signal2.svg" alt="">
                <img class="card" src="/resources/ict/dglib/roompad/img/auth/rfid.png" alt="">
                <img class="signal3" src="/resources/ict/dglib/roompad/img/auth/signal1.svg" alt="">
                <img class="signal4" src="/resources/ict/dglib/roompad/img/auth/signal2.svg" alt="">
            </div>
        </div>

    </div>
</div>
</body>
</html>