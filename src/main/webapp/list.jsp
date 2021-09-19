<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" href="./static/css/border_flex.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/list.css">
    <script src="./static/js/jquery.js"></script>
    <script src="./static/js/list.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/config.js"></script>
    <title>项目详情页面</title>
</head>

<body>

    <!--项目详情页面  -->
    <!--导航栏  -->
    <div class="nav">
        <div class="container top_style">
            <div class="back_index"><a href="index.jsp">回到主站</a></div>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control search_top_text" placeholder="请输入要搜索的内容">
                </div>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
            <div class="nav navbar-nav" style="height: 50px">
                    <a href="person?actionName=projectJoin&pageNav=user_resouce.jsp&pageNum=1">
                        <img src="user?actionName=userHead&imageName" class="img-circle user_head" id="user_head">
                        <div class="user_name">${user.userName}</div>
                    </a>
            </div>
        </div>
    </div>
    <!-- 导航栏end -->
    <!-- 主要盒子 -->
    <div class="main_box container">
        <div class="row">
            <!-- 左边栏区域 -->
<%--            <div class="left_box  col-md-2">--%>
<%--                <div class="create_person">--%>
<%--                    <h1>创始人</h1>--%>
<%--                    <ul class="media-list">--%>
<%--                        <li class="media">--%>
<%--                            <div class="media-left">--%>
<%--                                <a href="#">--%>
<%--                                    <img class="media-object img-circle" src="./static/image/information01.png" alt="...">--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                            <div class="media-body">--%>
<%--                                <h4 class="media-heading create_user_name">create_user_name</h4>--%>
<%--                                <div class="create_user_content">不要羡慕我我只是一个传说</div>--%>
<%--                            </div>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--                <div class="log_list">--%>
<%--                </div>--%>
<%--            </div>--%>
            <!-- 左边栏区域end -->
            <!--内容区域 -->
            <div class="main col-md-7">
                <!-- 标题区域 -->
                <div class="container">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h1 class="text_title">${project.projectName}</h1>
                            <div class="row text_information">
                                <div class="project_type col-md-3">
                                    <button type="button" class="btn btn-info">
                                        ${project.projectType}
                                    </button>
                                </div>
                                <div class="writer col-md-offset-0 col-md-1"><a href="user?actionName=IntoElsePerosn&userId=${project.userId}">${createUser.userName}</a></div>
                                <div class="time_visited col-md-offset-3 col-md-2 ">
                                    <span class="glyphicon glyphicon-eye-open eye"></span>
                                    ${project.visitNumber}</div>
                                <div class="time_create col-md-offset-10">${project.createTime}</div>
                            </div>
                        </div>
                        <!-- 简介 -->
                        <div class="panel-footer">
                            <div class="text_small">

                            </div>
                        </div>
                        <!-- 简介end -->
                    </div>
                </div>
                <!-- 标题区域end -->


                <div class="container">
                    <div class="head_position">
                        <h2>项目的简介:</h2>
                        <hr>
                        <div class="project_head">
                            <c:if test="${empty project.projectHead}">
                                <p>这个组长很懒没有写简介</p>
                            </c:if>
                            <c:if test="${!empty project.projectHead}">
                                ${project.projectHead}
                            </c:if>
                        </div>
                    </div>
                </div>
                <!-- 主体区域 -->
                <c:if test="${assionment < 1 && project.userId != user.userId}">
                    <h2 style="color: red;margin: 50px">抱歉你没有对应的权限</h2>
                </c:if>
                <c:if test="${assionment >= 1 || project.userId == user.userId}">
                    <div class="container">
                        <div class="filedList">
                            <c:if test="${empty resourceList}">
                                <h3 style="margin-left: 350px;margin-top: 68px">你没有存数据到数据库中!!</h3>
                                <hr>
                            </c:if>
                            <c:if test="${!empty resourceList}">
                                <ul>
                                    <c:forEach var="resource" items="${resourceList}">
                                    <a href="download?fileName=${resource.resourceName}&projectId=${project.projectId}">
                                        <li class="simple_resource">
                                            <div class="fildFont glyphicon glyphicon-save-file"></div>
                                            <div class="fildName">${resource.resourceName}</div>
                                            <div class="fildUserName">${resource.userId}</div>
                                            <div class="fildTime">${resource.postTime}</div>
                                            <div class="clickNum">${resource.uploadNum}</div>
                                        </li>
                                    </a>
                                        <a data-toggle="modal" data-target="#${resource.docId}">
                                          <span class="glyphicon glyphicon-minus"></span>
                                        </a>


                                        <%--删除资源--%>
                                        <div class="modal fade" id="${resource.docId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <h4>你确定要删除这个资源吗?</h4>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                        <a style="border: 1px solid #ccc" type="button" href="project?actionName=deleteResource&docId=${resource.docId}&resourceName=${resource.resourceName}&projectId=${project.projectId}">确定</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                        <div class="text_main">
                                <p id="projectContent"></p>
                                ${project.projectContent}
                        </div>
                        <div class="map_img">
                                <img src="province/${project.province}.png">
                        </div>
                    </div>
                </c:if>


                <!-- 主体区域end -->
            </div>
            <!-- 内容区域 -->

            <div class="subnav">
                <div class="sub_index">
                    <ul class="sub_top">
                        <c:if test="${project.userId != user.userId}">
                        <a href="javascript:;"  data-toggle="modal" data-target="#writeMycontent">
                            <li class="glyphicon glyphicon-envelope"></li>
                                <p>去投递</p>
                        </a>
                        </c:if>

                        <c:if test="${project.userId == user.userId}">
                            <a href="javascript:;"  data-toggle="modal" data-target="#dontJoin">
                                <li class="glyphicon glyphicon-envelope"></li>
                                <p>去投递</p>
                            </a>
                        </c:if>
                        <a href="javascript:;"  data-toggle="modal" data-target="#postResource">
                            <li class="glyphicon glyphicon-envelope"></li>
                            <p>去上传</p>
                        </a>
                        <a href="javascript:;">
                            <li class="glyphicon glyphicon-time"></li>
                            <p>已投递</p>
                        </a>
                    </ul>
                    <ul class="sub_button">
                        <a href="#">
                            <li class="glyphicon glyphicon-chevron-up"></li>
                        </a>
                        <a href="#">
                            <li>反馈</li>
                        </a>
                        <a href="javascript:;" data-toggle="modal" data-target="#closedProject">
                            <li>结束项目</li>
                        </a>
                    </ul>
                </div>
            </div>



            <%--上传的模态--%>
            <div class="modal fade" id="postResource" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="project?actionName=postResource&projectId=${project.projectId}" method="post" enctype="multipart/form-data" id="postMain" >
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <input input type="file" name="myFile" multiple>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <input style="border: 1px solid #ccc" type="button" onclick="checkPostResource()" value="确认">
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <%--结束的模态--%>
                <div class="modal fade" id="closedProject" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            </div>
                            <div class="modal-body">
                                <h2>你确认结束这个项目吗?</h2>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <a href="project?actionName=deleteProject&projectId=${project.projectId}" class="btn" style="color: red;border: 1px solid #ccc">确认</a>
                            </div>
                        </div>
                    </div>
                </div>

            <%--提示的模态--%>
            <div class="modal fade" id="dontJoin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <h2>你是创建者不可以加入这个项目!!!</h2>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
            <%--加入的模态--%>
            <div class="modal fade" id="writeMycontent" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <form action="project" id="write_content">
                            <input type="hidden" name="actionName" value="intoThisProject">
                            <input type="hidden" name="projectId" value="${project.projectId}">
                            <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">个人简介</h4>
                        </div>
                            <div class="modal-body">
                                <textarea name="content" style="margin-left: 100px;width: 220px;height: 67px" id="content"></textarea>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <input type="button" class="btn" onclick="checkContent()" value="发送请求">
                                <p style="color: red" id="error_msg"></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 主要盒子end -->

    <%--  相关创业咨询  --%>


    <%--相关咨询--%>

    <!--页脚部分  -->
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
    </footer>
    <!-- 页脚部分end -->

<script>
    $(function () {
        $("#projectContent").add(${project.projectContent});
    })
</script>

</body>

</html>