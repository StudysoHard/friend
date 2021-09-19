<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>个人页面</title>
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <link rel="stylesheet" href="./static/css/start.css">
    <link rel="stylesheet" href="./static/css/user.infromation.css">
    <link rel="stylesheet" href="./static/css/user.privacy.css">
    <link rel="stylesheet" href="./static/css/user.resouce.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/user.manage.css">
    <link rel="stylesheet" href="./static/css/create_project.css">
    <!-- addition css -->
    <link rel="stylesheet" href="./static/css/style.copy.css">
    <script src="./static/js/util.js"></script>
    <script src="./static/js/jquery.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/person.js"></script>
    <script src="./static/js/config.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/editor_api.js"></script>
    <script type="text/javascript" charset="utf-8" src="./ueditor/lang/zh-cn/zh-cn.js"></script>

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
                        <a href="javascript:;"><img src="user?actionName=userHead&imageName" style="width: 25px;height: 25px; border-radius: 50%;"></a>
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
                        <li class="fl">我加入的</li>
                        <li class="fl">待完成的</li>
                        <li class="fl">我参与的</li>
                    </ul>
                    <ul>
                        <li class="fl">
                            <c:if test="${empty pageCount}">
                                *
                            </c:if>
                            <c:if test="${!empty pageCount}">
                                ${pageCount}
                            </c:if>
                        </li>
                        <li class="fl">
                            <c:if test="${empty finalCount}">
                                *
                            </c:if>
                            <c:if test="${!empty finalCount}">
                                ${finalCount}
                            </c:if>
                        </li>
                        <li class="fl">
                            <c:if test="${empty count}">
                                *
                            </c:if>
                            <c:if test="${!empty count}">
                                ${count}
                            </c:if>
                        </li>
                    </ul>
                    <div class="count-button">
                        <button type="button" class="btn btn-info">创建项目</button>
                        <button type="button" class="btn btn-info">资源搜索</button>
                    </div>
                </div>
                <!-- 统计栏end -->
                <!--  图标栏目 start -->
                <div class="chart fl">
                    <h2>${user.userName}</h2>
                    <img class="fan_diagram" src="user?actionName=userHead&imageName">
                </div>
                <!-- 图标栏 end -->
                <!-- 公告栏 start -->
                <div class="newflash fr" style="margin-top: 19px;">
                    <div class="new-header">
                        <ul>
                            <li class="fl">综合</li>
                            <li class="fl">公告</li>
                            <li class="fl">社区</li>
                            <li class="fl">消息</li>
                        </ul>
                    </div>

                    <div class="move_position">
                        <a id="left" class="carousel-control glyphicon glyphicon-chevron-left" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="sr-only">Previous</span>
                        </a>
                        <a id="right" class="carousel-control glyphicon glyphicon-chevron-right" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

                        <!-- Wrapper for slides -->
                            <c:if test="${empty requestList}">
                                <h style="margin-top: 30px;position: absolute">你目前没有请求</h>
                            </c:if>
                            <c:if test="${!empty requestList}">
                        <div class="carousel-inner" role="listbox" id="submit_style" style="position: relative;height: 135px;margin-top: 50px;">
                            <c:forEach items="${requestList}" var="list">
                                    <div class="item <c:if test="${list.requestId == first}">active</c:if>">
                                        <img src="person?actionName=requestHead&userId=${list.userId}">
                                        <div class="userName">
                                            ${list.userName}
                                        </div>
                                        <div class="userSex">
                                            <c:if test="${list.userSex == 0}">
                                                男
                                            </c:if>
                                            <c:if test="${list.userSex == 1}">
                                                女
                                            </c:if>
                                        </div>
                                        <div class="join_project">
                                            <a href="project?actionName=showresource&projectId=${list.projectId}">
                                                申请的项目
                                            </a>
                                            <a href="javascript:;" data-toggle="modal" data-target="#${list.requestId}">
                                                查看他的申请
                                            </a>
                                            ${list.userIntroduce}
                                        </div>
                                    </div>
                                <%--                                            点击之后的模态弹出框--%>
                                <div class="modal fade" id="${list.requestId}" tabindex="-1" role="dialog" aria-labelledby="showElseContent">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <form action="project" id="write_content">
                                                <input type="hidden" name="actionName" value="intoThisProject">
                                                <input type="hidden" name="projectId" value="${list.projectId}">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h4 class="modal-title" id="myModalLabel">个人简介</h4>
                                                </div>
                                                <div class="modal-body">
                                                        ${list.userIntroduce}
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                                    <input type="hidden" value="aloudTomyProject" name="actionName">
                                                    <input type="hidden" value="${list.userId}">
                                                    <a href="member?actionName=agreeThisPost&projectId=${list.projectId}&userName=${list.userName}" class="btn" style="color: red;border: 1px solid #ccc">同意请求</a>
                                                    <p style="color: red" id="error_msg"></p>
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                </div>

                            </c:forEach>
                                <!-- Indicators -->
                                <ol id="button_ol" class="carousel-indicators" style="position: relative;">
                                    <c:forEach begin="0" end="${count_request-1}" var="num">
                                    <li data-target="#carousel-example-generic" data-slide-to="${num}" <c:if test="${num == 0}">class= "active"</c:if>
                                    ></li>
                                    </c:forEach>
                                </ol>
                        </div>
                            </c:if>
                        </div>

                </div>
                <!-- 公告栏 end -->
            </div>

            <div class="content-button"><span class="change_font glyphicon glyphicon-hand-down real_font"></span></div>
                <div class="contentbar fl">
                    <ul>
                        <li>
                            <span class="glyphicon glyphicon-send" style="transition: all 1s;<c:if test="${active == 0}">color: orange</c:if>"></span>
                            <a href="person?actionName=showMyProject&pageNav=user_manage.jsp&pageNum=1&active=0">我创建的</a>
                        </li>
                        <li>
                            <span style="transition: all 1s;" class="glyphicon glyphicon-search" ></span>
                            <a href="#">我的信息</a>
                        </li>

                        <li>
                            <span style="transition: all 1s;<c:if test="${active == 2}">color: orange</c:if>" class="glyphicon glyphicon-hdd"></span>
                            <a href="person?actionName=projectJoin&pageNav=user_resouce.jsp&pageNum=1&active=2">加入的项目</a>
                        </li>

                        <li>
                            <span style="transition: all 1s;<c:if test="${active == 3}">color: orange</c:if>" class="glyphicon glyphicon-cog"></span>
                            <a href="person?actionName=movePage&pageNav=user_information.jsp&active=3">个人设置</a>
                        </li>
                        <hr style="background-color: #e5e5e5;">
                        <li>
                            <span style="transition: all 1s;<c:if test="${active == 4}">color: orange</c:if>" class="glyphicon glyphicon-plus"></span>
                            <a href="person?actionName=movePage&pageNav=create_project.jsp&active=4">创建项目</a>
                        </li>
                        <li>
                            <span style="transition: all 1s;<c:if test="${active == 5}">color: orange</c:if>" class="glyphicon glyphicon-thumbs-up"></span>
                            <a href="person?actionName=movePage&pageNav=user_privacy.jsp&active=5">关于隐私</a>
                        </li>

                        <li>
                            <span style="transition: all 1s;" class="glyphicon glyphicon-off"></span>
                            <a href="javascript:;" data-toggle="modal" data-target="#deleteUser">注销用户</a>
                        </li>
                    </ul>
                </div>


<%--            确认删除模态弹出框--%>
            <div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        </div>
                        <div class="modal-body">
                            <h2>你确认要注销吗?</h2>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <a href="user?actionName=deleteUser" class="btn" style="color: red;border: 1px solid #ccc">确认</a>
                        </div>
                    </div>
                </div>
            </div>


            <c:if test="${empty changePage}">
                <c:import url="person/user_manage.jsp"></c:import>
            </c:if>
            <c:if test="${!empty changePage}">
                <c:import url="${changePage}"></c:import>
            </c:if>
        </div>
    </section>
    <!--content-->
</body>

</html>