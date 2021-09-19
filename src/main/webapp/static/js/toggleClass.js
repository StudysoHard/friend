window.addEventListener("load", function() {
    $(".toggele_button").mouseover(function() {
        $(this).toggleClass("toggle_class1");
    })
    $(".toggele_button").mouseleave(function() {
        $(this).toggleClass("toggle_class2")
    })
})