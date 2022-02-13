package com.example.wanandroid.Presenter.fond;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/12 20:30
 */
public class SystemContentPresenter implements IMessagePresenter {

    private static final int MSG_SYSTEM_CONTENT = 22;
    private Context context;

    public SystemContentPresenter(Context context){
        this.context = context;
    }

    @Override
    public void loadSuccess(Message msg) {
        mSystemContentHandler.sendMessage(msg);
    }

    @Override
    public void loadFail() {
        Toast.makeText(context, "网络似乎不给力", Toast.LENGTH_SHORT).show();
    }

    Handler mSystemContentHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_SYSTEM_CONTENT){
                String jsonStr = (String) msg.obj;
                Gson gson = new Gson();
                ArticleListBean articleListBean = gson.fromJson(jsonStr, ArticleListBean.class);
                ArrayList<ArticleListBean.Data.DataS> articleList = articleListBean.data.dataS;

            }
        }
    };
}
