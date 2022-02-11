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
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 21:11
 */
public class LoginModel implements ILoginModel {

    private static final int MSG_LOGIN = 51;
    private static final String URL_LOGIN = "https://www.wanandroid.com/user/login";

    @Override
    public void getData(ArrayList<RequestParameter> parameters, IMessagePresenter loginPresenter) {
        Message msg = new Message();
        NetUtils netUtils = new NetUtils();
        netUtils.postRequest(URL_LOGIN, parameters, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                msg.what = MSG_LOGIN;
                msg.obj = response.body().string();
                loginPresenter.loadLoginCondition(msg);
            }
        });
    }
}
