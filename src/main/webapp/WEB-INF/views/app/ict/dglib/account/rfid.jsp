<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/account/css/rfid.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<script>
    $(function () {
        const $input = $("#member_id");

        // 한글 → 영문 매핑
        const charMapping = {
            'ㅁ': 'a', 'ㅠ': 'b', 'ㅊ': 'c', 'ㅇ': 'd', 'ㄷ': 'e', 'ㄹ': 'f', 'ㅎ': 'g',
            'ㅗ': 'h', 'ㅑ': 'i', 'ㅓ': 'j', 'ㅏ': 'k', 'ㅣ': 'l', 'ㅡ': 'm', 'ㅜ': 'n',
            'ㅐ': 'o', 'ㅔ': 'p', 'ㅂ': 'q', 'ㄱ': 'r', 'ㄴ': 's', 'ㅅ': 't', 'ㅕ': 'u',
            'ㅍ': 'v', 'ㅈ': 'w', 'ㅌ': 'x', 'ㅛ': 'y', 'ㅋ': 'z'
        };

        // 특정 조합 입력 무시 (IME 입력 방지)
        $(document).on("keydown", function (e) {
            if (e.keyCode === 21 || e.keyCode === 229 || e.isComposing) {
                e.preventDefault();
            }
        });

        // 입력값 처리 (Enter 입력 시 실행)
        $input.on("keydown", function (e) {
            if (e.key === "Enter") {
                e.preventDefault();

                let rawValue = $input.val();
                let converted = "";

                if (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/.test(rawValue)) {
                    for (let char of rawValue) {
                        converted += charMapping[char] || char;
                    }
                } else {
                    converted = rawValue;
                }

                alert(converted);

                $.ajax({
                    url: "/api/klas/rfidLogin.do",
                    type: "POST",
                    data: { member_id: converted },
                    dataType: "json",
                    success: function (res) {
                        if (res.result === "SUCCESS") {
                            // 로그인 성공 시 처리
                            location.href = "/ict/dglib/account/main";
                        } else {
                            alert(res.message || "로그인 실패");
                        }
                    },
                    error: function (xhr, status, err) {
                        console.error("로그인 요청 실패:", err);
                        alert("서버와 통신 중 오류가 발생했습니다.");
                    }
                });

                $input.val("");
            }
        });

        // 항상 입력창에 포커스 유지
        function keepFocus() {
            $input.focus();
        }
        document.onmousemove = keepFocus;
        document.onmousedown = keepFocus;
        document.onmouseup = keepFocus;

        // 페이지 로드 시 포커스
        $input.focus();
    });
</script>

<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="content">
                <div class="title">RFID 로그인</div>
                <div class="description">기기 하단의 회원증 인식부에 회원증을 올려주세요</div>

                <input id="member_id" type="text" name="member_id" size="30" title="아이디" autocomplete="off" style="position:absolute;top:-5000px;left:-5000px;opacity:0;" inputmode="none">

                <div class="decoration">
                    <img class="signal1" src="/resources/ict/dglib/account/img/signal1.svg" alt="">
                    <img class="signal2" src="/resources/ict/dglib/account/img/signal2.svg" alt="">
                    <img class="card" src="/resources/ict/dglib/account/img/rfid.png" alt="">
                    <img class="signal3" src="/resources/ict/dglib/account/img/signal1.svg" alt="">
                    <img class="signal4" src="/resources/ict/dglib/account/img/signal2.svg" alt="">
                </div>
            </div>
            <jsp:include page="/WEB-INF/views/app/ict/dglib/${param.from == 'smart' ? 'smart' : 'touch'}/nav.jsp" />
        </div>
    </div>
    <div class="scrollUp">
        <img src="/resources/ict/common/img/common/scrollUp.svg" alt="">
    </div>
</div>
</body>
</html>
