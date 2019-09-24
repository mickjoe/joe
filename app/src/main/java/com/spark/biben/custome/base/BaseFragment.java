package com.spark.biben.custome.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.lzy.okgo.OkGo;
import com.spark.biben.custome.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment{
    protected T presenter;
    /**
     * 是否正在加载数据
     */
    protected boolean isLoad = false;
    protected boolean isInit = false;
    protected boolean isNeedLoad = true;
    private View rootView;
    private PopupWindow loadingPopup;
    private BaseActivity mBaseActivity;
    private Unbinder unbinder;
    private Fragment base;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        base = this;
        if(createPresenter() != null){
            presenter = createPresenter();
            presenter.attach((V) this);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(getLayoutId(), null);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
         unbinder = ButterKnife.bind(this, rootView);
        isInit = true;
        initView();
        tryToLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        tryToLoadData();
    }

    private void tryToLoadData(){
        if(!isInit)
            return;
        if(!isNeedLoad)
            return;
        if(getUserVisibleHint()){
            rootView.post(new Runnable(){
                @Override
                public void run(){
                    loadData();
                }
            });
            isLoad = true;
        }else{
            if(isLoad)
                stopLoad();
        }
    }

    @Override
    public void onDestroyView(){
        isInit = false;
        isLoad = false;
        OkGo.getInstance().cancelTag(base.getClass().getSimpleName());
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.mBaseActivity = (BaseActivity) context;
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mBaseActivity = null;
    }

    protected BaseActivity getmActivity(){
        return mBaseActivity;
    }

    /**
     * 隐藏时停止 加载数据
     */
    protected void stopLoad(){
    }

    /**
     * 创建泛型的Presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    @Override
    public void onDestroy(){
        if(presenter != null && presenter.isViewAttached()){
            presenter.detachView();
        }
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void loadData();

    protected abstract void initView();

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
            loadingPopup.showAtLocation(mBaseActivity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
