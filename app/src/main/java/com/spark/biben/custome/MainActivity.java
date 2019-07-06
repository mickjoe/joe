package com.spark.biben.custome;

import android.util.Log;

import com.spark.biben.custome.base.BaseActivity;
import com.spark.biben.custome.entity.Areas;
import com.spark.biben.custome.mvp.presenter.MainActPresenter;
import com.spark.biben.custome.mvp.view.MainView;

public class MainActivity extends BaseActivity<MainView, MainActPresenter> implements MainView{
    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void loadData(){
        presenter.getAreas(MainActivity.class.getSimpleName());
    }

    @Override
    protected void initView(){
        setTitleToolbar("你好", true);
    }

    @Override
    protected MainActPresenter createPresenter(){
        return new MainActPresenter(this);
    }

    @Override
    public void getareasSuccess(Areas areas){
        Log.e("zhong", "getareasSuccess: "+areas.getData().get(0).getAreaCode());
    }

    @Override
    public void getareasFailed(int code, String msg){
        Log.e("zhong", "getareasSuccess: "+msg);
    }

    @Override
    public void hideLoadingPopup(){
    }

    @Override
    public void displayLoadingPopup(){
    }
}
