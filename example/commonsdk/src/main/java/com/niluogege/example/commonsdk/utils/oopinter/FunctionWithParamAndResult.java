package com.niluogege.example.commonsdk.utils.oopinter;

/**
 * Created by niluogege on 2018/11/5.
 * 有参数有返回值的方法
 */
public abstract class FunctionWithParamAndResult<Param, Result> extends Function {
    public FunctionWithParamAndResult(String functionName) {
        super(functionName);
    }


    public abstract Result function(Param param);
}
