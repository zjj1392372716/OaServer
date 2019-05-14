package com.meils.oa.biz.impl;

import com.meils.oa.biz.CourseBiz;
import com.meils.oa.dao.CourseDao;
import com.meils.oa.dao.LessonsDao;
import com.meils.oa.dao.TimetableDao;
import com.meils.oa.entity.Course;
import com.meils.oa.entity.Lessons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程业务处理
 */
@Service("CourseBiz")
public class CourseBizImpl implements CourseBiz {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private TimetableDao timetableDao;
    @Autowired
    private LessonsDao lessonsDao;
    public void addCourse(Course course) {
        courseDao.insert(course);
    }

    public void editCourse(Course course) {
        courseDao.update(course);
    }

    public void delete(Integer id) {
        //删除课表
        timetableDao.delete(id);
        // 删除课程
        courseDao.delete(id);
        // 节次回复
    }

    public Course findOneByNum(Course course) {
        return courseDao.findOneByNum(course);
    }

    public List<Course> findByTId(Integer tId) {
        return courseDao.findByTId(tId);
    }

    public int getMaxId() {
        return courseDao.getMaxId();
    }

    public void updateTeacher(Course course) {
        courseDao.updateTeacher(course);
    }

    public Integer getTotalNum() {
        return courseDao.selectNums();
    }

    public List<Course> getCourseList(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return courseDao.selectList(data);
    }


}
