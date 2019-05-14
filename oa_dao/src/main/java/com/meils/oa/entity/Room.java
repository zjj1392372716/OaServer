package com.meils.oa.entity;

/**
 * 教室 room
 */
public class Room {

    private Integer roomId;
    private String roomNum;
    private Integer peopleNum;
    private Integer isMedia;
    private Integer isDelete;
    private String token;      // 接收token用
    private Integer totalNum;  //总数


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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getIsMedia() {
        return isMedia;
    }

    public void setIsMedia(Integer isMedia) {
        this.isMedia = isMedia;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
