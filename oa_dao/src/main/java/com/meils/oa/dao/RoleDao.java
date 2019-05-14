package com.meils.oa.dao;

import com.meils.oa.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色DAO 接口
 */
@Repository("RoleDao")
public interface RoleDao {

    /**
     * 插入一个角色数据
     * @param role
     */
    void insert (Role role);

    /**
     * 更新一个角色的信息
     * @param role
     */
    void update (Role role);

    /**
     * 更新角色状态
     * @param role
     */
    void updateState (Role role);

    /**
     * 查找对应id的角色信息
     * @param id
     */
    Role findOne (Integer id);


    /**
     * 查询一组角色列表
     * @param data
     * @return
     */
    List<Role> selectList(Map<String,Object> data);
}
