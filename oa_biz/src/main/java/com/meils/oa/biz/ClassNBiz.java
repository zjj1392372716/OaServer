package com.meils.oa.biz;


import com.meils.oa.entity.ClassN;

import java.util.List;


/**
 * 班级业务处理
 */


public interface ClassNBiz {

    /**
     * 添加班级
     * @param _class
     */
    void addClass(ClassN _class);

    /**
     * 编辑班级信息
     * @param _class
     */
    void editClass(ClassN _class);

    /**
     * 更新班级状态
     * @param _class
     */
    void updateState(ClassN _class);


    /**
     * 获取某一个班级信息
     * @param id
     * @return
     */
    ClassN getClassOne(Integer id);

    /**
     * 获取总数
     * @return
     */
    ClassN getTotal();

    /**
     * 获取班级列表
     * @param page     页数 1开始
     * @param pageSize 页内数量 10
     * @return
     */
    List<ClassN> getClassList(Integer page, Integer pageSize);
}
