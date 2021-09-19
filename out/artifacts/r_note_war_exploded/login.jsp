<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入login.css -->
    <link rel="stylesheet" href="./static/css/login.css">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="static/js/jquery.js"></script>
    <script src="static/js/util.js"></script>
    <script src="./static/js/config.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <title>个人登录</title>
</head>
<div class="container">
    <div class="registerarea">
        <h3 style="position: relative;margin-top: 10px;" class="back"><a href="./index.jsp">回到主站</a></h3>
        <h3 style="position: relative;margin-top: 10px;" class="back"><a href="./register.jsp">去注册</a></h3>
        <h3>登录</h3>
        <div class="reg_form">
            <form action="user" method="post" id="login_data">
                <ul>
                    <%--                    隐藏域区分用户是登录 标识为login--%>
                    <input type="hidden" name="actionName" value="login">
                    <li><label>用户名:</label> <input type="text" class="inp" name="username" id="user_name" value="${resultInfo.getResult().getUserName()}">
                        <span id="boolean_span_username" style="color: #b1191a">
                            </span></li>
                    <li><label>登录密码:</label> <input type="password" class="inp" name="userpwd" id="user_pwd" value="${resultInfo.getResult().getUserPwd()}">
                        <span id="boolean_span_pwd" style="color: #b1191a">
                            </span></li>
                        <li class="agree" style="margin-left: 50px">
                            <input type="checkbox" id="rem" name="rem"> <P style="display: inline-block;">记住我</P>
                        </li>
                    <li class="agree">
                        <input type="checkbox" checked> <p style="display: inline-block">同意协议并登录</p><a href="#">《知晓用户协议》</a>
                    </li>
                    <li style="position: absolute;margin-left: 122px">
                        <span id="msg" style="color: red;width: 50px;height: 50px">${resultInfo.msg}</span>
                    </li>
                    <li>
                        <input type="button" value="完成登录" class="btn" onclick="checklogin()">
                    </li>
                </ul>
            </form>
        </div>
    </div>

    <div class="mod_copyright">
        <div class="links">
            关于我们 | 联系我们 | 联系客服 | 商家入驻 | English Site | Contact U
        </div>
        <div class="copyright">
            地址：莆田学院 邮编：350001 电话：123456 传真：789456 邮箱: nihaoxian.com 闽ICP备08001421号闽公网安备110108007702
        </div>

    </div>
</div>

<body>

</body>

</html>