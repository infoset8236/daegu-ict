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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/nonTouch/css/life.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/nonTouch/js/life.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="headerKoEnVert">
        <div class="headerEn">life information</div>
        <div class="headerKo">생활정보</div>
    </div>
    <div class="content">
        <div class="weatherWidget">
            <div>
                <div class="todayInfo">
                    <div>TODAY</div>
                    <div>맑음</div>
                </div>
                <div class="todayTemp">35.6˚</div>
                <div class="feelsLike">
                    <div>체감온도</div>
                    <div>+3.4˚</div>
                </div>
            </div>
            <img class="todayIcon" src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
        </div>
        <div class="lifeIndex">
            <div>미세먼지<span>아주좋음</span></div>
            <div>식중독<span>주의</span></div>
        </div>
        <div class="weeklyForecast">
            <div class="weeklyTitle">weekly Forecast</div>
            <div class="weeklyList">
                <%-- 반복문으로 div 출력 --%>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
                <div class="weeklyItem">
                    <img src="/resources/ict/dglib/nonTouch/img/life/01n.png" alt="">
                    <div class="weeklyInfo">
                        <div>월 06.30</div>
                        <div>32.7˚</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="newsArea">
            <div class="newsAreaTitle">headline news</div>
            <div class="newsList">
                <%-- 반복문으로 div 출력 --%>
                <div class="news">
                    <div>
                        지난 3월 말 강원도 양양군 해안에서 구조된 어린 점박이물범, 다시 자연의 품으로 돌아가
                        박이물범, 다시 자연의 품으로 돌아가
                        박이물범, 다시 자연의 품으로 돌아가
                    </div>
                    <div>
                        2025-06-29  11:00
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">Daegu metropolitan library</div>
</div>
</body>
</html>
