package com.niluogege.example.commonsdk.utils;

import java.util.List;


public class ListUtils {

    /**
     * 判断list是否为空
     * @param list
     * @return
     */
    public static boolean isListEmpty( List list ) {
        if( list != null && list.size() > 0 ) {
            return false;
        }

        return true;
    }

    /**
     * 判断list是否为空
     * @param list
     * @return
     */
    public static boolean isListNotEmpty( List list ) {
        if( list != null && list.size() > 0 ) {
            return true;
        }

        return false;
    }


    /**
     * 判断是否包含元素
     * @param object
     * @param objectList
     * @return
     */
    public static boolean isInList(Object object, List objectList) {
        boolean ret = false;

        if (!isListEmpty(objectList)) {
            for (Object o : objectList) {
                if (o.equals(object)) {
                    ret = true;
                    break;
                }
            }
        }

        return ret;
    }
}
