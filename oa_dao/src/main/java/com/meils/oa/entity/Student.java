package com.meils.oa.entity;

/**
 * 学生表 student
 * @外键关系： 学生从属于某一个班级
 *
 */
public class Student {

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", classId=" + classId +
                ", sNum='" + sNum + '\'' +
                ", sAge=" + sAge +
                ", startTime='" + startTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isDelete=" + isDelete +
                ", classN=" + classN +
                ", totalNum=" + totalNum +
                '}';
    }

    private Integer sId;
    private String sName;
    private Integer classId;
    private String sNum;
    private Integer sAge;
    private String startTime;
    private String createTime;
    private Integer isDelete;
    private Integer totalNum;  //总数
    private ClassN classN;



    private String token;      // 接收token用


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



    public ClassN getClassN() {
        return classN;
    }

    public void setClassN(ClassN classN) {
        this.classN = classN;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }




}
