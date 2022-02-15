package com.example.wanandroid.Presenter.fond;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.wanandroid.view.activities.SystemContentActivity;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 发现模块中体系的适配器
 * @email : 2803724412@qq.com
 * @date : 2022/1/26 16:38
 */
public class FondSystemAdapter extends RecyclerView.Adapter<FondSystemAdapter.ViewHolder> {

    private static final String TAG = "FondSystemAdapter";
    private ArrayList<SystemBean.Data> titleList;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public FondSystemAdapter(Context context, ArrayList<SystemBean.Data> titleList) {
        this.context = context;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tab, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(titleList.get(position).getName());
        ArrayList<SystemBean.Data.Children> contentList = titleList.get(position).getChildren();
        // 子Recyclerview的设置   瀑布流布局
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        holder.rvContent.setLayoutManager(layoutManager);
        FondSystemBtnAdapter fondSystemBtnAdapter = new FondSystemBtnAdapter(context, contentList);
        fondSystemBtnAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemShortClick(int position) {
                Intent intent = new Intent();
                intent.setClass(context, SystemContentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(int position) {

            }
        });
        holder.rvContent.setAdapter(fondSystemBtnAdapter);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
