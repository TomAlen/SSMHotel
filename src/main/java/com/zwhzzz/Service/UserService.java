package com.zwhzzz.Service;

import com.zwhzzz.Mapper.UserDao;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    //根据用户名找出对象
    public User findByusername(String userName) {

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(userName);
        List<User> users = userDao.selectByExample(userExample);
        User user = users.get(0);
        return user;
    }

    //更改密码
    public int editPassword(User user) {
        int editPassword = userDao.editPassword(user);
        return editPassword;
    }


    //找出用户
    public List<User> findUserList(Map<String,Object> queryMap) {
        return userDao.getUserList(queryMap);
    }

    //根据用户名查出用户
    public User findByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(username);
        if(userDao.selectByExample(userExample).size() > 0) {
            return userDao.selectByExample(userExample).get(0);
        }
       return null;
    }

    //添加用户
    public int insertUser(User user) {
        return userDao.insert(user);
    }

    //更新用户
    public int updateUser(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }


    //删除用户
    public int deleteUser(String ids) {
        Integer id = Integer.parseInt(ids);
        List<Integer> idStr = new ArrayList<>();
        idStr.add(id);
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(idStr);
         return userDao.deleteByExample(userExample);

    }



}
