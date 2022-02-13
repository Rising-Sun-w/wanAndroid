package com.example.wanandroid.model.homepage;

import android.os.Message;
import android.util.Log;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.utils.NetUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/6 08:37
 */
public class HomepageModel {

    private static final String URL_SLIDESHOW = "https://www.wanandroid.com/banner/json";
    private static final String BASEURL_ARTICLE = "https://www.wanandroid.com/article/list/";
    private static final int MSG_BANNER = 12;
    private static final int MSG_ARTICLE = 11;
    private static final String TAG = "HomepageModel";
    NetUtils netUtils = new NetUtils();

    public void getArticleData(IMessagePresenter iMessagePresenter, int page) {
        String URL_ARTICLE = BASEURL_ARTICLE + page + "/json";
        Log.e(TAG, "=====page=====" + page);
        netUtils.getRequest(URL_ARTICLE, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iMessagePresenter.loadFail();
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = MSG_ARTICLE;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
                response.close();
            }
        });
    }

    public void getBannerData(IMessagePresenter iMessagePresenter){
        netUtils.getRequest(URL_SLIDESHOW, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iMessagePresenter.loadFail();
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = MSG_BANNER;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
                response.close();
            }
        });
    }
}
