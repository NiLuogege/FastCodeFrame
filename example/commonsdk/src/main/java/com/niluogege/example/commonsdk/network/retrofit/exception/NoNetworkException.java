package com.niluogege.example.commonsdk.network.retrofit.exception;

/**
 * Created by LuoChen on 2017/12/2.
 *
 * 没有网络的异常
 */

public class NoNetworkException extends RuntimeException {
    public NoNetworkException(String message) {
        super(message);
    }
}
