package com.meils.oa.dao;
import com.meils.oa.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 教室 DAO 接口
 */
@Repository("RoomDao")
public interface RoomDao {
    /**
     * 插入一个数据
     * @param room
     */
    void insert (Room room);

    /**
     * 更新一个信息
     * @param room
     */
    void update (Room room);

    /**
     * 更新状态
     * @param room
     */
    void updateState (Room room);

    /**
     * 查找对应id的信息
     * @param id
     * @param id
     */
    Room findOne (Integer id);

    /**
     * 查找总数
     */
    int selectNums ();

    /**
     * 查询一组列表
     * @param data
     * @return
     */
    List<Room> selectList(Map<String,Object> data);

    /**
     * 查询一组列表
     * @return
     */
    List<Room> getAll();


}
