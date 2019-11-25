package com.monitor.changtian.activity.person;

import android.content.Intent;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.activity.SignatureActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.utils.AccountInfo;
import com.monitor.changtian.utils.AccountInfoPer;
import com.monitor.service.FrontdeskService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity(R.layout.activity_seeting)
public class SeetingActivity extends BaseActvity {

    @AfterViews
    void init() {
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initImageBackText(this.getResources().getString(R.string.seeting));
    }

    //跳转修改密码
    @Click(R.id.stv_updatepwd)
    public void stv_updatepwd() {
        ForgetpwdActivity_.intent(this).start();
    }

    //跳转到版本更新界面
    @Click(R.id.stv_updateversion)
    public void stv_updateversion() {

    }

    //跳转到签名测试界面
    @Click(R.id.stv_sign)
    public void stv_sign() {
        SignatureActivity_.intent(this).start();
    }

    //注销
    @Click(R.id.stv_login)
    public void stv_login() {
        //关闭当前

        AccountInfo.loginOut(this);

        AccountInfoPer.loginOut(this);
        //清除密码
        MyApplication.getInstance().setPwd("");
        //清除权限
        MyApplication.getInstance().setUserPermission("");
        //清除ID
        MyApplication.getInstance().setUserCode("");
        EventBus.getDefault().post(new FinishEvent("关闭"));
        finish();
        LoginActivity_.intent(this).start();

    }



    @Subscribe
    public void closeactivity(FinishEvent event) {
        if (event != null) {
            if (event.getMessage().equals("关闭")) {
                finish();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

}
