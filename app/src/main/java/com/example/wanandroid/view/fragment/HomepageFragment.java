package com.example.wanandroid.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.Presenter.adapter.homepage.HomepagePresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.Presenter.adapter.homepage.ArticleAdapter;
import com.example.wanandroid.Presenter.adapter.homepage.BannerAdapter;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.bean.SlideshowBean;
import com.example.wanandroid.utils.NetUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/19 10:59
 */
public class HomepageFragment extends Fragment {

    private static final String TAG = ".HomepageFragment";
    RecyclerView rv;
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_homepage, container, false);
        viewPager = v.findViewById(R.id.vp_hpg_banner);
        rv = v.findViewById(R.id.rv_hpg_article);
        new HomepagePresenter(getActivity(), viewPager, rv);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}