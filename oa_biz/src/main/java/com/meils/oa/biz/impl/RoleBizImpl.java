package com.meils.oa.biz.impl;

import com.meils.oa.biz.RoleBiz;
import com.meils.oa.dao.RoleDao;
import com.meils.oa.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RoleBiz")
public class RoleBizImpl implements RoleBiz {


    @Autowired
    private RoleDao roleDao;


    /**
     * 添加角色
     * @param role
     */
    public void addRole(Role role) {
        roleDao.insert(role);
    }

    public void editRole(Role role) {
        roleDao.update(role);
    }

    public void updateState(Role role) {
        roleDao.updateState(role);
    }

    public Role getRoleOne(Integer id) {
        return roleDao.findOne(id);
    }

    public List<Role> getRoleList(Integer page, Integer pageSize) {

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return roleDao.selectList(data);
    }
}
