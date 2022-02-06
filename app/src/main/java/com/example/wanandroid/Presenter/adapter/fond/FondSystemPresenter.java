package com.example.wanandroid.Presenter.adapter.fond;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.session.IMediaControllerCallback;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.IMessagePresenter;
import com.example.wanandroid.model.bean.LoginRegisterBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.example.wanandroid.model.fond.NavigationModel;
import com.example.wanandroid.model.fond.SystemModel;
import com.example.wanandroid.view.fragment.FondSystemFragment;
import com.example.wanandroid.view.fragment.IFondSystemFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/28 19:49
 */
public class FondSystemPresenter implements IMessagePresenter {

    private static final String TAG = ".SystemPresenter";
    private static final int MSG_SYSTEM = 21;
    private Context context;

    static ArrayList<SystemBean.Data> dataList;
    static ArrayList<SystemBean.Data.Children> childrenList;
    RecyclerView rv;

    public FondSystemPresenter(Context context, RecyclerView rv) {
        this.context = context;
        this.rv = rv;
        SystemModel.getData(this);
        NavigationModel.getData(this);
    }

    @Override
    public void loadLoginCondition(Message msg) {
        mHandler.sendMessage(msg);
    }


    Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_SYSTEM) {
                String jsonStr = (String) msg.obj;
                Gson gson = new Gson();
                SystemBean systemBean = gson.fromJson(jsonStr, SystemBean.class);
                if (systemBean.getErrorCode() == 0) {
                    // 数据返回成功
                    dataList = systemBean.data;
                    Log.e(TAG, "=====Presenter.dataList=====" + dataList);
                    FondSystemAdapter systemAdapter = new FondSystemAdapter(context, dataList, childrenList);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(layoutManager);
                    rv.setAdapter(systemAdapter);
                } else {
                    Toast.makeText(context, systemBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
