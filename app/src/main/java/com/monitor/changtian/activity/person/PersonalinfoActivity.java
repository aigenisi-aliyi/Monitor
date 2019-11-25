package com.monitor.changtian.activity.person;

import com.allen.library.SuperTextView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.UserInfo;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.LoginPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_personalinfo)
public class PersonalinfoActivity extends BaseActvity implements SubmitView<List<UserInfo>> {


    @ViewById(R.id.stv_user)
    SuperTextView stv_user;

    @ViewById(R.id.stv_name)
    SuperTextView stv_name;
    @ViewById(R.id.stv_sex)
    SuperTextView stv_sex;
    @ViewById(R.id.stv_shengri)
    SuperTextView stv_shengri;
    @ViewById(R.id.stv_workTime)
    SuperTextView stv_workTime;

    LoginPresenter loginPresenter;

    @AfterViews
    void init() {
        initImageBackText("个人资料");

        ShowDialogtitle("请稍等...",this);
        loginPresenter = new LoginPresenter(this, this);
        String liginname = MyApplication.getInstance().getUser();
        String data = "{loginName:\"" + liginname + "\",userid:\"\"}";
        loginPresenter.GetUserInfo(data);
    }
    @Override
    public void onData(List<UserInfo> userInfos) {
        DissDialog();
        if (userInfos != null) {
            stv_user.getRightTextView().setText(userInfos.get(0).getLoginName());
            stv_name.getRightTextView().setText(userInfos.get(0).getUserName());
            stv_sex.getRightTextView().setText(userInfos.get(0).getSex());
            stv_shengri.getRightTextView().setText(userInfos.get(0).getBirthday());
            stv_workTime.getRightTextView().setText(userInfos.get(0).getWorktime());
        }
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}
