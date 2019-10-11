package com.spark.biben.custome.mvp.ui;

import com.spark.biben.custome.R;
import com.spark.biben.custome.base.BaseActivity;
import com.spark.biben.custome.base.BasePresenter;

public class TestActivity extends BaseActivity{
    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void loadData(){
    }

    @Override
    protected void initView(){
    }

    @Override
    protected BasePresenter createPresenter(){
        return null;
    }
}
