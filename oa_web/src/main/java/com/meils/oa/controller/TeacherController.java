package com.meils.oa.controller;

import com.meils.oa.biz.RoomBiz;
import com.meils.oa.biz.TeacherBiz;
import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Room;
import com.meils.oa.entity.Teacher;
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
 * 教师管理
 */

@Controller("TeacherController")
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherBiz teacherBiz;
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
     * 获取教师列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getTeacherList", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse getTeacherList(@RequestParam(value="page", required = true) Integer page, @RequestParam(value="pageSize", required = false) Integer pageSize, @RequestParam(value="token", required = false) String token) {
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
            int nums = teacherBiz.selectNums();
            List<Teacher> l = teacherBiz.getTeacherList(page, pageSize);
            ListContainer list = new ListContainer<Teacher>(l);
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
     * 添加教师
     * @param teacher
     * @in    tName
     * @in    tPhone
     * @in    tPreCourse
     * @in    startTime
     * @in    tMajor
     * @return
     */
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addTeacher(Teacher teacher) {

        String token = teacher.getToken();
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
            long timestamp = new Date().getTime();
            String l = String.valueOf(timestamp);
            teacher.setCreateTime(l);
            // 生成一个numer教工编号

            // 教工编号： TEA 20190501 5
            String num = "TEA";
            num += teacher.getStartTime();
            // 获取id
            int maxId = 99999999;
            maxId = teacherBiz.getMaxId();
            num += ++maxId;
            num = num.replace("-", "");
            teacher.settNum(num);

            Admin a  = new Admin();
            // TODO:
            a.setUsername(teacher.gettNum()); // 用户名 = 职工号
            String pass = teacher.gettNum() + teacher.getStartTime();
            pass = pass.replace("-", "");
            pass = pass.substring(0, pass.length() - 4);
            // pass = 职工号 + 入学年份

            // 加密处理
            String pass1 = pass + GlobalVar.TOKEN_MD5_KEY + "0000";
            // password + GlobalVar.TOKEN_MD5_KEY + "0000"
            String s = Md5Utils.Md5(pass1);

            // 设置token
            a.setToken(s);
            a.setCreateTime(l);
            a.setRoleId(3);
            teacherBiz.addTeacher(teacher, a);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }


    /**
     * 获取一个教师信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTeracherOne", method = RequestMethod.POST, params = "id")
    public @ResponseBody JsonResponse<Teacher> getTeracherOne(@RequestParam(value="id", required = true) Integer id,
                                                              @RequestParam(value="token", required = true) String token ) {

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
            Teacher d = teacherBiz.getTeacherOne(id);
            return new JsonResponse<Teacher>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


    /**
     * 获取一个教师信息
     * @param num
     * @return
     */
    @RequestMapping(value = "/getTeracherOneByNum", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<Teacher> getTeracherOneByNum(
            @RequestParam(value="num", required = true) String num,
            @RequestParam(value="token", required = true) String token ) {

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
            Teacher d = teacherBiz.getTeacherOneByNum(num);
            return new JsonResponse<Teacher>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }




    /**
     * 更新一个教师的信息
     * @param teacher
     * @in    tId
     * @in    tName
     * @in    tPhone
     * @in    tPreCourse
     * @in    tMajor
     * @return
     */
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateTeacher(Teacher teacher) {

        String token = teacher.getToken();

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
            teacherBiz.editTeacher(teacher);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 更新一个教师的信息(教师身份)
     * @param teacher
     * @in    tPhone
     * @in    tMajor
     * @in    tPreCourse
     * @in    tNum
     * @return
     */
    @RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateTeacherInfo(Teacher teacher) {

        String token = teacher.getToken();

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
            teacherBiz.updateInfo(teacher);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 更新一个教师的状态
     * @param teacher
     * @in  tId
     * @in  isDelete
     * @return
     */
    @RequestMapping(value = "/updateTeacherState", method = RequestMethod.POST)
    public @ResponseBody
    JsonResponse updateTeacherState(Teacher teacher) {
        String token = teacher.getToken();
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
            teacherBiz.updateState(teacher);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }



    /**
     * 获取全部教师
     * @return
     */
    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.POST)
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

            List<Teacher> l = teacherBiz.getAllTeacher();
            ListContainer list = new ListContainer<Teacher>(l);

            return new JsonResponse<ListContainer>(1, "获取成功", list);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

}
