$(document).ready(function () {
    $(".agreementTrigger").on("click", function () {
        $("#commonPopup").fadeIn(300);
    });

    $("#commonPopup").on("click", "button", function () {
        $("#commonPopup").fadeOut(300);
    });

    $(document).on("click", function (e) {
        if ($(e.target).is("#commonPopup")) {
            $("#commonPopup").fadeOut(300);
        }
    });
});
