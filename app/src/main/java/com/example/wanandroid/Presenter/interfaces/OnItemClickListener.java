package com.example.wanandroid.Presenter.interfaces;

/**
 * @author : RisingSun
 * @description ： TODO: item点击事件的接口
 * @email : 2803724412@qq.com
 * @date : 2022/2/7 00:40
 */
public interface OnItemClickListener {
    /**
     * item的点击事件
     * @param position 指定item的下标
     */
    void onItemShortClick(int position);

    void onItemLongClick(int position);
}
