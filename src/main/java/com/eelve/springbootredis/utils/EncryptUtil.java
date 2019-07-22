package com.eelve.springbootredis.utils;

import java.security.MessageDigest;

/**
 * @ClassName EncryptUtil 加密算法生成
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/7/22 14:24
 * @Version 1.0
 **/
public class EncryptUtil {

    /**
     * Sha1加密 方式
     * @param str
     * @return 返回加密后的字符串，出现异常返回null
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            //java.security.MessageDigest  JDK Java原生加密API
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            //加密
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; i++) {  //处理加密结果
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];  //等价于：byte0 >>> 4 & 15
                buf[k++] = hexDigits[byte0 & 0xf];    //等价于：byte0 & 15
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }


}

