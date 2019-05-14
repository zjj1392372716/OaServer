package com.meils.oa.biz;

import com.meils.oa.entity.Lessons;

import java.util.List;

public interface LessonsBiz {

    /**
     * 获取班级列表
     * @return
     */
    List<Lessons> getLessonsList();

    void updateSelect(Lessons lessons);

    List<Lessons> getAllLessons();
}
