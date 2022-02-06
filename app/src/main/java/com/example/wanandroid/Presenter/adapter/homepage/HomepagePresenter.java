package com.example.wanandroid.Presenter.adapter.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.wanandroid.Presenter.IMessagePresenter;
import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.bean.SlideshowBean;
import com.example.wanandroid.model.homepage.HomepageModel;
import com.example.wanandroid.view.activities.ContentActivity;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/6 08:42
 */
public class HomepagePresenter implements IMessagePresenter {

    private Context mContext;
    private ViewPager vpHpg;
    private RecyclerView rvHpg;
    private static final String TAG = "HomepagePresenter";

    HomepageModel homepageModel = new HomepageModel();
    Gson gson = new Gson();

    public HomepagePresenter(Context context, ViewPager vp, RecyclerView rv) {
        mContext = context;
        vpHpg = vp;
        rvHpg = rv;
        homepageModel.getDataS(this);
    }

    @Override
    public void loadLoginCondition(Message msg) {
        switch (msg.what) {
            case 11:
                mArticleHandler.sendMessage(msg);
                break;
            case 12:
                mBannerHandler.sendMessage(msg);
                Log.e("HomepagePresenter", "mBannerHandler");
                break;
            default:
        }
    }

    final Handler mBannerHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            SlideshowBean slideshowBean = gson.fromJson(jsonStr, SlideshowBean.class);
            // errorCode = 0 ---> 请求成功
            if (slideshowBean.getErrorCode() == 0) {
                ArrayList<SlideshowBean.Data> dataList = slideshowBean.data;
                ArrayList<String> imgUrlList = new ArrayList<>();
                for (SlideshowBean.Data d : dataList) {
                    imgUrlList.add(d.getImagePath());
                }
                vpHpg.setAdapter(new BannerAdapter(imgUrlList, (Context context, ArrayList<String> urls, int position, View imageView) -> {
                    Glide.with(context)
                            .load(urls.get(position))
                            .placeholder(R.drawable.ic_cache)
                            .error(R.drawable.ic_cache)
                            .into((ImageView) imageView);
                }));
//                vpHpg.setCurrentItem(vpHpg.getAdapter().getCount() / 2);
            }
        }
    };


    Handler mArticleHandler = new Handler(Looper.myLooper()) {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            ArticleListBean articleListBean = gson.fromJson(jsonStr, ArticleListBean.class);
            // 0 ----> 请求成功
            if (articleListBean.getErrorCode() == 0) {
                ArrayList<ArticleListBean.Data.DataS> articleList = new ArrayList<>(articleListBean.data.dataS);
                LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                rvHpg.setLayoutManager(layoutManager);
                ArticleAdapter articleAdapter = new ArticleAdapter(mContext, articleList);
                // 子item的点击事件
                articleAdapter.setOnItemClickListener((int position) -> {
                    // 跳转到ContentActivity
                    Intent intent = new Intent();
                    intent.setClass(mContext, ContentActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                });
                rvHpg.setAdapter(articleAdapter);
            }
        }
    };

}
