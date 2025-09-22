<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="waveBottomArea">
    <%-- 뒤로가기 버튼 --%>
    <%--  <div class="back" onclick="history.back();"></div>--%>
    <div class="userState">
        <img src="/resources/ict/common/img/wave/noUser.svg" alt="">
        <a href="/ict/dglib/account/index.do?from=${fn:contains(pageContext.request.requestURI, '/smart/') ? 'smart' : 'touch'}">로그인</a>
    </div>
    <%-- 로그인 상태(남성) / 여성은 woman.svg --%>
    <!--
    <div class="userState">
        <img src="/resources/ict/common/img/wave/man.svg" alt="">
        <a href="/ict/dglib/account/myPage.do"><span>남바다</span>님</a>
        <button class="logout"></button>
    </div>
    -->
    <a class="goToAI" href="/ict/dglib/ai/index.do"></a>
    <div class="navigation">
        <a id="search" href="/ict/dglib/smart/index.do" data-paths="/ict/dglib/smart/index.do">검색</a>
        <a id="chart" href="/ict/dglib/smart/chart.do" data-paths="/ict/dglib/smart/chart.do">키워드추천</a>
        <a id="custom" href="/ict/dglib/smart/custom.do" data-paths="/ict/dglib/smart/custom.do">맞춤형추천</a>
        <a id="librarian" href="/ict/dglib/smart/librarian.do" data-paths="/ict/dglib/smart/librarian.do">사서추천</a>
        <a id="bigData" href="/ict/dglib/smart/bigData.do" data-paths="/ict/dglib/smart/bigData.do">빅데이터추천</a>
        <a id="myPage" href="/ict/dglib/account/myPage.do" data-paths="/ict/dglib/account/myPage.do">나의서재</a>
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
