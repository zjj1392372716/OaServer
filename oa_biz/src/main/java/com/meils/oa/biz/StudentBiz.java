package com.meils.oa.biz;

import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Student;

import java.util.List;

/**
 * 学生业务处理接口
 */
public interface StudentBiz {
    /**
     * 添加学生
     * @param student
     */
    void addStudent(Student student, Admin a);

    /**
     * 编辑学生信息
     * @param student
     */
    void editStudent(Student student);

    /**
     * 更新学生状态
     * @param student
     */
    void updateState(Student student);


    /**
     * 获取某一个学生信息
     * @param id
     * @return
     */
    Student getStudentOne(Integer id);

    /**
     * 获取总数
     * @return
     */
    int selectNums();

    /**
     * 获取学生列表
     * @param page     页数 1开始
     * @param pageSize 页内数量 10
     * @return
     */
    List<Student> getStudentList(Integer page, Integer pageSize);


    Student findOneByNum(String num);

}
