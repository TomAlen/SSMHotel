<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>毕业设计|酒店管理系统用户中心</title>
    <meta name="Author" content="钟炜宏">
    <meta name="Keywords" content="酒店管理系统">
    <meta name="Description" content="酒店管理系统">
    <link rel="stylesheet" href="/home/css/index.css"/>
    <!--<link rel="stylesheet" href="css/myOrder.css"/>-->
    <link rel="stylesheet" href="/home/css/account_index.css"/>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<!--头部-->
<div id="c_header"></div>
<!--主体-->
<div id="contain" style="margin-top: 50px;">
    <!--tab选项卡-->
    <ul class="tabs">

        <li><a href="../index">首页</a></li>
        <li><a href="#order">我的订单</a></li>
        <li><a href="#info">我的资料</a></li>
        <li><a href="#pwd">修改密码</a></li>

    </ul>

    <div class="content">

        <div class="order" style="display: block;">
            <table>
                <thead>
                <tr>
                    <!--<th colspan="4">订单编号：</th>-->
                    <!--<th colspan="2" >订单时间:</th>-->
                    <th>房型图片</th>
                    <th>房型</th>
                    <th>入住人</th>
                    <th>手机号</th>
                    <th>身份证号</th>
                    <th>入住价格</th>
                    <th>状态</th>
                    <th>天数</th>
                    <th>下单时间</th>
                    <th>备注</th>
                    <th>退订</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${bookOrderList }" var="bookOrder">
                    <tr>
                        <c:forEach items="${roomTypeList }" var="roomType">
                            <c:if test="${roomType.id == bookOrder.roomTypeId }">
                                <td><img src="${roomType.photo }" width="150px" style="margin: 12px"></td>
                                <td>${roomType.name }</td>
                            </c:if>
                        </c:forEach>
                        <td>${bookOrder.name }</td>
                        <td>${bookOrder.mobile }</td>
                        <td>${bookOrder.idCard }</td>
                        <td>￥${bookOrder.price}
                            <c:if test="${bookOrder.cuStatus == 1}">
                                <span class="label label-danger" style="margin-left: 7px">促销价</span>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${bookOrder.status == 0 }">
                                <font color="red">预定中</font>
                            </c:if>
                            <c:if test="${bookOrder.status == 1 }">
                                已入住
                            </c:if>
                            <c:if test="${bookOrder.status == 2 }">
                                已结算离店
                            </c:if>
                        </td>
                        <td>${bookOrder.checkDays}</td>
                        <td><fmt:formatDate value="${bookOrder.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${bookOrder.remark }</td>
                        <td>
                            <c:if test="${bookOrder.status == 0}">
                                <button id="deleteOrder" type="button" class="btn btn-success" onclick="deleteOrder()">退订</button>
                                <input type="hidden" id="bookOrderId" value="${bookOrder.id}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
        <div class="info">
            <form id="update-info-form">
                <table style="border:0px;cellspacing:0px;">
                    <tbody>
                    <tr>
                        <td style="border:0px;">用户名：</td>
                        <td style="float:left;width:400px;max-width: 420px;border:0px;"><input class="form-control" type="text" value="${account.name}" name="name"/></td>
                    </tr>
                    <tr style="border:0px;">
                        <td style="border:0px;">真实姓名：</td>
                        <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="text" value="${account.realname}" name="realname"/></td>
                    </tr>
                    <tr>
                        <td style="border:0px;">身份证号：</td>
                        <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="text" value="${account.idCard}" name="idCard"/></td>
                    </tr>
                    <tr>
                        <td style="border:0px;">手机号码：</td>
                        <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="text" value="${account.mobile}" name="mobile"/></td>
                    </tr>
                    <tr>
                        <td style="border:0px;">联系地址：</td>
                        <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="text" value="${account.address}" name="address"/></td>
                    </tr>
                    <tr>
                        <td style="border:0px;">
                            <button type="button" id="update-info-btn" class="btn btn-success" style="width:100px;">提交
                            </button>
                        </td>
                        <td style="float:left;width:400px;max-width: 820px;border:0px;"></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="pwd">
            <table style="border:0px;cellspacing:0px;">
                <tbody>
                <tr>
                    <td style="border:0px;">原密码：</td>
                    <td style="float:left;width:400px;max-width: 420px;border:0px;"><input class="form-control" type="password" id="old-password"/></td>
                </tr>
                <tr style="border:0px;">
                    <td style="border:0px;">新密码：</td>
                    <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="password" id="new-password"/></td>
                </tr>
                <tr>
                    <td style="border:0px;">重复密码：</td>
                    <td style="float:left;width:400px;max-width: 820px;border:0px;"><input class="form-control" type="password" id="renew-password"/></td>
                </tr>

                <tr>
                    <td style="border:0px;"></td>
                    <td style="float:left;margin-top:15px;width:400px;max-width: 820px;border:0px;">
                        <button type="button" class="btn btn-success" id="update-password-btn" style="width:100px;">提交
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>
    </div>

</div>
<!--底部-->
<div id="c_footer"></div>
<script src="/home/js/jquery-1.11.3.js"></script>
<script>
    $(".tabs").on("click", "li a", function () {
        $(this).addClass("active").parents().siblings().children(".active").removeClass("active");
        var href = $(this).attr("href");
        href = href.slice(1);
        var $div = $("div.content>div." + href);
        $div.show().siblings().hide();
        //修改个人信息
        $("#update-info-btn").click(function () {
            $.ajax({
                url: 'update_info',
                type: 'post',
                dataType: 'json',
                data: $("#update-info-form").serialize(),
                success: function (data) {
                    alert(data.msg);
                }
            });
        });
        //修改密码
        $("#update-password-btn").click(function () {
            var oldPassword = $("#old-password").val();
            var newPassword = $("#new-password").val();
            var renewPassword = $("#renew-password").val();
            if (oldPassword == null || oldPassword == '') {
                alert('请填写原密码！');
                return;
            }
            if (newPassword == null || newPassword == '') {
                alert('请填写新密码！');
                return;
            }
            if (newPassword != renewPassword) {
                alert('两次密码不一致！');
                return;
            }
            $.ajax({
                url: 'update_pwd',
                type: 'post',
                dataType: 'json',
                data: {oldPassword: oldPassword, newPassword: newPassword},
                success: function (data) {
                    alert(data.msg)
                }
            });
        });
    });

    function deleteOrder() {
        var confirm1 = confirm("亲～  退订了就不能入住了呦～");
        if (confirm1 == true) {
            var bookOrderId = $('#bookOrderId').val();
            $.ajax({
                url: './delete',
                type: 'post',
                dataType: 'json',
                data: {id: bookOrderId},
                success: function (data) {
                    if (data.success == true) {
                        alert("退订房间成功！");
                        window.location.reload();
                    } else {
                        alert(data.msg);
                    }
                }
            })
        }

    }
</script>

</body>
</html>