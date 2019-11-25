package com.monitor.changtian;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.monitor.accuse.AccuseInfoActivity;
import com.monitor.accuse.QualitycontrolApprovalListActivity_;
import com.monitor.changtian.activity.AddSampleInfoActivity;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.activity.SampleProofreadActivity_;
import com.monitor.changtian.activity.task.TaskActivity;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.EventBus.AddEvent;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.bean.EventBus.JumpEvent;
import com.monitor.changtian.bean.EventBus.PushMessage;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.ReshEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestEvent;
import com.monitor.changtian.bean.UserPermissionBean;
import com.monitor.changtian.fragment.IndexFragment_;
import com.monitor.changtian.fragment.MessageListFragment_;
import com.monitor.changtian.fragment.PersonFragment_;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.MainPresenter;
import com.monitor.changtian.utils.AccountInfo;
import com.monitor.changtian.utils.AccountInfoPer;
import com.monitor.changtian.widght.BottomBar;
import com.monitor.detectionschemeaudit.DetectionAuditActivity;
import com.monitor.notification.NotificationUtils;
import com.monitor.offer.OfferinfoActivity_;
import com.monitor.service.FrontdeskService;
import com.monitor.taskallocation.TaskCorrelationinfoActivity;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;

import static com.monitor.changtian.constant.PublicConstant.AUDIT;
import static com.monitor.changtian.constant.PublicConstant.LOGO_APP;
import static com.monitor.changtian.constant.PublicConstant.NAME_APP;

public class MainActivity extends BaseActvity implements SubmitView<ResultBean> {

    ImageView tab_post_icon;
    BottomBar bottomBar;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initNot("ddddd", "0", "", "");
        /**
         * 订阅rabmitMQ
         */
        bottomBar = findViewById(R.id.bottom_bar);
        tab_post_icon = findViewById(R.id.tab_post_icon);
        // 首页禁用滑动返回
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initeLayout();
        mainPresenter = new MainPresenter(this);
        handler.postDelayed(runnable, 4000);//每两秒执行一次runnable.
        /**
         * 启动服务
         */
        startService(new Intent(this, FrontdeskService.class));
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            //要做的事情
            handler.postDelayed(this, 4000);
            String data = "{tocken:\"" + MyApplication.getInstance().getToken() + "\",source:\"0\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
            mainPresenter.VerificationTockenInfo(data);
        }
    };
    public void initeLayout() {

        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#22b583")
                .addItem(IndexFragment_.class,
                        getString(R.string.home),
                        R.drawable.ihome11,
                        R.drawable.index_active)
                .addItem(MessageListFragment_.class,
                        getString(R.string.message),
                        R.drawable.message2,
                        R.drawable.message_active)
                .addItem(PersonFragment_.class,
                        getString(R.string.me),
                        R.drawable.iuser11,
                        R.drawable.useractive)
                .build();
    }
    /**
     * 创建通知栏
     *
     * @param content
     */
    NotificationManager mNotificationManager;

    public void initNot(String content, String mytypes, String Subid, String reamk) {

//        NotificationUtils notificationUtils = new NotificationUtils(this);
//        mNotificationManager= notificationUtils.getManager();
//        notificationUtils.sendNotification("2","channel_name_2",2,"", content);
//        JumpIntent(notificationUtils.Getbuilder(), mytypes, Subid, reamk);
//        notificationUtils.getManager().notify(2, notificationUtils.Getnotification());


        if (Build.VERSION.SDK_INT >= 26) {
//            Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
//            vibrator.vibrate(400);
            NotificationChannel channel = new NotificationChannel("2", "channel_name_2", NotificationManager.IMPORTANCE_DEFAULT);
            channel.canBypassDnd();//是否绕过请勿打扰模式
            channel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
            channel.enableVibration(true);//是否允许震动
//            channel.setVibrationPattern(new long[]{0});
            channel.getAudioAttributes();//获取系统通知响铃声音的配置
            channel.setBypassDnd(true);//设置可绕过  请勿打扰模式

            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(getApplicationContext(), "2")
                    .setContentTitle(NAME_APP)
                    .setContentText(content)
                    .setSmallIcon(LOGO_APP)
                    .setAutoCancel(true);
            JumpIntent(builder, mytypes, Subid, reamk);
            mNotificationManager.notify(2, builder.build());
        } else {
            Vibrator vibrator = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle(NAME_APP)
                    .setContentText(content)
                    .setSmallIcon(LOGO_APP)
                    .setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND);
            mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            JumpIntent(builder, mytypes, Subid, reamk);
            mNotificationManager.notify(2, builder.build());
        }
    }

    public void JumpIntent(Notification.Builder mBuilder, String mytypes, String spid, String remak) {

        switch (mytypes) {
            case "1":
                EventBus.getDefault().post(new UpdateEvent("DetectionSchemeAudit"));
//                if (closeStr.equals("Detection")) {
//                    ViseLog.d("11111");
//                    //只消失
//                    PendingIntent pi18 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
//                    mBuilder.setContentIntent(pi18);
//                    msg("方案已审核!");
//                } else {
//                    ViseLog.d("2222");
                Intent intent = new Intent(MainActivity.this, DetectionAuditActivity.class);
                intent.putExtra("type", "0");
                intent.putExtra("schid", spid);
                PendingIntent pi = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi);
//                }
                break;
            case "2":
                EventBus.getDefault().post(new UpdateEvent("Firsttrial"));
                Intent intent1 = new Intent(MainActivity.this, OfferinfoActivity_.class);
                intent1.putExtra("type", "0");
                intent1.putExtra("id", spid);
                intent1.putExtra("sqstatus", "0");
                PendingIntent pi0 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent1}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi0);
                break;
            case "3":
                EventBus.getDefault().post(new UpdateEvent("Rechecktrial"));
                Intent intent3 = new Intent(MainActivity.this, OfferinfoActivity_.class);
                intent3.putExtra("type", "1");
                intent3.putExtra("id", spid);
                intent3.putExtra("sqstatus", "5");
                PendingIntent pi3 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent3}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi3);
                break;

            case "5":
                EventBus.getDefault().post(new ReshEvent("更新"));
                Intent intent5 = new Intent(MainActivity.this, AccuseInfoActivity.class);
                intent5.putExtra("id", spid);
                intent5.putExtra("schid", remak);
                PendingIntent pi5 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent5}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi5);
                break;
            case "6":
                EventBus.getDefault().post(new UpdateEvent("TaskAllocationMain"));
                Intent intent6 = new Intent(MainActivity.this, TaskCorrelationinfoActivity.class);
                intent6.putExtra("coid", spid);
                intent6.putExtra("schid", remak);
                intent6.putExtra("status", "0");
                PendingIntent pi6 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent6}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi6);
                break;

            case "7":
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                Intent intent7 = new Intent(MainActivity.this, TaskActivity.class);
                intent7.putExtra("taskid", spid);
                PendingIntent pi7 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent7}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi7);
                break;

            case "9":
                EventBus.getDefault().post(new UpdateEvent("taskAudit"));
                Intent intent9 = new Intent(MainActivity.this, SampleProofreadActivity_.class);
                intent9.putExtra("type", AUDIT);
                intent9.putExtra("stutas", "2");
                intent9.putExtra("sampid", spid);
                PendingIntent pi9 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent9}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi9);
                break;
            case "12":
                EventBus.getDefault().post(new UpdateEvent("Audittask"));
                Intent intent12 = new Intent(MainActivity.this, TaskCorrelationinfoActivity.class);
                intent12.putExtra("isstoppass", "1");
                intent12.putExtra("status", "2");
                intent12.putExtra("taskid", spid);
                intent12.putExtra("coid", spid);
                intent12.putExtra("schid", remak);
                PendingIntent pi12 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent12}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi12);
                break;
            case "13":
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                //只消失
                PendingIntent pi13 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi13);
                break;
            case "17":
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                //只消失
                PendingIntent pi17 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi17);
                break;
            case "18":
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                //只消失
                PendingIntent pi18 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi18);
                break;
            case "23":
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                //只消失
                PendingIntent pi23 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi23);
                break;
            case "28":
                Intent intent28 = new Intent(MainActivity.this, QualitycontrolApprovalListActivity_.class);
                intent28.putExtra("tpcid", spid);
                PendingIntent pi28 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent28}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi28);
                break;

            default:
                EventBus.getDefault().post(new UpdateEvent("SampleList"));
                //只消失
                PendingIntent pi111 = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{new Intent()}, PendingIntent.FLAG_CANCEL_CURRENT);
                mBuilder.setContentIntent(pi111);
                break;
//                    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//                                Intent intent11= new Intent(this, LoginActivity_.class);
//            PendingIntent pi = PendingIntent.getActivities(this, 0, new Intent[]{intent11}, PendingIntent.FLAG_CANCEL_CURRENT);

        }


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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
        if (mNotificationManager != null) {
            mNotificationManager.cancelAll();
        }
        stopService(new Intent(this, FrontdeskService.class));
        handler.removeCallbacks(runnable);
    }

    /**
     * 判断是否已经操作，如果操作则不做跳转只提示
     *
     * @param event
     */
    String closeStr = "";

    @Subscribe
    public void ClosesEvent(CloseEvent event) {

        if (event != null) {
            if (event.getClostStr().equals("Detection")) {
                if (mNotificationManager != null) {
                    mNotificationManager.cancelAll();
                }
            }
        }

    }

    @Subscribe
    public void tagEvent(AddEvent event) {
//        if (event != null) {
//
//            if (event.getTarget() == 2) {
//
//                if (event.isIstar() != 0) {
//                    if (event.isIstar() == 1) {
//                        tab_post_icon.setImageResource(R.drawable.close);
//                        bottomBar.setFirstChecked(2).build();
//                    } else if (event.isIstar() == 2) {
//                        tab_post_icon.setImageResource(R.drawable.add);
//                        bottomBar.setFirstChecked(0).build();
//
//                        EventBus.getDefault().post(new VisibilityEvent("0"));
//                    }
//                }
//
//            } else {
//                tab_post_icon.setImageResource(R.drawable.add);
//            }
//
//        }
    }

//    @Subscribe
//    public void jump(JumpEvent event) {
//        if (event != null) {
//
//            if (event.getType() == 1) {
//                bottomBar.setFirstChecked(1).build();
//            } else {
//                bottomBar.setFirstChecked(3).build();
//            }
//
//        }
//    }

    @Subscribe
    public void closeactivity(FinishEvent event) {
        if (event != null) {
            if (event.getMessage().equals("关闭")) {
                finish();
            }
        }
    }

    @Subscribe
    public void Testsss(PushMessage event) {

        ViseLog.d("sllsssssss");
        if (event != null) {
            initNot(event.getContent(), event.getMtypes(),
                    event.getSubid(), event.getRemark());
        }
    }
    @Override
    public void onData(ResultBean resultBean) {

    }
    /**
     * 返回0 代表账户以及失效 在别的地方登录了
     *
     * @param t
     */
    @Override
    public void onMessage(ResultBean t) {
        if (t != null) {
            if (t.getResult().equals("0")) {
                AccountInfo.loginOut(this);
                AccountInfoPer.loginOut(this);
                handler.removeCallbacks(runnable);
                //清除密码
                MyApplication.getInstance().setPwd("");
                if(mNotificationManager!=null){
                    mNotificationManager.cancelAll();
                }
                DialogActivity_.intent(this).start();
                // 停止服务
                stopService(new Intent(this, FrontdeskService.class));
            }
        }
    }
    @Override
    public void OnError(String s) {

    }
    ArrayList<ResultBean.RolejarryBean> rolejarrys=new ArrayList<>();
    @Subscribe
    public void Modeaaa(UserPermissionBean event) {
        if (event != null) {
            rolejarrys.addAll(event.getRolejarry());
            EventBus.getDefault().post(new UserPermissionBean(rolejarrys));
        }
    }
}
