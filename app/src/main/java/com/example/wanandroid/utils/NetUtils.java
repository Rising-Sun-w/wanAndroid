package com.example.wanandroid.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/15 20:32
 */
public class NetUtils {

    /**
     * GET 异步请求
     * 第一步：获得client对象
     * 第二步：构造request对象
     * 第三步：将Request封装为Call
     * 第四步：根据需要调用同步或者异步请求方法
     */
    public void getRequest1(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * POST请求
     * 第一步：获得client对象
     * 第二步：构建FormBody，传参（键值对）
     * 第三步：构建Request,将FormBody作为Post方法的参数传入
     * 第四步：将Request封装为Call
     * 第五步：调用请求，重写回调方法
     * <p>
     * 使用时需要传一个RequestParameter类型的数组进来
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

    public void postStrRequest(String url, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        RequestBody requestBody = RequestBody.create(MediaType
                .parse("text/plain;charset=utf-8"), "{username:admin;password:admin}");
        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 使用Glide请求图片
     *
     * @param context
     * @param url
     */
    public void requestImage(Context context, String url) {
        Glide.with(context)
                .load(url)
                //网络请求加载时占用imageView，使其不为空
                .placeholder(R.drawable.ic_cache)
                //当请求错误时显示的图片
                .error(R.drawable.ic_cache);
        //禁用掉Glide的缓存功能
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
        //固定图片大小
//                .override(100,100)
//                .into(img);
    }

    /**
     * 图片上传
     */
    public void photoRequest(Context mContext) {
        File file = new File(Environment.getExternalStorageDirectory(), "1.png");
        if (!file.exists()) {
            Toast.makeText(mContext, "图片不存在", Toast.LENGTH_SHORT).show();
        } else {
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        }
    }
}
