package com.xq.mvp.generalframework.ui.movie;

import android.view.View;
import android.widget.TextView;

import com.xq.mvp.R;
import com.xq.mvp.generalframework.base.BaseActivity;

/**
 * @author 小侨
 * @time 2017/7/21  10:25
 * @desc movie页面View层
 */

public class MovieActivity extends BaseActivity<MoviePresenter> implements MovieContract.IView {

    // 互相关联
    // MovieActivity类中：extends BaseActivity<MoviePresenter>
    // MoviePresenter类中：extends BasePresenter<MovieActivity>

    private TextView mTv;

    @Override
    protected int setContentView() {
        return R.layout.activity_movie;
    }

    @Override
    protected void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    protected MoviePresenter initPresenter() {
        return new MoviePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showData(String s) {
        mTv.setText(s);
    }

    public void onJump(View view) {
        mPresenter.getData();
    }
}
