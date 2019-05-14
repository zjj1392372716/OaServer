package com.meils.oa.entity;

/**
 * 角色 role表
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private Integer weight;
    private Integer isDelete;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", weight=" + weight +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    private String token;      // 接收token用


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
