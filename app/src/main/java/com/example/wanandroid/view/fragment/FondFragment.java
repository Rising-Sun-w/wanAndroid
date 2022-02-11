package com.example.wanandroid.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.Presenter.fond.FondAdapter;
import com.example.wanandroid.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class FondFragment extends Fragment {

    private ViewPager2 vpFond;
    private TabLayout tabFond;

    private static final String TAG = "FondFragment";

    ArrayList<String> tabTitleList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fond, container, false);
        initView(v);
        initAdds();

        // 将title添加到tab里
        for (int i = 0; i < tabTitleList.size(); i++) {
            tabFond.addTab(tabFond.newTab().setText(tabTitleList.get(i)));
        }

        // Tab滑动事件
        FondAdapter fondAdapter = new FondAdapter(this, fragmentList);
        vpFond.setAdapter(fondAdapter);

        // 将Tab和vp绑定起来
        new TabLayoutMediator(tabFond, vpFond, (@NonNull TabLayout.Tab tab, int position) -> {
            tab.setText(tabTitleList.get(position));
        }).attach();
        return v;
    }

    private void initView(View v) {
        tabFond = v.findViewById(R.id.tab_fond);
        vpFond = v.findViewById(R.id.vp_fond);
    }

    private void initAdds() {
        tabTitleList.add("体系");
        tabTitleList.add("导航");

        fragmentList.add(new FondSystemFragment());
        fragmentList.add(new FondNavigationFragment());
    }
}