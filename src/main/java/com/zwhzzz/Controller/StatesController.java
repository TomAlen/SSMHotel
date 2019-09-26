package com.zwhzzz.Controller;

import com.zwhzzz.Service.StatisticService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-26
 */
@Controller
@RequestMapping("/admin/stats")
public class StatesController {

    private final StatisticService statisticService;

    public StatesController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ModelAndView toIndex(ModelAndView model) {
        model.setViewName("/stats/stats");
        return model;
    }


    @RequestMapping(value = "/get_stats", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStats(String type) {
        Map<String, Object> result = new HashMap<>(0);
        if (StringUtils.isBlank(type)) {
            result.put("success", false);
            result.put("msg", "请选择类型统计！");
            return result;
        }

        //分别对按月和按日统计分开分析
        switch (type) {
            case "day": {
                List<Map> statsByDays = statisticService.getStatsByDays();
                Map<String, Object> statDatas = getStatDatas(statsByDays);
                result.put("success", true);
                result.put("content", statDatas);
                return result;
            }
            case "month": {
                List<Map> statsByMonths = statisticService.getStatsByMonths();
                Map<String, Object> statDatas = getStatDatas(statsByMonths);
                result.put("success",true);
                result.put("content",statDatas);
                return  result;
            }
            default:{
                result.put("success",false);
                result.put("msg","请选择正确的类型统计！");
                return result;
            }
        }
    }


    /**
     * 对查出的值进行处理
     *
     * @param getValueByStats
     * @return
     */
    public Map<String, Object> getStatDatas(List<Map> getValueByStats) {
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
