<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	response.setHeader("X-Frame-Options", "DENY");
	response.setHeader("X-Content-Type-Options", "nosniff");
	response.setHeader("X-XSS-Protection", "1");
%>
<html lang="ko">
<head>
<meta charset="utf-8"/>
<title>에러페이지</title>
<!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<![endif]-->
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=0,maximum-scale=10"/>
<link rel="stylesheet" type="text/css" href="/resources/common/css/fontawesome.min.css"/>
<link rel="stylesheet" type="text/css" href="/resources/common/css/default.css"/>
<!--[if lte IE 7]>
<link rel="stylesheet" type="text/css" href="/resources/common/css/fontawesome-ie7.min.css"/>
<![endif]-->
<!--[if lte IE 8]>
<link rel="stylesheet" type="text/css" href="/resources/homepage/aaa/css/ie.css"/>
<![endif]-->
<script type="text/javascript" src="/resources/common/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="/resources/common/js/common.js"></script>
</head>
<body>

<!-- 500 에러-->
<div class="wrapper">
	<div class="dev-page-error-block">
		<i class="display fa fa-warning"></i>
		<h3>500</h3>
		<h4>죄송합니다.<br/>요청하신 페이지에 오류가 발생 하였습니다.</h4>
		<p class="txt">방문하시려는 페이지의 주소가 잘못 입력되었거나,<br/>페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br/><br/>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.<br/>오류가 계속 발생하는 경우 관리자에게 문의하시기 바랍니다.</p>
		<p><a href="javascript:history.go(-1);" class="btn btn1"><i class="fa fa-long-arrow-left"></i><span>이전 페이지</span></a></p>
	</div>
</div>

</body>
</html>