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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/smart/css/result.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>
    <script src="/resources/ict/dglib/smart/js/result.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>BOOK SEARCH</div>
                <div>도서검색</div>
            </div>
            <div class="content">
                <div class="totalCount">검색어 <span>'오늘'</span>에 대해 총 <span>706건</span>이 검색되었습니다.</div>
                <ul class="resultList">
					<%-- 반복문으로 li 출력 --%>
                    <li class="resultItem">
                        <div class="wrapper">
                            <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="">
                            <div class="bookInfo">
                                <div>나는 오늘 불안과 친구가 되기로 했다오늘 밤</div>
                                <div>
                                    저&nbsp;&nbsp;자&nbsp;&nbsp;명
                                    <span>공윤희, 윤예림</span>
                                </div>
                                <div>
                                    발&nbsp;&nbsp;행&nbsp;&nbsp;처
                                    <span>창비교육</span>
                                </div>
                                <div>
                                    발행년도
                                    <span>2023</span>
                                </div>
                                <div>
                                    I&nbsp;&nbsp;S&nbsp;&nbsp;B&nbsp;&nbsp;N
                                    <span>9791189228637</span>
                                </div>
                            </div>
                        </div>
                        <a href="/ict/dglib/smart/detail.do">상세보기</a>
                    </li>
                </ul>
                <div class="pagination">
                    <a class="first" href=""></a>
                    <a class="prev" href=""></a>
                    <ul>
                        <li class="active">1</li>
                        <li>2</li>
                        <li>3</li>
                    </ul>
                    <a class="next" href=""></a>
                    <a class="last" href=""></a>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/smart/nav.jsp"/>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>