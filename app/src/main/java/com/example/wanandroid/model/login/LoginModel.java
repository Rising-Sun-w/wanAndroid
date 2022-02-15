package com.example.wanandroid.model.login;

import android.os.Message;
import android.util.Log;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.utils.NetUtils;
import com.example.wanandroid.utils.RequestParameter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO: 登录数据处理
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 21:11
 */
public class LoginModel {

    private static final int MSG_LOGIN = 51;
    private static final String URL_LOGIN = "https://www.wanandroid.com/user/login";

    public void getData(ArrayList<RequestParameter> parameters, IMessagePresenter iMessagePresenter) {
        Message msg = new Message();
        NetUtils netUtils = new NetUtils();
        netUtils.postRequest(URL_LOGIN, parameters, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iMessagePresenter.loadFail();
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                msg.what = MSG_LOGIN;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
                response.close();
            }
        });
    }
}
