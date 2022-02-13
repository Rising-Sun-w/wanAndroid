package com.example.wanandroid.Presenter.fond;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.Presenter.interfaces.IMessagePresenter;
import com.example.wanandroid.model.bean.NavigationBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.example.wanandroid.model.fond.NavigationModel;
import com.example.wanandroid.model.fond.SystemModel;
import com.example.wanandroid.view.fragment.FondSystemFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/28 19:49
 */
public class FondTabsPresenter implements IMessagePresenter {

    private static final String TAG = ".SystemPresenter";
    private static final int MSG_SYSTEM = 21;
    private static final int MSG_NAVIGATION = 31;
    @SuppressLint("StaticFieldLeak")
    private final Context context;

    Gson gson = new Gson();

    static ArrayList<SystemBean.Data> dataList;
    RecyclerView rv;

    public FondTabsPresenter(Context context, RecyclerView rv, int type) {
        this.context = context;
        this.rv = rv;
        if (type == MSG_SYSTEM) {
            SystemModel.getSystemData(this);
        } else if (type == MSG_NAVIGATION) {
            NavigationModel.getNavigationData(this);
        }
    }

    @Override
    public void loadSuccess(Message msg) {
        if (msg.what == MSG_SYSTEM) {
            Log.e(TAG, "====SYSTEM.msg.what===" + msg.what);
            mSystemHandler.sendMessage(msg);
        } else if (msg.what == MSG_NAVIGATION) {
            mNavigationHandler.sendMessage(msg);
            Log.e(TAG, "====NAVIGATION.msg.what===" + msg.what);
        }
    }

    @Override
    public void loadFail() {
        Toast.makeText(context, "网络似乎不给力", Toast.LENGTH_SHORT).show();
    }

    Handler mSystemHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            SystemBean systemBean = gson.fromJson(jsonStr, SystemBean.class);
            if (systemBean.getErrorCode() == 0) {
                // 数据返回成功
                dataList = systemBean.data;
                FondSystemAdapter systemAdapter = new FondSystemAdapter(context, dataList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(systemAdapter);
            } else {
                Toast.makeText(context, systemBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
            }

        }
    };

    Handler mNavigationHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String jsonStr = (String) msg.obj;
            NavigationBean navigationBean = gson.fromJson(jsonStr, NavigationBean.class);
            if (navigationBean.getErrorCode() == 0) {
                ArrayList<NavigationBean.Data> navigationData = new ArrayList<>(navigationBean.data);
                FondNavigationAdapter navigationAdapter = new FondNavigationAdapter(context, navigationData);
                LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(navigationAdapter);
            } else {
                Toast.makeText(context, navigationBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
