package com.spark.biben.custome.mvp.presenter;

import com.spark.biben.custome.base.BasePresenter;
import com.spark.biben.custome.entity.Areas;
import com.spark.biben.custome.init_interface.DataCallbackListener;
import com.spark.biben.custome.mvp.view.MainView;
import com.spark.biben.custome.request.DataModel;

public class MainActPresenter extends BasePresenter<MainView>{

    public MainActPresenter(MainView mainView){
        super(mainView);
    }

    public void getAreas(String tag){
        DataModel.getDataModelInstance().getAreas(tag, new DataCallbackListener<Areas>(){
            @Override
            public void onsuccess(Areas areas){
                getBindView().getareasSuccess(areas);
            }

            @Override
            public void onfailed(int code, String msg){
                getBindView().getareasFailed(code, msg);
            }
        });
    }
}
