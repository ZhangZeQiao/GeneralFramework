package com.xq.mvprxremd.generalframework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xq.mvprxremd.generalframework.base.mvp.IView;

/**
 * @author 小侨
 * @time 2017/7/26  17:56
 * @desc fragment基类（来源：http://www.jianshu.com/p/651146bd0688）
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {

    protected Context mContext; // activity的上下文
    protected P mPresenter;
    private int mLayoutId;

    @Override
    public Context getContext() {
        return mContext;
    }

    public BaseFragment getFragment() {
        return this;
    }

    /**
     * 绑定 activity
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

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
    }

    /**
     * 运行在 onCreate之后
     * 生成 view视图
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        return view;
    }

    /**
     * 运行在onCreateView之后
     * 加载数据
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 由于fragment生命周期比较复杂，所以Presenter在onCreateView创建视图之后再进行绑定，不然会报空指针异常
        mPresenter.attachView(this);
        initView();
    }

    protected abstract P initPresenter();

    private int getContentView() {
        mLayoutId = setContentView();
        Bundle bundle = new Bundle();
        bundle.putInt("layoutId", mLayoutId);
        setArguments(bundle);
        return mLayoutId;
    }

    protected abstract int setContentView();

    protected abstract void initView();

    @Override
    public void showLoading() {
        showToast("--- 下载中 ---");
    }

    @Override
    public void hideLoading() {
        showToast("+++ 下载完 +++");
    }

    /**
     * 跳转fragment
     */
    public void startFragment(Fragment toFragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(this).add(android.R.id.content, toFragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * fragment回退
     */
    public void onBack() {
        getFragmentManager().popBackStack();
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
