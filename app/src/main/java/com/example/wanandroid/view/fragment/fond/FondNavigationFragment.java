package com.example.wanandroid.view.fragment.fond;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Presenter.fond.FondTabsPresenter;
import com.example.wanandroid.R;

/**
 * @author : RisingSun
 * @description ： TODO: 导航
 * @email : 2803724412@qq.com
 * @date : 2022/2/14 13:14
 */
public class FondNavigationFragment extends Fragment {

    RecyclerView rvFondN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fond_system, container, false);
        rvFondN = v.findViewById(R.id.rv_fond_system);
        new FondTabsPresenter(getContext(), rvFondN, 31);
        return v;
    }
}