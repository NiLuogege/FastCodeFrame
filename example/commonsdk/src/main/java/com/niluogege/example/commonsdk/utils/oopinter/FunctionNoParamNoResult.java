package com.niluogege.example.commonsdk.utils.oopinter;

/**
 * Created by niluogege on 2018/11/5.
 * 没参数页没有返回值
 */
public abstract class FunctionNoParamNoResult extends Function {
    public FunctionNoParamNoResult(String functionName) {
        super(functionName);
    }

    public abstract void function();
}
