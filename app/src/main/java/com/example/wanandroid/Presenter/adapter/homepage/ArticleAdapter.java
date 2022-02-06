package com.example.wanandroid.Presenter.adapter.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.view.activities.ContentActivity;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/22 13:26
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private ArrayList<ArticleListBean.Data.DataS> articleList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    private static final String TAG = ".ArticleAdapter";

    public ArticleAdapter(Context context, ArrayList<ArticleListBean.Data.DataS> list) {
        this.articleList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hpg_article, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleListBean.Data.DataS dataS = articleList.get(position);
        holder.tvAuthor.setText(dataS.getAuthor());
        holder.tvTime.setText(dataS.getNiceDate());
        holder.tvTitle.setText(dataS.getTitle());
        holder.tvType.setText(dataS.getSuperChapterName() + "·" + dataS.getChapterName());
        holder.itemView.setOnClickListener((View v) -> {
            if (onItemClickListener != null){
                ContentActivity contentActivity = new ContentActivity();
                contentActivity.setURl(articleList.get(position).getLink());
                contentActivity.setTextTvTitle(articleList.get(position).getTitle());
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTime;
        TextView tvTitle;
        TextView tvType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_article_author);
            tvTime = itemView.findViewById(R.id.tv_article_time);
            tvTitle = itemView.findViewById(R.id.tv_article_title);
            tvType = itemView.findViewById(R.id.tv_article_type);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
