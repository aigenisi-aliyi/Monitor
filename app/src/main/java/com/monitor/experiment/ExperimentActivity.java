package com.monitor.experiment;

import android.view.KeyEvent;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.Date;

/**
 * 实验界面
 */
@EActivity(R.layout.activity_experiment)
public class ExperimentActivity extends BaseActvity {

    @AfterViews
    void init() {

//        initNullBtnTitlebar("实验");
    }


    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isTaskRoot()) {
                long now = new Date().getTime();
                if (now - mLastBackTime < TIME_DIFF) {
                    return super.onKeyDown(keyCode, event);
                } else {
                    mLastBackTime = now;
                    msg("再按一次返回键退出");
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
