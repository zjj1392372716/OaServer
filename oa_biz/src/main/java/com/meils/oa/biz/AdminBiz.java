package com.meils.oa.biz;

import com.meils.oa.entity.Admin;

/**
 * 管理员业务接口
 */
public interface AdminBiz {

    /**
     * 查找某一个管理员信息
     * @param a
     * @return
     */
    Admin findOne(Admin a);

    void addAdmin(Admin a);

}
