package com.example.wanandroid.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.wanandroid.R;

/**
 * @author : RisingSun
 * @description ： TODO: 首页文章列表和问答列表点击后的显示
 * @email : 2803724412@qq.com
 * @date : 2022/2/9 13:58
 */

public class ContentActivity extends AppCompatActivity {

    private WebView webView;
    private TextView tvTitle;
    private static String URl;
    private static String textTvTitle;
    private static final String TAG = "ContentActivity";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        webView = findViewById(R.id.web_article);
        tvTitle = findViewById(R.id.tv_content_title);
        tvTitle.setSelected(true);
        tvTitle.setText(textTvTitle);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URl);
    }

    public void setURl(String URl) {
        ContentActivity.URl = URl;
    }

    public void setTextTvTitle(String textTvTitle) {
        ContentActivity.textTvTitle = textTvTitle;
    }
}