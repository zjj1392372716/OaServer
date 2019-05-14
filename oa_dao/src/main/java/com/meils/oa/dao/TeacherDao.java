package com.meils.oa.dao;

import com.meils.oa.entity.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 教师 DAO 接口
 */
@Repository("TeacherDao")
public interface TeacherDao {

    /**
     * 插入一个数据
     * @param teacher
     */
    void insert (Teacher teacher);

    /**
     * 更新一个信息
     * @param teacher
     */
    void update (Teacher teacher);

    /**
     * 更新状态
     * @param teacher
     */
    void updateState (Teacher teacher);


    /**
     * 更新教师信息
     * @param teacher
     */
    void updateInfo (Teacher teacher);

    /**
     * 查找对应id的信息
     * @param id
     * @param id
     */
    Teacher findOne (Integer id);

    /**
     * 查找对应id的信息
     * @param tNum
     */
    Teacher findOne1 (String tNum);

    /**
     * 查找总数
     */
    int selectNums ();
    /**
     * 获取最大id
     */

    int getMaxId();

    /**
     * 查询一组列表
     * @param data
     * @return
     */
    List<Teacher> selectList(Map<String,Object> data);


    List<Teacher> getAll();
}
