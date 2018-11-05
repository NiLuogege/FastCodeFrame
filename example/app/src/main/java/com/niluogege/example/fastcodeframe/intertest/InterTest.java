package com.niluogege.example.fastcodeframe.intertest;

import com.niluogege.example.commonsdk.utils.ToastUtils;
import com.niluogege.example.commonsdk.utils.oopinter.FunctionManager;
import com.niluogege.example.commonsdk.utils.oopinter.FunctionWithParamAndResult;
import com.niluogege.example.commonsdk.utils.oopinter.FunctionWithParamOnly;

/**
 * Created by niluogege on 2018/11/5.
 */
public class InterTest {
    public static final String withParamAndResult = InterTest.class.getName() + "withParamAndResult";
    public static final String WithParamOnly = InterTest.class.getName() + "WithParamOnly";

    public InterTest() {
        FunctionManager.getInstance().addFunc(functionWithParamAndResult);
        FunctionManager.getInstance().addFunc(functionWithParamOnly);
    }


    public void remove() {
        FunctionManager.getInstance().removeFunc(functionWithParamAndResult);
        FunctionManager.getInstance().removeFunc(functionWithParamOnly);
    }


    FunctionWithParamAndResult functionWithParamAndResult = new FunctionWithParamAndResult<String, String>(withParamAndResult) {
        @Override
        public String function(String s) {
            return s;
        }
    };

    FunctionWithParamOnly functionWithParamOnly = new FunctionWithParamOnly<String>(WithParamOnly) {

        @Override
        public void function(String s) {
            ToastUtils.show(s + "WithParamOnly");
        }
    };


}
