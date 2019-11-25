package com.monitor.changtian.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MyPagerAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.TabEntity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

/**
 * Created by ken on 2018/8/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

@EActivity(R.layout.activity_sample_info)
public class SampleTaskMainFragment extends BaseActvity {

    @ViewById(R.id.ctl)
    CommonTabLayout ctl;
    @ViewById(R.id.vp)
    ViewPager vp;
    @StringArrayRes
    String[] tasks_title;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public View mDecorView;

    @AfterViews
    void init() {
        initImageBackText("任务");
        for (int i = 0; i < tasks_title.length; i++) {
            mTabEntities.add(new TabEntity(tasks_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new CurtainFragment_());
        mFragments.add(new SampleProofreadFragment_());
        mDecorView = this.getWindow().getDecorView();
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, tasks_title));
        ctl.setTabData(mTabEntities);
        initViewpage(ctl, vp);
        vp.setOffscreenPageLimit(2);
    }
}
