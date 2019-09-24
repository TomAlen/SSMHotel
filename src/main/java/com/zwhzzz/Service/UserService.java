package com.zwhzzz.Service;

import com.zwhzzz.Mapper.UserDao;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

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





}
