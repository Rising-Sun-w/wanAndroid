package com.example.wanandroid.Presenter.homepage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.wanandroid.Presenter.interfaces.OnLoadImage;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 轮播图适配器
 * @email : 2803724412@qq.com
 * @date : 2022/1/20 15:30
 */
public class BannerAdapter extends PagerAdapter {

    private ArrayList<String > urlList;
    private OnLoadImage mOnLoadImageListener;

    /**
     *
     * @param urlList  所有图片的URL
     * @param onLoadImager 接口，调用层发挥
     */
    public BannerAdapter(ArrayList<String> urlList, OnLoadImage onLoadImager) {
        this.urlList = urlList;
        this.mOnLoadImageListener = onLoadImager;
    }

    /**
     * 设置为整型最大值可以“伪造”无限循环滑动
     *
     * @return ViewPage可以滑动的次数
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imgV = new ImageView(container.getContext());
        // 等比例缩放图片，占满容器
        imgV.setScaleType(ImageView.ScaleType.FIT_XY);
        // 当数值设在图片List范围内，
        position = position % urlList.size();
        if (mOnLoadImageListener != null) {
            // 设置回调，传入数据，让调用层（Activity）去处理加载图片的逻辑
            mOnLoadImageListener.onLoadImage(container.getContext(), urlList, position, imgV);
        }
        // 把每一个item添加到ViewPager容器中
        container.addView(imgV);
        return imgV;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}

