package com.example.wanandroid.model.fond;

import android.os.Message;
import android.util.Log;

import com.example.wanandroid.Presenter.IMessagePresenter;
import com.example.wanandroid.utils.NetUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/28 19:18
 */
public class SystemModel {

    private static final String TAG = "SystemModel";

    private static final String URL_SYSTEM = "https://www.wanandroid.com/tree/json";

    /**
     * 网络请求数据
     */
    public static void getData(IMessagePresenter iMessagePresenter){
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest1(URL_SYSTEM, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 21;
                msg.obj = response.body().string();
                iMessagePresenter.loadLoginCondition(msg);
            }
        });
    }
}
