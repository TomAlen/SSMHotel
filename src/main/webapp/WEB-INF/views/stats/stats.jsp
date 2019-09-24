<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.codecoord.com" prefix="c" %>
<%@include file="../common/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
    </div>

    <div class="easyui-accordion" style="width:960px;height:600px;">
        <div title="营业统计分析" iconCls="icon-chart-curve" style="overflow:auto;padding:10px;">
            <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
            <div id="charts-div" style="width: 800px;height:500px;"></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/echarts/js/echarts.common.min.js"></script>
<!-- Begin of easyui-dialog -->
<%@include file="../common/footer.jsp"%>

<!-- End of easyui-dialog -->
<script type="text/javascript">
    $(document).ready(function () {
        statsByDays()
    })

    function statsByDays() {
        getData('day')
    }
    function statsByMonth() {
        getData('month')
    }
    
    function getData(type) {
        $.ajax({
            url:'get_stats',
            type:'post',
            dataType:'json',
            data:{type:type},
            success:function (data) {
                if(data.type == 'success'){
                    var title = "酒店营业额统计"
                    if(type == 'day') {
                          // option.title == option.title + "（按日统计）"
                        title = title +"（按日统计）"
                    }else{
                          // option.title == option.title + "（按月统计）"
                        title = title +"（按月统计）"
                    }
                   /* option.xAxis.data == data.content.stat_date;
                    option.series.data == data.content.money;*/
                    var option = {
                        title : {
                            text: title,
                            subtext: ''
                        },
                        tooltip : {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['营业额']
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                dataView : {show: true, readOnly: false},
                                magicType : {show: true, type: ['line', 'bar']},
                                restore : {show: true},
                                saveAsImage : {show: true}
                            }
                        },

                        xAxis : [
                            {
                                type : 'category',
                                data : data.content.stat_date
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                                name:'营业额',
                                type:'bar',
                                data:data.content.money,
                                markPoint : {
                                    data : [
                                        {type : 'max', name: '最大值'},
                                        {type : 'min', name: '最小值'}
                                    ]
                                },
                                markLine : {
                                    data : [
                                        {type : 'average', name: '平均值'}
                                    ]
                                }
                            },

                        ]
                    };
                    myChart.setOption(option)
                }
                else{
                    alert(data.msg);
                }

            }
        })

    }
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts-div'));
    myChart.setOption(option)


</script>