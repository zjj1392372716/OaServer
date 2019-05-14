package com.meils.oa.dao;

import com.meils.oa.entity.ClassN;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 班级DAO 接口
 */
@Repository("ClassNDao")
public interface ClassNDao {
    /**
     * 插入一个数据
     * @param _class
     */
    void insert (ClassN _class);

    /**
     * 更新一个信息
     * @param _class
     */
    void update (ClassN _class);

    /**
     * 更新状态
     * @param _class
     */
    void updateState (ClassN _class);

    /**
     * 查找对应id的信息
     * @param id
     */
    ClassN findOne (Integer id);

    /**
     * 查找总数
     */
    ClassN getTotal ();

    /**
     * 查询一组列表
     * @param data
     * @return
     */
    List<ClassN> selectList(Map<String,Object> data);

}
