package com.example.wanandroid.Presenter.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.model.bean.LoginRegisterBean;
import com.example.wanandroid.model.register.RegisterModel;
import com.example.wanandroid.utils.RequestParameter;
import com.example.wanandroid.view.activities.LoginActivity;
import com.example.wanandroid.view.activities.RegisterActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/15 18:25
 */
public class RegisterPresenter implements IMessagePresenter {

    private Context context;
    private ArrayList<RequestParameter> parameters;

    private static final int MSG_REGISTER = 52;
    RegisterModel registerModel = new RegisterModel();

    public RegisterPresenter(Context context, ArrayList<RequestParameter> parameters){
        this.context = context;
        this.parameters = parameters;
        registerModel.getRegisterData(parameters, this);
    }

    @Override
    public void loadSuccess(Message msg) {
        mHandler.sendMessage(msg);
    }

    @Override
    public void loadFail() {

    }

    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_REGISTER) {
                String jsonStr = (String) msg.obj;
                Gson gson = new Gson();
                LoginRegisterBean registerBean = gson.fromJson(jsonStr, LoginRegisterBean.class);
                // errorCode: 0 = 注册成功， -1 = 用户名已经被注册或者两次输入密码不一
                if (registerBean.getErrorCode() == 0) {
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                    // 持久化存储
                    SharedPreferences preferences = context.getSharedPreferences("register", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", parameters.get(0).getName());
                    editor.putString("password", parameters.get(0).getValues());
                    editor.apply();

                    context.startActivity(new Intent(context, LoginActivity.class));
                    RegisterActivity registerActivity = new RegisterActivity();
                    registerActivity.onDestroy();
                    Log.e("TAG", "存储的值 username=" + preferences.getString("username", "") + "password=" + preferences.getString("password", ""));
                } else {
                    Toast.makeText(context, registerBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
