package com.niluogege.example.commonsdk.utils.oopinter;

/**
 * Created by niluogege on 2018/11/5.
 * 只有返回值没有参数的方法
 */
public abstract class FunctionWithResultOnly<Result> extends Function {
    public FunctionWithResultOnly(String functionName) {
        super(functionName);
    }

    public abstract Result function();
}
