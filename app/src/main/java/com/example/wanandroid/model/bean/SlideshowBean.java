package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/19 08:35
 */
public class SlideshowBean {

    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data {

        @SerializedName("desc")
        private String desc;

        @SerializedName("id")
        private int id;

        @SerializedName("imagePath")
        private String imagePath;

        @SerializedName("isVisible")
        private int isVisible;

        @SerializedName("order")
        private int order;

        @SerializedName("title")
        private String title;

        @SerializedName("type")
        private int type;

        @SerializedName("url")
        private String url;

        public String getDesc() {
            return desc;
        }

        public int getId() {
            return id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public int getIsVisible() {
            return isVisible;
        }

        public int getOrder() {
            return order;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @SerializedName("errorCode")
    private int errorCode;


    @SerializedName("errorMsg")
    private String errorMsg;

    public ArrayList<Data> getData() {
        return data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "SlideshowBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
