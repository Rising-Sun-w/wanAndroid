package com.example.wanandroid.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Presenter.homepage.HomepagePresenter;
import com.example.wanandroid.R;

/**
 * @author : RisingSun
 * @description ： TODO: 首页
 * @email : 2803724412@qq.com
 * @date : 2022/1/19 10:59
 */
public class HomepageFragment extends Fragment {

    private static final String TAG = ".HomepageFragment";
    RecyclerView rv;
    ViewPager viewPager;
    // rv是否加载状态
    private Boolean loading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        viewPager = v.findViewById(R.id.vp_hpg_banner);
        rv = v.findViewById(R.id.rv_hpg_article);
        new HomepagePresenter(getActivity(), viewPager, rv, true);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}