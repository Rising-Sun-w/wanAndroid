package com.example.wanandroid.model.login;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.utils.RequestParameter;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/24 21:12
 */
public interface ILoginModel {

    /**
     * 实现网络请求获取数据
     *
     * @param parameters 键值对
     */
    void getData(ArrayList<RequestParameter> parameters, IMessagePresenter loginPresenter);
}
