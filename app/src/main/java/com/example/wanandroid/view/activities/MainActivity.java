package com.example.wanandroid.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.wanandroid.R;
import com.example.wanandroid.view.fragment.CollectFragment;
import com.example.wanandroid.view.fragment.FondFragment;
import com.example.wanandroid.view.fragment.HomepageFragment;
import com.example.wanandroid.view.fragment.MyFragment;
import com.example.wanandroid.view.fragment.QaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * @author : RisingSun
 * @description ： TODO:
 * @email : 2803724412@qq.com
 * @date : 2022/1/18 13:34
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = ".MainActivity";

    private final ArrayList<Fragment> fragmentMainList = new ArrayList<>();
    private BottomNavigationView btnNag;
    private ViewPager2 vpMain;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNag = findViewById(R.id.btnNag_main);
        vpMain = findViewById(R.id.vp_main);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction().replace(R.id.fly_main, new HomepageFragment()).commit();
        }
        initFragment();

        // ViewPage2的设置
//        vpMain.setAdapter(new MainAdapter(MainActivity.this, fragmentMainList));
//        myPageChangeCallback = new MyPageChangeCallback(btnNag, getSupportFragmentManager().beginTransaction(), fragmentMainList);
//        vpMain.registerOnPageChangeCallback(myPageChangeCallback);

        // fragment与底部导航栏的适配
        btnNag.setOnNavigationItemSelectedListener((@NonNull MenuItem item) -> {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.homepage:
                    fragment = new HomepageFragment();
                    break;
                case R.id.qa:
                    fragment = new QaFragment();
                    break;
                case R.id.collect:
                    fragment = new CollectFragment();
                    break;
                case R.id.fond:
                    fragment = new FondFragment();
                    break;
                case R.id.my:
                    fragment = new MyFragment();
                    break;
                default:
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fly_main, fragment).commit();
            }
            return true;
        });


    }

    /**
     * 底部导航栏与ViewPage2滑动时的适配
     */
//    static class MyPageChangeCallback extends ViewPager2.OnPageChangeCallback {
//        BottomNavigationView bottomNavigationView;
//        FragmentTransaction transaction;
//        ArrayList<Fragment> fragmentArrayList;
//
//        public MyPageChangeCallback(BottomNavigationView bottomNavigationView, FragmentTransaction transaction, ArrayList<Fragment> fragmentArrayList) {
//            this.bottomNavigationView = bottomNavigationView;
//            this.transaction = transaction;
//            this.fragmentArrayList = fragmentArrayList;
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            super.onPageSelected(position);
//            transaction.hide(showFragmentName());
//            bottomNavigationView.getMenu().getItem(position).setChecked(true);
//            Log.e(TAG, "======MyPageChangeCallback,onPageSelected=======");
//        }
//        @SuppressLint("RestrictedApi")
//        private Fragment showFragmentName() {
//            int i;
//            for(i = 0; i < fragmentArrayList.size(); i++) {
//                Fragment fragment = fragmentArrayList.get(i);
//                if(fragment!=null && fragment.isAdded()&&fragment.isMenuVisible()) {
//                    break;
//                }
//            }
//            return fragmentArrayList.get(i);
//        }
//    }



//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        vpMain.unregisterOnPageChangeCallback(myPageChangeCallback);
//        Log.e(TAG, "======onDestroy=======");
//    }


    private void initFragment() {
        fragmentMainList.add(new HomepageFragment());
        fragmentMainList.add(new QaFragment());
        fragmentMainList.add(new CollectFragment());
        fragmentMainList.add(new FondFragment());
        fragmentMainList.add(new MyFragment());
    }
}