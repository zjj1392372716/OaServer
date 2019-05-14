package com.meils.oa.controller;

import com.meils.oa.biz.AdminBiz;
import com.meils.oa.biz.StudentBiz;
import com.meils.oa.entity.Admin;
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

@Controller("StudentController")
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentBiz studentBiz;
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
     * 获取学生列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
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
            int nums = studentBiz.selectNums();
            List<Student> l = studentBiz.getStudentList(page, pageSize);
            ListContainer list = new ListContainer<Student>(l);
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
     * 添加学生
     * @param student
     * @in    sName
     * @in    sNum
     * @in    classId
     * @in    sAge
     * @in    startTime
     * @return
     */
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addStudent(Student student) {

        String token = student.getToken();
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
            student.setCreateTime(l);


            // 创建用户
            Admin a = new Admin();
            // TODO:
            a.setUsername(student.getsNum()); // 用户名 = 学号
            String pass = student.getsNum() + student.getStartTime();
            pass = pass.replace("-", "");
            pass = pass.substring(0, pass.length() - 4);
            // pass = 学号 + 入学年份


            // 加密处理
            String pass1 = pass + GlobalVar.TOKEN_MD5_KEY + "0000";
            // password + GlobalVar.TOKEN_MD5_KEY + "0000"
            String s = Md5Utils.Md5(pass1);


            a.setToken(s);
            a.setCreateTime(l);
            a.setRoleId(2);
            System.out.println(a);
            studentBiz.addStudent(student, a);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }

    /**
     * 获取一个学生信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getStudentOne", method = RequestMethod.POST, params = "id")
    public @ResponseBody JsonResponse<Student> getStudentOne(@RequestParam(value="id", required = true) Integer id,@RequestParam(value="token", required = true) String token ) {

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
            Student d = studentBiz.getStudentOne(id);
            return new JsonResponse<Student>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }

    /**
     * 更新一个学生的信息
     * @param student
     * @in    sName
     * @in    sNum
     * @in    classId
     * @in    sAge
     * @in    startTime
     * @return
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateStudent(Student student) {

        String token = student.getToken();

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
            studentBiz.editStudent(student);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

    /**
     * 更新一个班级的状态
     * @param student
     * @in  sId
     * @in  isDelete
     * @return
     */
    @RequestMapping(value = "/updateStudentState", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateStudentState(Student student) {

        String token = student.getToken();

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
            studentBiz.updateState(student);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }



    /**
     * 获取一个学生信息,通过学号
     * @param num
     * @return
     */
    @RequestMapping(value = "/getStudentOneByNum", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<Student> getStudentOneByNum(@RequestParam(value="num", required = true) String num,
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
            Student d = studentBiz.findOneByNum(num);
            return new JsonResponse<Student>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }
}
