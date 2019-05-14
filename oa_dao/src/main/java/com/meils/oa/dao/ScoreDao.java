package com.meils.oa.dao;


import com.meils.oa.entity.SC;
import com.meils.oa.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("ScoreDao")
public interface ScoreDao {

    Integer selectNums(Map<String,Object> data);

    List<Score> selectList(Map<String,Object> data);


    Integer selectNums1(Map<String,Object> data);

    List<Score> selectList1(Map<String,Object> data);


    void update(Score score);

}
