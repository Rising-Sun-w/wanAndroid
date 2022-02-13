package com.example.wanandroid.Presenter.Qa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.Presenter.interfaces.OnItemClickListener;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.qa.QaModel;
import com.example.wanandroid.view.activities.ContentActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/9 13:58
 */
public class QaPresenter implements IMessagePresenter {
    private static final int MSG_QA = 111;
    Gson gson = new Gson();
    private static int page = 0;
    private final Context mContext;
    private final RecyclerView mRvQa;
    private static final String TAG = "QaPresenter";
    private Boolean isRefresh;
    private static ArrayList<ArticleListBean.Data.DataS> qaArticleList = new ArrayList<>();

    public QaPresenter(Context context, RecyclerView rv, Boolean isRefresh) {
        mContext = context;
        mRvQa = rv;
        QaModel.getData(this, page++);
        this.isRefresh = isRefresh;
    }

    @Override
    public void loadSuccess(Message msg) {
        if (msg.what == MSG_QA) {
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public void loadFail() {
        Toast.makeText(mContext, "网络似乎不给力", Toast.LENGTH_SHORT).show();
    }

    Handler mHandler = new Handler(Looper.myLooper()) {
        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_QA) {
                String jsonStr = (String) msg.obj;
                ArticleListBean articleListBean = gson.fromJson(jsonStr, ArticleListBean.class);
                ArrayList<ArticleListBean.Data.DataS> qaList = new ArrayList<>(articleListBean.data.dataS);
                int size = qaArticleList.size();
                if (null != qaList){
                    qaArticleList.addAll(qaList);
                } else {
                    // 底部显示没有数据啦:

                }
                QaAdapter adapter = new QaAdapter(mContext, mRvQa);
                if (page - 1 == 0 || isRefresh) {
                    // page为0，第一次进行网络请求, isRefresh控制是否是点击底部导航栏的问答
                    LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
                    adapter = new QaAdapter(mContext, qaArticleList, mRvQa, page);
                    adapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemShortClick(int position) {
                            Intent intent = new Intent();
                            intent.setClass(mContext, ContentActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }

                        @Override
                        public void onItemLongClick(int position) { }
                    });
                    mRvQa.setLayoutManager(layoutManager);
                    mRvQa.setAdapter(adapter);
                } else {
                    // 直接改变Adapter中控制Recyclerview的item的数据，并将焦点聚集在当前显示item（避免重新刷新后回到顶部）
                    adapter.setQaList(qaArticleList);
                    mRvQa.scrollToPosition(size);
                }
            }
        }
    };
}
