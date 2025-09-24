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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/roompad/css/index.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/roompad/js/index.js"></script>
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
        <a href="/ict/dglib/roomPad/schedule.do" class="btnNav">일별스케줄</a>
    </div>

    <div class="rpContent">
        <!-- 사용자 정보 -->
        <div class="rpUser">
            <a href="/ict/dglib/roomPad/myPage.do" class="usrProfileLink">
                <img src="/resources/ict/dglib/roompad/img/user.svg" alt="프로필이미지">
            </a>
        </div>

        <div class="rpContainer">
            <!-- 룸 정보 -->
            <div class="rpInfo">
                <div class="rpTit">
                    <div class="rpTitEn">group study room 01</div>
                    <div class="rpTitKo">그룹 스터디룸 01</div>
                </div>
                <div class="rpTime">이용시간 : 09시 ~ 18시</div>
            </div>

            <!-- 예약 시간 -->
            <div class="rpReservation">
                <div class="resvInfo">
                    <div class="resvLbl">Reservation time</div>
                    <div class="resvTime">11:00 ~ 12:00</div>
                </div>
                <a href="javascript:void(0);" class="resvChkLink">이용자 확인</a>
            </div>

            <!-- 대기자 리스트 슬라이드 -->
            <div class="rpQueue">
                <div class="queueSlideList">
                    <div class="queueSlideItem">
                        <div class="queueStat">대기중</div>
                        <div class="queueUsrName">이*현</div>
                        <div class="queueTime">12:00 ~ 13:00</div>
                    </div>
                </div>
                <div class="rpsArrows">
                    <a href="" class="queueArrow queuePrev">
                        <img src="/resources/ict/dglib/roompad/img/slide-arrow-prev.svg" alt="">
                    </a>
                    <a href="" class="queueArrow queueNext">
                        <img src="/resources/ict/dglib/roompad/img/slide-arrow-next.svg" alt="">
                    </a>
                </div>
                <div class="queuePaging">
                    <div class="queueGauge"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 모달 영역 -->
<div id="usrVeriModal" class="modal">
    <div id="usrVeriPopupContainer"  class="usrChkPopup">
        <div class="ucpHeader">
            <div class="ucpTit">이용자 확인</div>
        </div>
        <div class="ucpContent">
            <div class="ucpScroll">
                <div class="ucpList">
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                    <div class="ucpItem">이*현</div>
                </div>
            </div>
        </div>
        <div class="ucpBtn">확인</div>
    </div>
</div>
</body>
</html>

<script>
    $(document).ready(function() {
        // 버튼 클릭 시 모달 열기
        $(".resvChkLink").on("click", function() {
            $("#usrVeriModal").fadeIn();
        });

        // 닫기 버튼 클릭
        $("#usrVeriModal .ucpBtn").on("click", function() {
            $("#usrVeriModal").fadeOut();
        });

        // 모달 바깥 클릭 시 닫기
        $(window).on("click", function(e) {
            if ($(e.target).is("#usrVeriModal")) {
                $("#usrVeriModal").fadeOut();
            }
        });
    });

</script>