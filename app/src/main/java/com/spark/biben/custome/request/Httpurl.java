package com.spark.biben.custome.request;

public class Httpurl{
    public static final String host = "http://192.168.0.46/";
    //获取区号
    public static String getAreaCodeUrl() {
        return host + "/ug/member/getAreaCode";
    }

}
