package com.example.wanandroid.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.Presenter.login.LoginPresenter;
import com.example.wanandroid.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @author : RisingSun
 * @description ： TODO: 登录
 * @email : 2803724412@qq.com
 * @date : 2022/1/18 13:34
 */
public class LoginActivity extends AppCompatActivity {

    TextView tvExperience;
    TextInputEditText ieTextLoginUsername;
    TextInputEditText ieTextLoginPwd;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        tvRegister.setOnClickListener((View v) -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        tvExperience.setOnClickListener((View v) -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            onDestroy();
        });

        btnLogin.setOnClickListener((View v) -> {
            String username = ieTextLoginUsername.getText().toString().trim();
            String pwd = ieTextLoginPwd.getText().toString().trim();
            if ("".equals(username) || "".equals(pwd)) {
                Toast.makeText(LoginActivity.this, "账号或密码未输入", Toast.LENGTH_SHORT).show();
            } else {
                new LoginPresenter(LoginActivity.this, username, pwd);
            }
        });
    }

    public void showLoading() {
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tvExperience = findViewById(R.id.tv_login_experienceImmediately);
        ieTextLoginUsername = findViewById(R.id.ieText_login_account);
        ieTextLoginPwd = findViewById(R.id.ieText_login_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
    }
}