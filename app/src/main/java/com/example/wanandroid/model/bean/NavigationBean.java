package com.example.wanandroid.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 导航接口对应bean
 * @email : 2803724412@qq.com
 * @date : 2022/2/11 22:49
 */
public class NavigationBean {
    @SerializedName("data")
    public ArrayList<Data> data;

    public static class Data{
        @SerializedName("articles")
        public ArrayList<Articles> articles;

        public static class Articles{
            @SerializedName("apkLink")
            private String apkLink;

            @SerializedName("audit")
            private int audit;

            @SerializedName("author")
            private String author;

            @SerializedName("canEdit")
            private Boolean canEdit;

            @SerializedName("chapterId")
            private int chapterId;

            @SerializedName("chapterName")
            private String chapterName;

            @SerializedName("collect")
            private Boolean collect;

            @SerializedName("courseId")
            private int courseId;

            @SerializedName("desc")
            private String desc;

            @SerializedName("descMd")
            private String descMd;

            @SerializedName("envelopePic")
            private String envelopePic;

            @SerializedName("fresh")
            private Boolean fresh;

            @SerializedName("host")
            private String host;

            @SerializedName("id")
            private int id;

            @SerializedName("link")
            private String link;

            @SerializedName("niceDate")
            private String niceDate;

            @SerializedName("niceShareDate")
            private String niceShareDate;

            @SerializedName("origin")
            private String origin;

            @SerializedName("prefix")
            private String prefix;

            @SerializedName("projectLink")
            private String projectLink;

            @SerializedName("publishTime")
            private long publishTime;

            @SerializedName("realSuperChapterId")
            private int realSuperChapterId;

            @SerializedName("selfVisible")
            private int selfVisible;

            @SerializedName("shareUser")
            private String shareUser;

            @SerializedName("superChapterId")
            private int superChapterId;

            @SerializedName("superChapterName")
            private String superChapterName;

            @SerializedName("title")
            private String title;

            @SerializedName("type")
            private int type;

            @SerializedName("userId")
            private int userId;

            @SerializedName("visible")
            private int visible;

            @SerializedName("zan")
            private int zan;

            public String getApkLink() {
                return apkLink;
            }

            public int getAudit() {
                return audit;
            }

            public String getAuthor() {
                return author;
            }

            public Boolean getCanEdit() {
                return canEdit;
            }

            public int getChapterId() {
                return chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public Boolean getCollect() {
                return collect;
            }

            public int getCourseId() {
                return courseId;
            }

            public String getDesc() {
                return desc;
            }

            public String getDescMd() {
                return descMd;
            }

            public String getEnvelopePic() {
                return envelopePic;
            }

            public Boolean getFresh() {
                return fresh;
            }

            public String getHost() {
                return host;
            }

            public int getId() {
                return id;
            }

            public String getLink() {
                return link;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public String getNiceShareDate() {
                return niceShareDate;
            }

            public String getOrigin() {
                return origin;
            }

            public String getPrefix() {
                return prefix;
            }

            public String getProjectLink() {
                return projectLink;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public int getRealSuperChapterId() {
                return realSuperChapterId;
            }

            public int getSelfVisible() {
                return selfVisible;
            }

            public String getShareUser() {
                return shareUser;
            }

            public int getSuperChapterId() {
                return superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName;
            }

            public String getTitle() {
                return title;
            }

            public int getType() {
                return type;
            }

            public int getUserId() {
                return userId;
            }

            public int getVisible() {
                return visible;
            }

            public int getZan() {
                return zan;
            }

            @Override
            public String toString() {
                return "Articles{" +
                        "apkLink='" + apkLink + '\'' +
                        ", audit=" + audit +
                        ", author=" + author +
                        ", canEdit=" + canEdit +
                        ", chapterId=" + chapterId +
                        ", chapterName='" + chapterName + '\'' +
                        ", collect=" + collect +
                        ", courseId=" + courseId +
                        ", desc='" + desc + '\'' +
                        ", descMd='" + descMd + '\'' +
                        ", envelopePic='" + envelopePic + '\'' +
                        ", fresh=" + fresh +
                        ", host='" + host + '\'' +
                        ", id=" + id +
                        ", link='" + link + '\'' +
                        ", niceDate='" + niceDate + '\'' +
                        ", niceShareDate='" + niceShareDate + '\'' +
                        ", origin='" + origin + '\'' +
                        ", prefix='" + prefix + '\'' +
                        ", projectLink='" + projectLink + '\'' +
                        ", publishTime=" + publishTime +
                        ", realSuperChapterId=" + realSuperChapterId +
                        ", selfVisible=" + selfVisible +
                        ", shareUser='" + shareUser + '\'' +
                        ", superChapterId=" + superChapterId +
                        ", superChapterName='" + superChapterName + '\'' +
                        ", title='" + title + '\'' +
                        ", type=" + type +
                        ", userId=" + userId +
                        ", visible=" + visible +
                        ", zan=" + zan +
                        '}';
            }
        }

        @SerializedName("cid")
        private int cid;

        @SerializedName("name")
        private String name;

        public ArrayList<Articles> getArticles() {
            return articles;
        }

        public int getCid() {
            return cid;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "articles=" + articles +
                    ", cid=" + cid +
                    ", same='" + name + '\'' +
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
        return "NavigationBean{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
