package com.example.wanandroid.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SystemContentActivity extends AppCompatActivity {

    private TabLayout tabSystem;
    private ViewPager2 vpSystem;
    private ArrayList<SystemBean.Data.Children> contentList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();
    private static final String TAG = "SystemContentActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_content);

        tabSystem = findViewById(R.id.tab_system_content);
        vpSystem = findViewById(R.id.vp_system_content);

        // 添加title到tab里
        for (int i = 0; i < titleList.size(); i++) {
            tabSystem.addTab(tabSystem.newTab().setText(titleList.get(i)));
        }
        Log.e(TAG, "=======titleList=====" + titleList);

    }

    public void setContentList(ArrayList<SystemBean.Data.Children> contentList) {
        this.contentList = contentList;
        initContentList();
    }

    private void initContentList() {
        for (SystemBean.Data.Children children : contentList) {
            titleList.add(children.getName());
        }
    }
}