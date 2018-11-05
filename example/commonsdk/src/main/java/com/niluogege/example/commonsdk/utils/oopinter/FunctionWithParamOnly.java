package com.niluogege.example.commonsdk.utils.oopinter;

/**
 * Created by niluogege on 2018/11/5.
 * 只有参数没有返回值
 */
public abstract class FunctionWithParamOnly<Param> extends Function {
    public FunctionWithParamOnly(String functionName) {
        super(functionName);
    }

    public abstract void function(Param param);
}
