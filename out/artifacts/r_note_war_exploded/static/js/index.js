window.addEventListener("load", function() {
    // 添加快速匹配的鼠标事件
    $("#user_head").mouseover(function() {
        this.attr("class", "img-circle user_head user_head_change");
    })
    $("#user_head").mouseleave(function() {
        this.attr("class", "img-circle user_head")
    })
})