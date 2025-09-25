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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/nav.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/dglib/roompad/js/nav.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="navigation">
    <div class="libraryTit">대구도서관</div>
    <div class="todayWrap">
        <div class="todayTit">TODAY SCHEDULE</div>
        <div class="roomClock">
            <canvas id="gc"></canvas>
        </div>
        <div class="todayInfo">
            <div class="todayDate">2025년 11월 05일(수)</div>
            <div class="todayTime">11:08</div>
        </div>
    </div>>
    <a id="btnNav" class="btnNav" href="#">메인</a>
</div>
</body>
</html>
