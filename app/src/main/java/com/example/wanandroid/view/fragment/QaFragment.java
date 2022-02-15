package com.example.wanandroid.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Presenter.Qa.QaPresenter;
import com.example.wanandroid.R;
import com.example.wanandroid.Presenter.Qa.QaAdapter;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.utils.NetUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author RisingSun
 * @description ： TODO: 问答
 * @email : 2803724412@qq.com
 * @Data 2022/1/22 10:46
 */
public class QaFragment extends Fragment {

    private static RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qa, container, false);
        rv = v.findViewById(R.id.rv_qa);
        new QaPresenter(getContext(), rv, true);
        return v;
    }
}