package com.monitor.accuse;

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
 * Created by ken on 2018/8/3.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EActivity(R.layout.activity_offer)
public class AccuseMainFragment extends BaseActvity {
    @ViewById(R.id.ctl)
    CommonTabLayout ctl;
    @ViewById(R.id.vp)
    ViewPager vp;
    @StringArrayRes
    String[] accuse_title;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public View mDecorView;

    @AfterViews
    void init() {
        initImageBackText("质控");
        for (int i = 0; i < accuse_title.length; i++) {
            mTabEntities.add(new TabEntity(accuse_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new AccuseActivity_());
        mFragments.add(new AwaitAccuseFragment_());
        mFragments.add(new YAwaitAccuseFragment_());
        mFragments.add(new QualitycontrolApprovalFragment_());
        mDecorView = this.getWindow().getDecorView();
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, accuse_title));
        ctl.setTabData(mTabEntities);
        initViewpage(ctl, vp);
        vp.setOffscreenPageLimit(3);
    }
}
