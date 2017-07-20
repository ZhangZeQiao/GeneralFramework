package com.xq.generalframework.base.mvp;

/**
 * @author 小侨
 * @time 2017/7/20  15:18
 * @desc Presenter基类
 */

public abstract class BasePresenter<V> {


    /**
     * Presenter作为View与Model之间的中介，
     * 需要持有View的实例，以便获取数据后修改ui
     **/
    public V mView;

    /**
     * 绑定与解绑是共有属性，可以抽到这里
     **/
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 当activity退出时，为避免后台仍在下载数据，进行解绑
     **/
    public void detachView() {
        mView = null;
    }

    /**
     * 获取数据
     **/
    public abstract int getData();

    /**
     * 显示数据
     **/
    public abstract void showData();

}
