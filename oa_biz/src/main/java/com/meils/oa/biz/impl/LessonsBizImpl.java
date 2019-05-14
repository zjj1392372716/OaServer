package com.meils.oa.biz.impl;

import com.meils.oa.biz.LessonsBiz;
import com.meils.oa.dao.LessonsDao;
import com.meils.oa.entity.Lessons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节次业务处理
 */
@Service("LessonsBiz")
public class LessonsBizImpl implements LessonsBiz {

    @Autowired
    private LessonsDao lessonsDao;


    // 获取未选择的节次
    public List<Lessons> getLessonsList() {
        return lessonsDao.selectList();
    }

    public void updateSelect(Lessons lessons) {
        lessonsDao.updateSelect(lessons);
    }

    public List<Lessons> getAllLessons() {
        return lessonsDao.getAll();
    }

}
