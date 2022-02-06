package com.example.wanandroid.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.LoginRegisterBean;
import com.example.wanandroid.utils.NetUtils;
import com.example.wanandroid.utils.RequestParameter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/18 13:34
 */
public class RegisterActivity extends AppCompatActivity {

    TextInputEditText ieTextRegisterUsername;
    TextInputEditText ieTextRegisterPwd;
    TextInputEditText ieTextRegisterRePwd;
    Button btnRegister;
    CheckBox isAgreement;

    private static String username;
    private static String password;

    private static final String URL_REGISTER = "https://www.wanandroid.com/user/register";
    private static final int MSG_REGISTER = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initControls();
        btnRegister.setOnClickListener((View v) -> {
            if (isAgreement.isChecked()) {
                String zUsername = ieTextRegisterUsername.getText().toString().trim();
                String pwd = ieTextRegisterPwd.getText().toString().trim();
                String rePwd = ieTextRegisterRePwd.getText().toString().trim();
                if ("".equals(zUsername) || "".equals(pwd) || "".equals(rePwd)) {
                    Toast.makeText(RegisterActivity.this, "账号或密码未输入", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayList<RequestParameter> regParameter = new ArrayList<>();
                    username = String.valueOf(ieTextRegisterUsername.getText());
                    password = String.valueOf(ieTextRegisterRePwd.getText());
                    regParameter.add(new RequestParameter("username", username));
                    regParameter.add(new RequestParameter("password", password));
                    regParameter.add(new RequestParameter("repassword", String.valueOf(ieTextRegisterRePwd.getText())));
                    requestRegister(regParameter);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "请同意用户协议哦！", Toast.LENGTH_SHORT).show();
            }
        });
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
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    // 持久化存储
                    SharedPreferences preferences = getSharedPreferences("register", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();

                    Log.e("TAG", "存储的值 username=" + preferences.getString("username", "") + "password=" + preferences.getString("password", ""));
                } else {
                    Toast.makeText(RegisterActivity.this, registerBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };


    private void requestRegister(ArrayList<RequestParameter> parameters) {
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
                msg.what = MSG_REGISTER;
                msg.obj = response.body().string();
                Log.e("TAG", "Register请求得到的数据" + msg.obj);
                mHandler.sendMessage(msg);
            }
        });
    }

    private void initControls() {
        ieTextRegisterUsername = findViewById(R.id.ieText_register_account);
        ieTextRegisterPwd = findViewById(R.id.ieText_register_password);
        ieTextRegisterRePwd = findViewById(R.id.ieText_register_rePassword);
        btnRegister = findViewById(R.id.btn_register);
        isAgreement = findViewById(R.id.cb_register_agreement);
    }
}