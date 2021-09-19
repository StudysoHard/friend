<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <!-- 用户信息 start-->
    <div class="user_infromation user_resouce">
        <div class="user_information-top">个人信息</div>
        <form action="" method="POST">
            <div class="user_information-img ">
                <img src="../note/static/image/${user.userHead}" style="width: 80px; height: 80px; border-radius: 5px;">
                <span style="display: inline-block">图片:</span>
                <%-- 修改用户图片--%>
                <input style="display: inline-block;width: 160px"  type="file"  name="file" multiple="multiple">
                <a class="img_promit" href="user?actionName=changeUserImg">确认修改</a>
                <div class="user_title">
                    <span><input type="text" value="标签:"></span>
                </div>
            </div>
        </form>
        <div class="user_basis_information-header">
            基本信息
        </div>
        <form class="user_basis_information" action="" method="post">
            <div class="user_name-box">
                <label for="">昵称</label>
                <input class="user_name" type="text">
            </div>
            <div class="sex-box">
                <label for="">性别</label>
                <input name="sex" type="radio" value="man">男 &nbsp;&nbsp;
                <input name="sex" type="radio" value="woman">女 &nbsp;&nbsp;
            </div>
            <div class="living-box">
                <label for="">居住地</label>
                <input type="text" name="nation"  value="国家">
                <input type="text" name="provinces"  value="省份/地区">
                <input type="text" name="city" value="城市">
            </div>
            <div class="birthday-box">
                <label for="">出生年月</label>
                <select name=""  aria-valuetext="年">
                <option value="">123</option>
            </select>
                <select name=""  aria-valuetext="年">
                <option value="">123</option>
            </select>
                <select name=""  aria-valuetext="年">
                <option value="">123</option>
            </select>
            </div>
            <div class="person_introduction">
                <label for="">个人介绍</label>
                <textarea name="" id="" cols="120" rows="3"></textarea>
            </div>
            <div>
                <button type="submit">保存</button>
            </div>
        </form>
    </div>