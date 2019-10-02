<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>酒店管理后台管理登录</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="/login/css/style.css">
    <link rel="stylesheet" type="text/css" href="/login/css/reset.css">
    <link href="/home/css/index.css" type="text/css" rel="Stylesheet"/>
    <link href="/home/css/login.css" type="text/css" rel="Stylesheet"/>
    <link rel="stylesheet" href="/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css" type="text/css"/>
    <script src="/home/js/jquery-1.11.3.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/layui/lay/modules/layer.js"></script>
<body>

<div id="particles-js">
    <div class="login" style="display: block;">
        <div style="margin-top: 41px;font-size: 26px;font-family: ' KaiTi ' ;text-align: center">
            <b>酒店后台管理系统</b>
        </div>
        <div class="login-top">
            登录
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/login/images/name.png"></div>
            <div class="login-center-input">
                <input type="text" name="username" id="username" value="Admin" placeholder="请输入您的用户名"
                       onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入您的用户名&#39;">
                <div class="login-center-input-text">用户名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/login/images/password.png"></div>
            <div class="login-center-input">
                <input type="password" name="password" id="password" value="123" placeholder="请输入您的密码"
                       onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入您的密码&#39;">
                <div class="login-center-input-text">密码</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="/login/images/cpacha.png"></div>
            <div class="login-center-input">
                <input style="width:50%;" type="text" name="cpacha" id="cpacha" value="" placeholder="请输入验证码"
                       onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入验证码&#39;">
                <div class="login-center-input-text">验证码</div>
                <img id="cpacha-img" title="点击切换验证码" style="cursor:pointer;"
                     src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="110px" height="30px"
                     onclick="changeCpacha()">
            </div>
        </div>

        <div class="login-button">
            登录
        </div>

        <div style="float: right;margin: 0 auto;margin-top: 24px;margin-right: 20px;">
            <a href="/home/login">立即登录前台页面 &gt;&gt;</a>
        </div>
    </div>


    <div class="sk-rotating-plane"></div>
    <canvas class="particles-js-canvas-el" width="1147" height="952" style="width: 100%; height: 100%;"></canvas>
</div>

<!-- scripts -->
<script src="/login/js/particles.min.js"></script>
<script src="/login/js/app.js"></script>
<script src="/login/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript">
    function hasClass(elem, cls) {
        cls = cls || '';
        if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
        return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
    }

    function addClass(ele, cls) {
        if (!hasClass(ele, cls)) {
            ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
        }
    }

    function removeClass(ele, cls) {
        if (hasClass(ele, cls)) {
            var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
            while (newClass.indexOf(' ' + cls + ' ') >= 0) {
                newClass = newClass.replace(' ' + cls + ' ', ' ');
            }
            ele.className = newClass.replace(/^\s+|\s+$/g, '');
        }
    }

    function changeCpacha() {
        $("#cpacha-img").attr("src", 'get_cpacha?vl=4&w=130&h=40&type=loginCpacha&t=' + new Date().getTime());
    }

    document.querySelector(".login-button").onclick = function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var cpacha = $("#cpacha").val();
        if (username == '' || username == 'undefined') {
            layer.msg("请填写用户名！")
            return;
        }
        if (password == '' || password == 'undefined') {
            layer.msg("请填写密码！");
            return;
        }
        if (cpacha == '' || cpacha == 'undefined') {
            layer.msg("请填写验证码！");
            return;
        }
        addClass(document.querySelector(".login"), "active")
        addClass(document.querySelector(".sk-rotating-plane"), "active")
        document.querySelector(".login").style.display = "none"
        $.ajax({
            url: 'login',
            data: {username: username, password: password, cpacha: cpacha},
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.success == true) {
                    window.parent.location = 'index';
                } else {
                    removeClass(document.querySelector(".login"), "active");
                    removeClass(document.querySelector(".sk-rotating-plane"), "active");
                    document.querySelector(".login").style.display = "block";
                    layer.open({
                        title:'错误',
                        content:data.msg
                    })
                    //重新调用验证码，刷新验证码
                    changeCpacha();
                }
            }
        });
    }
</script>
</body>
</html>