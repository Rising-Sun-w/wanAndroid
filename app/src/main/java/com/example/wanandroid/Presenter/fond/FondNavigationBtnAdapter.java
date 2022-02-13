package com.example.wanandroid.Presenter.fond;

import android.content.Context;
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
import com.example.wanandroid.view.activities.ContentActivity;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/29 01:08
 */
public class FondNavigationBtnAdapter extends RecyclerView.Adapter<FondNavigationBtnAdapter.ViewHolder> {

    private final ArrayList<NavigationBean.Data.Articles> contentList;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    private static final String TAG = "FondNavigationAdapter";

    public FondNavigationBtnAdapter(Context context, ArrayList<NavigationBean.Data.Articles> navigationContentList) {
        this.context = context;
        this.contentList = navigationContentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_tab_rv_btn, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.btnContent.setText(contentList.get(position).getTitle());
        holder.itemView.setOnClickListener((View v) -> {
            if (onItemClickListener != null) {
                ContentActivity contentActivity = new ContentActivity();
                String url = contentList.get(position).getLink();
                char[] urlList = url.toCharArray();
                String urlSave = "";
                Boolean isUrlAdds = false;
                for (int i = 0; i < urlList.length; i++) {
                    if (urlList[i] == 's' && urlList[i + 1] == ':') {
                        break;
                    }
                    if (urlList[i] == ':' && urlList[i + 1] == '/') {
                        for (int j = i; j < urlList.length; j++) {
                            urlSave += urlList[j];
                        }
                        isUrlAdds = true;
                        break;
                    }
                }
                String urlTestSave = url;
                if (isUrlAdds) {
                    urlTestSave = "https" + urlSave;
                }
                contentActivity.setURl(urlTestSave);
                Log.e(TAG, "=======setURl========" + urlTestSave);
                contentActivity.setTextTvTitle(contentList.get(position).getTitle());
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
