package com.example.wanandroid.Presenter.fond;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.NavigationBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.example.wanandroid.view.activities.SystemContentActivity;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 发现模块体系item的适配器
 * @email : 2803724412@qq.com
 * @date : 2022/1/29 01:08
 */
public class FondSystemBtnAdapter extends RecyclerView.Adapter<FondSystemBtnAdapter.ViewHolder> {

    private final ArrayList<SystemBean.Data.Children> contentList;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public FondSystemBtnAdapter(Context context, ArrayList<SystemBean.Data.Children> systemContentList){
        this.context = context;
        this.contentList = systemContentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tab_rv_btn, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnContent.setText(contentList.get(position).getName());
        holder.itemView.setOnClickListener((View v) -> {
            if (onItemClickListener != null){
                SystemContentActivity systemContentActivity = new SystemContentActivity();
                systemContentActivity.setContentList(contentList);
                onItemClickListener.onItemShortClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button btnContent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnContent = itemView.findViewById(R.id.btn_item_tab_rv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
