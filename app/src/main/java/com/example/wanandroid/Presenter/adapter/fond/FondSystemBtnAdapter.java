package com.example.wanandroid.Presenter.adapter.fond;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.SystemBean;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/29 01:08
 */
public class FondSystemBtnAdapter extends RecyclerView.Adapter<FondSystemBtnAdapter.ViewHolder> {

    private final ArrayList<SystemBean.Data.Children> contentList;
    private final Context context;

    public FondSystemBtnAdapter(Context context, ArrayList<SystemBean.Data.Children> contentList){
        this.context = context;
        this.contentList = contentList;
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
}
