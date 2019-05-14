package com.meils.oa.entity;


/**
 * 课程类
 */
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseNum;
    private Integer tId;
//    private Integer cId;
    private String createTime;
    private String courseProperty;
    private String courseSort;
    private String examType;
    private String studyType;
    private String weeklyTimes;
    private Integer totalNum;  //总数
    private String token;      // 接收token用

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

//    public Integer getcId() {
//        return cId;
//    }
//
//    public void setcId(Integer cId) {
//        this.cId = cId;
//    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCourseProperty() {
        return courseProperty;
    }

    public void setCourseProperty(String courseProperty) {
        this.courseProperty = courseProperty;
    }

    public String getCourseSort() {
        return courseSort;
    }

    public void setCourseSort(String courseSort) {
        this.courseSort = courseSort;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getWeeklyTimes() {
        return weeklyTimes;
    }

    public void setWeeklyTimes(String weeklyTimes) {
        this.weeklyTimes = weeklyTimes;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
