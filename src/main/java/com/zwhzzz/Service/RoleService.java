package com.zwhzzz.Service;

import com.zwhzzz.Mapper.RoleDao;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.RoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    //根据id查出角色
    public Role findById(Integer UserByRoleId) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria()
                .andIdEqualTo(UserByRoleId);
        List<Role> roles = roleDao.selectByExample(roleExample);
        Role role = roles.get(0);
        return role;
    }

    //插入角色
    public int insertRole(Role role) {
        return roleDao.insert(role);
    }

    //模糊查询 获取角色列表
    public List<Role> get_Role_List(String name) {
        RoleExample roleExample = new RoleExample();
        name = "%" + name + "%";
        roleExample.createCriteria()
                .andNameLike(name);
        List<Role> roles = roleDao.selectByExample(roleExample);
        return roles;
    }

    //更新角色
    public int updateRole(Role role) {
        return roleDao.updateByPrimaryKey(role);
    }

    //删除角色
    public int deleteId(Integer id) {
        return roleDao.deleteByPrimaryKey(id);
    }
}
