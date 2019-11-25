package com.monitor.changtian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class TitleAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFragments;
    String[] mTitles;

    public TitleAdapter(FragmentManager fm, ArrayList<Fragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

//
//@Override
//    public CharSequence getPageTitle(int position) {
//        return mTitles[position];
//    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
