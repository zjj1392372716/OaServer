package com.meils.oa.controller;

import com.meils.oa.biz.CourseBiz;
import com.meils.oa.biz.LessonsBiz;
import com.meils.oa.entity.Lessons;
import com.meils.oa.entity.Role;
import com.meils.oa.entity.Teacher;
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
 * 节次 控制器
 */
@Controller("LessonsController")
@RequestMapping("/lessons")
public class LessonsController {

    @Autowired
    private LessonsBiz lessonsBiz;
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
     * 获取列表
     * @return
     */
    @RequestMapping(value = "/getLessonsList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getRoleList(@RequestParam(value="token", required = true) String token) {


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
            List<Lessons> l = lessonsBiz.getLessonsList();
            ListContainer list = new ListContainer<Lessons>(l);
            JsonResponse j = new JsonResponse<ListContainer>(1, "获取成功", list);
            return j;
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }

    }


    /**
     * 获取全部节次
     * @return
     */
    @RequestMapping(value = "/getAllLessons", method = RequestMethod.POST)
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

            List<Lessons> l = lessonsBiz.getAllLessons();
            ListContainer list = new ListContainer<Lessons>(l);

            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

    /**
     * 更新节次状态
     * @return
     */
    @RequestMapping(value = "/updateSelect", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse updateSelect(@RequestParam(value="token", required = false) String token, Integer id) {

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
            Lessons l = new Lessons();
            l.setLessonId(id);
            l.setIsSelect(0);
            lessonsBiz.updateSelect(l);
            return new JsonResponse<ListContainer>(1, "更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }
}
