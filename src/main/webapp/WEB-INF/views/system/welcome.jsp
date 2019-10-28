<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>欢迎页面</title>
</head>
<body>
<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
	<p style="font-size: 50px; line-height: 60px; height: 60px;">${admin.username}</p>
	<p>您上次登录的时间为 <b><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
								value="${log.createtime}"/></b></p>
	<p style="font-size: 25px; line-height: 30px; height: 30px;">酒店管理系统后台主页面</p>
  	<p>开发人员：钟炜宏</p>
  	
  	<hr />
  	<h2>系统环境</h2>
  	<p>系统环境：Linux</p>
	<p>开发工具：IntelliJ IDEA 2019.1</p>
	<p>Java版本：JDK 1.8</p>
	<p>服务器：tomcat 8.5</p>
	<p>数据库：MySQL</p>
	<p>系统采用技术： Spring+SpringMVC+Mybaits+EasyUI+jQuery+Ajax+面向接口编程</p>
</div>
</body>
</html>