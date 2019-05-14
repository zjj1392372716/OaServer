package com.meils.oa.biz.impl;

import com.meils.oa.biz.AdminBiz;
import com.meils.oa.dao.AdminDao;
import com.meils.oa.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminBiz")
public class AdminBizImpl implements AdminBiz {

    @Autowired
    private AdminDao adminDao;
    public Admin findOne(Admin a) {
        return adminDao.findOne(a);
    }

    public void addAdmin(Admin a) {
        adminDao.insert(a);
    }

}
