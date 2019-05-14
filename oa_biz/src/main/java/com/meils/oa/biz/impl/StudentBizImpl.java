package com.meils.oa.biz.impl;

import com.meils.oa.biz.StudentBiz;
import com.meils.oa.dao.AdminDao;
import com.meils.oa.dao.StudentDao;
import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生业务处理
 */
@Service("StudentBiz")
public class StudentBizImpl implements StudentBiz {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private AdminDao adminDao;

    public void addStudent(Student student, Admin a) {
        adminDao.insert(a);
        studentDao.insert(student);
    }

    public void editStudent(Student student) {
        studentDao.update(student);
    }

    public void updateState(Student student) {
        studentDao.updateState(student);
    }

    public Student getStudentOne(Integer id) {
        return studentDao.findOne(id);
    }

    public int selectNums() {
        return studentDao.selectNums();
    }

    public List<Student> getStudentList(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return studentDao.selectList(data);
    }

    public Student findOneByNum(String num) {
        return studentDao.findOneByNum(num);
    }
}
