package com.example.wanandroid.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
/**
 * @author : RisingSun
 * @description ： TODO: 我的
 * @email : 2803724412@qq.com
 * @date : 2022/1/19 10:59
 */
public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my, container, false);
        return v;
    }
}