package com.monitor.detectionschemeaudit;

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
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

@EActivity(R.layout.activity_offer)
public class DetectionSchemeAuditMainFragment extends BaseActvity {
    @ViewById(R.id.ctl)
    CommonTabLayout ctl;
    @ViewById(R.id.vp)
    ViewPager vp;
    @StringArrayRes
    String[] detection_title;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public View mDecorView;

    @AfterViews
    void init() {
        initImageBackText("方案审核");
        for (int i = 0; i < detection_title.length; i++) {
            mTabEntities.add(new TabEntity(detection_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new DetectionSchemeAuditFragment_());
        mFragments.add(new DetectionRechecktrialFragment_());
        mDecorView = this.getWindow().getDecorView();
//        vp = ViewFindUtils.find(mDecorView, R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, detection_title));
        ctl.setTabData(mTabEntities);
        initViewpage(ctl, vp);
        vp.setOffscreenPageLimit(2);
    }
}
