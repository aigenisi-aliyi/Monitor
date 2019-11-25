package com.monitor.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.monitor.changtian.MainActivity;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.LoginActivity;
import com.monitor.changtian.activity.LoginActivity_;
import com.monitor.changtian.bean.EventBus.FinishEvent;
import com.monitor.changtian.bean.EventBus.PushMessage;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.TestEvent;
import com.monitor.detectionschemeaudit.DetectionAuditActivity;
import com.monitor.notification.NotificationUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.vise.log.ViseLog;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;


import static com.monitor.changtian.constant.PublicConstant.LOGO_APP;
import static com.monitor.changtian.constant.PublicConstant.NAME_APP;
import static com.monitor.changtian.constant.PublicConstant.RABBIT_Release_adress;
import static com.monitor.changtian.constant.PublicConstant.RABBIT_Release_user;

/**
 * Created by Administrator on 2018/10/26.
 */

public class FrontdeskService extends Service {

    private static final String TAG = "DaemonService";
    public static final int NOTICE_ID = 100;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Log.d(TAG, "DaemonService---->onCreate被调用，启动前台service");

    }


    //开启消费者线程
    final Handler incomingMessageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String message = msg.getData().getString("msg");
            ViseLog.d("message:" + message);
            try {
                PushMessage pushMessage = JSON.parseObject(message, PushMessage.class);

                if (pushMessage.getLoginId().equals(MyApplication.getInstance().getUser())) {

                    ViseLog.d("发送");

                    PushMessage pushMessage1 = new PushMessage();
                    pushMessage1.setContent(pushMessage.getContent());
                    pushMessage1.setMtypes(pushMessage.getMtypes());
                    pushMessage1.setSubid(pushMessage.getSubid());
                    pushMessage1.setRemark(pushMessage.getRemark());

                    EventBus.getDefault().post(pushMessage1);
                } else {
                    ViseLog.d("不发送");
                }

            } catch (Exception e) {
                ViseLog.d("格式有误！");
            }

        }
    };


    private ConnectionFactory factory; // 声明ConnectionFactory对象
    public Thread subscribeThread;

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
    Channel channel =null;
    Connection connection=null;
    public void subscribe(final Handler handler, final String type) {
        subscribeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                      connection = factory.newConnection();
                    channel = connection.createChannel();
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
                    }
                }
            }
        });
        subscribeThread.start();
    }
    NotificationManager mNotificationManager;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        // 如果Service被终止
        // 当资源允许情况下，重启service

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("1", "channel_name_1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.canBypassDnd();//是否绕过请勿打扰模式
            channel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
            channel.enableVibration(true);//是否允许震动
            channel.getAudioAttributes();//获取系统通知响铃声音的配置
            channel.setBypassDnd(true);//设置可绕过  请勿打扰模式
            NotificationManager manager;
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(getApplicationContext(), "1")
                    .setContentTitle(NAME_APP)
//                    .setContentText("正在运行...")
                    .setSmallIcon(LOGO_APP)
                    .setAutoCancel(true);
            startForeground(1, builder.build());
            /**
             * 初始化
             */
            initRabbitmq();
            subscribe(incomingMessageHandler, "taskmessage");
        } else {
            Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle(NAME_APP)
//                    .setContentText("正在运行...")
                    .setSmallIcon(LOGO_APP)
                    .setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND);
            startForeground(1, builder.build());
            /**
             * 初始化
             */
            initRabbitmq();
            subscribe(incomingMessageHandler, "taskmessage");
        }

//
//        NotificationUtils notificationUtils = new NotificationUtils(this);
//        startForeground(1, notificationUtils.sendNotification("1", "channel_name_1", 1, "中测", "正在运行..."));
//        /**
//         * 初始化
//         */
//        initRabbitmq();
//        subscribe(incomingMessageHandler, "taskmessage");
//        //如果API大于18，需要弹出一个可见通知
//        if (Build.VERSION_CODES.O_MR1 <= Build.VERSION.SDK_INT) {
//            ViseLog.d("asdadad");
//            String CHANNEL_ONE_ID = "com.monitor.changtian";
//            String CHANNEL_ONE_NAME = "Channel One";
//            NotificationChannel notificationChannel = null;
//            notificationChannel = new NotificationChannel(CHANNEL_ONE_ID,
//                    CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setShowBadge(true);
//            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            manager.createNotificationChannel(notificationChannel);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//            Notification notification = new Notification.Builder(this).setChannelId(CHANNEL_ONE_ID)
//                    .setSmallIcon(R.mipmap.logo111)
//                    .setContentTitle("中测")
//                    .setContentIntent(pendingIntent)
//                    .getNotification();
//            notification.flags |= Notification.FLAG_NO_CLEAR;
//            startForeground(NOTICE_ID, notification);
//            /**
//             * 初始化
//             */
//            initRabbitmq();
//            subscribe(incomingMessageHandler, "taskmessage");
//        } else {
//            ViseLog.d("初始化1111");
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//            NotificationCompat.Builder mBuilder =
//                    new NotificationCompat.Builder(this)
//                            .setSmallIcon(R.mipmap.logo111)
//                            .setContentTitle("中测").setContentIntent(pendingIntent)
//                            .setAutoCancel(true);
////点击消失
////            Intent intent1 = new Intent(this, LoginActivity_.class);
////            PendingIntent pi = PendingIntent.getActivities(this, 0, new Intent[]{intent1}, PendingIntent.FLAG_CANCEL_CURRENT);
////            mBuilder.setContentIntent(pi);
//            mNotificationManager =
//                    (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification notification = mBuilder.build();
//            // 通知来时震动或者响铃
//            // notification.sound
//            // notification.vibrate
//            //使用系统默认声音用下面这条
//            notification.defaults = Notification.DEFAULT_SOUND;
//            // 点击以后消失
//            notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONGOING_EVENT;
//            // mId allows you to update the notification later on.
//            startForeground(NOTICE_ID, mBuilder.build());
//
//            /**
//             * 初始化
//             */
//            initRabbitmq();
//            subscribe(incomingMessageHandler, "taskmessage");
//        }

//        showChannel2Notification(this);
//        /**
//         * 初始化
//         */
//        initRabbitmq();
//        subscribe(incomingMessageHandler, "taskmessage");

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        // 如果Service被杀死，干掉通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            ViseLog.d("1111");
            NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mManager.cancelAll();
            subscribeThread.interrupt();


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (channel != null) {
                                channel.close();
                            }
                            if (connection != null) {
                                connection.close();
                            }
                        }catch (Exception e){

                        }

//                        if (channel != null) {
//                            try {
//                                channel.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } catch (TimeoutException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        if (connection != null) {
//                            try {
//                                connection.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
                    }
                }).start();
        }
        Log.d(TAG, "DaemonService---->onDestroy，前台service被杀死");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (intent == null) {
            NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mManager.cancel(NOTICE_ID);
            return;
        }
    }

}
