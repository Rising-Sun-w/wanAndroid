package com.example.wanandroid.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/19 12:50
 */
public class GsonParse {

    static Gson gson = new Gson();

    /**
     * 解析纯json对象或纯json数组
     */
    public GsonParse(String jsonStr, Context context){
//        if (jsonStr)
        gson.fromJson(jsonStr, context.getClass());
    }

    /**
     * Gson gson = new Gson();
     * String jsonStr = (String) msg.obj;
     * LoginRegisterBean loginBean = gson.fromJson(jsonStr, LoginRegisterBean.class);
     * @param jsonStr
     * @param beanClass
     */

    public static void strParse(String jsonStr, Class beanClass){
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
        beanClass = gson.fromJson(jsonStr, beanClass.getClass());
    }



}
