document.addEventListener("DOMContentLoaded", function() {
    var today = new Date();

    var returnDate = new Date();
    returnDate.setDate(today.getDate() + 4);

    function formatDate(date) {
        var month = (date.getMonth() + 1).toString().padStart(2, '0');
        var day = date.getDate().toString().padStart(2, '0');
        return month + '<span>월</span>' + day + '<span>일</span>';
    }

    document.querySelector(".lendingDayValue").innerHTML = formatDate(today);
    document.querySelector(".returnDayValue").innerHTML = formatDate(returnDate);
});