package com.meils.oa.controller;

import com.meils.oa.biz.RoleBiz;
import com.meils.oa.entity.Role;
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
 * 角色 控制器
 */
@Controller("RoleController")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleBiz roleBiz;
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
     * 获取角色列表
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public @ResponseBody JsonResponse getRoleList(@RequestParam(value="page", required = true) Integer page, @RequestParam(value="pageSize", required = false) Integer pageSize, @RequestParam(value="token", required = true) String token) {
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
            List<Role> l = roleBiz.getRoleList(page, pageSize);
            System.out.println(l);
            ListContainer list = new ListContainer<Role>(l);
            list.setPage(1);
            list.setPageSize(10);
            list.setTotalpages(1);
            list.setTotals(4);
            JsonResponse j = new JsonResponse<ListContainer>(1, "获取成功", list);
            return j;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("22222");
            return new JsonResponse(-1, "获取失败");
        }

    }

    /**
     * 添加角色
     * @param role
     * @in    roleName      角色名称
     * @in    weight        权重
     * @return
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public @ResponseBody JsonResponse addRole(Role role) {
        String token = role.getToken();
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
            roleBiz.addRole(role);
            return new JsonResponse(1, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "添加失败");
        }
    }

    /**
     * 获取一个角色
     * @param id
     * @return
     */
    @RequestMapping(value = "/getRoleOne", method = RequestMethod.POST, params = "id")
    public @ResponseBody JsonResponse<Role> getRoleOne(@RequestParam(value="id", required = true) Integer id, @RequestParam(value="token", required = true) String token) {
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
            Role d = roleBiz.getRoleOne(id);
            return new JsonResponse<Role>(1, "获取成功", d);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "获取失败");
        }
    }


    /**
     * 更新一个角色信息
     * @param d
     * @in  roleId
     * @in  roleName
     * @in  weight
     * @return
     */
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRole(Role d) {
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
            roleBiz.editRole(d);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }


    /**
     * 更新一个角色状态
     * @param d
     * @in  roleId
     * @in  isDelete
     * @return
     */
    @RequestMapping(value = "/updateRoleState", method = RequestMethod.POST)
    public @ResponseBody JsonResponse updateRoleState(Role d) {
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
            roleBiz.updateState(d);
            return new JsonResponse(1, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResponse(-1, "更新失败");
        }
    }

}
