<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>搜索模块</title>
    <link rel="stylesheet" href="./static/css/search.css">
    <link rel="stylesheet" href="./static/css/footer.css">
    <link rel="stylesheet" href="./static/css/bootstrap.min.css">
    <script src="static/js/search.js"></script>
    <script src="./static/js/config.js"></script>
    <script src="./static/js/bootstrap.min.js"></script>
    <script src="./static/js/jquery.js"></script>
</head>

<body>
    <!-- 导航栏 -->
    <div class="container-fluid">
        <nav class="navbar navbar-default">
            <a class="return_index" href="index.jsp">回到主站</a>
            <div class="username">${user.userName}</div>
            <div class="userhead"><img class="img-circle" src="./static/image/slider-image1.jpg" alt="" style="width: 30px; height: 30px;"></div>
            <!-- /.navbar-collapse -->
            <!-- /.container-fluid -->
        </nav>
    </div>

    <!-- 导航栏end -->

    <!-- 内容主体 -->
    <!-- 搜索区域 -->
    <div class="container" style="position: relative;z-index: 2">
        <div class="row">
            <div class="serch_top">
                <div class="search_content">
                    <form action="project" id="search_data">
                        <input name="actionName" type="hidden" value="searchLimit">
                        <input name="pageNum" type="hidden" value=1>
                        <ul>
                            <!-- 搜索的主体区域 -->
                            <li class="search_font">
                                <p class="glyphicon glyphicon-chevron-right"></p>
                            </li>
                            <li>
                                <input class="search_name" type="text" name="title" placeholder="${title}" id="search_by_name">
                            </li>
                            <li>
                                <button type="button" class="btn btn-primary search_submit" onclick="checksearch()">提交查询</button>
                            </li>
                            <li class="hidden search_main container-fluid panel panel-danger">
                                <!-- 下拉多选 -->
                                <div class="panel-body">
                                    <div style="font-size: large;position: absolute;margin-left: 30px;margin-top: 50px;">项目开始时间</div>
                                    <div class="begin_time search_button">
                                        <input name="date" id="date" type="hidden">
                                        <div class="year">
                                            <select name="begin_year" id="begin_year">
                                            <option>---请选择年份---</option>
                                            <option>2000</option>
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
                                        </div>
                                        <div class="month">
                                            <select name="begin_month" id="begin_month">
                                                <option>---请选择月份---</option>
                                                <option>12</option>
                                                <option>11</option>
                                                <option>10</option>
                                                <option>09</option>
                                                <option>08</option>
                                                <option>07</option>
                                                <option>06</option>
                                                <option>05</option>
                                                <option>04</option>
                                                <option>03</option>
                                                <option>02</option>
                                                <option>01</option>
                                                </select>
                                        </div>
                                        <div class="day">
                                            <select name="begin_day" id="begin_day">
                                                <option>---请选择几号---</option>
                                                <option>31</option>
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
                                                <option>09</option>
                                                <option>08</option>
                                                <option>07</option>
                                                <option>06</option>
                                                <option>05</option>
                                                <option>04</option>
                                                <option>03</option>
                                                <option>02</option>
                                                <option>01</option>
                                                </select>
                                        </div>
                                    </div>
                                </div>
                                <div style="font-size: large;position: absolute;margin-left: 45px;margin-top: 100px;">所属标签</div>
                                <div class="panel-body">
                                    <div class="search_position">
                                        <ul class="position_ul">
                                            <li><a href="javascript:;" data-code="10">标签1</a></li>
                                            <li><a href="javascript:;" data-code="11">标签2</a></li>
                                            <li><a href="javascript:;" data-code="12">标签3</a></li>
                                            <li><a href="javascript:;" data-code="13">标签4</a></li>
                                            <li><a href="javascript:;" data-code="14">标签5</a></li>
                                            <li><a href="javascript:;" data-code="15">标签6</a></li>
                                            <li><a href="javascript:;" data-code="16">标签7</a></li>
                                            <li><a href="javascript:;" data-code="17">标签8</a></li>
                                            <li><a href="javascript:;" data-code="18">标签9</a></li>
                                            <li><a href="javascript:;" data-code="19">标签10</a></li>
                                            <li><a href="javascript:;" data-code="20">标签11</a></li>
                                            <li><a href="javascript:;" data-code="21">标签12</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <div style="font-size: large;position: absolute;margin-left: 45px;margin-top: 60px;">人员数量</div>
                                <div class="panel-body search_people">
                                    <span>小型项目(<5)</span>
                                    <input type="radio" name="peopleNumber" value="small">
                                    <span>中等项目(<20)</span>
                                    <input type="radio" name="peopleNumber" value="middle">
                                    <span>大型项目(<100)</span>
                                    <input type="radio" name="peopleNumber" value="big">
                                    <span>超大型项目(>100)</span>
                                    <input type="radio" name="peopleNumber" value="largest">
                                </div>
                                <div class="panel-body search_sex">
                                    <div style="font-size: large;position: absolute;margin-left: -45px">创建者性别</div>
                                    <span>女</span>
                                    <input type="radio" name="createSex" value="0">
                                    <span>男</span>
                                    <input type="radio" name="createSex" value="1">
                                </div>
                                <div style="font-size: large;position: absolute;margin-left: 45px;margin-top: 100px;">你的学历</div>
                                <div class="panel-body">
                                    <div class="study_position">
                                        <input name="study" type="hidden" id="study">
                                        <select name="lastStudy" id="lastStudy">
                                            <option>小学</option>
                                            <option>中学</option>
                                            <option>高中</option>
                                            <option>专科</option>
                                            <option>本科</option>
                                            <option>硕士</option>
                                            <option>博士</option>
                                        </select>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </form>
                </div>
                <div class="search_font"></div>
            </div>
        </div>
    </div>
    <!-- 搜索区域end -->


    <!-- 内容区域 -->
    <div class="container" style="position: relative;">
        <c:if test="${empty page}">
            <h2>抱歉数据库中没有数据</h2>
        </c:if>
        <c:if test="${!empty page}">
        <div class="result_main">
            <ul>
                <c:forEach items="${page.dataList}" var="project">
                    <li class="project_simple">
                        <div class="project_main">
                            <div class="project_img">
                                <a href="project?projectId=${project.projectId}&actionName=showresource">
                                    <img src="./static/image/information03.png" alt="" class="img">
                                </a>
                            </div>
                            <div class="word_top">
                                <div class="project_title">
                                        ${project.projectName}
                                </div>
                                <div class="project_head">
                                        ${project.projectHead}
                                </div>
                            </div>
                            <div class="word_button">
                                <div class="createName">${project.userId}</div>
                                <div class="projectType">${project.projectType}</div>
                                <div class="projectPeopleNum">人员数量</div>
                            </div>
                        </div>
                        <div class="create_people">
                            <div class="csr">创始人</div>
                            <div><img src="./static/image/information01.png" alt="" class="create_people_img img-circle"></div>
                            <div class="create_people_name">创始人名字</div>
                            <div class="create_people_content">创始人简介</div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <!-- 分页按钮 -->
    <div class="container">
        <div class="turn_page">
            <ul class="page_main">
                <c:if test="${page.pageNum > 1}">
                    <a  href="project?actionName=movePage&pageNum=1">
                        <li class="glyphicon glyphicon-backward"></li>
                    </a>
                </c:if>
<%--                    左侧按钮--%>
                    <c:if test="${page.pageNum > 1}">
                        <a  href="project?actionName=movePage&pageNum=${page.prePage}">
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
                    <a href="project?actionName=movePage&pageNum=${p}">
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
                        <a href="project?actionName=movePage&pageNum=${page.nextPage}">
                            <li class="glyphicon glyphicon-chevron-right"></li>
                        </a>
                    </c:if>
            </ul>
        </div>
    </div>
    </c:if>

    <!-- 分页按钮end -->
    <!-- 内容主体end -->

    <!-- 右侧的快速匹配 -->
    <button class="fast_style toggele_button btn" style="position: fixed;left: 20vh;top: 80vh;z-index: 2;">快速匹配</button>
    <!-- 快速匹配end -->


    <!-- 底部区域 -->
    <div class="container-fluid">
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
    </div>
    <!-- 底部区域end-->
</body>

</html>