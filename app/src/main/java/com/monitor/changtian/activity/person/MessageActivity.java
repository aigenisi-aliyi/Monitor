package com.monitor.changtian.activity.person;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_message)
public class MessageActivity extends BaseActvity {

    @AfterViews
    void init() {
        initImageBackText("消息");
    }
}
