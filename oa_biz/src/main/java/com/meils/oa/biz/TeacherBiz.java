package com.meils.oa.biz;

import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Room;
import com.meils.oa.entity.Teacher;

import java.util.List;

/**
 * 教师业务接口
 */
public interface TeacherBiz {
    /**
     * 添加教师
     * @param teacher
     */
    void addTeacher(Teacher teacher, Admin a);

    /**
     * 编辑教师信息
     * @param teacher
     */
    void editTeacher(Teacher teacher);

    /**
     * 更新教师状态
     * @param teacher
     */
    void updateState(Teacher teacher);


    /**
     * 更新教师信息
     * @param teacher
     */
    void updateInfo(Teacher teacher);

    /**
     * 获取某一个教师信息
     * @param id
     * @return
     */
    Teacher getTeacherOne(Integer id);

    /**
     * 获取某一个教师信息
     * @param tNum
     * @return
     */
    Teacher getTeacherOneByNum(String tNum);

    /**
     * 获取总数
     * @return
     */
    int selectNums();

    /**
     * 获取最大id
     * @return
     */
    int getMaxId();

    /**
     * 获取学生列表
     * @param page     页数 1开始
     * @param pageSize 页内数量 10
     * @return
     */
    List<Teacher> getTeacherList(Integer page, Integer pageSize);


    List<Teacher> getAllTeacher();
}
