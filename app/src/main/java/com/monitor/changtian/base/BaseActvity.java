package com.monitor.changtian.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.monitor.changtian.ActivityConllector;
import com.monitor.changtian.MainActivity;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.LoginActivity;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.permission.DefaultRationale;
import com.monitor.changtian.permission.PermissionSetting;
import com.monitor.changtian.utils.DateUtil;
import com.monitor.changtian.utils.LogAndToastUtil;
import com.monitor.changtian.widght.SimpleToolbar;
import com.monitor.changtian.widght.loadingdialog.LoadingDialog;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.RABBIT_Release_adress;
import static com.monitor.changtian.constant.PublicConstant.RABBIT_Release_user;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class BaseActvity extends AppCompatActivity {
    private Toolbar main_toolbar1;



    //此方法，如果显示则隐藏，如果隐藏则显示
    public void hintKbOne() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
// 得到InputMethodManager的实例
        if (imm.isActive()) {
            // 如果开启
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }


    public void initToolbar() {
        main_toolbar1 = (Toolbar) findViewById(R.id.main_toolbar);
        if (main_toolbar1 != null) {
            setSupportActionBar(main_toolbar1);
            main_toolbar1.setNavigationIcon(R.mipmap.ic_action_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_action_back);
        }
    }

    /**
     * 只有中间文字的title
     */
    SimpleToolbar mSimpleToolbar;

    public void initCenterText(String content) {
        mSimpleToolbar = findViewById(R.id.simple_toolbar);
        mSimpleToolbar.setGoneLeftImage();
        mSimpleToolbar.setMainTitle(content);
    }

    /**
     * 带有返回按钮的
     */
    public void initImageBackText(String content) {
        mSimpleToolbar = findViewById(R.id.simple_toolbar);
        mSimpleToolbar.setMainTitle(content);
        mSimpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /**
     * 右侧提交按钮
     */

    public void initRightOnclikText(String content, String rightText, Rightlistener listenter) {
        setmEditListenter(listenter);
        mSimpleToolbar = findViewById(R.id.simple_toolbar);
        mSimpleToolbar.setMainTitle(content);
        mSimpleToolbar.setRightTitleText(rightText);
        mSimpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hintKbTwo();
                onBackPressed();
            }
        });
        mSimpleToolbar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditListenter.rightlistener();
            }
        });
    }


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

    public void ShowDialogtitle(String title, Context context) {
        builder1 = new LoadingDialog.Builder(context)
                .setMessage(title)
                .setCancelable(false);
        dialog1 = builder1.create();
        dialog1.show();
    }


    public LoadingDialog.Builder builder2;
    public LoadingDialog dialog2;


    public void ShowDialogtitle_cancel(String title, Context context) {
        builder2 = new LoadingDialog.Builder(context)
                .setMessage(title).setCancelable(true);
        dialog2 = builder2.create();
        dialog2.show();
    }

    /**
     * 关闭dialog
     */
    public void DissDialog() {

        if (dialog1 != null) {
            dialog1.dismiss();
        }
    }

    public void DissDialog_cancel() {

        if (dialog2 != null) {
            dialog2.dismiss();
        }
    }

    public void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    //此方法只是关闭软键盘
    public void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    public int[] mIconUnselectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};
    public int[] mIconSelectIds = {
            R.mipmap.ic_action_back, R.mipmap.ic_action_back,
            R.mipmap.ic_action_back, R.mipmap.ic_action_back};

    public ViewPager mViewPager;
    //设置分页
    public int page = 1;
    //判断下一页是否存在数据
    public boolean onLoadMore = false;
    //双击退出
    public long mLastBackTime = 0;
    public long TIME_DIFF = 2 * 1000;

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


    private Rightlistener mEditListenter;

    public void setmEditListenter(Rightlistener listenter) {
        mEditListenter = listenter;
    }

    public interface Rightlistener {

        void rightlistener();
    }

    public void msg(String msg) {
        LogAndToastUtil.toast(getApplicationContext(), msg);
    }

    /**
     * 设置空布局
     */
    View view;

    public void AddEmptyView(BaseQuickAdapter mainAdapter, RecyclerView recyclerView) {
        view = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        mainAdapter.setEmptyView(view);
    }

    /**
     * @param listener 空布局点击事件
     */
    public void setEmptylistener(View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }

    }


    public PermissionSetting mSetting;
    public Rationale mRationale;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
//        if(Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.RED);
//        }
//        ActivityConllector.addActivity(this);
//        StatusBarCompat.compat(this, R.drawable.titlebar_color);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);

//        initRabbitmq();
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
//                ViseLog.d("message:"+message);
            }
        };
        //开启消费者线程
//        subscribe(incomingMessageHandler);
    }





    /**
     * 可以传递 单一权限 也可以传递单一权限组
     *
     * @param permissions
     */
    //获取单个权限或者单个权限组
    public void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(BaseActvity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();

        try {
            Configuration config = new Configuration();
            config.setToDefaults();
            res.updateConfiguration(config, res.getDisplayMetrics());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return res;
    }

    /**
     * 可以传递多个权限 或者 多个权限组
     *
     * @param permissions
     */
    //获取多个权限或者多个权限组
    public void doublePermission(String[]... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {

                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {

                        if (AndPermission.hasAlwaysDeniedPermission(BaseActvity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }


    /**
     * @param root 最外层的View
     * @param scrollToView 不想被遮挡的View,会移动到这个Veiw的可见位置
     */
    private int scrollToPosition = 0;

    public void autoScrollView(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Rect rect = new Rect();

                        //获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);

                        //获取root在窗体的不可视区域高度(被遮挡的高度)
                        int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;

                        //若不可视区域高度大于150，则键盘显示
                        if (rootInvisibleHeight > 150) {

                            //获取scrollToView在窗体的坐标,location[0]为x坐标，location[1]为y坐标
                            int[] location = new int[2];
                            scrollToView.getLocationInWindow(location);

                            //计算root滚动高度，使scrollToView在可见区域的底部
                            int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;

                            //注意，scrollHeight是一个相对移动距离，而scrollToPosition是一个绝对移动距离
                            scrollToPosition += scrollHeight;

                        } else {
                            //键盘隐藏
                            scrollToPosition = 0;
                        }
                        root.scrollTo(0, scrollToPosition);

                    }
                });
    }


    private ConnectionFactory factory; // 声明ConnectionFactory对象
    private final static String QUEUE_NAME = "emergency2";
    public Thread subscribeThread;
    private static final String EXCHANGE_NAME = "emergency";
    private final static String EXCHANGE = "FinanceSend";

    /**
     * 连接设置
     */
    private void initRabbitmq() {
        factory = new ConnectionFactory();
        factory.setHost(RABBIT_Release_adress);//主机地址：
        factory.setPort(5672);          //端口号
        factory.setUsername(RABBIT_Release_user);    //用户名
        factory.setPassword(RABBIT_Release_user);    //密码
        factory.setRequestedHeartbeat(3);//连接心跳
//        factory.setAutomaticRecoveryEnabled(true);//设置连接恢复
    }

    /**
     * 审核
     *
     * @param handler
     */

    public void subscribe(final Handler handler, final String type) {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                try {

                    Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel();

                    channel.exchangeDeclare(type, "direct", true);
                    String queueName = channel.queueDeclare().getQueue();

                    channel.queueBind(queueName, type, "");
                    System.out.println(" 连接成功！" + type);
                    Consumer consumer = new DefaultConsumer(channel) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope,
                                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
                            String message = new String(body, "UTF-8");
                            System.out.println(" [x] Received '" + message + "'");
                            Message msg = handler.obtainMessage();
                            Bundle bundle = new Bundle();
                            bundle.putString("msg", message);
                            msg.setData(bundle);
                            handler.sendMessage(msg);
                        }
                    };
                    channel.basicConsume(queueName, true, consumer);


                } catch (Exception e1) {
                    Log.d("", "Connection broken: " + e1.getClass().getName());
                    try {
                        Thread.sleep(2000); //sleep and then try again
                    } catch (InterruptedException e) {
//                            break;
                    }
                }
//                }
            }
        });
        subscribeThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        subscribeThread.interrupt();
//        ActivityConllector.removeActivity(this);
    }




    //日历日期选择。
    Calendar selectCalendar;
    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startTime;

    public void showSelectDate(String time_Start, final TextView time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i2);
                calendar.set(Calendar.DAY_OF_MONTH, i3);
                time.setText("" + format.format(calendar.getTime()));
                selectCalendar = calendar;
                startTime = calendar.getTime();
            }
        }, year, month, day);

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(DateUtil.getStringToDate("1970-01-01", "yyyy-MM-dd"));
        datePicker.setMaxDate(new Date().getTime());
        datePickerDialog.setTitle(time_Start);
        datePickerDialog.show();
    }

    public String setStatetime(int DataTime) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, DataTime);
        Date monday = c.getTime();
        String preMonday = format.format(monday);
        return preMonday;
    }


    /**
     * 设置二次弹框确认
     */
    /**
     * 二次弹框确认
     */
    public void AgainDialog(String title) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgainFalse();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AgainTrue();
            }
        });
        builder.show();
    }

    /**
     * 确定
     */
    public void AgainTrue() {
    }

    /**
     * 取消
     */
    public void AgainFalse() {
    }

}
