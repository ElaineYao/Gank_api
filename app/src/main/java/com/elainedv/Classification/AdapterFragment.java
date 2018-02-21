package com.elainedv.Classification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Elaine on 18/2/16.
 */

public class AdapterFragment extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private FragmentManager fragmentManager;
    private ArrayList<String> titles;


    public AdapterFragment(FragmentManager fm,ArrayList<Fragment> fragments,ArrayList<String> titles){
        super(fm);
        this.fragments=fragments;
        this.titles=titles;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
