package com.meils.oa.entity;

/**
 * 管理员实体类 admin表
 * @外键关系： 管理员属于一种角色
 */
public class Admin {

    private Integer adminId;
    private String username;
    private String token;
    private String createTime;
    private Integer roleId;
    private Integer status;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role;


    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", createTime='" + createTime + '\'' +
                ", roleId=" + roleId +
                ", status=" + status +
                '}';
    }



}
