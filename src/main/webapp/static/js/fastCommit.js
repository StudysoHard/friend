window.addEventListener("load", function() {
    const buttonBox = document.querySelector('.button-box');
    const changeBox = document.querySelector('.change_box');
    const topBox = document.querySelector(".top_box");
    changeBox.addEventListener('mousedown', function(e) {
        var x = e.pageX - buttonBox.offsetLeft;
        var y = e.pageY - buttonBox.offsetTop;
        document.addEventListener('mousemove', move);

        function move(e) {
            buttonBox.style.left = e.pageX - x + 'px';
            buttonBox.style.top = e.pageY - y + 'px';
        }
        //鼠标弹起移动事件移除
        document.addEventListener('mouseup', function() {
            document.removeEventListener('mousemove', move);
        });
    });
    topBox.addEventListener('click',function (e) {
        $("body,html").animate({
            scrollTop: 0
        })
    })
    /**
     * 按钮事件
     * @type {number}
     */
    var personFlag = 1;
    var matchFlag = 1;
    var narowFlag = 1;
    $(".button-box").hover(function() {
        $(".narrow-box").stop().fadeToggle(40)
    })
    $(".change_box").on("click", function() {
        if (personFlag == 1) {
            $(".person_button").stop().animate({
                bottom: 0,
            }, 100, function() {
                personFlag = 0
            })
        } else {
            $(".person_button").stop().animate({
                bottom: 90,
            }, 150, function() {
                personFlag = 1
            })
        }
        if (matchFlag == 1) {
            $(".fast_match").stop().animate({
                bottom: 0,
            }, 100, function() {
                matchFlag = 0
            })
        } else {
            $(".fast_match").stop().animate({
                bottom: 45,
            }, 150, function() {
                matchFlag = 1
            })
        }
    })
    $(".narrow-box").on("click", function() {
        if (personFlag == 1) {
            $(".person_button").stop().animate({
                bottom: 0,
            }, 100, function() {
                personFlag = 0
            })
        } else {
            $(".person_button").stop().animate({
                bottom: 90,
            }, 150, function() {
                personFlag = 1
            })
        }
        if (matchFlag == 1) {
            $(".fast_match").stop().animate({
                bottom: 0,
            }, 100, function() {
                matchFlag = 0
            })
        } else {
            $(".fast_match").stop().animate({
                bottom: 45,
            }, 150, function() {
                matchFlag = 1
            })
        }
    })
})