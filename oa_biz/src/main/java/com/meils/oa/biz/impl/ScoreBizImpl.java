package com.meils.oa.biz.impl;

import com.meils.oa.biz.ScoreBiz;
import com.meils.oa.dao.ScoreDao;
import com.meils.oa.entity.SC;
import com.meils.oa.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("ScoreBiz")
public class ScoreBizImpl implements ScoreBiz {

    @Autowired
    private ScoreDao scoreDao;


    public List<Score> getScoreList(Integer[] ids, Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("idList", ids);
        return scoreDao.selectList(data);
    }

    public Integer gettotal(Integer[] ids) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("idList", ids);
        return scoreDao.selectNums(data);

    }

    public List<Score> selectList1(Integer sId, Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        data.put("sId", sId);
        return scoreDao.selectList1(data);
    }

    public Integer gettotal1(Integer sId) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sId", sId);
        return scoreDao.selectNums1(data);
    }

    public void update(Score score) {
        scoreDao.update(score);
    }
}
