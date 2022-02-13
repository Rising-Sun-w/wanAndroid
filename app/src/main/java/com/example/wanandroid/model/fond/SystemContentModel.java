package com.example.wanandroid.model.fond;

import android.os.Message;

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
 * @date : 2022/2/12 20:02
 */
public class SystemContentModel {
    private static final String BASE_URL = "https://www.wanandroid.com/article/list/";

    public static void getSystemContentData(int page, int cid, IMessagePresenter iMessagePresenter) {
        String url = BASE_URL + page + "/json?cid=" + cid;
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iMessagePresenter.loadFail();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 22;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
                response.close();
            }
        });
    }
}
