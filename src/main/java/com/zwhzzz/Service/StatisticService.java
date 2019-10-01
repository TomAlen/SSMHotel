package com.zwhzzz.Service;

import com.zwhzzz.Mapper.StatisticsDao;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-26
 */
@Service
public class StatisticService {

    private final StatisticsDao statisticsDao;


    public StatisticService(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    public List<Map> getStatsByDays() {
        return statisticsDao.getStatsByDays();
    }

    public List<Map> getStatsByMonths() {
        return statisticsDao.getStatsByMonths();
    }

    public List<Map> getStatsByName(String name) {
        return statisticsDao.getStatsByName(name);
    }
}
