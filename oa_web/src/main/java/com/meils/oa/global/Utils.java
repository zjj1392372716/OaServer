package com.meils.oa.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String formdata(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
