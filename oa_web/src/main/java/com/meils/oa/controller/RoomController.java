package com.meils.oa.controller;


import com.meils.oa.biz.RoomBiz;
import com.meils.oa.entity.Room;
import com.meils.oa.global.JsonResponse;
import com.meils.oa.global.ListContainer;
import com.meils.oa.global.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 教室管理
 */

@Controller("RoomController")
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomBiz roomBiz;
    @Autowired
    private RedisUtils redisUtils;  //记得注入

    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    public boolean checkRedis(String token) {
        // 判断token是否有效
        boolean exists = redisUtils.exists(token);

        if(!exists) {
            return false;
        } else {
            return true;
        }

    }


    /**
     * 获取教室列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getRoomList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getRoomList(@RequestParam(value="page", required = true) Integer page, @RequestParam(value="pageSize", required = false) Integer pageSize, @RequestParam(value="token", required = false) String token) {
        if(pageSize == null) {
            pageSize = 10;
        }
        if(page == null) {
            page = 1;
        }
        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }


        try{
            int nums = roomBiz.selectNums();
            List<Room> l = roomBiz.getRoomList(page, pageSize);
            ListContainer list = new ListContainer<Room>(l);
            list.setPage(page);
            list.setPageSize(pageSize);
            int pages = (int)Math.ceil((double)nums / pageSize);
            list.setTotalpages(pages);
            list.setTotals(nums);
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


    /**
     * 添加教室
     * @param room
     * @in    roomNum
     * @in    peopleNum
     * @in    isMedia
     * @return
     */
    @RequestMapping(value = "/addRoom", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addRoom(Room room) {

        String token = room.getToken();
        // 判断token是否有效

        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try {
            roomBiz.addRoom(room);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }


    /**
     * 获取一个教室信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRoomOne", method = RequestMethod.POST, params = "id")
    public @ResponseBody JsonResponse<Room> getRoomOne(@RequestParam(value="id", required = true) Integer id,@RequestParam(value="token", required = true) String token ) {

        // 判断token是否有效

        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try {
            Room d = roomBiz.getRoomOne(id);
            return new JsonResponse<Room>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }
    /**
     * 更新一个教室的信息
     * @param room
     * @in    roomId
     * @in    roomNum
     * @in    peopleNum
     * @in    isMedia
     * @return
     */
    @RequestMapping(value = "/updateRoom", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRoom(Room room) {

        String token = room.getToken();

        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }

        try {
            roomBiz.editRoom(room);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 更新一个班级的状态
     * @param room
     * @in  roomId
     * @in  isDelete
     * @return
     */
    @RequestMapping(value = "/updateRoomState", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRoomState(Room room) {
        String token = room.getToken();
        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }
        boolean exists = this.checkRedis(token);
        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }
        try {
            roomBiz.updateState(room);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }


    /**
     * 获取全部可用教室
     * @return
     */
    @RequestMapping(value = "/getAllRooms", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getAllRooms(@RequestParam(value="token", required = false) String token) {

        if("".equals(token) || token == null) {
            System.out.println("未传入token");
            return new JsonResponse(-1, "未传入token");
        }

        boolean exists = this.checkRedis(token);

        if(!exists) {
            return new JsonResponse(-1, "身份验证失败");
        } else {
            System.out.println("session_token is ok");
        }


        try{

            List<Room> l = roomBiz.getAllRooms();
            ListContainer list = new ListContainer<Room>(l);

            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

}
