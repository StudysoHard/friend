window.onload = function() {
    $(".check_all").change(function() {
        $(".j-checkbox").prop("checked", $(this).prop("checked"));
    })
    $(".j-checkbox").change(function() {
            if ($(".j-checkbox:checked").lenght == $(".j-checkbox").lenght) {
                $(".check_all").prop("checked", true);
            } else {
                $(".check_all").prop("checked", false);
            }
        })
        // font的点击事件
    $(".search_font").click(function() {
            $(this).toggleClass("font_button");
            $(".search_main").toggleClass("hidden");
            $(".result_main").toggleClass("move");
            $(".page_main").toggleClass("turn_button_move");
            $(".footer").toggleClass("footer_move");
        })
        // 省份的点击事件
    $(".position_ul_where>li>a").click(function() {
        $(this).parent().parent().find("a").not($(this)).removeClass('blue');
        $(this).addClass('blue');
        })
    // 类型的点击事件
    $(".position_ul_type>li>a").click(function() {
        $(this).parent().parent().find("a").not($(this)).removeClass('blue');
        $(this).addClass('blue');
    })
        //createPeople的隐藏事件
    $(".project_main").mouseover(function() {
        $(this).siblings(".create_people").css("display", "block")
    })
    $(".project_main").mouseleave(function() {
        $(this).siblings(".create_people").css("display", "none")
    })

    // 快速匹配的事件
    $(".fast_style").mouseover(function() {
        $(this).css("transform", "rotate(90deg)")
    })
    $(".fast_style").mouseleave(function() {
        $(this).css("transform", "rotate(0deg)")
    })
}