package com.meils.oa.biz.impl;

import com.meils.oa.biz.SCBiz;
import com.meils.oa.dao.SCDao;
import com.meils.oa.entity.SC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("SCBiz")
public class SCBizImpl implements SCBiz {

    @Autowired
    private SCDao scDao;
    public void addSC(SC sc) {
        scDao.insert(sc);
    }

    public SC findOne(SC sc) {
        return scDao.findOne(sc);
    }

    public List<SC> getSCList(Integer page, Integer pageSize, Integer sId) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("sId", sId);
        return scDao.selectList(data);
    }

    public List<SC> getSCList1(Integer sId) {
        return scDao.selectList1(sId);
    }

    public Integer selectNums(Integer sId) {
        return scDao.selectNums(sId);
    }

    public List<SC> getSCList2(Integer[] ids, Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("idList", ids);
        return scDao.selectList2(data);
    }

    public Integer getSCList3(Integer[] ids) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idList", ids);
        return scDao.selectList3(data);
    }

    public void delete(Integer id) {
        scDao.delete(id);
    }
}
