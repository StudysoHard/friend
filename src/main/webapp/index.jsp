<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" href="./static/css/index.css">
    <link rel="stylesheet" href="./static/css/border_flex.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/fastCommit.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="./static/js/toggleClass.js"></script>
    <script src="./static/js/jquery.js"></script>
    <script src="./static/js/fastCommit.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <title>线上整合平台</title>
</head>

<body>
    <!-- 导航模块 -->
    <nav class="navbar navbar-default" style="margin-bottom: 0;">
        <!-- bootstrap中支持的容器模式 
            1.流模式 默认100%的宽度适应 container-fluid
            2. 居中对齐模式 内容宽度居中 两边留白 container
        -->
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
                <a class="navbar-brand" href="./login.jsp" >更换用户</a>
            </div>
            <!-- 导航主体 -->
            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">首页 <span class="sr-only">(current)</span></a></li>
                    <li><a href="project?actionName=showAllProject&pageNum=1" >搜索</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">发现 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">圈子</a></li>
                            <li><a href="#">标签</a></li>
                            <li><a href="#">库工作</a></li>
                            <li><a href="#ranking_list">排行榜</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">广告投放</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" action="search.jsp">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">搜索关键字</button>
                </form>
                <div class="user_position">
                        <a href="person?actionName=projectJoin&pageNav=user_manage.jsp&pageNum=1">
                                <img src="user?actionName=userHead&imageName" class="img-circle user_head" id="user_head">
                                <div class="user_name">${user.userName}</div>
                        </a>
                </div>
            </div>
            <!-- 导航主体end -->
        </div>
    </nav>
    <!-- 导航模块end -->

    <!-- 右侧的快速匹配 -->
    <div class="button-box">
        <div class="top_box">
            <a>置顶</a>
        </div>
        <div class="person_button">
            <a href="person?actionName=projectJoin&pageNav=user_manage.jsp&pageNum=1">个人</a>
        </div>
        <div class="fast_match">
            <a href="project?actionName=showAllProject&pageNum=1">匹配</a>
        </div>
        <div class="change_box">
            收起
        </div>
    </div>
    <!-- 快速匹配end -->


    <!-- 轮播图 -->
    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <!-- 下方小圆点区域 -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <!-- 轮播图主体区域 -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="./static/image/slider-image1.jpg" alt="...">
                <div class="carousel-caption">
                    <h1>
                        胜利是不会向我们走来的，我们必须自己走向胜利。
                    </h1>
                </div>
            </div>
            <div class="item">
                <img src="./static/image/slider-image2.jpg" alt="...">
                <div class="carousel-caption">
                    <h1>拥有一个健康的体魄将使你的生活更具活力;拥有一个清醒的头脑将使你的人生更具理智;拥有一群朋友将使你的生活更具快乐;拥有一颗爱心将使你的生活更具和谐。
                    </h1>
                </div>
            </div>
            <div class="item">
                <img src="./static/image/slider-image3.jpg" alt="...">
                <div class="carousel-caption">
                    <h1>
                        一个真正的企业家，不能只靠胆大妄为东奔西撞，也不可能是在学院的课堂里说教出来的。他必须在市场经济的大潮中摸爬滚打，在风雨的锤炼中长大。
                    </h1>
                </div>
            </div>
        </div>
        <!-- 轮播图主体区域end -->
        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <!-- 轮播图end -->
    <!-- 内容区域 -->
    <div class="container">
        <!-- 内容区域居中显示 -->
        <div class="row">
            <c:if test="${empty indexType1}">
                <h2 style="color: #b1191a">推荐类型表中没有对应的数据</h2>
            </c:if>
            <c:if test="${!empty indexType1}">
                <h2 style="margin-top: 50px;margin-bottom: 10px;font-size: large;text-align: center">推荐模块</h2>
                <!-- 总共12列 -->
                <!-- 第一列 -->
                <div class="col-lg-6">
                    <c:forEach items="${indexType1}" var="indexType1">
                        <div class="border_flex" style="padding-top: 15px;">
                            <div class="card">
                                <span></span><span></span><span></span><span></span>
                                <div class="content">
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="type?actionName=showAllProject&typeId=${indexType1.typeId}&pageNum=1">
                                                <img class="media-object" src="./typeImg/${indexType1.typeImg}">
                                            </a>
                                        </div>
                                        <div class="media-body main_element">
                                            <h4 class="media-heading"><a href="#">${indexType1.typeName}</a></h4>
                                            ${indexType1.typeHead}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- 第一列end -->
                <!-- 第二列 -->
                <div class="col-lg-6">
                    <c:forEach items="${indexType2}" var="indexType2">
                        <div class="border_flex" style="padding-top: 15px;">
                            <div class="card">
                                <span></span><span></span><span></span><span></span>
                                <div class="content">
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="type?actionName=showAllProject&typeId=${indexType2.typeId}&pageNum=1">
                                                <img class="media-object" src="./typeImg/${indexType2.typeImg}">
                                            </a>
                                        </div>
                                        <div class="media-body main_element">
                                            <h4 class="media-heading"><a href="#">${indexType2.typeName}</a></h4>
                                                ${indexType2.typeHead}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- 第二列end -->
            </c:if>
        </div>

            <div class="row">
            <!-- 推荐项目模块 -->
            <c:if test="${empty indexProject1}">
                <h2 style="color: #b1191a">推荐项目表中没有数据!!!</h2>
            </c:if>
            <c:if test="${!empty indexProject1}">
                    <h2 style="margin-top: 50px;margin-bottom: 30px;font-size: large;text-align: center">推荐项目</h2>
                    <c:forEach items="${indexProject1}" var="indexProject1">
                        <div class="col-lg-3">
                            <a href="project?projectId=${indexProject1.projectId}&actionName=showresource">
                                <div class="thumbnail">
                                    <img src="./projectImg/${indexProject1.projectImg}">
                                    <div class="caption">
                                        <h3>${indexProject1.projectName}</h3>
                                        <p>${indexProject1.projectHead}</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
            <div class="row">
                <c:forEach items="${indexProject2}" var="indexProject2">
                    <div class="col-lg-3">
                        <a href="project?projectId=${indexProject2.projectId}&actionName=showresource">
                            <div class="thumbnail">
                                <img src="./projectImg/${indexProject2.projectImg}" alt="...">
                                <div class="caption">
                                    <h3>${indexProject2.projectName}</h3>
                                    <p>${indexProject2.projectHead}</p>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>
            </div>
            </c:if>
            <!-- 推荐项目模块end -->
            </div>
        </div>
    <!--  内容区域end-->

    <!--  排行榜区域-->
    <div class="container" id="ranking_list">
        <div class="row">
            <div style="margin-left: 530px;">
                <h1>排行榜</h1>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-primary">
                    <!-- Default panel contents -->
                    <div class="panel-heading">最多访问次数的项目</div>
                    <!-- Table -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th class="col-lg-4" style="padding-left: 35px;">排名</th>
                                <th class="col-lg-4">项目名</th>
                                <th class="col-lg-4">访问次数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">1</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information02.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body" style="padding-top: 5px;">
                                            <h4 class="media-heading"> <a href="#">idea</a></h4>
                                            idea模块
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">23</th>
                            </tr>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">2</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information03.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body" style="padding-top: 5px;">
                                            <h4 class="media-heading"> <a href="#">vscode</a></h4>
                                            vscode模块
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">15</th>
                            </tr>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">3</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information04.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body" style="padding-top: 5px;">
                                            <h4 class="media-heading"> <a href="#">navicate</a></h4>
                                            navicate模块
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">10</th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="panel panel-success">
                    <!-- Default panel contents -->
                    <div class="panel-heading">最多创建项目的用户</div>
                    <!-- Table -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th class="col-lg-4" style="padding-left: 35px;">排名</th>
                                <th class="col-lg-4">用户名</th>
                                <th class="col-lg-4">创建次数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">1</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information02.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading" style="padding-top: 22px; padding-left: 5px;"> <a href="#">lisa</a></h4>
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">23</th>
                            </tr>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">2</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information03.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading" style="padding-top: 22px; padding-left: 5px;"> <a href="#">joker</a></h4>
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">15</th>
                            </tr>
                            <tr>
                                <th style="padding-top: 30px; padding-left: 40px;">3</th>
                                <th>
                                    <div class="media">
                                        <div class="media-left">
                                            <a href="#">
                                                <img class="img-circle" src="./static/image/information04.png" alt="...">
                                            </a>
                                        </div>
                                        <div class="media-body">
                                            <h4 class="media-heading" style="padding-top: 22px; padding-left: 5px;"> <a href="#">defe</a></h4>
                                        </div>
                                    </div>
                                </th>
                                <th style="padding-top: 25px; padding-left: 10px;">10</th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- 排行榜区域end -->

    <!-- 相关咨询 -->
    <div class="container">
        <div class="recommend">
            <div class="recommend_head">
                相关资讯
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" style="color: #a94442;margin-left: 50px;">关于创业的十个小知识<span class="label label-info" style="margin-left: 20px;">推荐</span></h3>
                </div>
                <div class="panel-body">
                    <p style="display: inline-block;color: black;">在创业的道路上，犹如万人过独木桥，只有一小部分的人能到达终点。创业艰辛，但还是有成功的机会。想要做个出色的生意人,具备活泛的商业头脑是首要的。我们来教你几招!</p>
                    <div class="move_button">
                        <a href="https://zhuanlan.zhihu.com/p/157512019" style="color: #ffffff;text-decoration: none;">查看详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" style="color: #a94442;margin-left: 50px;">互联网创业如何规避风险<span class="label label-info" style="margin-left: 20px;">推荐</span></h3>
                </div>
                <div class="panel-body">
                    <p style="display: inline-block;color: black;">随着互联网和电子商务的快速发展，大学生互联网创业既面临巨大的机遇又面临一定的挑战。我们一起来看看创业如何规避风险</p>
                    <div class="move_button">
                        <a href="http://www.qncye.com/ruhe/wangluo/082051230.html" style="color: #ffffff;text-decoration: none;">查看详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" style="color: #a94442;margin-left: 50px;">没有创业经验的人应该如何创业？<span class="label label-info" style="margin-left: 20px;">推荐</span></h3>
                </div>
                <div class="panel-body">
                    <p style="display: inline-block;color: black;">很多人都想白手起家创业，也经常有人问没有经验怎么创业，其实白手起家或者没有经验也是有可能创业成功的，关键在于创业者是否能够不断地提升自己。</p>
                    <div class="move_button">
                        <a href="http://www.qncye.com/ruhe/zhidao/081751219.html" style="color: #ffffff;text-decoration: none;">查看详情</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" style="color: #a94442;margin-left: 50px;">创业失败的原因有哪些？<span class="label label-info" style="margin-left: 20px;">推荐</span></h3>
                </div>
                <div class="panel-body">
                    <p style="display: inline-block;color: black;">现在，不管是年轻人还是中年人，都想着自己去创业。今天，我们所处的这个时代，让我们看到了很多创业的机会，那么究竟创业失败的原因有哪些呢?创业者需要注意什么?</p>
                    <div class="move_button">
                        <a href="http://www.qncye.com/gushi/shibai/072751146.html" style="color: #ffffff;text-decoration: none;">查看详情</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 相关咨询end -->

    <!-- 底部模块的制作 -->
    <footer class="footer">
        <div class="w container">
            <div class="mod_help">
                <div class="mod_copyright">
                    <div class="links">
                        关于我们 | 联系我们 | 联系客服 | 广告入驻 | 营销中心 | 手机app | 友情链接  | 交流社区 | 线上公益 | English Site | Contact U
                    </div>
                    <div class="copyright">
                        地址：福建省莆田市城厢区龙桥街道1133号莆田学院三路楼101 邮编：350001 电话：123456789 传真：123456789 邮箱: heyongqiang+qq.con
                        闽ICP备08001421号闽公网安备110108007702
                    </div>
                </div>
            </div>
        </div>
    </footer>
        <!-- 底部模块的制作end -->
</body>

</html>