package com.example.wanandroid.Presenter.interfaces;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 轮播图的接口
 * @email : 2803724412@qq.com
 * @date : 2022/1/21 20:12
 */
public interface OnLoadImage {
    /**
     * 用于首页中的图片
     * @param context
     * @param urls
     * @param position
     * @param imageView
     */
    void onLoadImage(Context context, ArrayList<String> urls, int position, View imageView);
}
