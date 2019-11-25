package com.monitor.changtian.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.allen.library.SuperTextView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.monitor.changtian.MainActivity;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.RoleselectAdapter;
import com.monitor.changtian.adapter.SingleAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.UserPermissionBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.LoginPresenter;
import com.monitor.changtian.presenter.QueryBasicsPresenter;
import com.monitor.changtian.utils.AccountInfo;
import com.monitor.changtian.utils.AccountInfoPer;
import com.monitor.changtian.view.QueryBasicsView;
import com.monitor.leader.LeaderMainActivity_;
import com.vise.log.ViseLog;
import com.yanzhenjie.permission.Permission;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActvity implements SubmitView<ResultBean>, QueryBasicsView {

    @ViewById(R.id.rv_login_lodo)
    RoundedImageView rv_login_lodo;
    @ViewById(R.id.et_username)
    EditText et_username;
    @ViewById(R.id.et_password)
    EditText et_password;

    @ViewById(R.id.stv_login)
    SuperTextView stv_login;
    private LinearLayout ll_loginView;

    LoginPresenter loginPresenter;
    String isRoleStr = "";
    QueryBasicsPresenter queryBasicsPresenter;
    RoleselectAdapter roleselectAdapter;
    SingleAdapter singleAdapter;

    @AfterViews
    void init() {


        initCenterText("登录");
        // 首页禁用滑动返回
//        initNullBtnTitlebar(getString(R.string.login));
//        initToolbar();
        //获取位置
        doublePermission(Permission.Group.LOCATION, Permission.Group.CAMERA, Permission.Group.STORAGE);
        ll_loginView = (LinearLayout) findViewById(R.id.layout_content);
        roleselectAdapter = new RoleselectAdapter(R.layout.nopoint_select_item);
        autoScrollView(ll_loginView, stv_login);//弹出软键盘时滚动视图
        loginPresenter = new LoginPresenter(this, this);
        queryBasicsPresenter = new QueryBasicsPresenter(this, this);
        // 自动登录
        AutoLogin();

    }
    public void AutoLogin() {
        if (MyApplication.getInstance().getUser().length() > 0 && MyApplication.getInstance().getPWd().length() > 0) {
            ShowDialog(this);
            user = MyApplication.getInstance().getUser();
            pwd = MyApplication.getInstance().getPWd();
            et_username.setText(user);
            et_password.setText(pwd);
            String data = "{loginName:\"" + user + "\",source:\"0\",pwd:\"" + pwd + "\"}";
            loginPresenter.UserLogin(data, dialog1);
        } else {
            user = MyApplication.getInstance().getUser();
            pwd = MyApplication.getInstance().getPWd();
            isRoleStr = MyApplication.getInstance().getUserPermission();
            et_username.setText(user);
            et_password.setText(pwd);
        }
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String user = "", pwd = "";

    //登录
    @Click(R.id.stv_login)
    public void stv_login() {


        hintKbTwo();

        if (ISEmpty(getString(R.string.please_user), et_username.getText().toString())) {
            return;
        }

        if (ISEmpty(getString(R.string.please_pwd), et_password.getText().toString())) {
            return;
        }
        ShowDialog(this);
        user = et_username.getText().toString();
        pwd = et_password.getText().toString();
//        /**
//         * 根据输入用户名 跳转不同界面
//         */
//        if (user.equals("leader")) {
//            LeaderMainActivity_.intent(this).start();
//            finish();
//        } else if (user.equals("yangpin")) {
////            MyApplication.getInstance().setUser(et_username.getText().toString());
//            finish();
//            startActivity(new Intent(this, MainActivity.class).putExtra("type", "yangpin"));
//            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
//        } else if (user.equals("lingyong")) {
//            finish();
//            startActivity(new Intent(this, MainActivity.class).putExtra("type", "lingyong"));
//            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
//        } else if (user.equals("caiwu")) {
//            finish();
//            startActivity(new Intent(this, MainActivity.class).putExtra("type", "caiwu"));
//            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
//        } else {
        String data = "{loginName:\"" + user + "\",source:\"0\",pwd:\"" + pwd + "\"}";
        loginPresenter.UserLogin(data, dialog1);
//        }
    }

    //忘记密码
    @Click(R.id.tv_forgetpwd)
    public void tv_forgetpwd() {
        msg(getString(R.string.login_forgetpwd));
    }

    //注册
    @Click(R.id.tv_register)
    public void tv_register() {
        msg(getString(R.string.register));
    }

    @Override
    public void onData(ResultBean resultBean) {

    }


    List<ResultBean.RolejarryBean> rolejarryBeans = new ArrayList<>();

    String token = "";

    @Override
    public void onMessage(ResultBean t) {

        if (t != null) {
            if (t.getResult().equals("1")) {
                token = t.getTockenInfo();
                String data = "{loginName:\"" + et_username.getText().toString() + "\",querystring:\"\",rolename:\"\"}";
                queryBasicsPresenter.GetAllUserInfo(data);
                rolejarryBeans.addAll(t.getRolejarry());
                AccountInfoPer.saveAccount(LoginActivity.this,t);
            } else {
                DissDialog();
                msg(t.getErrormessage());
            }
        }
//
//                if (isRoleStr.length() > 0) {
//
////                  查询用户信息  主要获取用户id
//                    String data = "{loginName:\"" + et_username.getText().toString() + "\",querystring:\"\",rolename:\"\"}";
//                    queryBasicsPresenter.GetAllUserInfo(data);
//                } else {
//
//                    if (t.getRolejarry().size() > 0) {
//                        if (t.getRolejarry().size() == 1) {
//                            isRoleStr = t.getRolejarry().get(0).getRoleName();
////                  查询用户信息  主要获取用户id
//                            String data = "{loginName:\"" + et_username.getText().toString() + "\",querystring:\"\",rolename:\"\"}";
//                            queryBasicsPresenter.GetAllUserInfo(data);
//                        } else {
//                            //弹出选择权限的dialog框
//                            List<ResultBean.RolejarryBean> rolejarryBeanList = new ArrayList<>();
//                            rolejarryBeanList.addAll(t.getRolejarry());
//                            ShowDialogPemmm(rolejarryBeanList);
//                        }
//                    }
//                }
//
//            } else {
//                msg(t.getErrormessage());
//            }
//        }
    }


    public void ShowDialogPemmm(List<ResultBean.RolejarryBean> rolejarryBeans) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        final AlertDialog dia = builder.show();
        builder.setCancelable(false);
        View view = LayoutInflater.from(LoginActivity.this).inflate(R.layout.roleselect_item, null);
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(LoginActivity.this));
        singleAdapter = new SingleAdapter(LoginActivity.this, rolejarryBeans);
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
                    msg("请选择当前要登录的角色!");
                } else {
                    dia.dismiss();
                    isRoleStr = singleAdapter.GetCheckStr();
//                  查询用户信息  主要获取用户id
                    String data = "{loginName:\"" + et_username.getText().toString() + "\",querystring:\"\",rolename:\"\"}";
                    queryBasicsPresenter.GetAllUserInfo(data);
                }

            }
        });
        builder.setView(view);
        builder.show();

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {

        DissDialog();
        ViseLog.d("lslsls" + rolejarryBeans.size());

        EventBus.getDefault().post(new UserPermissionBean(rolejarryBeans));
        MyApplication.getInstance().setUser(et_username.getText().toString());
        MyApplication.getInstance().setPwd(et_password.getText().toString());
        MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
        MyApplication.getInstance().setToken(token);
        //保存用户详细信息
        AccountInfo.saveAccount(LoginActivity.this, userInfos.get(0));
//        finish();
        startActivity(new Intent(this, MainActivity.class).putExtra("type", "renwu1"));
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        //        if (isRoleStr.length() > 0) {
//            if (isRoleStr.indexOf("项目负责人") != -1) {
//                finish();
//                startActivity(new Intent(this, MainActivity.class).putExtra("type", "renwu"));
//                MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
//                MyApplication.getInstance().setUserPermission(isRoleStr);
//            } else if (isRoleStr.indexOf("方案审核") != -1) {
//                finish();
//                startActivity(new Intent(this, MainActivity.class).putExtra("type", "fanganshenhe"));
//                MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
//                MyApplication.getInstance().setUserPermission(isRoleStr);
//            } else if (isRoleStr.indexOf("报价") != -1) {
//                finish();
//                startActivity(new Intent(this, MainActivity.class).putExtra("type", "baojia"));
//                MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
//                MyApplication.getInstance().setUserPermission(isRoleStr);
//            } else if (isRoleStr.indexOf("质控") != -1) {
//                finish();
//                startActivity(new Intent(this, MainActivity.class).putExtra("type", "zhikong"));
//                MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
//                MyApplication.getInstance().setUserPermission(isRoleStr);
//            } else if (isRoleStr.indexOf("采样") != -1) {
//                finish();
//                startActivity(new Intent(this, MainActivity.class).putExtra("type", "renwu1"));
//                MyApplication.getInstance().setUserCode(userInfos.get(0).getId() + "");
//                MyApplication.getInstance().setUserPermission(isRoleStr);
//            } else {
//                isRoleStr = "";
//                msg("当前角色暂未开放!");
//            }
//        }
    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {

    }

    //双击退出
    private long mLastBackTime = 0;
    private long TIME_DIFF = 2 * 1000;

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
