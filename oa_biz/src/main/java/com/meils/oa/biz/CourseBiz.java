package com.meils.oa.biz;

import com.meils.oa.entity.Course;
import com.meils.oa.entity.Timetable;

import java.util.List;

/**
 * 课程业务管理
 */
public interface CourseBiz {

    /**
     * 添加课程
     * @param course
     */
    void addCourse(Course course);

    /**
     * 编辑学生信息
     * @param course
     */
    void editCourse(Course course);

    /**
     * 删除课程
     * @param id
     */
    void delete(Integer id);

    Course findOneByNum(Course course);

    List<Course> findByTId(Integer tId);

    /**
     * 获取最大id
     * @return
     */
    int getMaxId();

    /**
     * 选择任课教师
     * @param course
     */
    void updateTeacher(Course course);

    Integer getTotalNum();

    /**
     * 查询课程列表（供选课使用）
     * @param page
     * @param pageSize
     * @return
     */
    List<Course> getCourseList(Integer page, Integer pageSize);
}
