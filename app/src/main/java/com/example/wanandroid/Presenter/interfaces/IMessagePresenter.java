package com.example.wanandroid.Presenter.interfaces;

import android.os.Message;

/**
 * @author : RisingSun
 * @description ： TODO: 获得网络请求结果
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 20:37
 */
public interface IMessagePresenter {

    /**
     * 网络请求成功并返回信息
     * @param msg Model层向Presenter层传回msg
     */
    void loadSuccess(Message msg);

    /**
     * 网络请求失败
     */
    void loadFail();
}
