package com.niluogege.example.module_user;

import java.io.Serializable;

/**
 * 类名称：MeiziBaseRespose
 * 创建者：Create by niluogege
 * 创建时间：Create on 2017/8/9
 * 描述：封装服务器返回数据
 */
public class MeiziBaseRespose<T> implements Serializable {


    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    private T results;

    private boolean error;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
