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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/myPage.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/roompad/js/myPage.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <jsp:include page="/WEB-INF/views/app/ict/dglib/roomPad/nav.jsp" />

    <div class="myResCont">
        <div class="myResHead">
            <div class="myResTitEn">my Reservation status</div>
            <div class="myResTitKo">나의 예약현황</div>
        </div>

        <div class="myResList">
            <div class="myResItem">
                <div class="myResSum">
                    <div class="myResDate">2025. 07. 21. (월)</div>
                    <div class="myResSts stsCancel">취소</div>
                    <div class="myResUsr">이*현</div>
                </div>
                <div class="myResDtl">
                    <div class="myResInfo myResTm"><span>시간</span>11:00 ~ 12:00</div>
                    <div class="myResInfo myResPlc"><span>장소</span>그룹 스터디룸 19</div>
                </div>
            </div>
            <div class="myResItem">
                <div class="myResSum">
                    <div class="myResDate">2025. 07. 21. (월)</div>
                    <div class="myResSts stsCheckIn">입실<br />완료</div>
                    <div class="myResUsr">이*현</div>
                </div>
                <div class="myResDtl">
                    <div class="myResInfo myResTm"><span>시간</span>11:00 ~ 12:00</div>
                    <div class="myResInfo myResPlc"><span>장소</span>그룹 스터디룸 19</div>
                </div>
            </div>
            <div class="myResItem">
                <div class="myResSum">
                    <div class="myResDate">2025. 07. 21. (월)</div>
                    <div class="myResSts stsWait">대기중</div>
                    <div class="myResUsr">이*현</div>
                </div>
                <div class="myResDtl">
                    <div class="myResInfo myResTm"><span>시간</span>11:00 ~ 12:00</div>
                    <div class="myResInfo myResPlc"><span>장소</span>그룹 스터디룸 19</div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>