package com.spark.biben.custome.request;

import com.spark.biben.custome.entity.Areas;
import com.spark.biben.custome.init_interface.DataCallback;
import com.spark.biben.custome.init_interface.DataCallbackListener;
import com.spark.biben.custome.utils.tools.ConvertUtils;

public class DataModel{

    public static DataModel getDataModelInstance(){
        return DataModelHolder.dataModel;
    }

    private static class DataModelHolder{
        private static DataModel dataModel = new DataModel();
    }

    public void getAreas(String tag, final DataCallbackListener<Areas> dataCallbackListener){
        ConvertUtils.getConverUtilsInstance().convertNoParams(tag, Httpurl.getAreaCodeUrl(), Areas.class, new DataCallback<Areas>(){
            @Override
            public void convertSuccess(Areas areas){
                dataCallbackListener.onsuccess(areas);
            }

            @Override
            public void convertError(int code, String msg){
                dataCallbackListener.onfailed(code, msg);
            }
        });
    }
}
