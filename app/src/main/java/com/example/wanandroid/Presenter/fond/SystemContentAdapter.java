package com.example.wanandroid.Presenter.fond;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wanandroid.view.fragment.fond.BaseSystemContentFragment;

import java.util.List;

/**
 * @author : RisingSun
 * @description ： TODO: 体系中item点击事件所对应的适配器
 * @email : 2803724412@qq.com
 * @date : 2022/2/13 21:54
 */
public class SystemContentAdapter extends FragmentStateAdapter {

    private final List<BaseSystemContentFragment> fragmentList;

    public SystemContentAdapter(@NonNull Activity activity, List<BaseSystemContentFragment> fragmentList) {
        super((FragmentActivity) activity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
