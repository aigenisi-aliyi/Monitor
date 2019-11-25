package com.monitor.audit;

import android.view.KeyEvent;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import java.util.Date;

/**
 * 审批界面
 */
@EActivity(R.layout.activity_examine)
public class ExamineActivity extends BaseActvity {

    @AfterViews
    void init() {
        initImageBackText("审批");



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
