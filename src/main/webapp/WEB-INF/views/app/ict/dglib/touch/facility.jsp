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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/touch/css/facility.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="/resources/ict/dglib/touch/js/facility.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>FACILITY</div>
                <div>시설안내</div>
            </div>
            <div class="content">
                <div class="mapArea">
                    <img id="floorMap" src="/resources/ict/dglib/touch/img/facility/1F.png" alt="">
                </div>
                <div class="filterArea"></div>
                <div class="facilityList"></div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/touch/nav.jsp"/>
            <div class="popup" id="facilityPopup">
                <div class="popupContent">
                    <div class="" id="popupTitle"></div>
                    <img id="popupImg" src="" alt="">
                    <ul id="popupDesc"></ul>
                    <div class="caption" id="popupCaption"></div>
                    <div class="popupClose">
                        <div>닫기</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>