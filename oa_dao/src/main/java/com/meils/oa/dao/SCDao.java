package com.meils.oa.dao;

import com.meils.oa.entity.SC;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("SCDao")
public interface SCDao {


    void insert(SC sc);

    /**
     * 查询是否已经选择过该课程（课程id + 学生id）
     * @param sc
     * @return
     */
    SC findOne(SC sc);

    List<SC> selectList(Map<String,Object> data);
    List<SC> selectList1(Integer sId);
    Integer selectNums(Integer sId);



    List<SC> selectList2(Map<String,Object> data);

    Integer selectList3(Map<String,Object> data);
    void delete(Integer id);

}
