package com.example.wanandroid.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Presenter.fond.FondSystemAdapter;
import com.example.wanandroid.Presenter.fond.FondSystemPresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.SystemBean;

import java.util.ArrayList;

public class FondSystemFragment extends Fragment {

    RecyclerView rvFondSystem;
    public static ArrayList<SystemBean.Data> dataList;
    ArrayList<SystemBean.Data.Children> childrenList;
    FondSystemAdapter systemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fond_system, container, false);
        rvFondSystem = v.findViewById(R.id.rv_fond_system);
        new FondSystemPresenter(getContext(), rvFondSystem);
        return v;
    }
}