$(document).ready(function () {
    const buttons = $(".filter button");

    buttons.on("click", function () {
        const $this = $(this);

        if ($this.hasClass("active")) return;

        const $prev = $(".filter button.active");
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

    $('.bookSlide').each(function () {
        const $slider = $(this);
        const $items = $slider.children();
        const groupedItems = [];

        for (let i = 0; i < $items.length; i += 12) {
            const $group = $('<div class="wrapper"></div>');
            $items.slice(i, i + 12).appendTo($group);
            groupedItems.push($group);
        }

        $slider.empty().append(groupedItems);

        $slider.slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: groupedItems.length > 1,
            infinite: true
        });
    });
});
