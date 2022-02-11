package com.example.wanandroid.Presenter.Qa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.view.activities.ContentActivity;
import com.example.wanandroid.view.fragment.QaFragment;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/23 09:19
 */
public class QaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ArrayList<ArticleListBean.Data.DataS>> pageList;
    private ArrayList<ArticleListBean.Data.DataS> qaList;
    private final Context context;
    private QaFragment qaFragment = new QaFragment();
    private final RecyclerView rvQa;
    private Boolean isQaListNull = false;
    private OnItemClickListener onItemClickListener;
    private static final String TAG = ".QaAdapter";

    public QaAdapter(Context context, RecyclerView rvQa) {
        this.context = context;
        this.rvQa = rvQa;
    }

    public QaAdapter(Context context, ArrayList<ArticleListBean.Data.DataS> qaList, RecyclerView rv, int page) {
        this.context = context;
        this.qaList = qaList;
        isQaListNull = true;
        rvQa = rv;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qa, parent, false);
            return new ItemViewHolder(v);
        } else if (viewType == 1) {
            View v = View.inflate(parent.getContext(), R.layout.item_foot, null);
            Log.e(TAG, "=======isQaListNull======" + isQaListNull);
            return new FootViewHolder(v);
        }
        return null;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            position = position % 19;
            ArticleListBean.Data.DataS dataS = qaList.get(position);
            itemViewHolder.mAuthor.setText(dataS.getAuthor());
            itemViewHolder.mTime.setText(dataS.getNiceDate());
            itemViewHolder.mTitle.setText(dataS.getTitle());
            itemViewHolder.mText.setText(Html.fromHtml(dataS.getDesc()));
            itemViewHolder.mType.setText(dataS.getSuperChapterName() + "·" + dataS.getChapterName());
            int finalPosition = position;
            holder.itemView.setOnClickListener((View v) -> {
                if (onItemClickListener != null) {
                    ContentActivity contentActivity = new ContentActivity();
                    contentActivity.setTextTvTitle(dataS.getTitle());
                    contentActivity.setURl(dataS.getLink());
                    onItemClickListener.onItemShortClick(finalPosition);
                }
            });
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            Log.e(TAG, "====isQaListNull====" + isQaListNull);
            if (isQaListNull) {
                footViewHolder.tvFoot.setText("正在加载中···");
                // 再一次网络请求
                new QaPresenter(context, rvQa, false);
            } else {
                footViewHolder.tvFoot.setText("没有数据啦!");
            }
        }

    }

    @Override
    public int getItemCount() {
        // +1 ----> 添加的一个底布局
        return qaList.size() + 1;
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

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView mAuthor;
        private final TextView mTime;
        private final TextView mTitle;
        private final TextView mText;
        private final TextView mType;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mAuthor = itemView.findViewById(R.id.tv_qa_author);
            mTime = itemView.findViewById(R.id.tv_qa_time);
            mTitle = itemView.findViewById(R.id.tv_qa_title);
            mText = itemView.findViewById(R.id.tv_qa_text);
            mType = itemView.findViewById(R.id.tv_qa_type);
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

    public void setQaList(ArrayList<ArticleListBean.Data.DataS> qaList) {
        if (this.qaList == qaList) {
            isQaListNull = false;
        } else {
            this.qaList = qaList;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
