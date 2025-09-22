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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/nonTouch/css/bestBook.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/nonTouch/js/bestBook.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="headerKoEnVert">
        <div>BEST BOOK</div>
        <div>인기도서</div>
    </div>
    <div class="content">
        <div class="bestBookList">
            <%-- 반복문으로 div 출력 --%>
            <div class="book">
                <img src="/resources/ict/dglib/nonTouch/img/common/dummy.png" alt="">
                <div>1위</div>
                <div>나는 메트로폴리탄 미술관의 경비원입니다</div>
                <div>패트릭 브링리· 웅진지식하우스</div>
                <div><span>발행년도</span> 2023</div>
                <div><span>소장위치</span> 2F 종합자료실</div>
            </div>
        </div>
    </div>
    <div class="copyright">Daegu metropolitan library</div>
</div>
</body>
</html>