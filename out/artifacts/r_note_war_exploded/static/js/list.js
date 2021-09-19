window.onload = function() {
    $(".sub_top>a").mousemove(function () {
        $(this).children("p").css("display","block");
        $(this).children("li").css("color","rgb(49, 176, 213)");
    })
    $(".sub_top>a").mouseleave(function () {
        $(this).children("li").css("color","rgb(102, 102, 102)");
        $(this).children("p").css("display","none");
    })
}