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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/touch/css/courseList.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="/resources/ict/dglib/touch/js/courseList.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>COURSE</div>
                <div>강좌안내</div>
            </div>
            <div class="content">
                <div class="courseList">
                    <%-- 반복문으로 a 출력 --%>
                    <a class="course" href="/ict/dglib/touch/courseDetail.do">
                        <div class="courseHeader">
							<!--	<div class="courseStatus code1">수강신청</div>    -->
							<!--	<div class="courseStatus code2">대기자<br>신청</div>    -->
							<!--	<div class="courseStatus code3">신청완료</div>    -->
							<!--	<div class="courseStatus code4">대기자<br>신청완료</div>    -->
							<!--	<div class="courseStatus code5">신청대기</div>    -->
							<!--	<div class="courseStatus code6">접수마감</div>    -->
							<!--	<div class="courseStatus code7">수강종료</div>    -->
							
                            <div class="courseStatus code1">수강신청</div>
                            <div>
                                <div class="courseTitle">[노후준비 아카데미] 우리가족 슬기로운 경제생활</div>
                                <div class="courseMeta">
                                    <div class="coursePeriod">
                                        <span>접수기간</span>
                                        <p>2024-08-26 ~ 2024-08-30</p>
                                    </div>
                                    <div class="courseTarget">
                                        <span>수강대상</span>
                                        <p>4-6세 유아</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="courseNumbers">
                            <div class="courseCapacity">
                                <span>접수인원</span>
                                <p><em>100</em>/100</p>
                            </div>
                            <div class="courseWaitingList">
                                <span>후보인원</span>
                                <p><em>0</em>/3</p>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/touch/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>