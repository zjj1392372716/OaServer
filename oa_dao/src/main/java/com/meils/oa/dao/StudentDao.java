package com.meils.oa.dao;
import com.meils.oa.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 学生DAO 接口
 */
@Repository("StudentDao")
public interface StudentDao {
    /**
     * 插入一个数据
     * @param student
     */
    void insert (Student student);

    /**
     * 更新一个信息
     * @param student
     */
    void update (Student student);

    /**
     * 更新状态
     * @param student
     */
    void updateState (Student student);

    /**
     * 查找对应id的信息
     * @param id
     * @param id
     */
    Student findOne (Integer id);

    /**
     * 通过学号查询个人信息
     * @param num
     */
    Student findOneByNum (String num);

    /**
     * 查找总数
     */
    int selectNums ();

    /**
     * 查询一组列表
     * @param data
     * @return
     */
    List<Student> selectList(Map<String,Object> data);
}
