package com.xq.generalframework.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 小侨
 * @time 2017/7/20  14:44
 * @desc Activity基类
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements IView {

    // 当前View需要让中介Presenter持有自己的实例
    // BasePresenter<V>d的V由 BaseActivity<V, T>的V传入

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        initView();
        // 用于和当前View绑定/解绑Presenter
        mPresenter = initPresenter();
    }

    /**
     * 布局文件
     **/
    protected abstract int setContentView();

    /**
     * fbi
     **/
    protected abstract void initView();

    /**
     * 创建一个对应的Presenter实例
     **/
    protected abstract T initPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        // 当前View需要让中介Presenter持有自己的实例
        mPresenter.attachView((V) this); // this需要是extends/implements这个V的实例

        // 拿MovieActivity这个例子来说：MovieActivity implements MovieContract.IMovieView，
        // (V) this就符合泛型BaseActivity<V, T>规定
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 当activity退出时，为避免后台仍在下载数据，进行解绑
        mPresenter.detachView();
    }
}
