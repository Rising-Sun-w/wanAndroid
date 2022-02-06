package com.example.wanandroid.Presenter.adapter.fond;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wanandroid.Presenter.IMessagePresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.LoginRegisterBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/26 16:38
 */
public class FondSystemAdapter extends RecyclerView.Adapter<FondSystemAdapter.ViewHolder> {

    private static final String TAG = "FondSystemAdapter";
    private final ArrayList<SystemBean.Data> titleList;
    private ArrayList<SystemBean.Data.Children> contentList;
    private final Context context;

    public FondSystemAdapter(Context context, ArrayList<SystemBean.Data> titleList, ArrayList<SystemBean.Data.Children> contentList) {
        this.context = context;
        this.titleList = titleList;
        this.contentList = contentList;
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
         contentList = titleList.get(position).getChildren();
        // 子Recyclerview的设置   瀑布流布局
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        holder.rvContent.setLayoutManager(layoutManager);
        holder.rvContent.setAdapter(new FondSystemBtnAdapter(context, contentList));
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
}
