package com.zwhzzz.Service;

import com.zwhzzz.Mapper.LogDao;
import com.zwhzzz.Pojo.Log;
import com.zwhzzz.Pojo.LogExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class LogService {

    private final LogDao logDao;

    public LogService(LogDao logDao) {
        this.logDao = logDao;
    }

    //插入日志
    public void insertContent(String content) {
        Log record = new Log();
        record.setContent(content);
        record.setCreatetime(new Date());
        logDao.insert(record);
    }

    public int insertLog(Log log) {
        return logDao.insert(log);
    }


    //根据内容模糊查询
    public List<Log> getLogByContent(String content) {
        LogExample logExample = new LogExample();
        content = "%" + content + "%";
        logExample.createCriteria()
                .andContentLike(content);
        return logDao.selectByExample(logExample);
    }

    //批量删除
    public int deleteLog(String ids) {
        Integer logIds = Integer.parseInt(ids);
        List<Integer> log = new ArrayList<>(0);
        log.add(logIds);
        LogExample logExample = new LogExample();
        logExample.createCriteria()
                .andIdIn(log);
        return logDao.deleteByExample(logExample);
    }

    public Log selectLaterAdmin() {
        return logDao.selectLaterAdmin();
    }
}
