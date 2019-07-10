package com.spark.biben.custome.utils.tools;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.spark.biben.custome.config.CONSTANT_CLASS;
import com.spark.biben.custome.init_interface.DataCallback;
import com.spark.biben.custome.utils.WonderfulStringUtils;

import org.json.JSONObject;

/**
 * 解析工具类
 */
public class ConvertUtils{

    public static ConvertUtils getConverUtilsInstance(){
        return Holder.utils;
    }

    public static class Holder{
        private static ConvertUtils utils = new ConvertUtils();
    }

    public <T> void convert(String tag, String url, HttpParams params, final Class<T> classOfT, final DataCallback<T> callback){
        OkGo.<String>post(url).tag(tag).params(params).execute(new StringCallback(){
            @Override
            public void onSuccess(Response<String> response){
                try{
                    JSONObject object = new JSONObject(response.body());
                    if(object.optInt("code") == 0){
                        if(!WonderfulStringUtils.isEmpty(object.optString("data"))){
                            Gson gson = new Gson();
                            T t = gson.fromJson(object.optString("data"), classOfT);
                            callback.convertSuccess(t);
                        }else{
                            callback.convertError(object.optInt("code"), object.optString("string"));
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response){
                super.onError(response);
                callback.convertError(CONSTANT_CLASS.SOCKET_TIME_OUT, response.getException().getMessage());
            }
        });
    }

    public <T> void convertNoParams(String tag, String url, final Class<T> classOfT, final DataCallback<T> callback){
        OkGo.<String>post(url).tag(tag).execute(new StringCallback(){
            @Override
            public void onSuccess(Response<String> response){
                try{
                    JSONObject object = new JSONObject(response.body());
                    if(object.optInt("code") == 0){
                        if(!WonderfulStringUtils.isEmpty(object.optString("data"))){
                            Gson gson = new Gson();
                            T t = gson.fromJson(response.body(), classOfT);
                            callback.convertSuccess(t);
                        }else{
                            callback.convertSuccess((T)object.optString("message"));
                        }
                    }else {
                        callback.convertError(object.optInt("code"), object.optString("message"));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Response<String> response){
                super.onError(response);
                callback.convertError(CONSTANT_CLASS.SOCKET_TIME_OUT, CONSTANT_CLASS.REQUEST_FAILED);
            }

        });
    }
}
