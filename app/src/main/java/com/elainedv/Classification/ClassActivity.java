package com.elainedv.Classification;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.elainedv.Gank.R;
import com.elainedv.Utils.HttpUtil;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout class_tab;
    private ViewPager class_pager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    public static final String TAG=ClassActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        Log.i("---"+TAG+"----","onCreate");
        initView();
    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        class_tab = (TabLayout) findViewById(R.id.class_tab);
        class_pager = (ViewPager) findViewById(R.id.class_pager);
        setSupportActionBar(toolbar);
        titles=new ArrayList<>();
        fragments = new ArrayList<>();
        fragments.add(new Fragment_andr());
        titles.add("ANDROID");
        titles.add("前端");
        titles.add("IOS");
        fragments.add(new Fragment_fe());
        fragments.add(new Fragment_ios());
        AdapterFragment adapter=new AdapterFragment(getSupportFragmentManager(),fragments,titles);
        class_pager.setAdapter(adapter);
        class_tab.setupWithViewPager(class_pager);
        class_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                class_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

}
