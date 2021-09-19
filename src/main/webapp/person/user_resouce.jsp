<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="user_manage" style="position: relative">
    <div class="user_manage-header">
        <div class="fl" style="margin-left: 100px;font-size: larger;letter-spacing: 20px">共<span style="font-size: xx-large;color: red">${pageCount}</span>个项目</div>
    </div>
    <c:if test="${empty personJoined}">
        <h2 class="user_manage-content" style="color: red">该用户没有加入的项目!!!</h2>
    </c:if>
    <c:if test="${!empty personJoined}">
        <c:forEach items="${personJoined.dataList}" var="project">
            <div class="user_manage-content">
                <div class="user_manage-content-hd">
                    <span class="fl">${project.projectName}</span>
                    <div class="fr" style="margin-right: 20px">发布于${project.createTime}</div>
                </div>
                <div class="project_introduce">${project.projectHead}</div>
                <div class="fl">
                    参与人数12人
                </div>
                <div class="fr" style="margin-right: 20px">
                    <ul class="button-box">
                        <li class="fl">
                            <a href="project?projectId=${project.projectId}&actionName=showresource" style="width: 130px">
                                <span class="glyphicon glyphicon-search"></span> 去项目
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <div class="change-page" style="position: absolute;left: 260px;top: 590px">
        <ul>
            <c:if test="${personJoined.pageNum > 1}">
                <a  href="person?actionName=joinMove&pageNum=1&pageNav=user_resource.jsp&changePage=user_resouce.jsp">
                    <li class="glyphicon glyphicon-backward"></li>
                </a>
            </c:if>
            <%--                    左侧按钮--%>
            <c:if test="${personJoined.pageNum > 1}">
                <a  href="person?actionName=joinMove&pageNum=${personCreatePage.prePage}&pageNav=user_resouce.jsp&changePage=user_resource.jsp">
                    <li class="glyphicon glyphicon-chevron-left"></li>
                </a>
            </c:if>
            <%-- ...按钮--%>
            <c:if test="${personJoined.pageNum > 3}">
                <a>
                    <li class="glyphicon glyphicon-option-horizontal"></li>
                </a>
            </c:if>
            <%--  中间循环的超链接  --%>
            <c:forEach begin="${personJoined.startNavPage}" end="${personJoined.endNavPage}" var="p">
                <a href="person?actionName=joinMove&pageNum=${p}&pageNav=user_resouce.jsp&changePage=user_resource.jsp">
                    <li <c:if test="${personJoined.pageNum == p}">style="background-color:rgb(44, 107, 168);color: white"</c:if>>${p}</li>
                </a>
            </c:forEach>
            <%-- ...按钮--%>
            <c:if test="${personJoined.pageNum < personJoined.totalCount/5 - 2}">
                <a>
                    <li class="glyphicon glyphicon-option-horizontal"></li>
                </a>
            </c:if>
            <c:if test="${personJoined.pageNum < personJoined.endNavPage}">
                <a href="person?actionName=joinMove&pageNum=${personJoined.nextPage}&pageNav=user_resouce.jsp&changePage=user_resource.jsp">
                    <li class="glyphicon glyphicon-chevron-right"></li>
                </a>
            </c:if>
        </ul>
    </div>
    <div style="position: absolute;top: 800px;"></div>
</section>
