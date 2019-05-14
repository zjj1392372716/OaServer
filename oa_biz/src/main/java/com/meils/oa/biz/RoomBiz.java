package com.meils.oa.biz;

import com.meils.oa.entity.Room;

import java.util.List;

/**
 * 教室业务处理
 */
public interface RoomBiz {

    /**
     * 添加教室
     * @param room
    */
    void addRoom(Room room);

    /**
     * 编辑教室信息
     * @param room
     */
    void editRoom(Room room);

    /**
     * 更新教室状态
     * @param room
     */
    void updateState(Room room);


    /**
     * 获取某一个教室信息
     * @param id
     * @return
     */
    Room getRoomOne(Integer id);

    /**
     * 获取总数
     * @return
     */
    int selectNums();

    /**
     * 获取列表
     * @param page     页数 1开始
     * @param pageSize 页内数量 10
     * @return
     */
    List<Room> getRoomList(Integer page, Integer pageSize);


    /**
     * 获取全部可用教室
     * @return
     */
    List<Room> getAllRooms();

}
