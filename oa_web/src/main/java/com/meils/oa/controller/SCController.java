package com.meils.oa.controller;


import com.meils.oa.biz.CourseBiz;
import com.meils.oa.biz.SCBiz;
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

import java.util.Date;
import java.util.List;

@Controller("SCController")
@RequestMapping("/sc")

public class SCController {


    @Autowired
    private SCBiz scBiz;
    @Autowired
    private RedisUtils redisUtils;  //记得注入
    @Autowired
    private CourseBiz courseBiz;


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
     * 选课
     * @return
     */
    @RequestMapping(value = "/addSC", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse addSC(SC sc) {
        String token = sc.getToken();
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

            // 首先查询是否已经选过此课程
            SC s = scBiz.findOne(sc);
            if(s != null && s.getScId() != null) {
                return new JsonResponse(-1, "已经添加过该课程");
            } else {

                long timestamp = new Date().getTime();
                String l = String.valueOf(timestamp);
                sc.setCreateTime(l);
                scBiz.addSC(sc);
                return new JsonResponse<ListContainer>(1, "添加成功");
            }

        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }


    /**
     * 查询某一个学生的所选的课程
     * @param page
     * @param pageSize
     * @param sId
     * @param token
     * @return
     */
    @RequestMapping(value = "/getScList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getScListBySid(@RequestParam(value="page", required = true) Integer page,
                             @RequestParam(value="pageSize", required = false) Integer pageSize,
                             @RequestParam(value="sId", required = false) Integer sId,
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
            int nums = scBiz.selectNums(sId);
            List<SC> l = scBiz.getSCList(page, pageSize, sId);
            ListContainer list = new ListContainer<SC>(l);
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
     * 退课
     * @return
     */
    @RequestMapping(value = "/deleteSc", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse deleteSc(String token, Integer id) {

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
            scBiz.delete(id);
            return new JsonResponse(1, "退课成功");

        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "退课失败");
        }
    }

    /**
     * 查询选课的学生
     * @return
     */
    @RequestMapping(value = "/getSelectStu", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getSelectStu(String token, Integer tId, @RequestParam(value="page", required = true) Integer page,
                              @RequestParam(value="pageSize", required = false) Integer pageSize) {
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




            List<Course> ls = courseBiz.findByTId(tId);
            Integer[] idList = new Integer[ls.size()];
            for( int i = 0 ; i < ls.size() ; i++) {
                idList[i] = ls.get(i).getCourseId();
            }
            List<SC> l = scBiz.getSCList2(idList, page, pageSize);
            ListContainer list = new ListContainer<SC>(l);
            int nums = scBiz.getSCList3(idList);
            list.setPage(page);
            list.setPageSize(pageSize);
            int pages = (int)Math.ceil((double)nums / pageSize);
            list.setTotalpages(pages);
            list.setTotals(nums);
            return new JsonResponse<ListContainer>(1, "获取成功", list);


        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "退课失败");
        }
    }

}
