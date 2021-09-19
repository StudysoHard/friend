window.addEventListener("load", function() {
    //createPeople的隐藏事件
    $(".project_main").mouseover(function() {
        $(this).siblings(".create_people").css("display", "block")
    })
    $(".project_main").mouseleave(function() {
        $(this).siblings(".create_people").css("display", "none")
    })
})