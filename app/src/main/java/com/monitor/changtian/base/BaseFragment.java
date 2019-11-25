package com.monitor.changtian.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.monitor.changtian.R;
import com.monitor.changtian.permission.DefaultRationale;
import com.monitor.changtian.permission.PermissionSetting;
import com.monitor.changtian.utils.LogAndToastUtil;
import com.monitor.changtian.widght.SimpleToolbar;
import com.monitor.changtian.widght.loadingdialog.LoadingDialog;
import com.vise.log.ViseLog;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;

/**
 * Created by ken on 2018/4/23.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BaseFragment extends android.support.v4.app.Fragment {

    public LoadingDialog.Builder builder1;
    public LoadingDialog dialog1;

    /**
     * 显示dialog
     *
     * @param context
     */
    public void ShowDialog(Context context) {
        builder1 = new LoadingDialog.Builder(context)
                .setMessage("登录中...")
                .setCancelable(false);
        dialog1 = builder1.create();
        dialog1.show();
    }


    public void ShowDialogtitle(String title,Context context) {
//        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//防止翻转
        builder1 = new LoadingDialog.Builder(context)
                .setMessage(title)
                .setCancelable(false);
        dialog1 = builder1.create();
        dialog1.show();
    }

    /**
     * 关闭dialog
     */
    public void DissDialog() {

        if (dialog1 != null) {
            dialog1.dismiss();
        }
    }

    /**
     * 忽略系统的字体大小(可能会导致布局错乱)
     */

    //判断是否为空并输出内容
    protected boolean ISEmpty(String msg, String edittext) {
        if (TextUtils.isEmpty(edittext)) {
            msg(msg);
            return true;
        }
        return false;
    }

    public PermissionSetting mSetting;
    public Rationale mRationale;


    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(getActivity());
    }

    /**
     * 可以传递 单一权限 也可以传递单一权限组
     *
     * @param permissions
     */
//    //获取单个权限或者单个权限组
//    public void requestPermission(String... permissions) {
//        AndPermission.with(this)
//                .permission(permissions)
//                .rationale(mRationale)
//                .onGranted(new Action() {
//                    @Override
//                    public void onAction(List<String> permissions) {
//                    }
//                })
//                .onDenied(new Action() {
//                    @Override
//                    public void onAction(@NonNull List<String> permissions) {
//                        if (AndPermission.hasAlwaysDeniedPermission(getActivity(), permissions)) {
//                            mSetting.showSetting(permissions);
//                        }
//                    }
//                })
//                .start();
//    }

    /**
     * 只有中间文字的title
     */
    SimpleToolbar mSimpleToolbar;

    public void initCenterText(String content) {
        mSimpleToolbar = getActivity().findViewById(R.id.simple_toolbar);
        mSimpleToolbar.setGoneLeftImage();
        mSimpleToolbar.setMainTitle(content);
    }


    private Rightlistener mEditListenter;

    public void setmEditListenter(Rightlistener listenter) {
        mEditListenter = listenter;
    }

    public interface Rightlistener {

        void rightlistener();
    }

    public void msg(String msg) {
        LogAndToastUtil.toast(getActivity().getApplication(), msg);
    }

    /**
     * 设置空布局
     */
    View view;

    public void AddEmptyView(BaseQuickAdapter mainAdapter, RecyclerView recyclerView) {
        view = getActivity().getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        if (view != null) {
            mainAdapter.setEmptyView(view);
        }

    }

    /**
     * @param listener 空布局点击事件
     */
    public void setEmptylistener(View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }

    }

    public int[] mIconUnselectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};
    public int[] mIconSelectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};
    public ViewPager mViewPager;

    /**
     * 初始化
     *
     * @param ctl
     * @param vp
     */
    public void initViewpage(final CommonTabLayout ctl, final ViewPager vp) {

        ctl.setCurrentTab(0);
        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vp.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
//                    ctl.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
                if (position == 1) {
                    ViseLog.d("lslsl", "111");
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ctl.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(0);
    }


}
