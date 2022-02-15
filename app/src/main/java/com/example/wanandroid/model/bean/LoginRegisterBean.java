package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author : RisingSun
 * @description ： TODO: 登录接口对应的bean
 * @email : 2803724412@qq.com
 * @date : 2022/1/18 13:34
 */
public class LoginRegisterBean {

    @SerializedName("data")
    private Data data;

    private static class Data {

        @SerializedName("admin")
        private Boolean admin;

        @SerializedName("chapterTops")
        private String[] chapterTops;

        @SerializedName("coinCount")
        private int coinCount;

        @SerializedName("collectIds")
        private String[] collectIds;

        @SerializedName("email")
        private String email;

        @SerializedName("icon")
        private String icon;

        @SerializedName("id")
        private int id;

        @SerializedName("nickname")
        private String nickname;

        @SerializedName("password")
        private String password;

        @SerializedName("publicName")
        private String publicName;

        @SerializedName("token")
        private String token;

        @SerializedName("type")
        private int type;

        @SerializedName("username")
        private String username;
    }

    @SerializedName("errorCode")
    private int errorCode;

    @SerializedName("errorMsg")
    private String errorMsg;

    @Override
    public String toString() {
        return "LoginRegisterBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public Data getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
