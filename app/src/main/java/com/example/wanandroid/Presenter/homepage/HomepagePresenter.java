package com.example.wanandroid.Presenter.homepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.bean.SlideshowBean;
import com.example.wanandroid.model.homepage.HomepageModel;
import com.example.wanandroid.view.activities.ContentActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

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
    private static int page = 0;
    private Boolean isRefresh;
    private static ArrayList<ArticleListBean.Data.DataS> articleMoreList = new ArrayList<>();
    private static final String TAG = "HomepagePresenter";

    HomepageModel homepageModel = new HomepageModel();
    Gson gson = new Gson();

    public HomepagePresenter(Context context, RecyclerView rv, Boolean isRefresh) {
        mContext = context;
        rvHpg = rv;
        homepageModel.getArticleData(this, page++);
        this.isRefresh = isRefresh;
    }

    public HomepagePresenter(Context context, ViewPager vp, RecyclerView rv, Boolean isRefresh) {
        mContext = context;
        vpHpg = vp;
        rvHpg = rv;
        homepageModel.getBannerData(this);
        homepageModel.getArticleData(this, page++);
        this.isRefresh = isRefresh;
    }

    @Override
    public void loadSuccess(Message msg) {
        switch (msg.what) {
            case 11:
                mArticleHandler.sendMessage(msg);
                break;
            case 12:
                mBannerHandler.sendMessage(msg);
                break;
            default:
        }
    }

    @Override
    public void loadFail() {
        Toast.makeText(mContext, "网络似乎不给力", Toast.LENGTH_SHORT).show();
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
                ArticleAdapter articleAdapter = new ArticleAdapter(mContext, rvHpg);
                ArrayList<ArticleListBean.Data.DataS> articleList = new ArrayList<>(articleListBean.data.dataS);
                articleMoreList.addAll(articleList);
                int size = articleMoreList.size();
                if (page - 1 == 0 || isRefresh) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    rvHpg.setLayoutManager(layoutManager);
                    articleAdapter = new ArticleAdapter(mContext, articleMoreList, rvHpg);
                    rvHpg.setAdapter(articleAdapter);
                } else {
                    articleAdapter.setArticleList(articleMoreList);
                    rvHpg.scrollToPosition(size);
                }
                // 子item的点击事件
                articleAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemShortClick(int position) {
                        // 跳转到ContentActivity
                        Intent intent = new Intent();
                        intent.setClass(mContext, ContentActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onItemLongClick(int position) {
                    }
                });
            }
        }
    };

}
