package com.monitor.changtian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_look_content)
public class LookContentActivity extends BaseActvity {

    @ViewById(R.id.tv_content)
    TextView tv_content;

    @Extra
    String lookstr;

    @AfterViews
    void init() {

        initImageBackText("查看文件信息");

        if (lookstr != null) {
            tv_content.setText(lookstr);
        }

    }
}
