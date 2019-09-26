package com.zwhzzz.Service;

import com.zwhzzz.Mapper.CheckinnDao;
import com.zwhzzz.Pojo.Checkinn;
import com.zwhzzz.Pojo.CheckinnExample;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-26
 */
@Service
public class CheckinService {

    private final CheckinnDao checkinnDao;

    public CheckinService(CheckinnDao checkinnDao) {
        this.checkinnDao = checkinnDao;
    }

    public List<Checkinn> getCheckinList(Map<String, Object> queryMap) {
        return checkinnDao.getList(queryMap);
    }

    public int insertCheckin(Checkinn checkin) {
        return checkinnDao.insert(checkin);
    }

    public Checkinn findById(Integer id) {

        CheckinnExample checkinnExample = new CheckinnExample();
        checkinnExample.createCriteria()
                .andIdEqualTo(id);
        List<Checkinn> checkinns = checkinnDao.selectByExample(checkinnExample);
        if(checkinns.size() > 0) {
            return checkinns.get(0);
        }
        return null;
    }

    public int updateCheckin(Checkinn checkin) {
        return checkinnDao.updateByPrimaryKeySelective(checkin);
    }
}
