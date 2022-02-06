package com.example.wanandroid.Presenter.interfaces;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/21 20:12
 */
public interface OnLoadImage {
    /**
     * 用于首页中的图片
     * @param context Context
     * @param urls  包含图片url的集合
     * @param position ArrayList指针
     * @param imageView 指定图片显示的位置
     */
    void onLoadImage(Context context, ArrayList<String> urls, int position, View imageView);
}
