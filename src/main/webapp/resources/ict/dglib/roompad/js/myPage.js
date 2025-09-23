$(document).ready(function () {
    const slider = {
        $container: $(".myResList"), // 슬라이더 컨테이너
        $items: $(".myResList").children(".myResItem"), // 슬라이드 아이템
        groupSize: 3 // 한 그룹당 아이템 수
    };

    // myResItem이 3개 이상일 때만 그룹화 및 슬라이더 활성화
    if (slider.$items.length >= 4) {
        // 그룹화: 슬라이드 아이템을 groupSize(3개) 단위로 묶음
        for (let i = 0; i < slider.$items.length; i += slider.groupSize) {
            const $groupItems = slider.$items.slice(i, i + slider.groupSize);
            $groupItems.wrapAll('<div class="slideGroup"></div>');
        }

        // Slick 슬라이더 초기화
        slider.$container.slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: true, // dots 활성화
            infinite: true,
            variableWidth: true
        }).on('init', function(event, slick) {
            // 슬라이더 초기화 시 dots 표시
            $(".slick-dots").show();
        });
    } else {
        // myResItem이 3개 미만일 경우, 슬라이더와 dots 숨김
        slider.$container.removeClass("slick-initialized slick-slider"); // Slick 클래스 제거
        $(".slick-dots").hide(); // dots 숨김
    }

});

window.onload = function () {
    const canv = document.getElementById("gc");
    const ctx = canv.getContext("2d");

    // Canvas 크기 (CSS 크기 맞춤)
    canv.width = canv.clientWidth;
    canv.height = canv.clientHeight;

    const size = canv.width;
    const cx = size / 2;
    const cy = size / 2;

    const rem = 32;

    // 바늘 길이(px)
    const hourLength = 2.1875 * rem;
    const minLength  = 4 * rem;
    const secLength  = 4 * rem;

    // 바늘 두께(px)
    const hourWidth = 0.125 * rem;
    const minWidth  = 0.125 * rem;
    const secWidth  = 0.03 * rem;

    // 안쪽 들어가는 길이(px) (적당히 조정, 필요 시 고정값 사용 가능)
    const hourInner = 1.25 * rem;
    const minInner  = 1.25 * rem;
    const secInner  = 1.25 * rem;

    function clock() {
        ctx.clearRect(0, 0, canv.width, canv.height);

        const now = new Date();
        const h = now.getHours() % 12;
        const m = now.getMinutes();
        const s = now.getSeconds();

        // 시침
        drawRotatedRect(cx, cy, hourLength, hourWidth, (h/12 + m/720 + s/43200) * 360, "#fff", hourInner);
        // 분침
        drawRotatedRect(cx, cy, minLength, minWidth, (m/60 + s/3600) * 360, "#fff", minInner);
        // 초침
        drawRotatedRect(cx, cy, secLength, secWidth, (s/60) * 360, "#fff", secInner);
    }

    function drawRotatedRect(x, y, length, height, degrees, color, innerLength = 0) {
        ctx.save();
        ctx.translate(x, y);
        ctx.rotate((degrees - 90) * Math.PI / 180);
        ctx.fillStyle = color;
        ctx.fillRect(-innerLength, -height/2, length + innerLength, height);
        ctx.restore();
    }

    clock();
    setInterval(clock, 1000);
}