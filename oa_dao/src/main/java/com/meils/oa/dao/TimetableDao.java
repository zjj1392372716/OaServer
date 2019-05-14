package com.meils.oa.dao;


import com.meils.oa.entity.Student;
import com.meils.oa.entity.Timetable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 课表
 */

@Repository("TimetableDao")
public interface TimetableDao {

    /**
     * 插入一个数据
     * @param timetable
     */
    void insert (Timetable timetable);

    /**
     * 更新一个信息
     * @param timetable
     */
    void update (Timetable timetable);

    /**
     * 删除
     * @param id
     */
    void delete (Integer id);


    /**
     * 查找总数
     */
    int selectNums ();


    /**
     * 根据课程id查询到所有的课表cId
     * @param id
     * @param id
     */
    List<Timetable> findByCourse (Integer id);

    /**
     * 查询课程信息
     * @param data
     * @return
     */

    List<Timetable> selectList(Map<String,Object> data);

    /**
     * 获取全部课表
     * @return
     */
    List<Timetable> selectList1();


    /**
     * 获取全部课表
     * @return
     */
    List<Timetable> selectList2(Integer[] idList);

}
