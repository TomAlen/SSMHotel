package com.zwhzzz.Util;

import java.util.Arrays;

/**
 * @author alen zhong
 * @date 19-9-22
 */
public class CalMonthUtil {


    //计算预定订单的天数
    public static int DaysWithBookOrder(int arriveMonth, int leaveMonth, String arriveTime, String leaveTime) {

        //初始化月份数组
        //31天
        int LaDaysWithMonth[] = {1, 3, 5, 7, 8, 10, 12};
        //30天
        int LunDaysWithMonth[] = {2, 4, 6, 9, 11};

        int days = 0;

        //①同月计算天数
        if (arriveMonth == leaveMonth) {
            int arrive = Integer.parseInt(arriveTime.substring(8));
            int leave = Integer.parseInt(leaveTime.substring(8));
            days = leave - arrive;
        }

        //这里只判断两个月之内的日期数
        //② 到达月份为1 3 5 7 8 10 12 为31天，计算隔月的天数
        if (Arrays.asList(LaDaysWithMonth).contains(arriveMonth) && arriveMonth != leaveMonth) {
            int arrive = Integer.parseInt(arriveTime.substring(8));
            int leave = Integer.parseInt(leaveTime.substring(8));
            days = 31 - arrive + leave;
        }

        //③ 到达月份为2 4 6 9 11 为30天 ，为了减少逻辑量 ，2月设为30天
        if (Arrays.asList(LunDaysWithMonth).contains(arriveMonth) && arriveMonth != leaveMonth) {
            int arrive = Integer.parseInt(arriveTime.substring(8));
            int leave = Integer.parseInt(leaveTime.substring(8));
            days = 30 - arrive + leave;
        }
        return days;
    }

}
