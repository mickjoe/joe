package com.spark.biben.custome.mvp.view;

import com.spark.biben.custome.base.BaseView;
import com.spark.biben.custome.entity.Areas;

public interface MainView extends BaseView{
    void getareasSuccess(Areas areas);
    void getareasFailed(int code,String msg);
}
