<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" href="./static/css/start.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/member.css">
    <!-- addition css -->
    <link rel="stylesheet" href="./static/css/style.copy.css">
    <link rel="stylesheet" href="./static/css/user.resouce.css">
    <script src="./static/js/jquery.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/person.js"></script>
    <title>Document</title>
</head>

<body style="background: url(./static/image/bg.jpg);">
<!-- 导航 -->
<div class="person-header boxshadowcolor">
    <div class="w">
        <div class="person-header-left fl">
            <ul>
                <li>
                    <a href="index.jsp">志同道和线上交流系统欢迎您</a>
                </li>
            </ul>
        </div>
        <div class="person-header-right fr">
            <ul>
                <li>
                    <a href="">首页 </a>
                </li>
                <li>
                    <a href="">信息 </a>
                </li>
                <li class="user_head">
                    <a href="#"><img src="imag/头像.jpg" alt="" style="width: 25px;height: 25px; border-radius: 50%;"></a>
                    <ul class="white boxshadowcolor user-menu">
                        <li class="user-name ">
                            <div class="user_name">${user.userName}</div>
                        </li>
                        <li class=" glyphicon glyphicon-search">
                            <a href="#">搜索</a>
                        </li>
                        <li class=" glyphicon glyphicon-pencil">
                            <a href="#">修改个人信息</a>
                        </li>
                        <li class="glyphicon glyphicon-folder-close">
                            <a href="#">我的项目</a>
                        </li>
                        <li class="glyphicon glyphicon-off">
                            <a href="#">退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- 导航end -->

<!-- 项目简介区域 -->
<section class="member_head container">
    <div class="container-fluid">
        <div class="container type">
            <!-- 左侧的图片 -->
            <div class="type_img_position">
                <img class="type_img" style="display: inline-block;" src="./static/image/information05.png" alt="">
            </div>
            <!-- 图片end -->
            <div class="type_main_position">
                <div class="type_main_title">
                    <h2 style="color:white;">${project.projectName}</h2>
                </div>
                <div class="type_main_content">
                    <p style="color:gray;">内容介绍 :</p>
                    <div class="type_content">
                        ${project.projectHead}
                    </div>
                </div>
            </div>
            <!-- 类型右侧的盒子 -->
            <!-- 类型右侧end -->
        </div>
    </div>
</section>
<!-- 项目简介区域end -->


<!-- 成员主体 -->
<c:if test="${empty memberPage}">
<div class="container">
    <h2 style="color: red;height: 200px">
        你没有员工!!!!
    </h2>
</div>
</c:if>
<c:if test="${!empty memberPage}">
<div class="result_main container">
    <h2>成员列表</h2>
    <ul>
        <c:forEach var="member" items="${memberPage.dataList}">
            <li class="member_simple">
                <div class="user_img"><img src="./static/image/${member.memberHead}" alt=""></div>
                <div class="user_main">
                    <div class="user_name">
                        <div>名字: ${member.userName}</div>
                    </div>
                    <div class="user_ziwu">
                        <div>权限: ${member.memberAssionment}</div>
                    </div>
                    <div class="user_function">
                        <a href="member?actionName=moveMember&userName=${member.userName}&projectId=${member.projectId}"><span id="move" class="glyphicon glyphicon-minus"></span></a>
                        <a href="member?actionName=upMember&userName=${member.userName}&memberAssionment=${member.memberAssionment}&projectId=${member.projectId}"><span id="upAs" class="glyphicon glyphicon-arrow-up"></span></a>
                        <a href="member?actionName=downMember&userName=${member.userName}&memberAssionment=${member.memberAssionment}&projectId=${member.projectId}"><span id="doAs" class="glyphicon glyphicon-arrow-down"></span></a>
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</c:if>
<!-- 成员主体end -->

<!-- 分页按钮 -->
<div class="change-page container" style="position: relative;top: 30px;height: 50px">
    <ul>
        <c:if test="${memberPage.pageNum > 1}">
            <a  href="member?actionName=PageMove&pageNum=1">
                <li class="glyphicon glyphicon-backward"></li>
            </a>
        </c:if>
        <%--                    左侧按钮--%>
        <c:if test="${memberPage.pageNum > 1}">
            <a  href="member?actionName=PageMove&pageNum=${memberPage.prePage}">
                <li class="glyphicon glyphicon-chevron-left"></li>
            </a>
        </c:if>
        <%-- ...按钮--%>
        <c:if test="${memberPage.pageNum > 3}">
            <a>
                <li class="glyphicon glyphicon-option-horizontal"></li>
            </a>
        </c:if>
        <%--  中间循环的超链接  --%>
        <c:forEach begin="${memberPage.startNavPage}" end="${memberPage.endNavPage}" var="p">
            <a href="member?actionName=PageMove&pageNum=${p}">
                <li <c:if test="${memberPage.pageNum == p}">style="background-color:rgb(44, 107, 168);color: white"</c:if>>${p}</li>
            </a>
        </c:forEach>
        <%-- ...按钮--%>
        <c:if test="${memberPage.pageNum < memberPage.totalCount/5 - 2}">
            <a>
                <li class="glyphicon glyphicon-option-horizontal"></li>
            </a>
        </c:if>
        <c:if test="${memberPage.pageNum < memberPage.endNavPage}">
            <a href="member?actionName=PageMove&pageNum=${memberPage.nextPage}&projectId=${projectId}">
                <li class="glyphicon glyphicon-chevron-right"></li>
            </a>
        </c:if>
    </ul>
</div>
<!-- 分页按钮end -->

<!-- 底部区域 -->
<footer class="footer">
    <div class="w container">
        <div class="mod_help">
            <dl>
                <dt>服务指南</dt>
                <dd><a href="#">购物流程</a></dd>
                <dd><a href="#">会员介绍</a></dd>
                <dd><a href="#">生活旅行/团购</a></dd>
                <dd><a href="#">常见问题</a></dd>
                <dd><a href="#">大家电</a></dd>
                <dd><a href="#">联系客服</a></dd>
            </dl>
            <dl>
                <dt>服务指南</dt>
                <dd><a href="#">上门自提</a></dd>
                <dd><a href="#">211限时达</a></dd>
                <dd><a href="#">配送服务查询</a></dd>
                <dd><a href="#">配送费收取标准</a></dd>
                <dd><a href="#">海外配送</a></dd>
            </dl>
            <dl>
                <dt>服务指南</dt>
                <dd><a href="#">货到付款</a></dd>
                <dd><a href="#">在线支付</a></dd>
                <dd><a href="#">分期付款</a></dd>
                <dd><a href="#">邮局汇款</a></dd>
                <dd><a href="#">公司转账</a></dd>
            </dl>
            <dl>
                <dt>服务指南</dt>
                <dd><a href="#">售后政策</a></dd>
                <dd><a href="#">价格保护</a></dd>
                <dd><a href="#">退款说明</a></dd>
                <dd><a href="#">返修/退换货</a></dd>
                <dd><a href="#">取消订单</a></dd>
            </dl>
            <dl>
                <dt>服务指南</dt>
                <dd><a href="#">夺宝岛</a></dd>
                <dd><a href="#">DIY装机</a></dd>
                <dd><a href="#">延保服务</a></dd>
                <dd><a href="#">品优购E卡</a></dd>
                <dd><a href="#">品优购通信</a></dd>
            </dl>
            <dl>
                <dt>帮助中心
                <dd>
                    <img src="images/wx_cz.jpg" alt="">
                    品优购客户端
                </dd>
            </dl>
        </div>
        <div class="mod_copyright">
            <div class="links">
                关于我们 | 联系我们 | 联系客服 | 商家入驻 | 营销中心 | 手机品优购 | 友情链接 | 销售联盟 | 品优购社区 | 品优购公益 | English Site | Contact U
            </div>
            <div class="copyright">
                地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096 电话：400-618-4000 传真：010-82935100 邮箱: zhanghj+itcast.cn
                京ICP备08001421号京公网安备110108007702
            </div>
        </div>
    </div>
</footer>
<!-- 底部区域end -->
</body>

</html>