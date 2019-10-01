package com.zwhzzz.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-29
 */
public class CalStatDatas {

    /**
     * 对查出的值进行处理
     *
     * @param getValueByStats
     * @return
     */
    public static Map<String, Object> getStatDatas(List<Map> getValueByStats) {
        Map<String, Object> result = new HashMap<>(0);
        //用于接收时间
        List<String> keyList = new ArrayList<>(0);
        //用于接收营业额
        List<Float> valueList = new ArrayList<>(0);
        //遍历，依次取值并存入List中
        for (Map getValueByStat : getValueByStats) {
            keyList.add(getValueByStat.get("stat_date").toString());
            Float money = Float.valueOf(getValueByStat.get("money").toString());
            valueList.add(money);
        }
        result.put("stat_date", keyList);
        result.put("money", valueList);
        return result;
    }

}
