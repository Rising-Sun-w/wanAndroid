package com.example.wanandroid.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import com.example.wanandroid.Presenter.register.RegisterPresenter;
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
 * @description ： TODO: 注册
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
                    new RegisterPresenter(RegisterActivity.this, regParameter);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "请同意用户协议哦！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initControls() {
        ieTextRegisterUsername = findViewById(R.id.ieText_register_account);
        ieTextRegisterPwd = findViewById(R.id.ieText_register_password);
        ieTextRegisterRePwd = findViewById(R.id.ieText_register_rePassword);
        btnRegister = findViewById(R.id.btn_register);
        isAgreement = findViewById(R.id.cb_register_agreement);
    }
}