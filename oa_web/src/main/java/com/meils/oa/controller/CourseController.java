package com.meils.oa.controller;


import com.meils.oa.biz.CourseBiz;
import com.meils.oa.entity.Course;
import com.meils.oa.entity.Role;
import com.meils.oa.entity.Student;
import com.meils.oa.global.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 课程 控制器
 */
@Controller("CourseController")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseBiz courseBiz;
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
     * 添加课程
     * @param course
     * @in    roleName      角色名称
     * @in    weight        权重
     * @return
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse addRole(Course course) {
        String token = course.getToken();
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
            course.setCreateTime(l);
            //生成一个编号吧

            // 编号： COUR 20190501 5
            String num = "COUR";
            num += Utils.formdata(course.getCreateTime());
            Integer maxId = courseBiz.getMaxId();
            num += ++maxId;
            num = num.replace("-", "");
            System.out.println(num);
            course.setCourseNum(num);

            try{
                courseBiz.addCourse(course);
                // 返回id
                BackCommon b = new BackCommon<String>(num);
//
                return new JsonResponse<BackCommon>(1, "添加成功", b);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return new JsonResponse(-1, "添加失败");
            }



        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }

    /**
     * 选择任课教师
     * @param course
     * @in  courseId
     * @in  tId
     * @return
     */
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRoleState(Course course) {
        String token = course.getToken();
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
            courseBiz.updateTeacher(course);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 通过课程编号查询课程信息
     * @param courseNum
     * @param token
     * @return
     */
    @RequestMapping(value = "/getCourseByNum", method = RequestMethod.POST)
    public @ResponseBody JsonResponse getCourseByNum(String courseNum, String token) {

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
            Course c = new Course();
            c.setCourseNum(courseNum);
            Course cc = courseBiz.findOneByNum(c);

            return new JsonResponse(1, "更新成功", cc);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }


    /**
     * 删除课程
     * @in  id
     * @return
     */
    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public @ResponseBody JsonResponse deleteCourse(Integer id, String token) {

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
            courseBiz.delete(id);
            return new JsonResponse(1, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "删除失败");
        }
    }

    /**
     * 获取课程列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getCourseList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getStudentList(@RequestParam(value="page", required = true) Integer page, @RequestParam(value="pageSize", required = false) Integer pageSize, @RequestParam(value="token", required = false) String token) {
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
            int nums = courseBiz.getTotalNum();
            List<Course> l = courseBiz.getCourseList(page, pageSize);
            ListContainer list = new ListContainer<Course>(l);
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
     * 查询所任课程
     * @param tId
     * @return
     */
    @RequestMapping(value = "/getCourseListByTid", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getCourseListByTid(@RequestParam(value="tId", required = true) Integer tId,
                                @RequestParam(value="token", required = true) String token) {
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
            List<Course> l = courseBiz.findByTId(tId);
            ListContainer list = new ListContainer<Course>(l);
            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }



}
