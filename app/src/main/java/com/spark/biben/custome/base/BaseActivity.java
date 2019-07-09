package com.spark.biben.custome.base;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.spark.biben.custome.R;
import com.spark.biben.custome.init_interface.BackEventListener;
import com.spark.biben.custome.utils.ActivityCollection;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity{
    private Toolbar titleToolbar;
    private Activity base;
    private TextView titleCenter;
    protected T presenter;
    private PopupWindow loadingPopup;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        base = this;
        initBaseView();
        unbinder = ButterKnife.bind(this);
        ActivityCollection.addActivity(base);
        initView();
        if(createPresenter() != null){
            presenter = createPresenter();
            presenter.attach((V) this);
        }
        getWindow().getDecorView().post(new Runnable(){
            @Override
            public void run(){
                loadData();
            }
        });
    }

    private void initBaseView(){
        View view = LayoutInflater.from(base).inflate(R.layout.actvity_base, null, false);
        titleToolbar = view.findViewById(R.id.base_toolbar);
        titleToolbar.setTitle("");
        setSupportActionBar(titleToolbar);
        FrameLayout frameLayout = view.findViewById(R.id.fl_content);
        View mainContent = LayoutInflater.from(base).inflate(getLayoutId(), null, false);
        frameLayout.addView(mainContent);
        setContentView(view);
        titleCenter = view.findViewById(R.id.tv_base_title);
    }

    protected abstract int getLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

    /**
     * 控制标题栏
     *
     * @param title
     * @param isCenter
     */

    protected void setTitleToolbar(String title, boolean isCenter){
        if(!TextUtils.isEmpty(title)){
            titleToolbar.setVisibility(View.VISIBLE);
        }
        if(isCenter){
            titleCenter.setVisibility(View.VISIBLE);
            titleCenter.setText(title);
        }else{
            titleCenter.setVisibility(View.GONE);
            titleToolbar.setTitle(title);
        }
    }

    /**
     * toolbar是否导航
     *
     * @param isNavigation
     */
    protected void setToolbarNavigation(boolean isNavigation){
        if(isNavigation){
            titleToolbar.setNavigationOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    finish();
                }
            });
        }
    }

    /**
     * toolbar导航返回事件
     *
     * @param isNavigation
     */
    protected void setToolbarNavigation(boolean isNavigation, final BackEventListener listener){
        if(isNavigation){
            titleToolbar.setNavigationOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    listener.backEventer();
                }
            });
        }
    }

    /**
     * toolbar导航图片
     *
     * @param icon
     */
    protected void setToolbarNavigationIcon(int icon){
        titleToolbar.setNavigationIcon(icon);
    }

    /**
     * 创建泛型的Presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    @Override
    protected void onDestroy(){
        if(presenter != null && presenter.isViewAttached()){
            presenter.detachView();
        }
        OkGo.getInstance().cancelTag(base.getClass().getSimpleName());
        unbinder.unbind();
        super.onDestroy();
    }

    public void hideLoadingPopup(){
        try{
            if(loadingPopup != null && this.loadingPopup.isShowing()){
                this.loadingPopup.dismiss();
            }
        }catch(Exception e){
        }
    }

    public void displayLoadingPopup(){
        //初始化加载dialog
        if(loadingPopup == null){
            View loadingView = getLayoutInflater().inflate(R.layout.pop_loading, null);
            loadingPopup = new PopupWindow(loadingView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            loadingPopup.setFocusable(true);
            loadingPopup.setClippingEnabled(false);
            loadingPopup.setBackgroundDrawable(new ColorDrawable());
        }
        try{
            loadingPopup.showAtLocation(base.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
