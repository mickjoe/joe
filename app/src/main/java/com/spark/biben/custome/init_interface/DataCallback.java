package com.spark.biben.custome.init_interface;

public interface DataCallback<T>{
    void convertSuccess(T t);
    void convertError(int code,String msg);
}
