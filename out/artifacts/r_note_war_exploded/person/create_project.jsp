<!-- 页面头部 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!-- 下方主体区域 -->
    <form action="project?actionName=createProject" method="post" enctype="multipart/form-data" id="createProject">
        <div class="user_resouce">
            <ul>
                <li>
                    <span></span>
                    <p class="text_projectName">项目名:</p>
                    <input type="text" name="projectName" id="projectName">
                </li>
                <li>
                    <span></span>
                    <input type="hidden" name="projectType_real" id="projectType_real">
                    <p class="text_projectType">项目标签:</p>
                    <select name="projectType" id="projectType">
                        <option>前端</option>
                        <option>后端</option>
                        <option>嵌入式</option>
                        <option>移动端开发</option>
                        <option>运维</option>
                    </select>
                </li>
                <li>
                    <span></span>
                    <p class="text_projectHead">项目简介:</p>
                    <textarea name="projectHead" id="projectHead"></textarea>
                </li>
                <li>
                    <span></span>
                    <p class="text_projectContent">项目内容:</p>
                    <textarea id="projectContent" name="projectContent"  style="width:700px;height:200px;margin-left: 60px"></textarea>
                    <input type="hidden" name="projectContent_real" id="projectContent_real">
                </li>
                <li>
                    <span></span>
                    <p class="text_projectImg">项目图片:</p>
                    <input input type="file" name="myFile" multiple>
                </li>
            </ul>
            <input style="margin-left: 46%;margin-top: 100px;width: 100px;" type="button" onclick="checkCreate()" value="提交">
            <p id="show_msg" style="color: red;font-size: large">${resultset.msg}</p>
        </div>
    </form>
<script type="text/javascript">
    const ue =  UE.getEditor('projectContent');
</script>