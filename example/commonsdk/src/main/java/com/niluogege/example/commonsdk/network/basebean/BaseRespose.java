package com.niluogege.example.commonsdk.network.basebean;

import java.io.Serializable;

/**
 * 类名称：BaseRespose
 * 创建者：Create by liujc
 * 创建时间：Create on 2017/8/9
 * 描述：封装服务器返回数据
 */
public class BaseRespose<T> implements Serializable {
    public int code;
    public int status;
    public String msg;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public boolean success() {
        return 200 == code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code=" + code +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
