package com.spark.biben.custome;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.spark.biben.custome.base.BaseActivity;
import com.spark.biben.custome.base.BaseFragment;
import com.spark.biben.custome.entity.Areas;
import com.spark.biben.custome.fragment.Fourfragment;
import com.spark.biben.custome.fragment.OneFragment;
import com.spark.biben.custome.fragment.ThreeFragment;
import com.spark.biben.custome.fragment.TwoFragment;
import com.spark.biben.custome.mvp.presenter.MainActPresenter;
import com.spark.biben.custome.mvp.ui.TestActivity;
import com.spark.biben.custome.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainActPresenter> implements MainView{
    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_menu)
    RadioGroup rgMenu;
    private List<BaseFragment> fragments;
    private int position = 0;
    private BaseFragment mContent;

    @Override
    protected int getLayoutId(){
        return R.layout.activity_main;
    }

    @Override
    protected void loadData(){
        presenter.getAreas(MainActivity.class.getSimpleName());
        rgMenu.setOnCheckedChangeListener(listener);
        rgMenu.check(R.id.rb_one);
    }

    @Override
    protected void initView(){
        setTitleToolbar("", false);
        initFragment();
    }

    @Override
    protected MainActPresenter createPresenter(){
        return new MainActPresenter(this);
    }

    @Override
    public void getareasSuccess(Areas areas){
        Log.e("zhong", "getareasSuccess: " );
    }

    @Override
    public void getareasFailed(int code, String msg){
        Log.e("zhong", "getareasFailed: " );
    }

    public void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new Fourfragment());
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId){
            switch(checkedId){
                case R.id.rb_one://one
                    position = 0;
                    break;
                case R.id.rb_two://two
                    position = 1;
                    break;
                case R.id.rb_three://thee
                    position = 2;
                    break;
                case R.id.rb_four://four
                    position = 3;
                    startActivity(new Intent(MainActivity.this,TestActivity.class));
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换到Fragment
            switchFrament(mContent, to);
        }
    };

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(BaseFragment from, BaseFragment to){
        if(from != to){ //才切换
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction(); //开启事务
            //判断to有没有被添加
            if(!to.isAdded()){//to没有被添加
                //1.from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //2.添加to
                if(to != null){
                    ft.add(R.id.fl_main, to).commitAllowingStateLoss();
                }
            }else{ //to已经被添加
                //1.from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //2.显示to
                if(to != null){
                    ft.show(to).commitAllowingStateLoss();
                }
            }
        }
    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment(){
        BaseFragment fragment = fragments.get(position);
        return fragment;
    }
}
