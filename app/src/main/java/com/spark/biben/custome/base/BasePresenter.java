package com.spark.biben.custome.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter<V>{
    private V mView;
    // 弱引用
    protected WeakReference<V> mViewRef;

    /**
     * 绑定View
     *
     * @param view
     */
    public void attach(final V view){
        //动态代理
        mView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
                //在View层显示数据之前用户可能退出了View层的页面，会在Activity的onDestroy()方法中会把mView置为null
                //由于View层都是接口，这里采用了动态代理，如果在View层显示数据之前用户可能退出了View层的页面，返回null的话，onSuccess()方法不会执行
                if(mView == null){
                    return null;
                }
                //每次调用View层接口的方法，都会执行这里
                return method.invoke(view, args);
            }
        });
        if(mView != null){
            mViewRef = new WeakReference<V>(mView);
        }
    }

    /**
     * 获取当前弱引用对象
     *
     * @return t
     */
    protected V getBindView(){
        return mViewRef.get();
    }

    /**
     * 判断当前view是否关联
     *
     * @return boolean
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * 解除关联
     */
    public void detachView(){
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
