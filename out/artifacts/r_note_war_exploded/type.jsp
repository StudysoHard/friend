<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./static/css/type.css">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="./static/js/type.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/jquery.js"></script>
    <title>类型页面</title>
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
                <a class="navbar-brand" href="index.jsp" data-toggle="modal">回主页</a>
            </div>
            <!-- 导航主体 -->
            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#"><img src="./static/image/${user.userHead}" class="img-circle user_head" alt="" id="user_head"></a>
                    </li>
                    <li>
                        <div class="user_name">${user.userName}</div>
                    </li>
                </ul>
            </div>
            <!-- 导航主体end -->
        </div>
    </nav>
    <!-- 导航模块end -->

    <!-- 类型简介区域 -->
    <section>
        <div class="container-fluid">
            <div class="container type">
                <!-- 左侧的图片 -->
                <div class="type_img_position">
                    <img class="type_img" style="display: inline-block;" src="./static/image/${typeInformation.typeImg}" alt="">
                </div>
                <!-- 图片end -->
                <div class="type_main_position">
                    <div class="type_main_title">
                        <h2 style="color:white;">${typeInformation.typeName}</h2>
                    </div>
                    <div class="type_main_content">
                        <p style="color:gray;">内容介绍 :</p>
                        <div class="type_content">
                            ${typeInformation.typeHead}
                        </div>
                    </div>
                </div>
                <!-- 类型右侧的盒子 -->
                <!-- 类型右侧end -->
            </div>
        </div>
    </section>
    <!-- 类型简介区域end -->

    <!-- 项目主体区域 -->

    <div class="container type_project_main">
        <div>
            <h2>主要的项目</h2>
        </div>
        <c:if test="${empty projectList}">
            <h2 style="color: #b1191a">该类型数据库中并没有对应的数据!!!</h2>
        </c:if>
        <c:if test="${!empty projectList}">
            <div class="result_main">
                <ul>
                    <c:forEach items="${projectList}" var="project">
                        <li class="project_simple">
                            <div class="project_main">
                                <div class="project_img">
                                    <img src="./static/image/${project.projectImg}" alt="" class="img">
                                </div>
                                <div class="word_top">
                                    <div class="project_title">
                                            ${project.projectHead}
                                    </div>
                                    <div class="project_content">
                                            ${project.projectContent}
                                    </div>
                                </div>
                                <div class="word_button">
                                    <div class="createName">${project.userId}</div>
                                    <div class="projectType">${project.projectType}</div>
                                    <div class="projectPeopleNum">${member.people}</div>
                                </div>
                            </div>
                            <div class="create_people">
                                <div class="csr">创始人</div>
                                <div><img src="./static/image/information01.png" alt="" class="create_people_img img-circle"></div>
                                <div class="create_people_name">${creaetPeople.userName}</div>
                                <div class="create_people_content">${createPeople.userContent}</div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>
    <!-- 项目主体区域end -->

    <!-- 分页按钮 -->
    <div class="container">
        <div class="turn_page">
            <ul class="page_main">
                <li>
                    <a href="#">
                        <span>&laquo;</span>
                    </a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">
                        <span>&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 分页按钮end -->


    <!-- 底部模块的制作 -->
    <footer class="footer">
        <div class="w container">
            <div class="mod_help">
                    <div class="mod_copyright">
                        <div class="links">
                            关于我们 | 联系我们 | 联系客服 | 商家入驻 | 营销中心 | 手机品优购 | 友情链接 | 销售联盟 | 品优购社区 | 品优购公益 | English Site | Contact U
                        </div>
                        <div class="copyright">
                            地址：福建省莆田市城厢区龙桥街道1133号莆田学院三路楼101 邮编：350001 电话：123456789 传真：123456789 邮箱: heyongqiang+qq.con
                            闽ICP备08001421号闽公网安备110108007702
                        </div>
                    </div>
            </div>
            </footer>
    <!-- 底部模块的制作end -->
</body>

</html>