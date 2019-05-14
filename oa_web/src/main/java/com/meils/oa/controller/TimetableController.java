package com.meils.oa.controller;


import com.meils.oa.biz.RoleBiz;
import com.meils.oa.biz.SCBiz;
import com.meils.oa.biz.TimetableBiz;
import com.meils.oa.dao.TimetableDao;
import com.meils.oa.entity.*;
import com.meils.oa.global.JsonResponse;
import com.meils.oa.global.ListContainer;
import com.meils.oa.global.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * 课表管理
 */

@Controller("TimetableController")
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableBiz timetableBiz;
    @Autowired
    private RedisUtils redisUtils;  //记得注入
    @Autowired
    private SCBiz scBiz;
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
     * 添加课表
     * @param timetable
     * @return
     */
    @RequestMapping(value = "/addTimetable", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse addTimetable(Timetable timetable) {
        String token = timetable.getToken();
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
            long timestamp = new Date().getTime();
            String l = String.valueOf(timestamp);
            timetable.setCreateTime(l);
            Lessons lessons = new Lessons();
            lessons.setIsSelect(1);
            lessons.setLessonId(timetable.getLessonId());

            timetableBiz.addTimetable(timetable, lessons);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }


    /**
     * 获取课表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCourseList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getCourseList(@RequestParam(value="page", required = true) Integer page,
                               @RequestParam(value="pageSize", required = false) Integer pageSize,
                               @RequestParam(value="token", required = false) String token) {
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
            int nums = timetableBiz.selectNums();
            List<Timetable> l = timetableBiz.getTimeTable(page, pageSize);
            ListContainer list = new ListContainer<Timetable>(l);
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
     * 获取课表
     * @return
     */
    @RequestMapping(value = "/getCourseList1", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getCourseList1(
                               @RequestParam(value="token", required = false) String token) {

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
            List<Timetable> l = timetableBiz.getTimeTable1();
            ListContainer list = new ListContainer<Timetable>(l);
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


    /**
     * 获取某个学生的课表
     * @return
     */
    @RequestMapping(value = "/getCourseList2", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getCourseList2(
            @RequestParam(value="token", required = false) String token, Integer sId) {

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

            // TODO: 首先获取该同学所选的全部课程
            List<SC> ls = scBiz.getSCList1(sId);
            Integer[] idList = new Integer[ls.size()];
            for( int i = 0 ; i < ls.size() ; i++) {
                idList[i] = ls.get(i).getCourseId();
            }
            List<Timetable> l = timetableBiz.getTimeTable2(idList);
            ListContainer list = new ListContainer<Timetable>(l);
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

    /**
     * 通过课程id获取所有的cId
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/findByCourse", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse findByCourse(@RequestParam(value="id", required = true) Integer id,
                               @RequestParam(value="token", required = false) String token) {

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
            List<Timetable> l = timetableBiz.findByCourse(id);
            ListContainer list = new ListContainer<Timetable>(l);
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

}
