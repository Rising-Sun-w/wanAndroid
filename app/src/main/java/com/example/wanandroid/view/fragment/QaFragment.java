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

import com.example.wanandroid.R;
import com.example.wanandroid.Presenter.adapter.QaAdapter;
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
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @Data 2022/1/22 10:46
 */
public class QaFragment extends Fragment {

    private static String URL_QA = "https://wanandroid.com/wenda/list/";
    private static final int MSG_QA = 111;
    private static final String TAG = ".QaFragment";

    ArrayList<ArticleListBean.Data.DataS> qaList = new ArrayList<>();
    Gson gson = new Gson();

    private int page = 0;
    RecyclerView rv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_qa, container, false);
        rv = v.findViewById(R.id.rv_qa);
        initInfo();
        return v;
    }

    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_QA) {
                String jsonStr = (String) msg.obj;
                ArticleListBean articleListBean = gson.fromJson(jsonStr, ArticleListBean.class);
                qaList.addAll(articleListBean.data.dataS);
                if (page - 1 == 0) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                    rv.setLayoutManager(layoutManager);
                    QaAdapter adapter = new QaAdapter(getContext(), qaList);
                    rv.setAdapter(adapter);
                }
            }
        }
    };

    public void initInfo() {
        if (page <= 40) {
            URL_QA = URL_QA + page + "/json";
            page++;
            new Thread(() -> {
                NetUtils netUtils = new NetUtils();
                netUtils.getRequest1(URL_QA, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message msg = new Message();
                        msg.what = MSG_QA;
                        msg.obj = response.body().string();
                        mHandler.sendMessage(msg);
                    }
                });
            }).start();
        }
    }
}