package com.xq.mvprxre.generalframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author 小侨
 * @time 2017/7/21  10:10
 * @desc activity基类
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        initView();

        // 先创建才能绑定/解绑
        mPresenter = initPresenter();
        mPresenter.attachView(this);
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
