package com.monitor.changtian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ken on 2018/7/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mFragments = new ArrayList<>();
    String[] title = new String[]{};

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] title) {
        super(fm);
        this.mFragments = mFragments;
        this.title = title;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
