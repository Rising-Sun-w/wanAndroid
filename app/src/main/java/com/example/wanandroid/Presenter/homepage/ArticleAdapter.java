package com.example.wanandroid.Presenter.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.Qa.QaAdapter;
import com.example.wanandroid.Presenter.Qa.QaPresenter;
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
public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ArticleListBean.Data.DataS> articleList;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    private final RecyclerView rvHpgArt;
    private static Boolean isQaListNull = false;

    private static final String TAG = ".ArticleAdapter";

    public ArticleAdapter(Context context, RecyclerView recyclerView){
        this.context = context;
        this.rvHpgArt = recyclerView;
    }

    public ArticleAdapter(Context context, ArrayList<ArticleListBean.Data.DataS> list, RecyclerView recyclerView) {
        this.articleList = list;
        this.context = context;
        this.rvHpgArt = recyclerView;
        isQaListNull = true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hpg_article, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == 1){
            View v = View.inflate(parent.getContext(), R.layout.item_foot, null);
            return new ArticleAdapter.FootViewHolder(v);
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ArticleAdapter.ItemViewHolder itemViewHolder = (ArticleAdapter.ItemViewHolder) holder;
            ArticleListBean.Data.DataS dataS = articleList.get(position);
            itemViewHolder.tvAuthor.setText(dataS.getAuthor());
            itemViewHolder.tvTime.setText(dataS.getNiceDate());
            itemViewHolder.tvTitle.setText(dataS.getTitle());
            itemViewHolder.tvType.setText(dataS.getSuperChapterName() + "·" + dataS.getChapterName());
            // item点击事件
            holder.itemView.setOnClickListener((View v) -> {
                if (onItemClickListener != null){
                    ContentActivity contentActivity = new ContentActivity();
                    contentActivity.setURl(articleList.get(position).getLink());
                    contentActivity.setTextTvTitle(articleList.get(position).getTitle());
                    onItemClickListener.onItemShortClick(position);
                }
            });
        } else if (holder instanceof FootViewHolder){
            ArticleAdapter.FootViewHolder footViewHolder = (ArticleAdapter.FootViewHolder) holder;
            if (isQaListNull) {
                footViewHolder.tvFoot.setText("正在加载中···");
                // 再一次网络请求
                new HomepagePresenter(context, rvHpgArt, false);
            } else {
                footViewHolder.tvFoot.setText("没有数据啦!");
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        // 若position + 1等于item总和，那么此时就为最后一个item ---> 脚布局
        if (position + 1 == getItemCount()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTime;
        TextView tvTitle;
        TextView tvType;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_article_author);
            tvTime = itemView.findViewById(R.id.tv_article_time);
            tvTitle = itemView.findViewById(R.id.tv_article_title);
            tvType = itemView.findViewById(R.id.tv_article_type);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {

        ProgressBar prBarFoot;
        TextView tvFoot;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            prBarFoot = itemView.findViewById(R.id.prBar_foot);
            tvFoot = itemView.findViewById(R.id.tv_foot);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void setArticleList(ArrayList<ArticleListBean.Data.DataS> articleList) {
        this.articleList = articleList;
    }
}
