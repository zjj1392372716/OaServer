package com.meils.oa.biz.impl;

import com.meils.oa.biz.TeacherBiz;
import com.meils.oa.dao.AdminDao;
import com.meils.oa.dao.TeacherDao;
import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师业务处理
 */
@Service("TeacherBiz")
public class TeacherBizImpl implements TeacherBiz {

    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private AdminDao adminDao;


    public void addTeacher(Teacher teacher, Admin a) {

        teacherDao.insert(teacher);
        // 生成一个用户
        adminDao.insert(a);
    }

    public void editTeacher(Teacher teacher) {
        teacherDao.update(teacher);
    }

    public void updateState(Teacher teacher) {
        teacherDao.updateState(teacher);
    }

    public void updateInfo(Teacher teacher) {
        teacherDao.updateInfo(teacher);
    }

    public Teacher getTeacherOne(Integer id) {
        return teacherDao.findOne(id);
    }

    public Teacher getTeacherOneByNum(String tNum) {
        return teacherDao.findOne1(tNum);
    }

    public int selectNums() {
        return teacherDao.selectNums();
    }

    public int getMaxId() {
        return teacherDao.getMaxId();
    }

    public List<Teacher> getTeacherList(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return teacherDao.selectList(data);
    }

    public List<Teacher> getAllTeacher() {
        return teacherDao.getAll();
    }
}
