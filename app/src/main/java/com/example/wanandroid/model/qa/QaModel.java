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
 * @description ： TODO: 问答数据处理
 * @email : 2803724412@qq.com
 * @date : 2022/2/9 14:04
 */
public class QaModel {

    private static final String BASEURL_QA = "https://wanandroid.com/wenda/list/";
    private static final String TAG = "QaModel";

    public static void getData(IMessagePresenter iMessagePresenter, int page) {
        Log.e(TAG, "=====page====" + page);
        String urlQa = BASEURL_QA + page + "/json";
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest(urlQa, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iMessagePresenter.loadFail();
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 111;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
            }
        });
    }
}
