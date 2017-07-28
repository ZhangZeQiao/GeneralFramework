package com.xq.mvprxremd.generalframework.base.recyclerview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author 小侨
 * @time 2017/7/25  15:37
 * @desc ${TODD}
 * <p>
 * 然后就是加载更多功能，RecyclerView 有一个方法 addOnScrollListener ，
 * 我们只要传入一个 RecyclerView.OnScrollListener 就可以实现加载更多了，
 * 但是事实是为了充分保证 RecyclerView 的灵活性，Android 本身是没有对这个滑动接口做处理的，
 * 需要我们自定义个加载更多的接口去实现它，然后才能真正实现加载更多。
 * <p>
 * 实现起来也很简单，我们只要重写 onScrolled 方法即可。
 * 下面是一个封装好的加载更多的接口实现类，然后作为参数传进去就好了。
 */

public abstract class OnPulldownListener extends RecyclerView.OnScrollListener {

    private int mCurrentPage = 1;
    private LinearLayoutManager mLinearLayoutManager;
    private int mTotalItemCount;
    private int mLastCompletelyVisibleItemPosition; // 最后一个完全可见的 item

    public OnPulldownListener(LinearLayoutManager linearLayoutManager) {
        mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        mTotalItemCount = mLinearLayoutManager.getItemCount();
        mLastCompletelyVisibleItemPosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();

        if (mTotalItemCount == mLastCompletelyVisibleItemPosition + 1) { // position与size相差 1
            mCurrentPage++;
            onLoadMore(mCurrentPage);
        }
    }

    protected abstract void onLoadMore(int currentPage);
}
