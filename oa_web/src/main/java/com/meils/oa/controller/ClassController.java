package com.meils.oa.controller;


import com.meils.oa.biz.ClassNBiz;
import com.meils.oa.entity.ClassN;
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

@Controller("ClassController")
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassNBiz classNBiz;

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
     * 获取班级列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getClassList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getClassList(@RequestParam(value="page", required = true) Integer page, @RequestParam(value="pageSize", required = false) Integer pageSize, @RequestParam(value="token", required = false) String token) {
        if(pageSize == null) {
            pageSize = 10;
        }
        if(page == null) {
            page = 1;
        }
        // 校验token

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
            ClassN c = classNBiz.getTotal();
            List<ClassN> l = classNBiz.getClassList(page, pageSize);
            ListContainer list = new ListContainer<ClassN>(l);
            list.setPage(page);
            list.setPageSize(pageSize);
            int pages = (int)Math.ceil( (double)c.getTotalNum() / pageSize);
            list.setTotalpages(pages);
            list.setTotals(c.getTotalNum());
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

    /**
     * 添加班级
     * @param classN
     * @in    className
     * @return
     */
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addClass(ClassN classN) {
        String token = classN.getToken();
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
            classNBiz.addClass(classN);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }

    /**
     * 获取一个班级
     * @param id
     * @return
     */
    @RequestMapping(value = "/getClassOne", method = RequestMethod.POST, params = "id")
    public @ResponseBody JsonResponse<ClassN> getClassOne(@RequestParam(value="id", required = true) Integer id, @RequestParam(value="token", required = true) String token) {

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
            ClassN d = classNBiz.getClassOne(id);
            return new JsonResponse<ClassN>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


    /**
     * 更新一个班级信息
     * @param d
     * @in  classId
     * @in  className
     * @return
     */
    @RequestMapping(value = "/updateClass", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateClass(ClassN d) {
        String token = d.getToken();
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
            classNBiz.editClass(d);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 更新一个班级的状态
     * @param d
     * @in  classId
     * @in  isDelete
     * @return
     */
    @RequestMapping(value = "/updateClassState", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateClassState(ClassN d) {
        String token = d.getToken();
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
            classNBiz.updateState(d);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }



}
