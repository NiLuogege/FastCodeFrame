package com.niluogege.example.commonsdk.utils.oopinter;

import com.niluogege.example.commonsdk.utils.ILog;
import com.niluogege.example.commonsdk.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niluogege on 2018/11/5.
 */
public class FunctionManager {
    private Map<String, FunctionWithParamAndResult> withParamAndResultMap = new HashMap<>();//有参数有返回值的回掉容器
    private Map<String, FunctionWithParamOnly> withParamOnlyMap = new HashMap<>();//只有参数的回掉容器
    private Map<String, FunctionWithResultOnly> withResultOnlyMap = new HashMap<>();//只有返回值的回掉容器
    private Map<String, FunctionNoParamNoResult> noParamNoResultMap = new HashMap<>();//没有参数没有返回值的回掉容器

    private FunctionManager() {
    }

    private static final class InnerHolder {
        private static FunctionManager manager = new FunctionManager();
    }

    public static synchronized FunctionManager getInstance() {
        return InnerHolder.manager;
    }

    /**
     * 有参数有返回值 类型 添加回掉
     */
    public FunctionManager addFunc(FunctionWithParamAndResult function) {
        if (function != null) {
            withParamAndResultMap.put(function.functionName, function);
        }
        return this;
    }

    /**
     * 只有参数 类型 添加回掉
     */
    public FunctionManager addFunc(FunctionWithParamOnly function) {
        if (function != null) {
            withParamOnlyMap.put(function.functionName, function);
        }
        return this;
    }

    /**
     * 只有返回值 类型 添加回掉
     */
    public FunctionManager addFunc(FunctionWithResultOnly function) {
        if (function != null) {
            withResultOnlyMap.put(function.functionName, function);
        }
        return this;
    }

    /**
     * 没有参数没有返回值 类型 添加回掉
     */
    public FunctionManager addFunc(FunctionNoParamNoResult function) {
        if (function != null) {
            noParamNoResultMap.put(function.functionName, function);
        }
        return this;
    }

    /**
     * 移除回掉
     */
    public FunctionManager removeFunc(Function function) {
        if (function != null) {
            if (function instanceof FunctionWithParamAndResult) {//有参数有返回值
                withParamAndResultMap.remove(function.functionName);
            }
        }

        return this;
    }

    /**
     * 执行 有参数有返回值 回掉方法
     *
     * @param funcName 方法名
     * @param param    参数
     * @param clz      返回类型
     * @param <Param>  参数类型
     * @param <Result> 返回类型
     * @return
     */
    public <Param, Result> Result invokeFunc(String funcName, Param param, Class<Result> clz) {
        if (StringUtils.isNotEmpty(funcName)) {
            if (withParamAndResultMap.containsKey(funcName)) {
                FunctionWithParamAndResult function = withParamAndResultMap.get(funcName);
                if (function != null) {

                    if (clz != null) {
                        return clz.cast(function.function(param));
                    } else {
                        return (Result) function.function(param);
                    }
                } else {
                    throw new FunctionException("Has no this function" + funcName);
                }
            } else {
                ILog.e("no this Key!");
            }
        } else {
            throw new FunctionException("funcName is empty!");
        }
        return null;
    }


    /**
     * 执行 只有参数 回掉方法
     *
     * @param funcName 方法名
     * @param param    参数
     * @param <Param>  参数类型
     */
    public <Param> void invokeFunc(String funcName, Param param) {
        if (StringUtils.isNotEmpty(funcName)) {
            if (withParamOnlyMap.containsKey(funcName)) {
                FunctionWithParamOnly function = withParamOnlyMap.get(funcName);
                if (function != null) {
                    function.function(param);
                } else {
                    throw new FunctionException("Has no this function" + funcName);
                }
            } else {
                ILog.e("no this Key!");
            }
        } else {
            throw new FunctionException("funcName is empty!");
        }
    }


    /**
     * 执行 只有返回值 回掉方法
     *
     * @param funcName 方法名
     * @param clz      返回类型
     * @param <Result> 返回类型
     * @return
     */
    public <Result> Result invokeFunc(String funcName, Class<Result> clz) {
        if (StringUtils.isNotEmpty(funcName)) {
            if (withResultOnlyMap.containsKey(funcName)) {
                FunctionWithResultOnly function = withResultOnlyMap.get(funcName);
                if (function != null) {

                    if (clz != null) {
                        return clz.cast(function.function());
                    } else {
                        return (Result) function.function();
                    }
                } else {
                    throw new FunctionException("Has no this function" + funcName);
                }
            } else {
                ILog.e("no this Key!");
            }
        } else {
            throw new FunctionException("funcName is empty!");
        }
        return null;
    }


    /**
     * 执行 没有参数没有返回值 回掉方法
     *
     * @param funcName 方法名
     */
    public void invokeFunc(String funcName) {
        if (StringUtils.isNotEmpty(funcName)) {
            if (noParamNoResultMap.containsKey(funcName)) {
                FunctionNoParamNoResult function = noParamNoResultMap.get(funcName);
                if (function != null) {
                    function.function();
                } else {
                    throw new FunctionException("Has no this function" + funcName);
                }
            } else {
                ILog.e("no this Key!");
            }
        } else {
            throw new FunctionException("funcName is empty!");
        }
    }

}
