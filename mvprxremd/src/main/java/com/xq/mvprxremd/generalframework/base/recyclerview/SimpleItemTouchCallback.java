package com.xq.mvprxremd.generalframework.base.recyclerview;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * @author 小侨
 * @time 2017/7/25  11:51
 * @desc 长按可拖拽，（没长按时）左右滑可删除
 * <p>
 * Android提供了ItemTouchHelper类，
 * 使得RecyclerView能够轻易地实现滑动和拖拽，
 * 此处我们要实现上下拖拽和侧滑删除。
 * 首先创建一个继承自ItemTouchHelper.Callback的类
 * <p>
 */

public class SimpleItemTouchCallback<T> extends ItemTouchHelper.Callback {

    private RecyclerView.Adapter mAdapter;
    private List<T> mData;
    private Drawable mDrawable;

    public SimpleItemTouchCallback(RecyclerView.Adapter adapter, List<T> data) {
        mAdapter = adapter;
        mData = data;
    }

    /**
     * 设置支持的拖拽和滑动的方向
     **/
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN; // 上下拖拽
        int swipeFlag = ItemTouchHelper.START | ItemTouchHelper.END; // 左->右和右->左滑动
        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /**
     * 拖拽时回调
     **/
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int from = viewHolder.getAdapterPosition();
        int to = target.getAdapterPosition();
        // TODO: 避免拉倒最后越界
        if (from >= mData.size()) {
            from = mData.size() - 1;
        }
        if (to >= mData.size()) {
            to = mData.size() - 1;
        }
        Collections.swap(mData, from, to);
        mAdapter.notifyItemMoved(from, to);
        return true;
    }

    /**
     * 滑动时回调
     **/
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int pos = viewHolder.getAdapterPosition();
        mData.remove(pos);
        mAdapter.notifyItemRemoved(pos);
    }

    /**
     * 状态变化时回调，一共有三个状态：
     * ACTION_STATE_IDLE(空闲状态)
     * ACTION_STATE_SWIPE(滑动状态)
     * ACTION_STATE_DRAG(拖拽状态)
     * 此方法中可以做一些状态变化时的处理，比如拖拽的时候修改背景色。
     **/
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            // TODO: 设置拖拽时的背景色，下面还原时，得考虑还原 CardView的样式
            mDrawable = viewHolder.itemView.getBackground(); // 获取原先样式
            viewHolder.itemView.setBackgroundColor(Color.GRAY);
        }
    }

    /**
     * 用户交互结束时回调。
     * 此方法可以做一些状态的清空，比如拖拽结束后还原背景色。
     **/
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        // 背景色还原（还原到原先样式）
        viewHolder.itemView.setBackground(mDrawable);
    }

    /**
     * 是否支持长按拖拽，默认为true。
     * 如果不想支持长按拖拽，则重写并返回false。
     **/
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
