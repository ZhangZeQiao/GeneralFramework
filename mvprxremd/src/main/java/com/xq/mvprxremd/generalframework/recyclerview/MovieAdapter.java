package com.xq.mvprxremd.generalframework.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xq.mvprxremd.R;
import com.xq.mvprxremd.generalframework.bean.Movies;

import java.util.List;

/**
 * @author 小侨
 * @time 2017/7/24  10:49
 * @desc movie列表adapter
 * <p>
 * 基本流程的adapter，为了不重复操作，用 QuickAdapter
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movies.SubjectsBean> mSubjects;

    public MovieAdapter(Context context, List<Movies.SubjectsBean> subjects) {
        mContext = context;
        mSubjects = subjects;
    }

    /**
     * 自定义ViewHolder
     **/
    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvAverage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvAverage = (TextView) itemView.findViewById(R.id.tv_average);
        }
    }

    /**
     * 创建ViewHolder
     **/
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // TODO: 最佳inflate写法
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list, parent, false);
        return new MovieViewHolder(itemView);
    }

    /**
     * 将数据与ViewHolder绑定
     **/
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // 数据绑定
        final Movies.SubjectsBean bean = mSubjects.get(position);
        holder.tvTitle.setText(bean.title);
        holder.tvAverage.setText(bean.rating.average + "");
        Glide.with(mContext)
                .load(bean.images.medium)
                .diskCacheStrategy(DiskCacheStrategy.ALL) // 加快显示速度：缓存在本地磁盘
                .into(holder.ivPoster);

        // 设置各控件点击监听
        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "这是《" + bean.title + "》的图片", Toast.LENGTH_LONG).show();
            }
        });

        // 设置item点击监听（最笨/快捷的方法）
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "《" + bean.title + "》", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

}
