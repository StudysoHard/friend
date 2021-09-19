<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册成功</title>
</head>
<body>
    <h1>恭喜注册成功!!!</h1>
    <button>点击跳转到登录页面</button>
    <script>
        var btn = document.querySelector('button');
        btn.addEventListener('click', function() {
            let time = setInterval(function() {
                location.href = 'login.jsp';
            }, 2000)
        })
    </script>
</body>
</html>