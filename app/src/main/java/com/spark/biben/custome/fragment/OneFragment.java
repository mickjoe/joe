package com.spark.biben.custome.fragment;

import com.spark.biben.custome.R;
import com.spark.biben.custome.base.BaseFragment;
import com.spark.biben.custome.base.BasePresenter;

public class OneFragment extends BaseFragment{
    @Override
    protected BasePresenter createPresenter(){
        return null;
    }

    @Override
    protected int getLayoutId(){
        return R.layout.fragment_one;
    }

    @Override
    protected void loadData(){
    }

    @Override
    protected void initView(){
    }
}
