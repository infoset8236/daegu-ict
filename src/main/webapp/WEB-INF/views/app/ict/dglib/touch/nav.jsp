<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="waveBottomArea">
    <c:choose>
        <c:when test="${empty sessionScope.member}">
            <div class="userState">
                <img src="/resources/ict/common/img/wave/noUser.svg" alt="">
                <a href="/ict/dglib/account/index.do?from=${fn:contains(pageContext.request.requestURI, '/smart/') ? 'smart' : 'touch'}">로그인</a>
            </div>
        </c:when>

        <c:otherwise>
            <c:set var="genderIcon" value="/resources/ict/common/img/wave/man.svg"/>
            <c:if test="${sessionScope.member.gpin_sex eq '1'}">
                <c:set var="genderIcon" value="/resources/ict/common/img/wave/woman.svg"/>
            </c:if>

            <div class="userState">
                <img src="${genderIcon}" alt="">
                <a href="/ict/dglib/account/myPage.do">
                    <span>${sessionScope.member.member_name}</span>님
                </a>
                <button class="logout" onclick="location.href='/ict/dglib/account/logout.do'"></button>
            </div>
        </c:otherwise>
    </c:choose>

    <a class="goToAI" href="/ict/dglib/ai/index.do"></a>
    <div class="navigation">
        <a id="index" href="/ict/dglib/touch/index.do" data-paths="/ict/dglib/touch/index.do">홈</a>
        <a id="notice" href="/ict/dglib/touch/notice.do" data-paths="/ict/dglib/touch/notice.do">공지사항</a>
        <a id="information" href="/ict/dglib/touch/information.do" data-paths="/ict/dglib/touch/information.do">이용안내</a>
        <a id="facility" href="/ict/dglib/touch/facility.do" data-paths="/ict/dglib/touch/facility.do">시설안내</a>
        <a id="event" href="/ict/dglib/touch/event.do" data-paths="/ict/dglib/touch/event.do">행사안내</a>
        <a id="course" href="/ict/dglib/touch/courseList.do" data-paths="/ict/dglib/touch/courseList.do,/ict/dglib/touch/courseDetail.do">강좌안내</a>
    </div>
    <div class="bf bfController">
        <div class="reset">초기화</div>
        <div class="scrollDown">화면내리기</div>
        <div class="windowController">
            <div class="zoomOut"></div>
            <div>화면크기(100%)</div>
            <div class="zoom"></div>
        </div>
        <div class="magnifier">돋보기</div>
    </div>
</div>

