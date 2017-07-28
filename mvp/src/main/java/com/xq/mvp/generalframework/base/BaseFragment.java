package com.xq.mvp.generalframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xq.mvp.generalframework.base.mvp.IView;

/**
 * @author 小侨
 * @time 2017/7/26  17:56
 * @desc fragment基类
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {

    protected P mPresenter;
    private int mLayoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    private int getContentView() {
        mLayoutId = setContentView();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", mLayoutId);
        setArguments(bundle);
        return mLayoutId;
    }

    protected abstract int setContentView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Activity重新创建时，会重新构建它所管理的Fragment，原先的Fragment的字段值将会全部丢失，
        // 但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来。
        // 所以尽量使用Fragment.setArguments(Bundle bundle)方式来传递参数
        Bundle bundle = getArguments();
        if (bundle != null) {
            mLayoutId = bundle.getInt("layoutId");
        }

        mPresenter = initPresenter();
        mPresenter.attachView(this);

        initView();
    }

    protected abstract P initPresenter();

    protected abstract void initView();

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
