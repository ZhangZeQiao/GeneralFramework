package com.xq.mvprxremd.generalframework.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 小侨
 * @time 2017/7/25  10:55
 * @desc 添加上拉加载更多的view
 * <p>
 * RecyclerView默认没有提供类似addHeaderView()和addFooterView()的API，
 * 因此这里介绍如何优雅地实现这两个接口。
 * <p>
 * 装饰器（Decorator）设计模式：该设计模式通过组合的方式，
 * 在不破话原有类代码的情况下，对原有类的功能进行扩展。
 * <p>
 */

public class PulldownAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    enum ITEM_TYPE {
        PULLDOWN,
        NORMAL
    }

    private RecyclerView.Adapter mNormalAdapter;
    private View mPulldownView;

    public PulldownAdapterWrapper(RecyclerView.Adapter normalAdapter) {
        mNormalAdapter = normalAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.PULLDOWN.ordinal()) {
            return new RecyclerView.ViewHolder(mPulldownView) {
            };
        } else {
            return mNormalAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == mNormalAdapter.getItemCount()) {
            return;
        } else {
            mNormalAdapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mNormalAdapter.getItemCount()) {
            return ITEM_TYPE.PULLDOWN.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mNormalAdapter.getItemCount() + 1;
    }

    public void addPulldownView(View view) {
        this.mPulldownView = view;
    }
}
