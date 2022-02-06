package com.example.wanandroid.view.fragment;

import android.content.Context;

import com.example.wanandroid.model.bean.SystemBean;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/2/5 14:35
 */
public interface IFondSystemFragment {
    /**
     * 获取数据
     * @param dataList 标题列表
     */
    void getData(Context context, ArrayList<SystemBean.Data> dataList);
}
