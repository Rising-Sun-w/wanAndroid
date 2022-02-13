package com.example.wanandroid.Presenter.interfaces;

import android.os.Message;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 20:37
 */
public interface IMessagePresenter {

    /**
     * 控制Model层的数据操作及调用View层的UI操作来完成“中间人”工作
     * 网络请求成功并返回信息
     *
     * @param msg Model层向Presenter层传回msg
     */
    void loadSuccess(Message msg);

    /**
     * 网络请求失败
     */
    void loadFail();
}
