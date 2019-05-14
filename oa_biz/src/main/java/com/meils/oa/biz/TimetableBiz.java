package com.meils.oa.biz;

import com.meils.oa.entity.Lessons;
import com.meils.oa.entity.Student;
import com.meils.oa.entity.Timetable;

import java.util.List;

public interface TimetableBiz {

    /**
     * 添加课表
     * @param timetable
     */
    void addTimetable(Timetable timetable, Lessons lessons);


    /**
     * 获取总数
     * @return
     */
    int selectNums();


    /**
     * 编辑教室信息
     * @param timetable
     */
    void editTimetable(Timetable timetable);

    List<Timetable> getTimeTable(Integer page, Integer pageSize);

    List<Timetable> getTimeTable1();


    List<Timetable> getTimeTable2(Integer[] idList);

    List<Timetable> findByCourse(Integer id);

}
