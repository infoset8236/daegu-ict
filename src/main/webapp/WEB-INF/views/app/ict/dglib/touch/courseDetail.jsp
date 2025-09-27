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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/touch/css/courseDetail.css">
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
            <div class="headerKoEnVert">
                <div>COURSE INFORMATION</div>
                <div>강좌정보</div>
            </div>
            <div class="content">
                <div class="courseInformation">
                    <div class="courseName">
                        <div class="decoration"></div>
                        <div class="infoBox">
                            <div>강좌명</div>
                            <div>
                                [해외대학 랜선투어]어서와 해외대학은 처음이지? - 일리노이 [해외대학 랜선투어]어서와 해외대학은 처음이지? - 일리노이
                            </div>
                        </div>
                    </div>
                    <div class="coursePeriod">
                        <div class="decoration"></div>
                        <div class="infoBox">
                            <div>강의기간</div>
                            <div>
                                2024-07-01 ~ 2024-07-31
                            </div>
                        </div>
                    </div>
                    <div class="courseApplyDate">
                        <div class="decoration"></div>
                        <div class="infoBox">
                            <div>접수기간</div>
                            <div>
                                2024-06-22 ~ 2024-06-27
                            </div>
                        </div>
                    </div>
                    <div class="applyStatus">
                        <div class="applyItem">
                            <div class="applyLabel">온라인접수</div>
                            <div class="applyCount"><span>6</span>/20</div>
                        </div>
                        <div class="applyItem">
                            <div class="applyLabel">대기자접수</div>
                            <div class="applyCount"><span>6</span>/20</div>
                        </div>
                    </div>
                </div>
                <div class="courseDetail">
                    <div class="courseDetailItem">
                        <div class="label">강사명</div>
                        <div class="value">김민정</div>
                    </div>
                    <div class="courseDetailItem">
                        <div class="label">강좌시간</div>
                        <div class="value">매주 화목 10:00 ~ 12:00</div>
                    </div>
                    <div class="courseDetailItem">
                        <div class="label">강좌장소</div>
                        <div class="value">2층 어린이자료실 그림책 숲</div>
                    </div>
                    <div class="courseDetailItem">
                        <div class="label">수강대상</div>
                        <div class="value">4~6세 유아</div>
                    </div>
                    <div class="summary">
                        <div class="label">안내사항</div>
                        <div>
                            * 사전연락 없이 무통보 미참석시 다음 수업신청에서 제외됩니다.
                        </div>
                    </div>
                    <a href="/ict/dglib/touch/courseForm.do" class="submit">신청하기</a>
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
