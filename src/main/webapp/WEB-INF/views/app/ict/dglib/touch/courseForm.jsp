<%@ page language="java" pageEncoding="utf-8" %>
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
    <link rel="stylesheet" type="text/css" href="/resources/ict/dglib/touch/css/courseForm.css">
    <script src="/resources/ict/common/plugin/jquery-3.7.1.min.js"></script>
    <script src="/resources/ict/common/plugin/slick.min.js"></script>
    <script src="/resources/ict/common/js/common.js"></script>
    <script src="/resources/ict/dglib/touch/js/courseForm.js"></script>
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div class="dim">
    <div class="container">
        <div class="scrollDownArea">
            <div class="headerKoEnVert">
                <div>COURSE REGISTRATION</div>
                <div>강좌신청</div>
            </div>

            <div class="content">
                <form action="">
                    <div class="form">
                        <div class="scroll">
                            <%--    항목 헤더    --%>
                            <div class="formHeader">
                                <div>수강생 정보</div>
                                <label class="customCheckbox">
                                    <input type="checkbox">
                                    <span class="checkmark"></span>
                                    신청자 정보와 동일
                                </label>
                            </div>

                            <%--    텍스트 인풋    --%>
                            <div class="col">
                                <div class="label">성명</div>
                                <label class="input">
                                    <input type="text" placeholder="성명을 입력해주세요">
                                </label>
                            </div>

                            <%--    성별    --%>
                            <div class="col">
                                <div class="label">성별</div>
                                <div class="genderGroup">
                                    <label class="gender">
                                        <input type="radio" name="gender" value="male" checked>
                                        <span>남</span>
                                    </label>
                                    <label class="gender">
                                        <input type="radio" name="gender" value="female">
                                        <span>여</span>
                                    </label>
                                </div>
                            </div>

                            <%--    텍스트 인풋    --%>
                            <div class="col">
                                <div class="label">생년월일</div>
                                <label class="input">
                                    <input type="text" placeholder="YYYYMMDD">
                                </label>
                            </div>

                            <%--    핸드폰 인풋    --%>
                            <div class="col">
                                <div class="label">휴대전화번호</div>
                                <div class="phone">
                                    <label class="input">
                                        <input type="text" placeholder="010">
                                    </label>
                                    <label class="input">
                                        <input type="text" placeholder="0000">
                                    </label>
                                    <label class="input">
                                        <input type="text" placeholder="0000">
                                    </label>
                                </div>
                            </div>

                            <%--    라디오 버튼    --%>
                            <div class="row">
                                <div class="label">SMS 수신동의 여부</div>
                                <div class="customRadio">
                                    <label class="radio">
                                        <input type="radio" name="agree" value="yes">
                                        <span class="radioMark"></span>
                                        동의
                                    </label>

                                    <label class="radio">
                                        <input type="radio" name="agree" value="no">
                                        <span class="radioMark"></span>
                                        미동의
                                    </label>
                                </div>
                            </div>

                            <div class="caption">
                                캡션 영역입니다.
                            </div>

                            <div class="warn">
                                주요 안내 영역입니다.
                            </div>
                        </div>
                    </div>


                    <div class="agreementSection">
                        <div class="agreementHeader">
                            <div class="agreementTitle">이용약관 및 개인정보의 수집·이용 동의 여부</div>
                            <div class="agreementTrigger">동의서</div>
                        </div>

                        <div class="customRadio">
                            <label class="radio">
                                <input type="radio" name="agree" value="yes">
                                <span class="radioMark"></span>
                                동의
                            </label>

                            <label class="radio">
                                <input type="radio" name="agree" value="no">
                                <span class="radioMark"></span>
                                미동의
                            </label>
                        </div>
                    </div>

                    <button class="submit">확인</button>
                </form>
            </div>

            <!--	ict 공통 팝업입니다. 다른 곳에도 아래 팝업 사용하시면 됩니다.	-->
            <div id="commonPopup" class="commonPopup">
                <div class="commonPopupContent">
                    <div>
                        제1장 총칙
                        <br><br>
                        제1조 (목적) 본 약관은 『대구광역시 도서관 통합 허브시스템』을 구축(이하 ‘통합시스템’이라 함)에 따라 대구광역시 공립 공공도서관 및 공립 작은도서관(이하 ‘도서관’ 이라 한다.) 회원 정보를 통합 운영함에 있어 이용자와 도서관간의 이용조건 및 절차, 이용에 관한 권리와 의무 등 제반사항을 규정함을 목적으로 한다.
                        <br><br>
                        제2조 (용어의 정의) ① 본 약관에서 사용하는 용어의 정의는 다음과 같다. 1. 서비스 : 온라인사이트 이용 및 오프라인도서관 이용 모두를 의미 2. 사이트 : 도서관에서 운영하는 홈페이지 3. 통합회원 : 도서관 회원 가입에 동의하고 본인확인절차를 통해 회원번호 부여 및 회원카드가 발급된 회원으로 자료의 관외 대출이 가능한 회원 또는 통합인증을 통해 통합회원으로 자격이 부여된 회원이며 책이음 이용정보제공이용약관에 동의하면 책이음회원으로 자격을 부여 한다. 4. 통합인증 : 통합회원, 책이음회원으로의 자격을 부여받는 절차를 의미하며 통합인증을 받지 않으면 일부 서비스의 제약을 받을 수 있다. 5. 아이디(ID) : 회원 식별과 서비스 이용을 위하여 이용자가 생성한 영문자 또는 기타 문자로 조합된 부호 6. 비밀번호(PASSWORD) : 회원의 정보 보호를 위해 이용자 자신이 설정한 문자와 숫자, 특수문자 등으로 조합된 부호 ② 본 약관에서 사용하는 용어의 정의는 제1항에서 정하는 것을 제외하고는 관계법령 및 서비스 별 안내에서 정하는 바에 의한다.
                    </div>
                    <button>확인</button>
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
