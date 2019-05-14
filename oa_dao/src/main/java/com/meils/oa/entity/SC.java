package com.meils.oa.entity;

/**
 * 选课
 */
public class SC {
    private Integer scId;
    private Integer courseId;
    private Integer sId;
    private String createTime;
    private Course course;
    private String tName;
    private String sNum;
    private String sName;

    public String getsNum() {
        return sNum;
    }

    public void setsNum(String sNum) {
        this.sNum = sNum;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "SC{" +
                "scId=" + scId +
                ", courseId=" + courseId +
                ", sId=" + sId +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
