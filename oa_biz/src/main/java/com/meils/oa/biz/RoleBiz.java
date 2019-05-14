package com.meils.oa.biz;


import com.meils.oa.entity.Role;

import java.util.List;

/**
 * 角色业务处理接口
 */

public interface RoleBiz {
    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 编辑角色信息
     * @param role
     */
    void editRole(Role role);

    /**
     * 更新角色状态
     * @param role
     */
    void updateState(Role role);


    /**
     * 获取某一个角色
     * @param id
     * @return
     */
    Role getRoleOne(Integer id);

    /**
     * 获取角色列表
     * @param page     页数 1开始
     * @param pageSize 页内数量 10
     * @return
     */
    List<Role> getRoleList(Integer page, Integer pageSize);

}
