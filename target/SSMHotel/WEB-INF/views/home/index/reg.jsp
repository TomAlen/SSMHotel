<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="Author" content="钟炜宏">
    <meta name="Keywords" content="酒店管理系统">
    <meta name="Description" content="酒店管理系统">
    <title>毕业设计|酒店管理系统注册页面</title>
    <link href="/home/css/login.css" type="text/css" rel="Stylesheet"/>
    <link href="/home/css/regsiter.css" type="text/css" rel="Stylesheet"/>
    <link href="/home/css/index.css" type="text/css" rel="Stylesheet"/>
    <link rel="stylesheet" href="/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css" type="text/css"/>
    <style>
        #ad > ul {
            margin: 0;
        }
    </style>
</head>
<body>
<!--头部-->
<header>
    <div>
        <a href=""><img src="/home/images/log.jpg" alt=""> </a> <span>注册</span>
    </div>

</header>
<!--中间部分-->
<div id="reg">
    <!---温馨提示-->
    <div class="msg">
        <div class="panel">
            <form id="user_info">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" id="uname" required minlength="5" maxlength="9"
                               lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" id="upwd" minlength="3" maxlength="12" name="password" required
                               lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="password" id="upwd2" minlength="3" maxlength="12" required lay-verify="required"
                               placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>
                    <span id="validate-password" class="layui-badge" style="display: none">两次密码输入不相等</span>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机</label>
                    <div class="layui-input-block">
                        <input type="text" name="mobile" id="uphone" required minlength="11" maxlength="11"
                               lay-verify="required" placeholder="请输入11位手机号" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <input class="layui-btn" value="提交" type="submit" id="btn-reg" style="cursor: pointer;width: 70px;">
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>
        <div id="ad">
            <div class="login">
                已有账号，去 <a href="login">登陆</a>
            </div>
        </div>
    </div>
    <div style="margin-top: 92px;font-size: 14px;">
        <%@include file="../common/footer.jsp" %>
    </div>
</div>
<script src="/home/js/jquery-1.11.3.js"></script>
<script src="/layui/layui.js"></script>
<script src="/layui/lay/modules/layer.js"></script>
<script>

    $('#btn-reg').click(function () {

        var data = $('#user_info').serialize();
        var name = $('#uname').val();
        var upwd = $('#upwd').val();
        var upwd2 = $('#upwd2').val();
        var uphone = $('#uphone').val();

        if(name == null || name == '') {
            layer.msg("用户名不能为空！");
            return;
        }
        if(upwd == null || upwd == '') {
            layer.msg("密码不能为空！");
            return;
        }
        if(upwd2 == null || upwd2 == '') {
            layer.msg("密码不能为空！");
            return;
        }
        if(uphone == null || uphone == '') {
            layer.msg("电话不能为空！");
            return;
        }

        //表单序列化，获得所有的用户输入
        //验证两个密码是否相等
        var upwd = $('#upwd').val();
        var upwd2 = $('#upwd2').val();
        console.log(upwd + "->" + upwd2)
        if (upwd != upwd2) {$('#upwd2').val();
             $('#validate-password').show()
             $('#upwd').val('');
             $('#upwd2').val('');
             return;
        }
        //异步提交请求数据
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: 'reg',
            data: data,
            success: function (result) {
                if (result.success == true) {
                    location.href = 'login';
                } else {
                    layer.open({
                        title: '错误',
                        content: result.msg
                    });
                    return;
                }
            }
        });
    })
</script>
</body>
</html>
