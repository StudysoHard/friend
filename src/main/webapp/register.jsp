<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入register.css -->
    <link rel="stylesheet" href="./static/css/register.css">
    <!-- 引入初始化 -->
    <link rel="stylesheet" href="./static/css/base.css">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="static/js/jquery.js"></script>
    <script src="static/js/config.js"></script>
    <script src="static/js/util.js"></script>
    <title>个人注册</title>
</head>
<div class="container">
    <div class="registerarea">
        <h3>注册新用户</h3>
        <a href="./login.jsp" style="color: rgb(51, 51, 51)"><h3>去登录</h3></a>
        <div class="container">
            <div class="reg_form">
                <form action="/note/user" method="post" id="register_data">
                    <ul>
                        <%--                    隐藏域区分用户是登录 标识为login--%>
                        <input type="hidden" name="actionName" value="register">
                        <li><label>手机号:</label> <input type="text" class="inp" name="usertelephone" id="user_telephone" value="${resultInfo.result.getUserTelephone()}">
                            <span id="boolean_span_telephone" style="color: #b1191a">
                        </span></li>
                        <li><label>用户名:</label> <input type="text" class="inp" name="username" id="user_name" value="${resultInfo.result.getUserName()}">
                            <span id="boolean_span_username" style="color: #b1191a">
                            </span></li>
                        <li><label>登录密码:</label> <input type="password" class="inp" name="userpwd" id="user_pwd" value="${resultInfo.result.getUserPwd()}">
                            <span id="boolean_span_pwd" style="color: #b1191a">
                            </span></li>
                        <li><label style="width: 100px;margin-left: -10px;">确认登录密码:</label> <input type="password" class="inp" name="userpwd" id="user_pwd_twice">
                            <span id="boolean_span_pwd_twice" style="color: #b1191a">
                            </span></li>
                        <li>
                            <label>真实姓名:</label> <input type="text" class="inp" name="userrealname" id="user_realName" value="${resultInfo.result.getUserRealName()}">
                        </li>
                        <li class="sex">
                            <label>性别:</label>
                            <span class="man">男</span><input type="radio" class="inp" name="usersex" id="user_sex_man" value="0">
                            <span class="woman">女</span><input type="radio" class="inp" name="usersex" id="user_sex_woman" value="1">
                        </li>
                        <li>
                            <label>生日:</label>
                            <input class="hidden" name="birthday" id="birthday">
                            <select name="year" style="margin-left: 30px" id="year">
                                <option selected>2000</option>
                                <option>2001</option>
                                <option>2002</option>
                                <option>2003</option>
                                <option>2004</option>
                                <option>2005</option>
                                <option>2006</option>
                                <option>2007</option>
                                <option>2008</option>
                                <option>2009</option>
                                <option>2010</option>
                                <option>2011</option>
                                <option>2012</option>
                                <option>2013</option>
                                <option>2014</option>
                                <option>2015</option>
                                <option>2016</option>
                                <option>2017</option>
                                <option>2018</option>
                                <option>2019</option>
                                <option>2020</option>
                                <option>2021</option>
                            </select>
                            <select name="month" style="margin-left: 30px" id="month">
                                <option selected>12</option>
                                <option>11</option>
                                <option>10</option>
                                <option>9</option>
                                <option>8</option>
                                <option>8</option>
                                <option>6</option>
                                <option>5</option>
                                <option>4</option>
                                <option>3</option>
                                <option>2</option>
                                <option>1</option>
                            </select>
                            <select name="day" style="margin-left: 30px" id="day">
                                <option selected>31</option>
                                <option>30</option>
                                <option>29</option>
                                <option>28</option>
                                <option>27</option>
                                <option>26</option>
                                <option>25</option>
                                <option>24</option>
                                <option>23</option>
                                <option>22</option>
                                <option>21</option>
                                <option>20</option>
                                <option>19</option>
                                <option>18</option>
                                <option>17</option>
                                <option>16</option>
                                <option>15</option>
                                <option>14</option>
                                <option>13</option>
                                <option>12</option>
                                <option>11</option>
                                <option>10</option>
                                <option>9</option>
                                <option>8</option>
                                <option>7</option>
                                <option>6</option>
                                <option>5</option>
                                <option>4</option>
                                <option>3</option>
                                <option>2</option>
                                <option>1</option>
                            </select>
                            <span style="margin-left: 20px;color: #b1191a" id="boolean_span_birthday"></span>
                        </li>
                            <li class="container">
                                <label style="width: 130px;height: 20px;margin-right: 50px">请选择你的最高学历</label>
                                <select name="userlargestStudy" style="margin-top: 20px" id="study">
                                <option selected>小学</option>
                                <option>中学</option>
                                <option>大学</option>
                                <option>研究生</option>
                                <option>小学</option>
                                <option>博士</option>
                                </select>
                            </li>
                        <li class="agree">
                            <input type="checkbox" style="margin-bottom: 5px;margin-right: 10px;width: 18px;height: 18px"> 同意协议并注册 <a href="#">《知晓用户协议》</a>
                        </li>
                        <li>
                            <span id="msg" style="color: red;width: 50px;height: 50px">${resultInfo.msg}</span>
                        </li>
                        <li>
                            <input type="button" value="完成注册" class="btn" onclick="checkRegister()">
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="mod_copyright">
            <div class="links">
                关于我们 | 联系我们 | 联系客服 | 商家入驻 | English Site | Contact U
            </div>
            <div class="copyright">
                地址：莆田学院 邮编：350001 电话：123456 传真：789456 邮箱: nihaoxian.com 闽ICP备08001421号闽公网安备110108007702
            </div>

        </div>
    </div>
</div>

<body>

</body>

</html>