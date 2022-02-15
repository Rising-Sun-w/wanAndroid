package com.example.wanandroid.utils;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author : RisingSun
 * @description ： TODO: 网络请求
 * @email : 2803724412@qq.com
 * @date : 2022/1/15 20:32
 */
public class NetUtils {

    /**
     * GET请求
     */
    public void getRequest(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST请求
     */
    public void postRequest(String url, ArrayList<RequestParameter> parameters, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        for (final RequestParameter p : parameters) {
            builder.add(p.getName(), p.getValues());
        }
        RequestBody formBody = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
