package com.example.wanandroid.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.wanandroid.Presenter.fond.SystemContentAdapter;
import com.example.wanandroid.R;
import com.example.wanandroid.model.bean.ArticleListBean;
import com.example.wanandroid.model.bean.SystemBean;
import com.example.wanandroid.view.fragment.fond.BaseSystemContentFragment;
import com.example.wanandroid.view.fragment.fond.CenterFragment;
import com.example.wanandroid.view.fragment.fond.LeftFragment;
import com.example.wanandroid.view.fragment.fond.RightFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO: 体系中item点击后的内容
 * @email : 2803724412@qq.com
 * @date : 2022/2/14 13:34
 */
public class SystemContentActivity extends AppCompatActivity {

    private static TabLayout tabSystem;
    private static ViewPager2 vpSystem;
    private static ArrayList<SystemBean.Data.Children> contentList = new ArrayList<>();
    private ArrayList<String> titleList = new ArrayList<>();
    private static final ArrayList<BaseSystemContentFragment> systemContentFragmentList = new ArrayList<>();
    private static final String TAG = "SystemContentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_content);

        tabSystem = findViewById(R.id.tab_system_content);
        vpSystem = findViewById(R.id.vp_system_content);

        // 取title
        for (SystemBean.Data.Children children : contentList) {
            titleList.add(children.getName());
        }
        initFragments();

        // 添加title到tab里
        for (int i = 0; i < titleList.size(); i++) {
            tabSystem.addTab(tabSystem.newTab().setText(titleList.get(i)));
        }

//        // 关联tab与vp2
//        new TabLayoutMediator(tabSystem, vpSystem, (@NonNull TabLayout.Tab tab, int position) -> {
//            tab.setText(titleList.get(position));
//        }).attach();

        // 设置Adapter
        SystemContentAdapter systemContentAdapter = new SystemContentAdapter(this, systemContentFragmentList);
        vpSystem.setAdapter(systemContentAdapter);
    }

    private void initFragments() {
        systemContentFragmentList.add(new CenterFragment());
        systemContentFragmentList.add(new LeftFragment());
        systemContentFragmentList.add(new RightFragment());
    }

    public void setContentList(ArrayList<SystemBean.Data.Children> contentList) {
        SystemContentActivity.contentList = contentList;
    }
}