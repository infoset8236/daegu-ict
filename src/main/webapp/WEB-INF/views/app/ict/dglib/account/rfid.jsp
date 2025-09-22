<%@ page language="java" pageEncoding="utf-8"%>
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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/account/css/rfid.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="content">
                <div class="title">RFID 로그인</div>
                <div class="description">기기 하단의 회원증 인식부에 회원증을 올려주세요</div>

                <div class="decoration">
                    <img class="signal1" src="/resources/ict/dglib/account/img/signal1.svg" alt="">
                    <img class="signal2" src="/resources/ict/dglib/account/img/signal2.svg" alt="">
                    <img class="card" src="/resources/ict/dglib/account/img/rfid.png" alt="">
                    <img class="signal3" src="/resources/ict/dglib/account/img/signal1.svg" alt="">
                    <img class="signal4" src="/resources/ict/dglib/account/img/signal2.svg" alt="">
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/${param.from == 'smart' ? 'smart' : 'touch'}/nav.jsp" />
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>
