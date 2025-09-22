$(document).ready(function () {
    const buttons = $(".tabMenu div");

    buttons.on("click", function () {
        const $this = $(this);

        if ($this.hasClass("active")) return;

        const $prev = $(".tabMenu div.active");
        gsap.to($prev, {
            scale: 1,
            color: "#fff",
            duration: 0.2,
            onComplete: () => {
                $prev.removeClass("active");
            }
        });

        $this.addClass("active");
        gsap.fromTo(
            $this,
            { scale: 1 },
            {
                scale: 1.1,
                color: "#fff",
                duration: 0.2,
                yoyo: true,
                repeat: 1
            }
        );
    });
});