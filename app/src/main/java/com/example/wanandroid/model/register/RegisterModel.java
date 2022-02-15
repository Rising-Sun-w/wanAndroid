package com.example.wanandroid.model.register;

import android.os.Message;
import android.util.Log;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.utils.NetUtils;
import com.example.wanandroid.utils.RequestParameter;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/15 18:30
 */
public class RegisterModel {

    private static final String URL_REGISTER = "https://www.wanandroid.com/user/register";

    public void getRegisterData(ArrayList<RequestParameter> parameters, IMessagePresenter iMessagePresenter) {
        Log.d("TAG", Log.getStackTraceString(new Throwable()));
        Message msg = new Message();
        NetUtils netUtils = new NetUtils();
        netUtils.postRequest(URL_REGISTER, parameters, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "请求失败了啊");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                msg.what = 52;
                msg.obj = response.body().string();
                iMessagePresenter.loadSuccess(msg);
                response.close();
            }
        });
    }

}
