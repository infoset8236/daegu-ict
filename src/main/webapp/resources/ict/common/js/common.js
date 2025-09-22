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

