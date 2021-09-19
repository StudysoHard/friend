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
                        <img src="./static/image/head01.png" class="img-circle user_head" id="user_head">
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
                <c:if test="${assionment < 1 || project.userId != user.userId}">
                    <h2 style="color: red;margin: 50px">抱歉你没有对应的权限</h2>
                </c:if>
                <c:if test="${assionment >= 1 || project.userId == user.userId}">
                    <div class="container">
                        <div class="filedList">
                            <c:if test="${empty resourceList}">
                                <h3>你没有存数据到数据库中!!</h3>
                                <hr>
                            </c:if>
                            <c:if test="${!empty resourceList}">
                                <ul>
                                    <c:forEach var="resource" items="${resourceList}">
                                    <a href="upload?fileName=${resource.resourceName}&projectId=${project.projectId}">
                                        <li class="simple_resource">
                                            <div class="fildFont glyphicon glyphicon-save-file"></div>
                                            <div class="fildName">${resource.resourceName}</div>
                                            <div class="fildUserName">${resource.userId}</div>
                                            <div class="fildTime">${resource.postTime}</div>
                                            <div class="clickNum">${resource.uploadNum}</div>
                                        </li>
                                    </a>
                                    </c:forEach>
                                </ul>
                            </c:if>
                        </div>
                        <div class="text_main">
                                <p id="projectContent"></p>
                                ${project.projectContent}
                        </div>
                    </div>
                </c:if>


                <!-- 主体区域end -->
            </div>
            <!-- 内容区域 -->

            <div class="subnav">
                <div class="sub_index">
                    <ul class="sub_top">
                        <a href="javascript:;"  data-toggle="modal" data-target="#writeMycontent">
                            <li class="glyphicon glyphicon-envelope"></li>
                            <p>去投递</p>
                        </a>
                        <a>
                            <li class="glyphicon glyphicon-heart"></li>
                            <p>沟通过</p>
                        </a>
                        <a href="#">
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
                        <a href="#">
                            <li>微信</li>
                        </a>
                    </ul>
                </div>
            </div>

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
    <!--右侧盒子区域 -->
    <div class="right_box">

    </div>
    <!-- 右侧区域end -->

    <%--  相关创业咨询  --%>


    <%--相关咨询--%>

    <!--页脚部分  -->
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
    <!-- 页脚部分end -->

<script>
    $(function () {
        $("#projectContent").add(${project.projectContent});
    })
</script>

</body>

</html>