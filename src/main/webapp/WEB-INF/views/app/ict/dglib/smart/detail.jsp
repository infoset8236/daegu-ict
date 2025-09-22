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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/smart/css/detail.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="/resources/ict/dglib/smart/js/detail.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>book information</div>
                <div>도서정보</div>
            </div>
            <div class="content">
                <div class="mainBookList">
                    <%-- 반복문으로 div 출력 --%>
                    <div class="mainBookListItem">
                        <div class="bookInformation">
                            <div class="mainBookImage">
                                <div>대출<br>가능</div>
                                <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="">
                            </div>
                            <div>
                                <div class="mainBookTitle">
                                    미래의 나를 만난1
                                </div>
                                <div class="mainBookAuthor">할 허시필드 / 위즈덤하우스</div>
                                <div class="mainBookMeta">
                                    <div>
                                        발행년도
                                        <span>2024</span>
                                    </div>
                                    <div>
                                        INBN&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <span>9791162543726</span>
                                    </div>
                                    <div>
                                        소장위치
                                        <span>2F 종합자료실</span>
                                    </div>
                                    <div>
                                        청구기호
                                        <span>813.8이560</span>
                                    </div>
                                </div>
                                <a href="" class="trigger">서가위치보기</a>
                            </div>
                        </div>

                        <div class="mainBookDesc">
                            <div>책소개</div>
                            <div>
                                더 나은 미래를 살고 싶다면 오늘을 잘 살아야 한다는 것을 모르는 사람이 있을까? 하지만 우리는 종종 눈앞의 유혹에 이끌려 당장 내일 후회할 선택들을 하곤 한다. 왜일까? 20여 년간 이 문제를 연구해 온 저자는 그 메커니즘의 핵심에 ‘미래 자아’가 있다고 말한다. 저자에 따르면 우리는 먼 미래의 나를 ‘나 자신’으로 여기는 것이 아니라 거의 타인처럼 느끼기 때문에, 낯선 대상을 위해서 현재의 즐거움을 희생하는 선택을 내리기 어렵다. 반대로 말하면, 미래 자아와의 심리적 거리를 좁히면 장기적으로 현명한 선택들을 하게 되어 더 행복한 삶을 살 수 있다는 것!
                                더 나은 미래를 살고 싶다면 오늘을 잘 살아야 한다는 것을 모르는 사람이 있을까? 하지만 우리는 종종 눈앞의 유혹에 이끌려 당장 내일 후회할 선택들을 하곤 한다. 왜일까? 20여 년간 이 문제를 연구해 온 저자는 그 메커니즘의 핵심에 ‘미래 자아’가 있다고 말한다. 저자에 따르면 우리는 먼 미래의 나를 ‘나 자신’으로 여기는 것이 아니라 거의 타인처럼 느끼기 때문에, 낯선 대상을 위해서 현재의 즐거움을 희생하는 선택을 내리기 어렵다. 반대로 말하면, 미래 자아와의 심리적 거리를 좁히면 장기적으로 현명한 선택들을 하게 되어 더 행복한 삶을 살 수 있다는 것!
                                더 나은 미래를 살고 싶다면 오늘을 잘 살아야 한다는 것을 모르는 사람이 있을까? 하지만 우리는 종종 눈앞의 유혹에 이끌려 당장 내일 후회할 선택들을 하곤 한다. 왜일까? 20여 년간 이 문제를 연구해 온 저자는 그 메커니즘의 핵심에 ‘미래 자아’가 있다고 말한다. 저자에 따르면 우리는 먼 미래의 나를 ‘나 자신’으로 여기는 것이 아니라 거의 타인처럼 느끼기 때문에, 낯선 대상을 위해서 현재의 즐거움을 희생하는 선택을 내리기 어렵다. 반대로 말하면, 미래 자아와의 심리적 거리를 좁히면 장기적으로 현명한 선택들을 하게 되어 더 행복한 삶을 살 수 있다는 것!
                            </div>
                        </div>
                    </div>
                </div>
                <div class="bookList">
                    <%-- 반복문으로 div 출력 --%>
                    <div class="book">
                        <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="" class="bookImage">
                        <div class="bookTitle">
                            미래의 나를 만난 후 오늘이 달라졌다1
                            : 5년 뒤 나를 바꾸는 퓨처 셀프의 비밀
                        </div>
                        <div class="bookAuthor">할 허시필드</div>
                    </div>
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/smart/nav.jsp"/>
        </div>
    </div>
    <div id="popup" class="popup">
        <div class="popupContent">
            <img src="/resources/ict/dglib/smart/img/detail/dummy.png" alt="">
            <div class="locationDetail">
                <div>도서명 : 파타</div>
                <div>자료실 : 2층 일반자료실</div>
                <div>청구기호 : 813.8이560</div>
            </div>
            <div class="btnWrapper">
                <button class="close">닫기</button>
                <button class="print">인쇄</button>
            </div>
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>