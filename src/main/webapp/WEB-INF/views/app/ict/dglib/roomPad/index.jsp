<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/font.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/common/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/index.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="navigation">
        <div class="library">대구도서관</div>
        <div class="caption">TODAY SCHEDULE</div>
        <div class="clock">
            <div class="hour"></div>
            <div class="minute"></div>
            <div class="second"></div>
        </div>

        <div class="date"></div>
        <div class="time"></div>
        <a href="">일별스케줄</a>
    </div>

    <div class="content">
        <div>
            <a href=""></a>
        </div>
        
        <div>
            <div>group study room 01</div>
            <div>그룹 스터디룸 01</div>
            <div>이용시간 : 09시 ~ 18시</div>
        </div>
        
        <div>
            <div>reservation time</div>
            <div>11:00 ~ 12:00</div>
            <div>이용자 확인</div>
        </div>

        <div class="list">
            <div class="wrapper">
                <div class="listItem">
                    <div>대기중</div>
                    <div>이*현</div>
                    <div>12:00 ~ 13:00</div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        function updateClock() {
            const now = new Date();
            const hours = now.getHours() % 12;
            const minutes = now.getMinutes();
            const seconds = now.getSeconds();

            // Calculate rotation angles
            const hourDeg = (hours + minutes / 60) * 30; // 360/12 = 30 degrees per hour
            const minuteDeg = (minutes + seconds / 60) * 6; // 360/60 = 6 degrees per minute
            const secondDeg = seconds * 6; // 360/60 = 6 degrees per second

            // Apply rotations using CSS transforms
            $('.hour').css('transform', `rotate(${hourDeg}deg)`);
            $('.minute').css('transform', `rotate(${minuteDeg}deg)`);
            $('.second').css('transform', `rotate(${secondDeg}deg)`);
        }

        // Update clock immediately and then every second
        updateClock();
        setInterval(updateClock, 1000);
    });
</script>
</body>
</html>