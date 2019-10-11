package com.spark.biben.custome.mvp.ui;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.spark.biben.custome.R;
import com.spark.biben.custome.adapter.TestAdapter;
import com.spark.biben.custome.base.BaseActivity;
import com.spark.biben.custome.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TestActivity extends BaseActivity{
    @BindView(R.id.tv_rv)
    RecyclerView tvRv;
    @BindView(R.id.tr_refresh)
    TwinklingRefreshLayout trRefresh;
    private List<String> datas = new ArrayList<>();
    @Override
    protected int getLayoutId(){
        return R.layout.activity_test;
    }

    @Override
    protected void loadData(){
        for(int i = 0; i <5 ; i++){
            datas.add(i+"----");
        }
        TestAdapter adapter = new TestAdapter(R.layout.item_test,datas);
        LinearLayoutManager manager = new LinearLayoutManager(TestActivity.this, LinearLayoutManager.VERTICAL, false);
        tvRv.setLayoutManager(manager);
        adapter.isFirstOnly(true);
        adapter.bindToRecyclerView(tvRv);
        trRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                },2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                },2000);
            }
        });

    }

    @Override
    protected void initView(){
        setTitleToolbar("测试",true);
        setToolbarNavigation(R.mipmap.back_icon_white,true);
    }

    @Override
    protected BasePresenter createPresenter(){
        return null;
    }


}
