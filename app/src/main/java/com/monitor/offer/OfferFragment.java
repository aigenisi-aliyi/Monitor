package com.monitor.offer;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MyPagerAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.TabEntity;
import com.monitor.offer.fragment.FirsttrialFragment_;
import com.monitor.offer.fragment.RechecktrialFragment_;
import com.monitor.offer.fragment.YFirsttrialFragment_;
import com.monitor.offer.fragment.YRechecktrialFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

/**
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function: 报价审批
 */

@EActivity(R.layout.activity_offer)
public class OfferFragment extends BaseActvity {

    @ViewById(R.id.ctl)
    CommonTabLayout ctl;
    @ViewById(R.id.vp)
    ViewPager vp;
    @StringArrayRes
    String[] offer_title;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public View mDecorView;

    @AfterViews
    void init() {
        initImageBackText("报价审批");
        for (int i = 0; i < offer_title.length; i++) {
            mTabEntities.add(new TabEntity(offer_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new FirsttrialFragment_());
        mFragments.add(new YFirsttrialFragment_());
        mFragments.add(new RechecktrialFragment_());
        mFragments.add(new YRechecktrialFragment_());
        mDecorView =this.getWindow().getDecorView();
//        vp = ViewFindUtils.find(mDecorView, R.id.vp);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, offer_title));
        ctl.setTabData(mTabEntities);
        initViewpage(ctl, vp);
        vp.setOffscreenPageLimit(4);
    }
}
