package com.niluogege.example.commonsdk.network.utils;

import android.os.Environment;

import com.aihuishou.httplib.HttpBaseContext;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

/**
 * Created by LuoChen on 2017/9/25.
 * <p>
 * key加密解密的工具类
 * <p>
 * 加密是将原来的byte数组转为 16进制,解密就是将16进制数据转为byte数组
 */

public class KeyEditUtils {


    /**
     * 加密
     *
     * @param outputFileName 输出文件的名字,文件会被方到SD卡根目录下
     * @param keyName        需要加密的key的名字,需要方到assert目录下
     */
    public static void encodeKey(String keyName,String outputFileName) {

        try {
            InputStream is = HttpBaseContext.getContext().getAssets().open(keyName);
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String encode = byteArr2HexStr(buffer);
            //存到目标文件中
            String outPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + outputFileName;
            File file = new File(outPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(encode, 0, encode.length());
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.d("加密成功");
        }

    }


    /**
     * 解密
     *
     * @param keyName 这个key需要放到assert下
     * @return
     */
    public static byte[] decodeKey(String keyName) {

        byte[] bytes = null;

        try {
            InputStream is = HttpBaseContext.getContext().getAssets().open(keyName);
            int size = is.available();


            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String str = new String(buffer);

            bytes = hexStr2ByteArr(str);
        } catch (Exception e) {

            e.printStackTrace();
            LogUtil.d("解密失败");
        }

        return bytes;


    }


    /**
     * 将byte数组转换为表现16进制的字符串
     *
     * @param arrB 须要转换的byte数组
     * @return 16进制表现的字符串
     * @throws Exception
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int bLen = arrB.length;
        //每一个字符占用两个字节，所以字符串的度长需是数组度长的2倍
        StringBuffer strBuffer = new StringBuffer(bLen * 2);
        for (int i = 0; i != bLen; ++i) {
            int intTmp = arrB[i];
            //把正数转化为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;//因为字一个字节是8位，从低往高数，第9位为符号为，加256，相当于在第九位加1
            }
            //小于0F的数据须要在后面补0，(因为原来是一个字节，在现成变String是两个字节，如果小于0F的话，明说大最也盛不满第一个字节。第二个需弥补0)
            if (intTmp < 16) {
                strBuffer.append("0");
            }
            strBuffer.append(Integer.toString(intTmp, 16));
        }
        return strBuffer.toString();
    }


    /**
     * 将表现16进制的字符串转化为byte数组
     *
     * @param hexStr
     * @return
     * @throws Exception
     */
    public static byte[] hexStr2ByteArr(String hexStr) throws Exception {
        byte[] arrB = hexStr.getBytes();
        int bLen = arrB.length;
        byte[] arrOut = new byte[bLen / 2];
        for (int i = 0; i < bLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }

}
