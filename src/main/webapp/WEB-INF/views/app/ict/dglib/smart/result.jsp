<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
                <form id="mainSearchForm" modelAttribute="libraryConfig">
                    <div class="totalCount">${searchType} <span>'${search_text}'</span>에 대해 총 <span>${bookCount}건</span>이 검색되었습니다.</div>
                    <ul class="resultList">
                        <%-- 반복문으로 li 출력 --%>
                        <c:forEach items="${bookSearch}" var="i" varStatus="status">
                        <li class="resultItem">
                            <div class="wrapper">
                                <c:choose>
                                    <c:when test="${fn:contains(i.IMAGE,'noimg')}">
                                        <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="">
                                    </c:when>
                                    <c:when test="${fn:contains(i.IMAGE,'bg_noImage2')}">
                                        <img src="/resources/ict/dglib/smart/img/common/dummy.png" alt="">
                                    </c:when>
                                    <c:otherwise>
                                        <img alt="" src="${i.IMAGE}" />
                                    </c:otherwise>
                                </c:choose>

                                <div class="bookInfo">
                                    <div>${i.TITLE_INFO}</div>
                                    <div>
                                        저&nbsp;&nbsp;자&nbsp;&nbsp;명
                                        <span>${i.AUTHOR}</span>
                                    </div>
                                    <div>
                                        발&nbsp;&nbsp;행&nbsp;&nbsp;처
                                        <span>${i.PUBLISHER}</span>
                                    </div>
                                    <div>
                                        발행년도
                                        <span>${i.PUB_YEAR}</span>
                                    </div>
                                    <div>
                                        I&nbsp;&nbsp;S&nbsp;&nbsp;B&nbsp;&nbsp;N
                                        <span>${i.ISBN}</span>
                                    </div>
                                </div>
                            </div>
                            <a href="javascript:void(0);" onclick="goDetail('${i.REG_NO}', '${i.MANAGE_CODE}');">상세보기</a>
                        </li>
                        </c:forEach>
                    </ul>

                    <div id="pagination" class="pagination">
                        <c:if test="${paging.firstPageNum > 0}">
                            <a class="first" href="#" keyValue="${paging.firstPageNum}"></a>
                        </c:if>

                        <c:if test="${paging.prevPageNum > 0}">
                            <a class="prev" href="#" keyValue="${paging.prevPageNum}"></a>
                        </c:if>

                        <ul>
                            <c:forEach var="i" begin="${paging.startPageNum}" end="${paging.endPageNum}">
                                <li <c:if test='${paging.viewPage eq i}'>class="active"</c:if>>${i}</li>
                            </c:forEach>
                        </ul>

                        <c:if test="${paging.nextPageNum > 0}">
                            <a class="next" href="#" keyValue="${paging.nextPageNum}"></a>
                        </c:if>

                        <c:if test="${paging.totalPageCount ne paging.lastPageNum}">
                            <a class="last" href="#" keyValue="${paging.totalPageCount}"></a>
                        </c:if>
                    </div>
                </form>
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
