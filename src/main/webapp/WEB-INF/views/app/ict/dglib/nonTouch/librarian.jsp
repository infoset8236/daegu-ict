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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/nonTouch/css/librarian.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/dglib/nonTouch/js/librarian.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="container">
    <div class="headerKoEnVert">
        <div class="headerEn">Librarian Recommended Book</div>
        <div class="headerKo">사서추천도서</div>
    </div>
    <div class="content">
        <div class="mainBookList">
            <%-- 반복문으로 div 출력 --%>
            <div class="mainBookListItem">
                <div class="mainBookImage">
                    <img src="/resources/ict/dglib/nonTouch/img/common/dummy.png" alt="">
                </div>

                <div class="mainBookTitle">
                    미래의 나를 만난 후 오늘이 달라졌다1
                    : 5년 뒤 나를 바꾸는 퓨처 셀프의 비밀
                </div>
                <div class="mainBookMeta">
                    <div>저자명<span>할 허시필드</span></div>
                    <div>발행년도<span>2024</span></div>
                    <div>발행처<span>비즈니스북스</span></div>
                    <div>소장위치<span>2F 종합자료실</span></div>
                </div>
                <div class="mainBookDesc">
                    더 나은 미래를 살고 싶다면 오늘을 잘 살아야 한다는 것을 모르는 사람이 있을까? 하지만 우리는 종종 눈앞의 유혹에 이끌려 당장 내일 후회할 선택들을 하곤 한다. 왜일까? 20여 년간 이 문제를 연구해 온 저자는 그 메커니즘의 핵심에 ‘미래 자아’가 있다고 말한다. 저자에 따르면 우리는 먼 미래의 나를 ‘나 자신’으로 여기는 것이 아니라 거의 타인처럼 느끼기 때문에, 낯선 대상을 위해서 현재의 즐거움을 희생하는 선택을 내리기 어렵다. 반대로 말하면, 미래 자아와의 심리적 거리를 좁히면 장기적으로 현명한 선택들을 하게 되어 더 행복한 삶을 살 수 있다는 것!
                </div>
            </div>
        </div>
        <div class="bookList">
            <%-- 반복문으로 div 출력 --%>
            <div class="book">
                <img src="/resources/ict/dglib/nonTouch/img/common/dummy.png" alt="" class="bookImage">
                <div class="bookTitle">
                    미래의 나를 만난 후 오늘이 달라졌다1
                    : 5년 뒤 나를 바꾸는 퓨처 셀프의 비밀
                </div>
                <div class="bookAuthor">할 허시필드</div>
            </div>
        </div>
    </div>
    <div class="copyright">Daegu metropolitan library</div>
</div>
</body>
</html>
