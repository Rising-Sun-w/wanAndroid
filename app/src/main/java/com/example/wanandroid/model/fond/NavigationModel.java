package com.example.wanandroid.model.fond;

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
 * @date : 2022/2/5 20:54
 */
public class NavigationModel {

    private static final String TAG = "NavigationModel";

    private static final String URL_NAVI = "https://www.wanandroid.com/navi/json";

    /**
     * 网络请求数据
     */
    public static void getData(IMessagePresenter iMessagePresenter){
        NetUtils netUtils = new NetUtils();
        netUtils.getRequest1(URL_NAVI, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = new Message();
                msg.what = 31;
                msg.obj = response.body().string();
                iMessagePresenter.loadLoginCondition(msg);
            }
        });
    }
}
