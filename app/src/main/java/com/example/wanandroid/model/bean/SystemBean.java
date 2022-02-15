package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : RisingSun
 * @description ： TODO: 体系接口对应bean
 * @email : 2803724412@qq.com
 * @date : 2022/1/28 19:02
 */
public class SystemBean {

    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data{

        @SerializedName("children")
        public ArrayList<Children> children;

        public static class Children{
            @SerializedName("children")
            private String[] children;

            @SerializedName("courseId")
            private int courseId;

            @SerializedName("id")
            private int id;

            @SerializedName("name")
            private String name;

            @SerializedName("order")
            private int order;

            @SerializedName("parentChapterId")
            private int parentChapterId;

            @SerializedName("userControlSetTop")
            private Boolean userControlSetTop;

            @SerializedName("visible")
            private int visible;

            public String[] getChildren() {
                return children;
            }

            public int getCourseId() {
                return courseId;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getOrder() {
                return order;
            }

            public int getParentChapterId() {
                return parentChapterId;
            }

            public Boolean getUserControlSetTop() {
                return userControlSetTop;
            }

            public int getVisible() {
                return visible;
            }

            @Override
            public String toString() {
                return "Children{" +
                        "children=" + Arrays.toString(children) +
                        ", courseId=" + courseId +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", order=" + order +
                        ", parentChapterId=" + parentChapterId +
                        ", userControlSetTop=" + userControlSetTop +
                        ", visible=" + visible +
                        '}';
            }
        }

        @SerializedName("courseId")
        private int courseId;

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("order")
        private int order;

        @SerializedName("parentChapterId")
        private int parentChapterId;

        @SerializedName("userControlSetTop")
        private Boolean userControlSetTop;

        @SerializedName("visible")
        private int visible;

        public ArrayList<Children> getChildren() {
            return children;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getOrder() {
            return order;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public Boolean getUserControlSetTop() {
            return userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "children=" + children +
                    ", courseId=" + courseId +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", parentChapterId=" + parentChapterId +
                    ", userControlSetTop=" + userControlSetTop +
                    ", visible=" + visible +
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
        return "SystemBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
