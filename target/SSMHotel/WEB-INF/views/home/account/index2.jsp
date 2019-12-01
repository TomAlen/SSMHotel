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
    <link rel="stylesheet" href="/layui/css/layui.css" type="text/css"/>
    <link rel="stylesheet" href="/layui/css/modules/layer/default/layer.css" type="text/css"/>
    <script src="/home/js/jquery-1.11.3.js"></script>
    <script src="/layui/layui.js"></script>
    <script src="/layui/lay/modules/layer.js"></script>
    <script type="text/javascript" src="/echarts/js/echarts.common.min.js"></script>
</head>
<script>
    /*下拉框展示*/
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
<body>
<!--头部-->
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">个人中心管理</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="../index">首页</a></li>
            <li class="layui-nav-item"><a href="/home/cuxiao/index">八折区</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href=""><img src="${account.photo}" class="layui-nav-img">${account.name}</a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/home/logout">注销登录</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test" id="tab-click">

                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="../index">首页</a>
                </li>

                <li class="layui-nav-item">
                    <a href="#order" id="order-active">个人订单</a>
                </li>

                <li class="layui-nav-item">
                    <a href="#info">个人资料</a>
                </li>

                <li class="layui-nav-item">
                    <a href="#orderStats" id="stats_name">个人账单统计</a>
                </li>

            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px" class="order">
            <table class="layui-table" lay-size="lg">
                <thead>
                <tr>
                    <th>房型图片</th>
                    <th>房型</th>
                    <th>入住人</th>
                    <th>手机号</th>
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
                            <c:if test="${roomType.id == bookOrder.roomtypeid }">
                                <td><img src="${roomType.photo }" width="150px" style="margin: 12px"></td>
                                <td>${roomType.name }</td>
                            </c:if>
                        </c:forEach>
                        <td>${bookOrder.name }</td>
                        <td>${account.laterMobile }</td>
                        <td>
                            <c:if test="${bookOrder.custatus != 1}">
                                ￥${bookOrder.price}
                            </c:if>
                            <c:if test="${bookOrder.custatus == 1}">
                                <span style="color: red">￥${bookOrder.price}</span>
                                <span class="layui-badge" style="margin-left: 7px">促销</span>
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
                        <td><fmt:formatDate value="${bookOrder.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${bookOrder.remark }</td>
                        <td>
                            <c:if test="${bookOrder.status != 0}">
                                <button class="layui-btn layui-btn-disabled">退订</button>
                            </c:if>
                            <c:if test="${bookOrder.status == 0}">
                                <button id="deleteOrder" type="button" class="layui-btn layui-btn-danger"
                                        onclick="deleteOrder()">退订
                                </button>
                                <input type="hidden" id="bookOrderId" value="${bookOrder.id}"/>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <div class="info" style="display: none">
            <div class="layui-card">
                <div class="layui-card-header"><b>基础信息</b></div>
                <div class="layui-card-body">
                    <div class="layui-col-md12">
                        <div class="layui-col-md5" style="margin-left: 49px;">
                            <p>
                                <img src="${account.photo}" id="avatarUrl" width="25%">
                            </p>
                        </div>
                        <div class="layui-col-md4" style="margin-top: 30px;margin-left: -331px;font-size: 13px;">
                            <p>用户名：${account.name}</p>
                            <p>真实姓名：${account.laterRealName}</p>
                            <p>手机号：${account.laterMobile}</p>
                        </div>
                    </div>
                    <hr class="layui-col-md12">
                    <div class="layui-col-md12">
                        <div class="layui-col-md4">
                            <b>修改个人信息</b>
                            <form class="layui-form" id="update-info-form">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="name" required lay-verify="required"
                                               value="${account.name}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">真实姓名</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="realname" required lay-verify="required"
                                               value="${account.realname}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">身份证号</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="idcard" required lay-verify="required"
                                               value="${account.idcard}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">手机号码</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="mobile" required lay-verify="required"
                                               value="${account.mobile}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">联系地址</label>
                                    <div class="layui-input-block">
                                        <input type="text" name="address" required lay-verify="required"
                                               value="${account.address}" autocomplete="off" class="layui-input">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <button class="layui-btn layui-btn-radius layui-btn-normal" id="update-info"
                                                style="float: right;margin-right: 31px;">立即提交
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="layui-col-md1">
                            <button class="layui-btn layui-btn-radius layui-btn-danger" style="margin-top: 289px;"
                                    id="update-password">修改密码
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="stats-bookorder" class="orderStats" style="display: none">
            <div class="layui-card">
                <div class="layui-card-header" style="margin: 0 auto;text-align: center;margin-top: 16px;"><b
                        style="font-size: 31px">个人账单统计</b></div>
                <div class="layui-card-body">
                    <div class="layui-col-md7">
                        <div id="charts-div" style="width: 720px;height:380px;margin-top: 43px;">

                        </div>
                    </div>
                    <div class="layui-col-md5" style="margin-top: 47px;">
                        <ul class="layui-timeline">
                            <c:forEach items="${bookOrderList}" var="bookOrder">
                                <li class="layui-timeline-item">
                                    <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
                                    <div class="layui-timeline-content layui-text">
                                        <h3 class="layui-timeline-title"><fmt:formatDate pattern="yyyy-MM-dd"
                                                                                         value="${bookOrder.createtime}"/></h3>
                                        <p>
                                            <b>入住时间： ${bookOrder.arrivetime} ~ ${bookOrder.leavetime}</b>
                                            <br>入住房型： ${bookOrder.roomtype.name}
                                            <br>消费金额： <span style="color: #FFB800">￥${bookOrder.price}</span>
                                        </p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--弹出修改密码的模态框-->
    <div id="edit-password-model" style="display: none;padding-top:38px">
        <div class="layui-form-item">
            <label class="layui-form-label">原密码</label>
            <div class="layui-input-inline">
                <input type="password" id="old-password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-inline">
                <input type="password" id="new-password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-input-inline">
                <input type="password" id="renew-password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-footer" align="center">
        © 钟炜宏 - 毕业设计
    </div>
</div>

<script>


    $('#tab-click').on('click', 'li a', function () {
        $(this).addClass("active").parents().siblings().children(".active").removeClass("active");
        var href = $(this).attr("href");
        href = href.slice(1);
        var $div = $("div.layui-body>div." + href);
        $div.show().siblings().hide();

        //修改个人信息
        $("#update-info").click(function () {
            var data = $("#update-info-form").serialize();
            $.ajax({
                url: 'update_info',
                type: 'post',
                dataType: 'json',
                data: data,
                success: function (data) {
                    console.log(data)
                    if (data.success == true) {
                        layer.msg("修改成功！");
                        window.location.href = 'index'
                    } else {
                        layer.open({
                            title: '错误',
                            content: data.msg
                        })
                        return;
                    }
                }
            });
        });

        $('#update-password').click(function () {
            layer.open({
                type: 1,
                title: '密码修改',
                area: ['400px', '300px'],
                shadeClose: false,//点击遮罩关闭
                content: $('#edit-password-model'),
                btn: ['修改', '取消'],
                yes: function (index, layero) {
                    var oldPassword = $("#old-password").val();
                    var newPassword = $("#new-password").val();
                    var renewPassword = $("#renew-password").val();
                    if (oldPassword == null || oldPassword == '') {
                        layer.msg("请填写密码！")
                        return;
                    }
                    if (newPassword == null || newPassword == '') {
                        layer.msg("请填写新密码！")
                        return;
                    }
                    if (newPassword != renewPassword) {
                        layer.msg("两次密码不一致！")
                        return;
                    }
                    $.ajax({
                        url: 'update_pwd',
                        type: 'post',
                        dataType: 'json',
                        data: {oldPassword: oldPassword, newPassword: newPassword},
                        success: function (data) {
                            if (data.success == true) {
                                layer.msg(data.msg);
                                $("#old-password").val('');
                                $("#new-password").val('');
                                $("#renew-password").val('');
                            } else {
                                layer.open({
                                    title: '提示',
                                    content: data.msg
                                })
                            }
                        }
                    });
                },
                success: function () {
                    $(".layui-layer-page").css("z-index", "198910151");
                },
                end: function () {
                    //删除遮罩元素
                    // $(".layui-layer-shade").remove()
                    $(".layui-layer-shade").css('display', 'none');
                    $('#edit-password-model').css('display', 'none');
                }
            })

        });

        $('#stats_name').click(function () {

            $.ajax({
                url: './get_stats',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.success == true) {
                        var dataAxis = data.content.stat_date;
                        var data = data.content.money;
                        var yMax = 800;
                        var dataShadow = [];

                        for (var i = 0; i < data.length; i++) {
                            dataShadow.push(yMax);
                        }

                        var option = {
                            title: {
                                text: '订单金额统计',
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    saveAsImage : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    magicType : {show: true, type: ['line']},
                                    restore : {show: true}
                                }
                            },
                            xAxis: {
                                data: dataAxis,
                                axisLabel: {
                                    inside: true,
                                    textStyle: {
                                        color: '#fff'
                                    }
                                },
                                axisTick: {
                                    show: false
                                },
                                axisLine: {
                                    show: false
                                },
                                z: 10
                            },
                            yAxis: {
                                axisLine: {
                                    show: false
                                },
                                axisTick: {
                                    show: false
                                },
                                axisLabel: {
                                    textStyle: {
                                        color: '#999'
                                    }
                                }
                            },
                            dataZoom: [
                                {
                                    type: 'inside'
                                }
                            ],
                            series: [
                                { // For shadow
                                    type: 'bar',
                                    itemStyle: {
                                        normal: {color: 'rgba(0,0,0,0.05)'}
                                    },
                                    barGap: '-100%',
                                    barCategoryGap: '40%',
                                    data: dataShadow,
                                    animation: false
                                },
                                {
                                    type: 'bar',
                                    itemStyle: {
                                        normal: {
                                            color: new echarts.graphic.LinearGradient(
                                                0, 0, 0, 1,
                                                [
                                                    {offset: 0, color: '#83bff6'},
                                                    {offset: 0.5, color: '#188df0'},
                                                    {offset: 1, color: '#188df0'}
                                                ]
                                            )
                                        },
                                        emphasis: {
                                            color: new echarts.graphic.LinearGradient(
                                                0, 0, 0, 1,
                                                [
                                                    {offset: 0, color: '#2378f7'},
                                                    {offset: 0.7, color: '#2378f7'},
                                                    {offset: 1, color: '#83bff6'}
                                                ]
                                            )
                                        }
                                    },
                                    data: data
                                }
                            ]
                        };
                        var zoomSize = 6;
                        myChart.on('click', function (params) {
                            console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                            myChart.dispatchAction({
                                type: 'dataZoom',
                                startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                                endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                            });
                        });

                        myChart.setOption(option)
                    } else {
                        layer.open({
                            title: '错误',
                            content: '个人账单为空！'
                        })
                    }
                }
            })
        });

    });


    function deleteOrder() {
        layer.confirm("亲～  退订了就不能入住了呦～", {btn: ['确定', '取消'], title: "提示"}, function () {
            var bookOrderId = $('#bookOrderId').val();
            $.ajax({
                url: './delete',
                type: 'post',
                dataType: 'json',
                data: {bookOrderId: bookOrderId},
                success: function (data) {
                    if (data.success == true) {
                        layer.open({
                            title: '成功',
                            content: "退订房间成功！"
                        })
                        window.location.reload();
                    } else {
                        layer.open({
                            title: '错误',
                            content: data.msg
                        })
                    }
                }
            })
        });
    }

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts-div'));

</script>

</body>
</html>