package com.example.wanandroid.Presenter;

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
     * 检查登录界面输入的账号和密码是否正确
     * @param msg Model层向Presenter层传回msg
     */
    void loadLoginCondition(Message msg);
}
