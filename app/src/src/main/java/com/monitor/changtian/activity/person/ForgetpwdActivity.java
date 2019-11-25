package com.monitor.changtian.activity.person;

import android.widget.EditText;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.ForgetpwdPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

@EActivity(R.layout.activity_forgetpwd)
public class ForgetpwdActivity extends BaseActvity implements SubmitView<ResultBean> {

    private ForgetpwdPresenter forgetpwdPresenter;

    @ViewById(R.id.et_oldpwd)
    EditText et_oldpwd;

    @ViewById(R.id.et_newpwd)
    EditText et_newpwd;

    @ViewById(R.id.et_cpwd)
    EditText et_cpwd;

    @AfterViews
    void init() {
        initImageBackText("修改密码");
        forgetpwdPresenter = new ForgetpwdPresenter(this, this);
    }

    String loginName =MyApplication.getInstance().getUser();
    String oldPwd = "";
    String newPwd = "";

    @Click(R.id.stv_submit)
    public void stv_submit() {

        if (ISEmpty("请输入旧密码", et_oldpwd.getText().toString())) {
            return;
        } else {

            if (MyApplication.getInstance().getPWd().equals(et_oldpwd.getText().toString())) {
                oldPwd = et_oldpwd.getText().toString();
            } else {
                et_oldpwd.setText("");
                msg("请输入正确的旧密码");
                return;
            }

        }
        if (ISEmpty("请输入新密码", et_newpwd.getText().toString())) {
            return;
        } else {
            newPwd = et_newpwd.getText().toString();
        }

        if (ISEmpty("请再次输入密码", et_cpwd.getText().toString())) {
            return;
        } else {
            if (!et_newpwd.getText().toString().equals(et_cpwd.getText().toString())) {
                msg("两次密码不一致!");
                return;
            }
        }


        ShowDialogtitle("请稍等...",this);

        String data = "{loginName:\"" + loginName + "\",oldPwd:\"" + oldPwd + "\",newPwd:\"" + newPwd + "\"}";

        forgetpwdPresenter.ModifyUserPwd(data);

    }

    @Override
    public void onData(ResultBean resultBean) {

    }

    @Override
    public void onMessage(ResultBean t) {

        DissDialog();
        if (t.getResult().equals("1")) {
            msg("修改成功");
            finish();
            //清除密码
            MyApplication.getInstance().setPwd("");
            EventBus.getDefault().post(new FinishEvent("关闭"));
            LoginActivity_.intent(this).start();
        } else {
            msg(t.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {
        msg(s);
        DissDialog();
    }
}
