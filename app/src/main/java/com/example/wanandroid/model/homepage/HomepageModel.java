package com.example.wanandroid.model.homepage;

import android.os.Message;
import android.util.Log;

import com.example.wanandroid.Presenter.IMessagePresenter;
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
    private static final String URL_ARTICLE = "https://www.wanandroid.com/article/list/0/json";
    private static final int MSG_BANNER = 12;
    private static final int MSG_ARTICLE = 11;
    private static final String TAG = "HomepageModel";

    public void getDataS(IMessagePresenter iMessagePresenter) {
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest1(URL_SLIDESHOW, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = MSG_BANNER;
                msg.obj = response.body().string();
                iMessagePresenter.loadLoginCondition(msg);
                response.close();
            }
        });
        netUtils.getRequest1(URL_ARTICLE, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = MSG_ARTICLE;
                msg.obj = response.body().string();
                iMessagePresenter.loadLoginCondition(msg);
                response.close();
            }
        });
    }
}
