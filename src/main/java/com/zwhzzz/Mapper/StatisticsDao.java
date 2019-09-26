package com.zwhzzz.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-26
 */
public interface StatisticsDao {

    List<Map> getStatsByDays();

    List<Map> getStatsByMonths();

}
