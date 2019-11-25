package com.monitor.changtian.adapter;

import android.support.v4.app.FragmentManager;

import com.ada.adapter.PagerAdapter;

import java.util.ArrayList;

/**
 * name : lishuai
 * time : 2018/1/10
 * title:
 */

public class BasePagerAdapter extends PagerAdapter {
    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setTitles(ArrayList<String> titles){
        super.titles = titles;
        this.titles = titles;
    }
}
