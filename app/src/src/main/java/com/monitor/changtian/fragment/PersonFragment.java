package com.monitor.changtian.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jauker.widget.BadgeView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.monitor.changtian.MainActivity;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.person.PersonalinfoActivity_;
import com.monitor.changtian.activity.person.SeetingActivity_;
import com.monitor.changtian.adapter.SingleAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.LoginPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/4/23.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.fragment_person)
public class PersonFragment extends BaseFragment implements SubmitView<ResultBean> {
    BadgeView badgeView;
    @ViewById(R.id.rv_login_lodo)
    RoundedImageView rv_login_lodo;
    LoginPresenter loginPresenter;
    SingleAdapter singleAdapter;
    String isRoleStr = "";

    AllUserInfo doctorinfo = MyApplication.getInstance().getAllUserInfo(); //获取用户信息

    @ViewById(R.id.tv_names)
    TextView tv_names;
    @ViewById(R.id.tv_info)
    TextView tv_info;
    @ViewById(R.id.tv_phones)
    TextView tv_phones;

    @AfterViews
    void init() {
        loginPresenter = new LoginPresenter(this, getActivity());
//        badgeView = new BadgeView(getActivity());
//        badgeView.setTargetView(rv_login_lodo);
//        badgeView.setBadgeGravity(Gravity.RIGHT | Gravity.BOTTOM);
//        badgeView.setBackgroundResource(R.drawable.camera);
//        badgeView.setText("");
        rv_login_lodo.setBackgroundResource(R.mipmap.user_un_login);
        tv_names.setText(doctorinfo.getUserName());
        tv_phones.setText("电话:"+doctorinfo.getPhonenum());
        tv_info.setText("性别:" + doctorinfo.getSex());
    }


    //跳转到设置界面
    @Click(R.id.ll_user)
    public void ll_user() {
        SeetingActivity_.intent(this).start();
    }

    //跳转到 个人资料
    @Click(R.id.ll_info)
    public void ll_info() {
        PersonalinfoActivity_.intent(this).start();
    }


//    /**
//     * 更换角色
//     */
//    @Click(R.id.ll_role)
//    public void ll_role() {
//
//        ShowDialogtitle("请稍等...", getActivity());
//        //请求接口返回用户权限
//        String data = "{loginName:\"" + MyApplication.getInstance().getUser() + "\",pwd:\"" + MyApplication.getInstance().getPWd() + "\"}";
//        loginPresenter.UserLogin(data, dialog1);
//    }

    @Override
    public void onData(ResultBean resultBean) {

    }

    @Override
    public void onMessage(ResultBean t) {
        DissDialog();
        if (t != null) {
            if (t.getResult().equals("1")) {
                //弹出选择权限的dialog框
                List<ResultBean.RolejarryBean> rolejarryBeanList = new ArrayList<>();
                rolejarryBeanList.addAll(t.getRolejarry());
                ShowDialogPemmm(rolejarryBeanList);
            } else {
                msg("当前登录用户暂无配置,故无法更改角色");
            }

        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


    public void ShowDialogPemmm(List<ResultBean.RolejarryBean> rolejarryBeans) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dia = builder.show();
        builder.setCancelable(false);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.roleselect_item, null);
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        singleAdapter = new SingleAdapter(getActivity(), rolejarryBeans);
        rv_list.setAdapter(singleAdapter);

        singleAdapter.setOnItemClickLitener(new SingleAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                singleAdapter.setSelection(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dia.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (singleAdapter.GetCheckStr().length() == 0) {

                    dia.dismiss();
                    msg("如果要更换角色,请选择需要更换的角色!");
                } else {
                    dia.dismiss();
                    isRoleStr = singleAdapter.GetCheckStr();
                    if (isRoleStr.indexOf("项目负责人") != -1) {
                        EventBus.getDefault().post(new FinishEvent("关闭"));
                        startActivity(new Intent(getActivity(), MainActivity.class).putExtra("type", "renwu"));
                        MyApplication.getInstance().setUserPermission(isRoleStr);
                    } else if (isRoleStr.indexOf("方案审核") != -1) {
                        EventBus.getDefault().post(new FinishEvent("关闭"));
                        startActivity(new Intent(getActivity(), MainActivity.class).putExtra("type", "fanganshenhe"));
                        MyApplication.getInstance().setUserPermission(isRoleStr);
                    } else if (isRoleStr.indexOf("报价") != -1) {
                        EventBus.getDefault().post(new FinishEvent("关闭"));
                        startActivity(new Intent(getActivity(), MainActivity.class).putExtra("type", "baojia"));
                        MyApplication.getInstance().setUserPermission(isRoleStr);
                    } else if (isRoleStr.indexOf("质控") != -1) {
                        EventBus.getDefault().post(new FinishEvent("关闭"));
                        startActivity(new Intent(getActivity(), MainActivity.class).putExtra("type", "zhikong"));
                        MyApplication.getInstance().setUserPermission(isRoleStr);
                    } else if (isRoleStr.indexOf("采样") != -1) {
                        EventBus.getDefault().post(new FinishEvent("关闭"));
                        startActivity(new Intent(getActivity(), MainActivity.class).putExtra("type", "renwu1"));
                        MyApplication.getInstance().setUserPermission(isRoleStr);
                    } else {
                        msg("当前角色暂未开放!");
                    }
                }

            }
        });
        builder.setView(view);
        builder.show();

    }
//
    //跳转到个人信息中心
    @Click(R.id.rv_login_lodo)
    public void rv_login_lodo() {

        PersonalinfoActivity_.intent(this).start();

    }
//
//    //跳转到消息界面
//    @Click(R.id.stv_message)
//    public void stv_message() {
//        MessageActivity_.intent(this).start();
//    }
//
//
//    @Click(R.id.stv_select)
//    public void stv_select() {
//        PhotoSelectActivity_.intent(this).start();
//    }
//
//    AlertDialog dia;
//
//    @Click(R.id.test)
//    public void test() {
//
////        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_item_status, null);
////        builder.setView(view);
////        dia = builder.show();
//
//    }
//
//    @Click(R.id.homeRecord)
//    public void homeRecord() {
//        HomeRecordActivity_.intent(this).start();
//    }
}
