package com.example.wanandroid.Presenter.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.view.fragment.QaFragment;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/23 09:19
 */
public class QaAdapter extends RecyclerView.Adapter<QaAdapter.ViewHolder> {

    ArrayList<ArrayList<ArticleListBean.Data.DataS>> pageList;
    ArrayList<ArticleListBean.Data.DataS> qaList;
    Context context;
    QaFragment qaFragment = new QaFragment();

    /**
     * 判断是否加载更多
     */
    private Boolean i = false;
    private static final String TAG = ".QaAdapter";

    public QaAdapter(Context context, ArrayList<ArticleListBean.Data.DataS> qaList) {
        this.context = context;
        this.qaList = qaList;
//        pageList.add(qaList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qa, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = position % 19;
        ArticleListBean.Data.DataS dataS = qaList.get(position);
        holder.mAuthor.setText(dataS.getAuthor());
        holder.mTime.setText(dataS.getNiceDate());
        holder.mTitle.setText(dataS.getTitle());
        holder.mText.setText(Html.fromHtml(dataS.getDesc()));
        holder.mType.setText(dataS.getSuperChapterName() + "·" + dataS.getChapterName());
        // 已经滑到底部，进行刷新
        if (position - 1 == qaList.size() - 1) {
            i = true;
            qaFragment.initInfo();
        }
    }

    @Override
    public int getItemCount() {
        return qaList.size() * 40;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mAuthor;
        private final TextView mTime;
        private final TextView mTitle;
        private final TextView mText;
        private final TextView mType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.tv_qa_author);
            mTime = itemView.findViewById(R.id.tv_qa_time);
            mTitle = itemView.findViewById(R.id.tv_qa_title);
            mText = itemView.findViewById(R.id.tv_qa_text);
            mType = itemView.findViewById(R.id.tv_qa_type);
        }
    }
}
