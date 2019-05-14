package com.meils.oa.entity;

/**
 * 教师表
 */
public class Teacher {

    private Integer tId;
    private String tName;
    private String tNum;
    private String tPhone;
    private String tMajor;
    private String tPreCourse;
    private String createTime;
    private String startTime;
    private Integer isDelete;
    private String token;      // 接收token用
    private Integer totalNum;  //总数

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettNum() {
        return tNum;
    }

    public void settNum(String tNum) {
        this.tNum = tNum;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettMajor() {
        return tMajor;
    }

    public void settMajor(String tMajor) {
        this.tMajor = tMajor;
    }

    public String gettPreCourse() {
        return tPreCourse;
    }

    public void settPreCourse(String tPreCourse) {
        this.tPreCourse = tPreCourse;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }



}
