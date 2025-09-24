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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/account/css/index.css">
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
                <div class="loginMessage">
                    회원 서비스 이용을 위해<br>
                    로그인 해주세요
                </div>
                <form id="loginForm" class="loginForm" method="post" autocomplete="off">
                    <label for="id" class="sr-only">
                        <img src="/resources/ict/dglib/account/img/id.svg" alt="">
                        <input type="text" id="id" name="member_id" placeholder="아이디를 입력해주세요" autocomplete="off" required>
                    </label>

                    <label for="password" class="sr-only">
                        <img src="/resources/ict/dglib/account/img/password.svg" alt="">
                        <input type="password" id="password" name="member_pw" placeholder="비밀번호를 입력해주세요" autocomplete="new-password" required>
                    </label>

                    <button type="submit" class="submit">로그인</button>
                </form>
                <div class="simpleLoginMessage">회원증으로 간편로그인하세요</div>
                <a href="/ict/dglib/account/rfid.do?from=${param.from == 'smart' ? 'smart' : 'touch'}" class="rfidLoginLink">
                    회원증 RFID로 로그인
                </a>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/${param.from == 'smart' ? 'smart' : 'touch'}/nav.jsp" />
        </div>

        <div id="commonPopup" class="commonPopup" style="display:none;">
            <div class="commonPopupContent">
                <div id="commonPopupMessage">최대 3개의 키워드를 선택하실 수 있습니다.</div>
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
