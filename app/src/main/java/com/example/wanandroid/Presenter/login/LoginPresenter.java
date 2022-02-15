package com.example.wanandroid.Presenter.login;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.model.bean.LoginRegisterBean;
import com.example.wanandroid.model.login.LoginModel;
import com.example.wanandroid.utils.RequestParameter;
import com.example.wanandroid.view.activities.LoginActivity;
import com.example.wanandroid.view.activities.MainActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 登录逻辑处理
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 20:39
 */
public class LoginPresenter implements IMessagePresenter {

    LoginModel loginModel = new LoginModel();
    LoginActivity loginActivity = new LoginActivity();
    private static final int MSG_LOGIN = 51;

    private Context context;

    public LoginPresenter(Context context, String username, String password) {
        ArrayList<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameter("username", username));
        parameters.add(new RequestParameter("password", password));
        this.context = context;
        loginModel.getData(parameters, this);
    }

    @Override
    public void loadSuccess(Message msg) {
        mHandler.sendMessage(msg);
    }

    @Override
    public void loadFail() {
        Toast.makeText(context, "网络似乎不给力", Toast.LENGTH_SHORT).show();
    }

    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_LOGIN) {
                String jsonStr = (String) msg.obj;
                Gson gson = new Gson();
                LoginRegisterBean loginBean = gson.fromJson(jsonStr, LoginRegisterBean.class);
                if (loginBean.getErrorCode() == -1) {
                    Toast.makeText(context, loginBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                    loginActivity.showLoading();
                }
            }
        }
    };
}
