package com.monitor.leader;

import android.widget.FrameLayout;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.widght.BottomBar_Leader;
import com.monitor.leader.fragment.Leader_HomeFragment_;
import com.monitor.leader.fragment.Leader_MyFragment_;
import com.monitor.leader.fragment.Leader_ProgressFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_leader_main)
public class LeaderMainActivity extends BaseActvity {


    @ViewById(R.id.bottom_bar_leader)
    BottomBar_Leader bottomBar;

    @ViewById(R.id.fl_leader)
    FrameLayout fl_leader;

    @AfterViews
    void init() {

        bottomBar.setContainer(R.id.fl_leader)
                .setTitleBeforeAndAfterColor("#999999", "#45ce63")
                .addItem(Leader_HomeFragment_.class,
                        "首页",
                        R.drawable.data2,
                        R.drawable.data)
                .addItem(Leader_MyFragment_.class,
                        "我的",
                        R.drawable.data2,
                        R.drawable.data)
                .addItem(Leader_ProgressFragment_.class,
                        "进度",
                        R.drawable.data2,
                        R.drawable.data)
                .build();

    }
}
