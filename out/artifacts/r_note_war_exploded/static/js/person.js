$(function() {
    var flag = 1;
    $('.user_head').on("mouseenter mouseleave", function() {
        $('.user-menu').stop().fadeToggle(1.5)
    })
    $('.content-button').click(function() {
        // if (flag) {
        //     $('.content-header').hide();
        //     $('.content-button').text('');
        //     flag = 0;
        // } else {
        //     $('.content-header').show();
        //     $('.content-button').text('');
        //     flag = 1;
        // }
        $(".change_font").toggleClass("change_turn");
        $(".content-header").slideToggle(500);
    })
    $(".contentbar>ul>li").mouseover(function() {
        $(this).children("span").css("color", "orange");
        $(this).children("span").css("font-weight", "largest");
    })
    $(".contentbar>ul>li").mouseleave(function() {
        $(this).children("span").css("color", "rgba(140, 140, 140)")
    })
})
