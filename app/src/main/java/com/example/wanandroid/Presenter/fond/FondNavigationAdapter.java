package com.example.wanandroid.Presenter.fond;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.NavigationBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.example.wanandroid.view.activities.ContentActivity;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 发现模块中导航的适配器
 * @email : 2803724412@qq.com
 * @date : 2022/1/26 16:38
 */
public class FondNavigationAdapter extends RecyclerView.Adapter<FondNavigationAdapter.ViewHolder> {

    private final ArrayList<NavigationBean.Data> navigationList;
    private final Context context;

    public FondNavigationAdapter(Context context, ArrayList<NavigationBean.Data> navigationList) {
        this.context = context;
        this.navigationList = navigationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(navigationList.get(position).getName());
        ArrayList<NavigationBean.Data.Articles> contentList = navigationList.get(position).getArticles();
        // 子Recyclerview的设置   瀑布流布局
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        holder.rvContent.setLayoutManager(layoutManager);
        FondNavigationBtnAdapter navigationBtnAdapter = new FondNavigationBtnAdapter(context, contentList);
        navigationBtnAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemShortClick(int position) {
                Intent intent = new Intent();
                intent.setClass(context, ContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {}
        });
        holder.rvContent.setAdapter(navigationBtnAdapter);
    }

    @Override
    public int getItemCount() {
        return navigationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final RecyclerView rvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_tab_title);
            rvContent = itemView.findViewById(R.id.rv_tab_content);
        }
    }
}
