package com.monitor.repertory;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MyPagerAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.TabEntity;
import com.monitor.changtian.widght.ViewFindUtils;
import com.monitor.repertory.fragment.ConReturnFragment_;
import com.monitor.repertory.fragment.DevReturnFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

import java.util.ArrayList;

@EFragment(R.layout.activity_repertory_main)
public class RepertoryMainActivity extends BaseFragment implements BaseFragment.Rightlistener {

    @StringArrayRes
    String[] reper_title;
    @ViewById(R.id.tl_4)
    CommonTabLayout tl_4;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};
    private int[] mIconSelectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};
    private View mDecorView;
    private ViewPager mViewPager;
    @AfterViews
    void init() {
//        RightimageToolbar("库存管理", this);
        for (int i = 0; i < reper_title.length; i++) {
            mTabEntities.add(new TabEntity(reper_title[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new DevReturnFragment_());
        mFragments.add(new ConReturnFragment_());
        mDecorView = getActivity().getWindow().getDecorView();
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
        mViewPager.setAdapter(new MyPagerAdapter(getFragmentManager(),mFragments,reper_title));
        tl_4.setTabData(mTabEntities);
        initViewpage(tl_4,mViewPager);

    }

    @Override
    public void rightlistener() {
        msg("库存管理");
    }



}
