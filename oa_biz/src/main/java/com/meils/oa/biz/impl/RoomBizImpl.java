package com.meils.oa.biz.impl;

import com.meils.oa.biz.RoomBiz;
import com.meils.oa.dao.RoomDao;
import com.meils.oa.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 教室业务处理
 */
@Service("RoomBiz")
public class RoomBizImpl implements RoomBiz {
    @Autowired
    private RoomDao roomDao;

    public void addRoom(Room room) {
        roomDao.insert(room);
    }

    public void editRoom(Room room) {
        roomDao.update(room);
    }

    public void updateState(Room room) {
        roomDao.updateState(room);
    }

    public Room getRoomOne(Integer id) {
        return roomDao.findOne(id);
    }

    public int selectNums() {
        return roomDao.selectNums();
    }

    public List<Room> getRoomList(Integer page, Integer pageSize) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("currIndex", (page-1)*pageSize);
        data.put("pageSize", pageSize);
        return roomDao.selectList(data);
    }

    public List<Room> getAllRooms() {
        return roomDao.getAll();
    }
}
