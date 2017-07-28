package com.xq.mvprxremd.generalframework.base.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author 小侨
 * @time 2017/7/25  10:55
 * @desc 添加header、footer
 * <p>
 * RecyclerView默认没有提供类似addHeaderView()和addFooterView()的API，
 * 因此这里介绍如何优雅地实现这两个接口。
 * <p>
 * 装饰器（Decorator）设计模式：该设计模式通过组合的方式，
 * 在不破话原有类代码的情况下，对原有类的功能进行扩展。
 * <p>
 */

public class AdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    enum ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }

    private RecyclerView.Adapter mNormalAdapter;
    private View mHeaderView;
    private View mFooterView;

    public AdapterWrapper(RecyclerView.Adapter normalAdapter) {
        mNormalAdapter = normalAdapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.ordinal()) {
            return new RecyclerView.ViewHolder(mHeaderView) {
            };
        } else if (viewType == ITEM_TYPE.FOOTER.ordinal()) {
            return new RecyclerView.ViewHolder(mFooterView) {
            };
        } else {
            return mNormalAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        } else if (position == mNormalAdapter.getItemCount() + 1) {
            return;
        } else {
            mNormalAdapter.onBindViewHolder(holder, position - 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        } else if (position == mNormalAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return mNormalAdapter.getItemCount() + 2;
    }

    public void addHeaderView(View view) {
        this.mHeaderView = view;
    }

    public void addFooterView(View view) {
        this.mFooterView = view;
    }
}
