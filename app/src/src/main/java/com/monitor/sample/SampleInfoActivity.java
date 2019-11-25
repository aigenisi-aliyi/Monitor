package com.monitor.sample;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MyPagerAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.TabEntity;
import com.monitor.changtian.widght.ViewFindUtils;
import com.monitor.sample.fragment.InspectionD_Fragment_;
import com.monitor.sample.fragment.InspectionL_Fragment_;
import com.monitor.sample.fragment.InspectionY_Fragment_;
import com.monitor.sample.fragment.InspectionZ_Fragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

@EActivity(R.layout.activity_sample_info)
public class SampleInfoActivity extends BaseActvity {


    @ViewById(R.id.ctl)
    CommonTabLayout ctl;
    @ViewById(R.id.vp)
    ViewPager vp;
    @StringArrayRes
    String[] sample_title;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();


    public View mDecorView;
    @AfterViews
    void init() {
        initCenterText("任务");
        for (int i = 0; i < sample_title.length; i++) {
            mTabEntities.add(new TabEntity(sample_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new InspectionD_Fragment_());
        mFragments.add(new InspectionZ_Fragment_());
        mFragments.add(new InspectionY_Fragment_());
        mFragments.add(new InspectionL_Fragment_());
        mDecorView = getWindow().getDecorView();

        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),mFragments,sample_title));
        ctl.setTabData(mTabEntities);
        initViewpage(ctl,mViewPager);
        mViewPager.setOffscreenPageLimit(4);

    }

}
