jQuery.event.special.touchstart = {
    setup: function (_, ns, handle) {
        this.addEventListener('touchstart', handle, { passive: false });
    },
};
jQuery.event.special.touchmove = {
    setup: function (_, ns, handle) {
        this.addEventListener('touchmove', handle, { passive: false });
    },
};


$(function () {
    const path = location.pathname;

    $('.navigation a').each(function () {
        const paths = $(this).data('paths').split(',');
        if (paths.includes(path)) {
            $(this).addClass('active');
        }
    });

    // 스크롤 이벤트
    $('.scrollDown').on('click', function () {
        $('.scrollUp').css('display', 'flex');
        $('.container').css({
            transition: 'transform 0.6s ease, height 0.6s ease',
            transform: 'translateY(60rem)',
            height: '60rem',
            overflowY: 'scroll'
        });
    });

    $('.scrollUp').on('click', function () {
        $('.scrollUp').css('display', 'none');
        $('.container').css({
            transition: 'transform 0.6s ease, height 0.6s ease',
            transform: 'translateY(0)',
            height: '120rem',
            overflowY: 'auto'
        });
    });
});

function showCommonPopup(message, callback) {
    $("#commonPopupMessage").text(message);
    $("#commonPopup").fadeIn();

    $("#commonPopupClose").off("click").on("click", function() {
        $("#commonPopup").fadeOut();
        if (typeof callback === "function") {
            callback();
        }
    });
}

$(function() {
    let prevUrl = document.referrer;
    if (!prevUrl || prevUrl.indexOf("/login") > -1) {
        prevUrl = "/";
    }

    $("#loginForm").on("submit", function(e) {
        e.preventDefault();

        const member_id = $("#id").val().trim();
        const member_pw = $("#password").val().trim();

        if (!member_id || !member_pw) {
            showCommonPopup("아이디와 비밀번호를 입력해주세요.");
            return;
        }

        $.ajax({
            url: "/api/klas/login.do",
            type: "POST",
            data: { member_id: member_id, member_pw: member_pw },
            dataType: "json",
            success: function(res) {
                if (res.result === "SUCCESS") {
                    if (res.redirectUrl && res.redirectUrl.trim() !== "") {
                        location.href = res.redirectUrl;
                    } else if (prevUrl && prevUrl.trim() !== "") {
                        location.href = prevUrl;
                    } else {
                        location.href = "/";
                    }
                } else {
                    showCommonPopup(res.message || "아이디 또는 비밀번호가 올바르지 않습니다.");
                }
            },
            error: function(xhr, status, error) {
                console.error("Login error:", error);
                showCommonPopup("로그인 요청 중 오류가 발생했습니다.");
            }
        });
    });
});

