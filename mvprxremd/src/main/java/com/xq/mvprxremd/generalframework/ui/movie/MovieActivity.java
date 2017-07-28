package com.xq.mvprxremd.generalframework.ui.movie;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.xq.mvprxremd.R;
import com.xq.mvprxremd.generalframework.base.BaseActivity;
import com.xq.mvprxremd.generalframework.base.recyclerview.OnPulldownListener;
import com.xq.mvprxremd.generalframework.base.recyclerview.PulldownAdapterWrapper;
import com.xq.mvprxremd.generalframework.base.recyclerview.QuickRlvAdapter;
import com.xq.mvprxremd.generalframework.base.recyclerview.SimpleItemTouchCallback;
import com.xq.mvprxremd.generalframework.bean.Movie;
import com.xq.mvprxremd.generalframework.bean.Movies;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小侨
 * @time 2017/7/21  10:25
 * @desc movie页面View层
 */
public class MovieActivity extends BaseActivity<MoviePresenter> implements MovieContract.IView {

    // 互相关联
    // MovieActivity类中：extends BaseActivity<MoviePresenter>
    // MoviePresenter类中：extends BasePresenter<MovieActivity>

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mMovieRecyclerView;
    // private MovieAdapter mMovieAdapter;
    private QuickRlvAdapter mMovieAdapter;
    private PulldownAdapterWrapper mPulldownAdapter;

    private List<Movies.SubjectsBean> mMoviesData = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.activity_movie;
    }

    @Override
    protected void initView() {
        mMovieRecyclerView = (RecyclerView) findViewById(R.id.rv);
        // 如果可以确定每个Item的高度固定，选用这个属性可以提高性能
        mMovieRecyclerView.setHasFixedSize(true);
        // 使用默认的动画效果
        mMovieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 使用默认的LayoutManager（竖直）
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieRecyclerView.setLayoutManager(linearLayoutManager);
        // 竖直线性不反转布局：setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // 表格布局：setLayoutManager(new GridLayoutManager(this, 3));
        // 瀑布流布局：setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        // TODO: 为 RecyclerView添加上拉刷新功能        TODO: 此处的 linearLayoutManager必须与上面 setLayoutManager()是同一个实例
        mMovieRecyclerView.addOnScrollListener(new OnPulldownListener(linearLayoutManager) {
            @Override
            protected void onLoadMore(int currentPage) {
                mPresenter.getMoreMovies(mMoviesData.size(), 10);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        //  设置进度条的颜色变化，最多可以设置4种颜色
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.GREEN);
        // 调整进度条距离屏幕顶部的距离
        // TODO: setProgressViewOffset(boolean scale, int start, int end)中start不能等于end，会造成下拉刷新时小圆圈不转动，颜色不显示
        mSwipeRefreshLayout.setProgressViewOffset(false, 0, 50);
        // 适配写法：mSwipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        // 设置下拉监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mMoviesData.clear(); // 清空，防止重复添加
                mPresenter.getMoreMovies(mMoviesData.size(), 10);
            }
        });
    }

    @Override
    protected MoviePresenter initPresenter() {
        return new MoviePresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getMovies(0, 10); // 一页显示 10 条数据
    }

    @Override
    public void showMovies(final Movies movies) {
        mMoviesData.addAll(movies.subjects);

        // 普通 adapter：
        // mMovieAdapter = new MovieAdapter(this, movies.subjects);
        // mMovieRecyclerView.setAdapter(mMovieAdapter);

        // TODO: 快捷 adapter：
        mMovieAdapter = new QuickRlvAdapter<Movies.SubjectsBean>(mContext, mMoviesData) {

            @Override
            protected int getLayoutId(int viewType) {
                return R.layout.item_movie;
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }

            @Override
            protected void convert(VH holder, final Movies.SubjectsBean bean, int position) {
                // 数据绑定
                holder.setImage(R.id.iv_poster, bean.images.medium);
                holder.setText(R.id.tv_title, bean.title);
                holder.setText(R.id.tv_average, bean.rating.average + "");
                // 设置各控件点击监听
                holder.getView(R.id.iv_poster).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MovieActivity.this, "这是《" + bean.title + "》的海报", Toast.LENGTH_LONG).show();
                    }
                });
                // 设置item点击监听（最快捷的方法）
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "《" + bean.title + "》", Toast.LENGTH_LONG).show();
                    }
                });
            }
        };

        // TODO: 装饰器（Decorator）设计模式添加上拉加载更多 view
        mPulldownAdapter = new PulldownAdapterWrapper(mMovieAdapter);
        mPulldownAdapter.addPulldownView(LayoutInflater.from(mContext).inflate(R.layout.item_pulldown, mMovieRecyclerView, false));

        if (mMoviesData.size() < 10) { // 小于 10说明数据不够
            // TODO: 为 RecyclerView设置长按滑动删除、拖拽功能
            ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallback(mMovieAdapter, mMoviesData));
            helper.attachToRecyclerView(mMovieRecyclerView);
            mMovieRecyclerView.setAdapter(mMovieAdapter);
        } else {
            // TODO: 为 RecyclerView设置长按滑动删除、拖拽功能
            ItemTouchHelper helper = new ItemTouchHelper(new SimpleItemTouchCallback(mPulldownAdapter, mMoviesData));
            helper.attachToRecyclerView(mMovieRecyclerView);
            mMovieRecyclerView.setAdapter(mPulldownAdapter);
        }
    }

    @Override
    public void showMoreMovies(Movies movies) {
        // TODO: 上拉刷新时 mPresenter.getMoreMovies() 中要调用 showMoreMovies，不要复用 showTop250()
        // TODO: 这样 notifyDataSetChanged()时才不会跳到第一项
        // TODO: 下拉刷新也指向这里，不要指向 showMovies()，这样就不会造成下拉刷新后不能拖拽 item
        mMoviesData.addAll(movies.subjects);
        if (mMoviesData.size() < 10) {
            mMovieAdapter.notifyDataSetChanged();
        } else {
            mPulldownAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showMovieById(Movie movie) {
        showToast("null");
    }

    @Override
    public void showLoading() {
        // super.showLoading();
        // TODO: 第一次进入，设置swipeRefreshLayout的刷新状态显示，要异步实现
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        // super.hideLoading();
        // 设置SwipeRefreshLayout当前是否处于刷新状
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
