document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("btnNav");
    const currentUrl = window.location.pathname; // 현재 페이지 경로

    if (currentUrl.endsWith("/index.do")) {
        btn.textContent = "일별스케줄";
        btn.href = "/ict/dglib/roomPad/schedule.do";
    } else {
        btn.textContent = "메인";
        btn.href = "/ict/dglib/roomPad/index.do";
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

    const rem = 16;

    // 바늘 길이(px)
    const hourLength = 4.375 * rem;
    const minLength  = 8 * rem;
    const secLength  = 8 * rem;

    // 바늘 두께(px)
    const hourWidth = 0.25 * rem;
    const minWidth  = 0.25 * rem;
    const secWidth  = 0.06 * rem;

    // 안쪽 들어가는 길이(px) (적당히 조정, 필요 시 고정값 사용 가능)
    const hourInner = 2.5 * rem;
    const minInner  = 2.5 * rem;
    const secInner  = 2.5 * rem;

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