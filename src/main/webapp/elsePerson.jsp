<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人页面</title>
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" href="./static/css/start.css">
    <link rel="stylesheet" href="./static/css/user.infromation.css">
    <link rel="stylesheet" href="./static/css/user.privacy.css">
    <link rel="stylesheet" href="./static/css/user.resouce.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/user.manage.css">
    <!-- addition css -->
    <link rel="stylesheet" href="./static/css/style.copy.css">
    <script src="./static/js/jquery.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/person.js"></script>
</head>


<body>
<!--header start-->
<div class="person-header boxshadowcolor">
    <div class="person-header-left fl">
        <ul>
            <li>
                <a href="javascript:;">志同道和线上交流系统欢迎您</a>
            </li>
        </ul>
    </div>
    <div class="person-header-right fr">
        <ul>
            <li>
                <a href="index.jsp">首页</a>
            </li>
            <li>
                <a href="">信息</a>
            </li>
            <li class="user_head">
                <a href="#"><img src="./static/image/person_simple.jpg" alt="" style="width: 25px;height: 25px; border-radius: 50%;"></a>
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
<!--header end-->
<!--content start-->
<section class="clearfix">
    <div class="content-box w" style="margin-top: 50px">
        <div class="w content-header">
            <!-- 统计栏 start -->
            <div class="count fl">
                <h2>资源总览</h2>
                <ul>
                    <li class="fl">项目总数</li>
                    <li class="fl">待完成的</li>
                    <li class="fl">我参与的</li>
                </ul>
                <ul>
                    <li class="fl">0</li>
                    <li class="fl">0</li>
                    <li class="fl">0</li>
                </ul>
                <div class="count-button">
                    <button type="button" class="btn btn-info">创建项目</button>
                    <button type="button" class="btn btn-info">资源搜索</button>
                </div>
            </div>
            <!-- 统计栏end -->
            <!--  图标栏目 start -->
            <div class="chart fl">
                <h2>图表分析</h2>
                <img class="fan_diagram" src="./static/image/project01.png" alt="">
            </div>
            <!-- 图标栏 end -->
            <!-- 公告栏 start -->
            <div class="newflash fr">
                <div class="new-header">
                    <ul>
                        <li class="fl">综合</li>
                        <li class="fl">公告</li>
                        <li class="fl">社区</li>
                        <li class="fl">消息</li>
                    </ul>
                </div>
            </div>
            <!-- 公告栏 end -->
        </div>
        <div class="content-button"><span class="change_font glyphicon glyphicon-hand-down real_font"></span></div>
    </div>
</section>
<!--content-->
</body>

</html>