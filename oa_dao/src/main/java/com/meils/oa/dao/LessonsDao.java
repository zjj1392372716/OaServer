package com.meils.oa.dao;


import com.meils.oa.entity.Lessons;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 节次 DAO 接口
 */
@Repository("LessonsDao")
public interface LessonsDao {
    /**
     * 查询一组列表
     * @return
     */
    List<Lessons> selectList();

    /**
     * 将某个lesson设置状态
     * @param lessons
     */
    void updateSelect(Lessons lessons);

    List<Lessons> getAll();

}
