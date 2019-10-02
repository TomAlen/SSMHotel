<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
 <meta name="Author" content="钟炜宏">
  <meta name="Keywords" content="酒店管理系统">
  <meta name="Description" content="酒店管理系统">
  <title>毕业设计酒店管理系统登录页面</title>
  <link href="/home/css/index.css" type="text/css" rel="Stylesheet" />
  <link href="/home/css/login.css" type="text/css" rel="Stylesheet" />
  <link rel="stylesheet" href="/layui/css/layui.css" type="text/css"/>
  <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css" type="text/css"/>
 </head>
 <body>
       <header>
	          <div>
				  <a href="index.html"><img src="/home/images/log.jpg" alt=""></a> <span>会员登录</span>
			  </div>
	   </header>
       <section>
	        <div class="left">
			    <img src="/home/images\index.jpg" alt="">
			</div>
			<div class="login">
				 <div id="normal">
						 <ul id="nor_log">
							<li class="name" style="margin-top:25px;">
							   <input id="name" name="uname"type="text" placeholder="请输入用户名">
							   <span class="icon"></span>
							</li>
							<li class="pwd" style="margin-top:25px;">
							   <input id="password" name="upwd" type="password" placeholder="密码">
							   <span class="icon"></span>
							</li>
						 </ul>
					   <div class="codes" style="margin-top:25px;">
							 <input id="vcode" maxlength="4" type="text" class="blur" placeholder="请输入验证码"/>
							 <img id="cpacha-img" src="../system/get_cpacha?vl=4&w=120&h=33&type=loginCpacha" onclick="changeVcode()" class="code" style="cursor:pointer;"/>
						 </div>
				 </div>
				 <div class="log" id="bt_login" style="margin-top:25px;cursor:pointer;">登 录</div>
			</div>
			<div class="reg">
			   <a href="reg">立即注册 &gt;&gt;</a>
			</div>
	   </section>
       <%@include file="../common/footer.jsp"%>
	  <script src="/home/js/jquery-1.11.3.js"></script>
	   <script src="/layui/layui.js"></script>
	   <script src="/layui/lay/modules/layer.js"></script>
<script>
function changeVcode(){
	$("#cpacha-img").attr("src",'../system/get_cpacha?vl=4&w=120&h=33&type=loginCpacha&t=' + new Date().getTime());
}

	$("#bt_login").click(function () {
		var name = $("#name").val();
		var password = $("#password").val();
		var vcode = $("#vcode").val();
		if (name == '' || password == '') {
			layer.open({
				title: '错误'
				, content: '请填写用户信息'
			});
			return;
		}
		if (vcode == '') {
			layer.open({
				title: '错误'
				,content: '请填写验证码'
			});
			return;
		}
		$.ajax({
			url: 'login',
			type: 'post',
			dataType: 'json',
			data: {name: name, password: password, vcode: vcode},
			success: function (data) {
				if (data.success == true) {
					window.location.href = 'index';
				} else {
					layer.open({
						title: '错误'
						, content: data.msg
					});
					changeVcode();
				}
			}
		});
	})
</script>
 </body>
</html>
