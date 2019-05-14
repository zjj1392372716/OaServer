package com.meils.oa.dao;

import com.meils.oa.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 课程DAO 接口
 */
@Repository("CourseDao")
public interface CourseDao {
    /**
     * 插入一个数据
     * @param course
     */
    void insert (Course course);

    /**
     * 更新基本信息
     * @param course
     */
    void update (Course course);

    /**
     * 更新教师信息，选择任课教师
     * @param course
     */
    void updateTeacher (Course course);

    /**
     * 删除
     * @param id
     */
    void delete (Integer id);

    /**
     * 查找对应id的信息
     * @param id
     */
    Course findOne (Integer id);

    /**
     * 查找对应id的信息
     * @param course
     */
    Course findOneByNum (Course course);

    /**
     * 查找某个老师的任教课程
     * @param tId
     */
    List<Course> findByTId (Integer tId);

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
    List<Course> selectList(Map<String,Object> data);




}
