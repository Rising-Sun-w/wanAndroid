package com.example.wanandroid.model.qa;

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
 * @date : 2022/2/9 14:04
 */
public class QaModel {

    private static final String BASEURL_QA = "https://wanandroid.com/wenda/list/";
    private static final String TAG = "QaModel";

    public static void getData(IMessagePresenter iMessagePresenter, int page) {
        Log.e(TAG, "=====page====" + page);
        String URL_QA = BASEURL_QA + page + "/json";
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest1(URL_QA, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 111;
                msg.obj = response.body().string();
                iMessagePresenter.loadLoginCondition(msg);
            }
        });
    }
}
