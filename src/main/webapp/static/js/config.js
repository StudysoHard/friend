/**
 * 检查表单的用户信息是否正确
 *
 */
function checklogin() {
    const userName = $("#user_name").val();
    const userPwd = $("#user_pwd").val();
    const rem = $("#rem");
    if(rem.prop("checked") == true){
        rem.val(1);
    }
    if(isempty(userName)){
        $("#boolean_span_username").html("不能输入空的用户名!!");
        return;
    }
    if(isempty(userPwd)){
        $("#boolean_span_pwd").html("不能输入空的密码!!");
        return;
    }
    //不为空就提交表单
    $("#login_data").submit();
}

/**
 *  注册页面
 */
function checkRegister() {
    const userTelephone = $("#user_telephone").val();
    const userName = $("#user_name").val();
    const userPwd = $("#user_pwd").val();
    const userPwdT = $("#user_pwd_twice").val();
    const year = $("#year").children("option:selected").val();
    const month = $("#month").children("option:selected").val();
    const day = $("#day").children("option:selected").val();
    const lastStudy = $("#lagestStudy").children("option:selected").val();
    $("#birthday").val(year+"-"+month+"-"+day);
    if(isempty(userTelephone)){
        //如果为空就提示用户
        $("#boolean_span_telephone").html("用户电话不能为空");
        return ;
    }
    if(isempty(userName)){
        //如果为空就提示用户
        $("#boolean_span_username").html("用户名不能为空");
        return ;
    }
    if(isempty(userPwd)){
        //如果为空就提示用户
        $("#boolean_span_pwd").html("用户密码不能为空");
        return ;
    }
    if(userPwdT != userPwd){
        $("#boolean_span_pwd_twice").html("两次输入密码不一样");
        return;
    }

    //不为空就提交表单
    $("#register_data").submit();
}

/**
 * 检查查询
 */

function checksearch(){
    const year = $("#begin_year").children("option:selected").val();
    const month = $("#begin_month").children("option:selected").val();
    const day = $("#begin_day").children("option:selected").val();
    // const study = $("#lastStudy").children("option:selected").val();
    let where = $(".position_ul_where").find(".blue").attr("data-code-where");
    let type = $(".position_ul_type").find(".blue").attr("data-code-type");
    // $("#study").val(study);
    $("#date").val(year+"-"+month+"-"+day);
    if(where == 0){
        where = null;
    }
    if(type == 0){
        type = null;
    }
    $("#where").val(where)
    $("#type").val(type)
    $("#search_data").submit();
}

/**
 * 创建项目
 */
function checkCreate(){
    const ue = UE.getEditor("projectContent");
    const projectContent =  $("#projectContent_real").val(ue.getContent())+"";
    const projectType = $("#projectType").children("option:selected").val()+"";
    const province = $("#select_province").children("option:selected").attr("data-code");
    $("#province").val(province)
    $("#projectType_real").val(projectType);
    const projectName = $("#projectName").val()+"";
    console.log("有触发")
    if(isempty(projectContent)){
        $("#show_msg").html("请输入你的项目简介!!")
        return
    }
    if(isempty(province)){
        $("#show_msg").html("请选中项目的省份!!")
        return;
    }
    if(isempty(projectName)){
        $("#show_msg").html("请输入你的项目名称!!")
        return;
    }
    if(isempty(projectType)){
        $("#show_msg").html("请输入你的项目类型!!")
        return;
    }
    $("#createProject").submit();
}

/**
 * 检查用户提交的东西
 */

function checkContent(){
    const content = $("#content").val();
    console.log(content)
    if(content.length == 0){
        $("#error_msg").html("输入的内容不要为空!!");
        return;
    }
    $("#write_content").submit();
}

function checkPostResource(){
    $("#postMain").submit();
}

/**
 * 修改信息的检查
 */

function checkChange(){
    const year = $("#year").children("option:selected").val();
    const month = $("#month").children("option:selected").val();
    const day = $("#day").children("option:selected").val();
    const userLabel = $("#user_select_label").children("option:selected").val();
    $("#userLabel").val(userLabel);
    $("#date").val(year+"-"+month+"-"+day);
    $("#changeUser").submit();
}


/**
 *  页面轮播图的function
 */

window.addEventListener('load', function() {
    let arrow_1 = document.querySelector('.left_sign');
    let arrow_2 = document.querySelector('.right_sign');
    let focus = document.querySelector('.focus');
    let focusWidth = focus.offsetWidth;
    focus.addEventListener('mouseenter', function() {
        arrow_1.style.display = 'block';
        arrow_2.style.display = 'block';
        clearInterval(time);
        time = null;
    })
    focus.addEventListener('mouseleave', function() {
        arrow_1.style.display = 'none';
        arrow_2.style.display = 'none';
        time = setInterval(function() {
            arrow_2.click();
        }, 2000)
    })
    //动态生成小圆圈
    let ul = focus.querySelector('ul');
    let ol = focus.querySelector('.circle');
    for (let i = 0; i < ul.children.length; i++) {
        let li = this.document.createElement('li');
        //记录当前的索引号
        li.setAttribute('index', i)
        ol.appendChild(li);
        //小圆圈的点击排他思想
        li.addEventListener('click', function() {
            for (let i = 0; i < ol.children.length; i++) {
                ol.children[i].className = '';
            }
            this.className = 'current';
            //点击圆圈移动
            //点击li获得当前索引
            let index = this.getAttribute('index');
            //点击了indext就要变化num
            num = index;
            click = index;
            console.log(-index * focusWidth);
            animate(ul, -index * focusWidth);
        })
    }
    //把ol的第一个li设置类名为current
    ol.children[0].className = 'current';
    //克隆第一张图片添加到最后
    let first = ul.children[0].cloneNode(true);
    ul.appendChild(first);
    //点击按钮移动图片
    let num = 0;
    let click = 0;
    let flag = true; //节流阀
    //右侧按钮
    arrow_2.addEventListener('click', function() {
        if (flag) {
            flag = false;
            //关闭节流阀
            if (num >= ul.children.length - 1) {
                ul.style.left = 0;
                num = 0;
            }
            num++;
            animate(ul, -num * focusWidth); {
                flag = true;
            }
            //绑定小圆圈和图片
            click++;
            if (click == ol.children.length) {
                click = 0;
            }
            for (let i = 0; i < ol.children.length; i++) {
                ol.children[i].className = '';
            }
            ol.children[click].className = 'current';
        }
    })
    //左侧按钮
    arrow_1.addEventListener('click', function() {
        if (flag) {
            if (num == 0) {
                num = ul.children.length - 1;
                ul.style.left = num * focusWidth + 'px';
            }
            num--;
            animate(ul, -num * focusWidth, function() { //使用回调函数打开节流阀
                flag = true;
            });
            //绑定小圆圈和图片
            click--;
            if (click < 0) {
                click = ol.children.length - 1;
            }
            for (let i = 0; i < ol.children.length; i++) {
                ol.children[i].className = '';
            }
            ol.children[click].className = 'current';
        }
    })
    let time = setInterval(function() {
        arrow_2.click();
    }, 3000)
})