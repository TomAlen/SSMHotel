package com.zwhzzz.Service;

import com.zwhzzz.Mapper.AuthorityDao;
import com.zwhzzz.Pojo.Authority;
import com.zwhzzz.Pojo.AuthorityExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class AuthorityService {

    @Autowired
    private AuthorityDao authorityDao;


    public List<Authority> getListByRoleId(Integer roleId) {
        AuthorityExample authorityExample = new AuthorityExample();
        authorityExample.createCriteria()
                .andRoleidEqualTo(roleId);
        List<Authority> authorities = authorityDao.selectByExample(authorityExample);
        return authorities;
    }

    //根据角色id删除角色，清空权限
    public int deleteByRoleId(Integer roleId) {
        AuthorityExample authorityExample = new AuthorityExample();
        authorityExample.createCriteria()
                .andRoleidEqualTo(roleId);
        return authorityDao.deleteByExample(authorityExample);
    }

    //
    public int add(Authority authority) {
        return authorityDao.insert(authority);
    }

}
