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
    <jsp:include page="/WEB-INF/views/app/ict/dglib/roomPad/nav.jsp" />
    <div class="roomPadCont">
        <!-- 사용자 정보 -->
        <div class="userInfo">
            <a href="/ict/dglib/roomPad/myPage.do" class="usrProfileLnk">
                <!-- 예약자 인증 페이지 변수처리 해주세요 -->
<!--        <a href="/ict/dglib/roomPad/myPage.do" class="usrProfileLnk">-->
                <img src="/resources/ict/dglib/roompad/img/user.svg" alt="프로필이미지">
            </a>
        </div>

        <div class="roomCont">
            <!-- 룸 정보 -->
            <div class="roomInfo">
                <div class="roomTit">
                    <div class="roomTitEn">group study room 01</div>
                    <div class="roomTitKo">그룹 스터디룸 01</div>
                </div>
                <div class="roomTime">이용시간 : 09시 ~ 18시</div>
            </div>

            <!-- 예약 시간 -->
            <div class="reservation">
                <div class="resvInfo">
                    <div class="resvTit">Reservation time</div>
                    <div class="resvTime">11:00 ~ 12:00</div>
                </div>
                <a href="javascript:void(0);" class="resvChkBtn">이용자 확인</a>
            </div>

            <!-- 대기자 리스트 슬라이드 -->
            <div class="queueList">
                <div class="queueSlideWrap">
                <!-- queueSlideItem 부분 반복문 -->
                    <div class="queueSlideItem">
                        <div class="queueStatus">대기중</div>
                        <div class="queueUsrName">이*현</div>
                        <div class="queueTime">12:00 ~ 13:00</div>
                    </div>
                </div>
                <div class="queueArrows">
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
<div id="usrResvModal" class="modal">
    <div id="usrResvPopupCont" class="usrResvPopup">
        <div class="usrResvHeader">
            <div class="usrResvTit">이용자 확인</div>
        </div>
        <div class="usrResvCont">
            <div class="usrResvScroll">
                <div class="usrResvList">
                    <!-- usrResvItem 부분 반복문 -->
                    <div class="usrResvItem">이*현</div>
                </div>
            </div>
        </div>
        <a href="#" class="btnUsrResv">확인</a>
    </div>
</div>

</body>
</html>

<script>
    $(document).ready(function() {
        // 버튼 클릭 시 모달 열기
        $(".resvChkBtn").on("click", function() {
            $("#usrResvModal").fadeIn();
        });

        // 닫기 버튼 클릭
        $("#usrResvModal .btnUsrResv").on("click", function() {
            $("#usrResvModal").fadeOut();
        });

        // 모달 바깥 클릭 시 닫기
        $(window).on("click", function(e) {
            if ($(e.target).is("#usrResvModal")) {
                $("#usrResvModal").fadeOut();
            }
        });
    });

</script>