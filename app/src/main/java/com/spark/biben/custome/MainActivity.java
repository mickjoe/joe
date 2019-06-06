package com.spark.biben.custome;

import com.spark.biben.custome.base.BaseActivity;
import com.spark.biben.custome.base.BasePresenter;

public class MainActivity extends BaseActivity{

    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void loadData(){
    }

    @Override
    protected void initView(){
        setTitleToolbar("你好", true);
    }

    @Override
    protected BasePresenter createPresenter(){
        return null;
    }
}
