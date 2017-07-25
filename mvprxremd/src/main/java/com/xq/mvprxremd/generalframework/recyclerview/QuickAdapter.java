package com.xq.mvprxremd.generalframework.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * @author 小侨
 * @time 2017/7/25  9:47
 * @desc 万能适配器/快速适配器
 * <p>
 * 为了创建一个RecyclerView的Adapter，每次我们都需要去做重复劳动，
 * 包括重写onCreateViewHolder(),getItemCount()、创建ViewHolder，并且实现过程大同小异，
 * 因此万能适配器出现了，他能通过以下方式快捷地创建一个Adapter：
 * <p>
 */

public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {

    private static Context mContext;
    private List<T> mDatas;

    public QuickAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    protected abstract int getLayoutId(int viewType); // 根据viewType返回布局ID

    protected abstract void convert(VH holder, T t, int position); // 做具体的bind操作

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.getVH(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    protected static class VH extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews; // 存储item view的控件
        private View mConvertView;

        public VH(View itemView) {
            super(itemView);

            mConvertView = itemView;
            mViews = new SparseArray<>();
        }

        public static VH getVH(ViewGroup parent, int layoutId) {
            View converView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(converView);
        }

        public <T extends View> T getView(int id) {
            View view = mViews.get(id);
            if (view == null) {
                view = mConvertView.findViewById(id);
                mViews.put(id, view); // 复用，避免重复fbi
            }
            return (T) view;
        }

        public void setText(int id, String value) {
            TextView textView = getView(id);
            textView.setText(value);
        }

        public void setImage(int id, String url) {
            ImageView imageView = getView(id);
            Glide.with(mContext)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL) // 加快显示速度：缓存在本地磁盘
                    .into(imageView);
        }
    }
}
