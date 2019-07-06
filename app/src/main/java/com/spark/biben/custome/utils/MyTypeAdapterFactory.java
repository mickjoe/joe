package com.spark.biben.custome.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class MyTypeAdapterFactory<T> implements TypeAdapterFactory{
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type){
        Class<T> rawType = (Class<T>) type.getRawType();
        if(rawType == Float.class || rawType == float.class){
            return (TypeAdapter<T>) new FloatlNullAdapter();
        }
        return null;
    }
}
