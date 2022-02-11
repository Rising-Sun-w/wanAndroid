package com.example.wanandroid.Presenter.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/27 20:39
 */
public class MainAdapter extends FragmentStateAdapter {

    private ArrayList<Fragment> fragmentMainList;

    public MainAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentMainList) {
        super(fragmentActivity);
        this.fragmentMainList = fragmentMainList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentMainList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentMainList.size();
    }
}
