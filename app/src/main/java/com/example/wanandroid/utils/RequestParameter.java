package com.example.wanandroid.utils;

/**
 * @author : RisingSun
 * @description ： TODO: 使用POST请求方式时辅助键值对添加工具
 * @email : 2803724412@qq.com
 * @date : 2022/1/16 18:03
 */
public class RequestParameter {
    private String name;
    private String values;

    public RequestParameter(String name, String values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
