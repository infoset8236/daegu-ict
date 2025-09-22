window.onload = function () {
    const canv = document.getElementById("gc");
    const ctx = canv.getContext("2d");

    // Canvas 내부 좌표계 크기 설정 (CSS 크기에 맞춤)
    const size = canv.clientWidth;
    canv.width = size;
    canv.height = size;

    const cx = size / 2;
    const cy = size / 2;

    // 바늘 길이 (중심에서 바깥쪽) & 안쪽 들어가는 길이(innerLength)
    const hourLength = size * 0.3;
    const minLength  = size * 0.4;
    const secLength  = size * 0.35;

    const hourInner = size * 0.1; // 시침 안쪽 길이
    const minInner  = size * 0.1; // 분침 안쪽 길이
    const secInner  = size * 0.1; // 초침 안쪽 길이

    function clock() {
        ctx.clearRect(0, 0, canv.width, canv.height);

        const now = new Date();
        const h = now.getHours() % 12;
        const m = now.getMinutes();
        const s = now.getSeconds();

        // 시침
        drawRotatedRect(cx, cy, hourLength, size * 0.015, (h/12 + m/720 + s/43200) * 360, "#fff", hourInner);
        // 분침
        drawRotatedRect(cx, cy, minLength, size * 0.015, (m/60 + s/3600) * 360, "#fff", minInner);
        // 초침
        drawRotatedRect(cx, cy, secLength, size * 0.005, (s/60) * 360, "#fff", secInner);
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