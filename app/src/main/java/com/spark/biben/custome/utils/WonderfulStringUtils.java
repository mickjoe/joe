package com.spark.biben.custome.utils;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/8/30.
 */

public class WonderfulStringUtils{

    /**
     * 判断文本是否为空为null
     */
    public static boolean isEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || "".equals(str) || "null".equals(str.toLowerCase())) return true;
        }
        return false;
    }

    //传入数额是否大于0
    public static boolean greaterThanZero(String str) {
        try {
            if (!WonderfulStringUtils.isEmpty(str)) {
                return Double.valueOf(str) > 0;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否在范围之间
     *
     * @param str
     * @param minValue
     * @param maxValue
     * @return
     */
    public static boolean ifBetween(String str, String minValue, String maxValue) {
        try {
            if (str != null && !"".equals(str) && Double.valueOf(str) > 0) {
                return Double.valueOf(str) >= Double.valueOf(minValue) && Double.valueOf(str) <= Double.valueOf(maxValue);
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }



    //根据某个字符将字符串转list<String>
    public static List<String> stringTransToList(String str, String regex) {
        return Arrays.asList(str.split(regex));
    }

    /**
     * 通过资源获取文字
     */
    public static String getStringById(Context context, int resid) {
        return context.getApplicationContext().getResources().getString(resid);
    }


    /**
     * 通过资源获取经处理的隐藏账号
     */
    public static String hideAccount(String msg) {
        if (WonderfulStringUtils.isEmpty(msg)) {
            return "";
        }
        if (msg.length() < 3) {
            return msg;
        }

        int middle = msg.length() / 3;
        int start_or_end = msg.length() / 3;
        float flo_peace = Float.valueOf(msg.length()) / 3;

        String str_Middle = flo_peace + "";
        int end = Integer.valueOf(str_Middle.substring(str_Middle.indexOf(".") + 1, str_Middle.indexOf(".") + 2));

        StringBuilder stringBuilder = new StringBuilder();

        if (end != 0) {
            if (end >= 5) {
                middle += 2;
            } else {
                middle += 1;
            }
        }
        stringBuilder.append(msg, 0, start_or_end);
        for (int i = 0; i < middle; i++) {
            stringBuilder.append("*");
        }

        stringBuilder.append(msg, msg.length() - start_or_end, msg.length());

        return stringBuilder.toString();
    }

    /**
     * 隐藏
     */
    public static String hideAccountAll(String msg) {
        if (WonderfulStringUtils.isEmpty(msg)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int strLength = msg.length();
        for (int i = 0; i < strLength; i++) {
            stringBuilder.append("*");
        }

        return stringBuilder.toString();
    }
}
