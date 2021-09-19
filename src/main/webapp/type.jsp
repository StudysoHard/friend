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
                        <a href="person?actionName=projectJoin&pageNav=user_manage.jsp&pageNum=1">
                            <img src="user?actionName=userHead" class="img-circle user_head" id="user_head">
                        </a>
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
                    <img class="type_img" style="display: inline-block;" src="./typeImg/${typeInformation.typeImg}">
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
                                    <img src="./projectImg/${project.projectImg}" class="img">
                                </div>
                                <div class="word_top">
                                    <div class="project_title">
                                            ${project.projectName}
                                    </div>
                                    <div class="project_content">
                                            ${project.projectHead}
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
                                <div><img src="./userImg/${createPeople.userHead}" alt="" class="create_people_img img-circle"></div>
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
                <c:if test="${page.pageNum > 1}">
                    <a  href="type?actionName=movePage&pageNum=1">
                        <li class="glyphicon glyphicon-backward"></li>
                    </a>
                </c:if>
                <%-- 左侧按钮--%>
                <c:if test="${page.pageNum > 1}">
                    <a  href="type?actionName=movePage&pageNum=${page.prePage}">
                        <li class="glyphicon glyphicon-chevron-left"></li>
                    </a>
                </c:if>
                <%-- ...按钮--%>
                <c:if test="${page.pageNum > 3}">
                    <a>
                        <li class="glyphicon glyphicon-option-horizontal"></li>
                    </a>
                </c:if>
                <%--  中间循环的超链接  --%>
                <c:forEach begin="${page.startNavPage}" end="${page.endNavPage}" var="p">
                    <a href="tpye?actionName=movePage&pageNum=${p}">
                        <li <c:if test="${page.pageNum == p}">style="background-color:rgb(44, 107, 168);color: white"</c:if>>${p}</li>
                    </a>
                </c:forEach>
                <%-- ...按钮--%>
                <c:if test="${page.pageNum < page.totalCount/5 - 2}">
                    <a>
                        <li class="glyphicon glyphicon-option-horizontal"></li>
                    </a>
                </c:if>
                <c:if test="${page.pageNum < page.endNavPage}">
                    <a href="type?actionName=movePage&pageNum=${page.nextPage}">
                        <li class="glyphicon glyphicon-chevron-right"></li>
                    </a>
                </c:if>
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
        </div>
    </footer>
    <!-- 底部模块的制作end -->
</body>

</html>