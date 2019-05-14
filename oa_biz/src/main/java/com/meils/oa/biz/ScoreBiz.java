package com.meils.oa.biz;

import com.meils.oa.entity.SC;
import com.meils.oa.entity.Score;

import java.util.List;

public interface ScoreBiz {

    List<Score> getScoreList(Integer[] ids, Integer page, Integer pageSize);
    Integer gettotal(Integer[] ids);

    List<Score> selectList1(Integer sId, Integer page, Integer pageSize);
    Integer gettotal1(Integer sId);
    void update(Score score);
}
