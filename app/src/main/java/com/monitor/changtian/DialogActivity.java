package com.monitor.changtian;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.utils.AccountInfo;
import com.monitor.changtian.utils.AccountInfoPer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;

@EActivity(R.layout.activity_dialog)
public class DialogActivity extends BaseActvity {

    Dialog dialog;

    @AfterViews
    void init() {

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("下线通知");
        builder.setCancelable(false);
        builder.setMessage("您的账号已经在别的设备登录。如非本人操作,密码可能已经泄露,建议尽快修改密码");
        //    设置一个NegativeButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //清除权限
                MyApplication.getInstance().setUserPermission("");
                //清除ID
                MyApplication.getInstance().setUserCode("");
                EventBus.getDefault().post(new FinishEvent("关闭"));
//                AccountInfo.loginOut(DialogActivity.this);
//                AccountInfoPer.loginOut(DialogActivity.this);
//                EventBus.getDefault().post(new FinishEvent("关闭"));
                finish();
                dialog.dismiss();
                LoginActivity_.intent(DialogActivity.this).start();

            }
        });
        dialog = builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
