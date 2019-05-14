package com.meils.oa.global;

/**
 * 返回JSON对象的工具类
 * @param <T>
 */
public class JsonResponse<T> {

    private Integer status = -1; // 状态
    private String msg = "";     // 提示信息
    private T data;              // 某种类型的data


    public JsonResponse() {
        this.data = null;
    }

    public JsonResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JsonResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }




}
