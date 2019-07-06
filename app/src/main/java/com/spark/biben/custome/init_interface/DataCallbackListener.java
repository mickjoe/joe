package com.spark.biben.custome.init_interface;

public interface DataCallbackListener<T>{
    void onsuccess(T t);
    void onfailed(int code,String msg);
}
