package com.meils.oa.biz.impl;

import com.meils.oa.biz.ClassNBiz;
import com.meils.oa.dao.ClassNDao;
import com.meils.oa.entity.ClassN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ClassNBiz")
public class ClassNBizImpl implements ClassNBiz {

    @Autowired
    private ClassNDao classNDao;

    public void addClass(ClassN _class) {
        classNDao.insert(_class);
    }

    public void editClass(ClassN _class) {
        classNDao.update(_class);
    }

    public void updateState(ClassN _class) {
        classNDao.updateState(_class);
    }

    public ClassN getClassOne(Integer id) {
        return classNDao.findOne(id);
    }

    public ClassN getTotal() {
        return classNDao.getTotal();
    }


    public List<ClassN> getClassList(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return classNDao.selectList(data);
    }
}
