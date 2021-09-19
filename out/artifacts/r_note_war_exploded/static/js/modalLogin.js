window.addEventListener("load",function () {
    const back = document.querySelector('.background');
    const input = document.querySelector('.input');
    const login = document.querySelector('#login');
    const close = document.querySelector('.close');
    const title = document.querySelector('.login_top');
    login.addEventListener('click', function() {
        back.style.display = 'block';
        input.style.display = 'block';
    })
    close.addEventListener('click', function() {
        back.style.display = 'none';
        input.style.display = 'none';
        $("#boolean_p").html("");
    })
    title.addEventListener('mousedown', function(e) {
        const x = e.pageX - input.offsetLeft;
        const y = e.pageY - input.offsetTop;
        document.addEventListener('mousemove', move)

        function move(e) {
            input.style.left = e.pageX - x + 'px';
            input.style.top = e.pageY - y + 'px';
        }
        document.addEventListener('mouseup', function() {
            document.removeEventListener('mousemove', move)
        })
    })
})