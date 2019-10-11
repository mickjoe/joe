package com.spark.biben.custome;

import android.app.Application;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.BallPulseView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.spark.biben.custome.config.CONSTANT_CLASS;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApp extends Application{
    private static MyApp app;
    @Override
    public void onCreate(){
        super.onCreate();
        app = this;
        initrefresh();
        initOkgo();
    }

    private void initrefresh(){
        TwinklingRefreshLayout.setDefaultHeader(ProgressLayout.class.getName());
        TwinklingRefreshLayout.setDefaultFooter(BallPulseView.class.getName());
    }

    /**
     * 获取程序的Application对象
     */
    public static MyApp getApp() {
        return app;
    }
    private void initOkgo(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(CONSTANT_CLASS.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(CONSTANT_CLASS.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(CONSTANT_CLASS.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        OkGo.getInstance().init(this)                       //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);
    }
}
