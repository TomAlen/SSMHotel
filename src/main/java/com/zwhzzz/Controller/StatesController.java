package com.zwhzzz.Controller;

import com.zwhzzz.Service.StatisticService;
import com.zwhzzz.Util.CalStatDatas;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
                Map<String, Object> statDatas = CalStatDatas.getStatDatas(statsByDays);
                result.put("success", true);
                result.put("content", statDatas);
                return result;
            }
            case "month": {
                List<Map> statsByMonths = statisticService.getStatsByMonths();
                Map<String, Object> statDatas = CalStatDatas.getStatDatas(statsByMonths);
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





}
