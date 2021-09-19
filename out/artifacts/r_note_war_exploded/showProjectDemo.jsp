<%--
  User: 86138
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>

    <script src="./static/js/jquery.js"></script>

</head>
<body>
    <h2 style="float: left;display: inline">project的列表</h2>
    <input type="hidden" name="actionName" value="showProject" id="actionName">
    <tr>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <script type="text/javascript">
        let actionName = $(".actionName").val();
        $.ajax({
            type : "post"
            ,url : "mynote/project",
                data :{
                actionName: actionName,
                },
            success : function e(res) {
                console.log(res)
            },
            error: function x(err) {
                console.log("出错了!!!")
            }
            })
    </script>
</body>
</html>
