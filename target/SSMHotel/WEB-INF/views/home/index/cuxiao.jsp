<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="Author" content="钟炜宏">
  <meta name="Keywords" content="酒店管理系统">
  <meta name="Description" content="酒店管理系统">
  <link href="/home/css/reservation.css" type="text/css" rel="Stylesheet"/>
  <link href="/home/css/index.css" type="text/css" rel="Stylesheet"/>
    <link rel="stylesheet" href="/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css" type="text/css"/>
    <script src="/home/js/jquery-1.11.3.js"></script>
    <script src="/layui/js/layui.js"></script>
    <script src="/layui/js/lay/modules/layer.js"></script>
<title>毕业设计|酒店管理系统八折促销活动页面</title>
    <script>
        /*下拉框展示*/
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>
</head>
<body>
<!--头部-->
<div id="c_header"></div>
<!--主体内容-->
<section>
    <ul class="layui-nav" style="margin: 0 128px">
        <c:if test="${account == null}">
            <li class="layui-nav-item" style="margin-left: 1030px;">
                <a href="/home/login">登录<span class="layui-badge-dot"></span></a>
            </li>
            <li class="layui-nav-item" style="float: right;">
                <a href="/home/reg">注册<span class="layui-badge-dot"></span></a>
            </li>
        </c:if>

        <c:if test="${account != null}">
            <li class="layui-nav-item" style="margin-left: 950px;">
                <a href="account/index">个人中心<span class="layui-badge-dot"></span></a>
            </li>

            <li class="layui-nav-item" style="float: right">
                <a href=""><img src="${account.photo}" class="layui-nav-img">${account.name}</a>
                <dl class="layui-nav-child">
                    <dd><a href="../account/index">个人订单</a></dd>
                    <dd><a href="/home/logout">注销登录</a></dd>
                </dl>
            </li>
        </c:if>
    </ul>
  <div id="subject">

    <img src="/home/images\Cu.jpg" alt="" height="256px" width="1200px">
  </div>

  <!---->
  <!---预订菜单--->
  <div id="due_menu">
    <!--关于-->
    
    <!--客房-->
    <div id="guest_rooms">
      <p class="booking_tab"><span></span>客房列表</p>
      <div class="chioce">
        <input type="text" placeholder="关键字" value="${kw }" id="kw"/>
        <input type="button" value="搜索" id="search-btn"/>
      </div>
      <form style="display:none;" action="index" method="get" id="search-form">
          <!--模糊查询 获取模糊查询的值-->
          <input type="hidden" name="name" id="search-name"></form>
      <!--列表-->
      <table id="pro_list" >
        <thead>
          <tr>
            <th width="200px">客房</th>
            <th>房型</th>
            <th>可住人数</th>
            <th>床位数</th>
            <th>打折房价(<span style="color: red;">8折</span>)</th>
            <th>房态</th>
            <th>预订</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${CuroomTypes }" var="roomType">
        <tr>
          <td><a href="#"><img src="${roomType.photo }" alt=""></a>
          </td>
          <td align="center">
              <b><p style="font-size: 17px">${roomType.name }</p></b>
            <p class="sub_txt">${roomType.remark }</p>
          </td>
          <td>${roomType.livenum }</td>
          <td>${roomType.bednum }</td>
          <td>
              <div style="color:#999;font-family: arial;text-decoration: line-through;">
                      ￥${roomType.prePrice}
              </div>
              <div style="font-size: 22px;color: red;">
                      ￥${roomType.price}
              </div>
          </td>
          <td>
          	<c:if test="${roomType.status == 0 }">
          		已满房
          	</c:if>
          	<c:if test="${roomType.status == 1 }">
          		可预订
          	</c:if>
          </td>
          <td>
          	<c:if test="${roomType.status == 0 }">
          		<input type="button" class="disable" value="满房" >
          	</c:if>
          	<c:if test="${roomType.status == 1 }">
          		<input type="button" value="预订" onclick="window.location.href='/home/account/eight_sale_book_order?roomTypeId=${roomType.id }'" >
          	</c:if>
          </td>
        </tr>
		</c:forEach>
        </tbody>
      </table>
      
      <div id="pages"></div>
      <!--  -列表菜单 -->
      <div></div>
    </div>
  </div>

</section>
<%@include file="../common/footer.jsp"%>
<script src="/home/js/jquery-1.11.3.js"></script>
<script>
    //加载完整个页面再加载js
$(document).ready(function(){
	$("#search-btn").click(function(){
	    //得到输入框的值
		$("#search-name").val($("#kw").val());
		//提交
		$("#search-form").submit();
	})
});
</script>
</body>