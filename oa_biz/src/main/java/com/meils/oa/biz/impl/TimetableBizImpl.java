package com.meils.oa.biz.impl;

import com.meils.oa.biz.LessonsBiz;
import com.meils.oa.biz.TimetableBiz;
import com.meils.oa.dao.LessonsDao;
import com.meils.oa.dao.TimetableDao;
import com.meils.oa.entity.Lessons;
import com.meils.oa.entity.Timetable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 课表业务处理
 */
@Service("TimetableBiz")
public class TimetableBizImpl implements TimetableBiz {

    @Autowired
    private TimetableDao timetableDao;
    @Autowired
    private LessonsDao lessonsDao;
    public void addTimetable(Timetable timetable, Lessons lessons) {

        lessonsDao.updateSelect(lessons);
        // 添加课表的同时还需要去将lesson中的节次设为被选择
        timetableDao.insert(timetable);
    }

    public int selectNums() {
        return timetableDao.selectNums();
    }

    public void editTimetable(Timetable timetable) {
        timetableDao.update(timetable);
    }

    public List<Timetable> getTimeTable(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return timetableDao.selectList(data);
    }

    public List<Timetable> getTimeTable1() {
        return timetableDao.selectList1();
    }

    public List<Timetable> getTimeTable2(Integer[] idList) {
        return timetableDao.selectList2(idList);
    }

    public List<Timetable> findByCourse(Integer id) {
        return timetableDao.findByCourse(id);
    }
}
