$(document).ready(function () {
    $('.courseList').each(function () {
        const $slider = $(this);
        const $courses = $slider.children('.course');

        const chunkSize = 7;
        let newHtml = '';
        for (let i = 0; i < $courses.length; i += chunkSize) {
            const chunk = $courses.slice(i, i + chunkSize);
            newHtml += '<div class="courseItem">';
            chunk.each(function () {
                newHtml += this.outerHTML;
            });
            newHtml += '</div>';
        }
        $slider.html(newHtml);

        const slideCount = $slider.children().length;

        $slider.slick({
            slidesToShow: 1,
            arrows: false,
            autoplay: true,
            autoplaySpeed: 8000,
            dots: slideCount > 1,
            infinite: true
        });
    });
});
