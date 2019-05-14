package com.meils.oa.dao;

import com.meils.oa.entity.Admin;
import org.springframework.stereotype.Repository;

/**
 * 角色DAO 接口
 */
@Repository("AdminDao")
public interface AdminDao {

    /**
     * 查找某一个用户
     * @param
     */
    Admin findOne (Admin a);

    /**
     * 插入
     * @param a
     */
    void insert (Admin a);
}


