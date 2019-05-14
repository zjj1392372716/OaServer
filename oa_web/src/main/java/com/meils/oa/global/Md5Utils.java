package com.meils.oa.global;
import org.springframework.data.redis.core.script.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * Md5 加密
 */
public class Md5Utils {

    public static String Md5(String s)
    {
        String str = DigestUtils.sha1DigestAsHex(s);

        System.out.println("md5Hex:     "+str);
        return str;
    }
}
