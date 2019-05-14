package com.meils.oa.controller;

import com.meils.oa.biz.AdminBiz;
import com.meils.oa.entity.Admin;
import com.meils.oa.entity.Role;
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
 * 管理员 controller
 */
@Controller("AdminController")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RedisUtils redisUtils;  //记得注入
    @Autowired
    private AdminBiz adminBiz;
    /**
     * 管理员登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JsonResponse<Admin> Login(@RequestParam(value="username", required = true) String username, @RequestParam(value="password", required = false) String password) {

        if("".equals(username) || "".equals(password)) {
            return new JsonResponse(-1, "参数不完整", null);
        }

        // 查询数据库是否存在
        String pass = password + GlobalVar.TOKEN_MD5_KEY + "0000";
        // password + GlobalVar.TOKEN_MD5_KEY + "0000"
        // md5加密
        String token = Md5Utils.Md5(pass);
        try {
            Admin a = new Admin();
            a.setUsername(username);
            a.setToken(token);
            Admin r = adminBiz.findOne(a);
            if(r.getUsername()!=null) {
                // 存在的情况下，创建一个token存入redis
                String token1 = token + GlobalVar.TOKEN_MD5_KEY + new Date().getTime();
                String session_token = Md5Utils.Md5(token1);
                try{
                    redisUtils.set(session_token, "success", (long) 12000000);
                    System.out.println(session_token);
                    Admin resultObj = new Admin();
                    resultObj.setUsername(r.getUsername());
                    resultObj.setToken(session_token);
                    resultObj.setRoleId(r.getRoleId());
                    // 组装一个对象
                    return new JsonResponse(1, "登陆成功", resultObj);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new JsonResponse(-1, "登陆失败", null);
                }/**/
            } else {
                return new JsonResponse(-1, "用户名或者密码错误", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "登陆失败", null);
        }
    }


}
