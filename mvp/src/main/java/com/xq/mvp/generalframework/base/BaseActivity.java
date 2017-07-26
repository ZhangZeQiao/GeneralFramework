package com.xq.mvp.generalframework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * @author 小侨
 * @time 2017/7/21  10:10
 * @desc activity基类
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected Context mContext;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        mContext = this;
        initView();

        // 先创建才能绑定/解绑
        mPresenter = initPresenter();
        mPresenter.attachView(this);

        initData();
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract void initData();

    @Override
    public void showLoading() {
        showToast("--- 下载中 ---");
    }

    @Override
    public void hideLoading() {
        showToast("+++ 下载完 +++");
    }

    @Override
    public void showToast(String message) {
        // TODO: 如果子类有自己的showLoading()、hideLoading()、showToast()，可以重写
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
