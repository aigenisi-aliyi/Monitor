package com.monitor.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.monitor.changtian.R;
import com.vise.log.ViseLog;

/**
 * Created by Administrator on 2018/11/12.
 */

public class NotificationUtils extends ContextWrapper {

    private NotificationManager manager;


    public NotificationUtils(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(String id, String name) {
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.canBypassDnd();//是否绕过请勿打扰模式
        channel.setVibrationPattern(new long[]{100, 100, 200});//设置震动模式
        channel.enableVibration(true);//是否允许震动
        channel.getAudioAttributes();//获取系统通知响铃声音的配置
        channel.setBypassDnd(true);//设置可绕过  请勿打扰模式
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification(String id, String title, String content) {
        ViseLog.d("getChannelNotification");
        builder = new Notification.Builder(getApplicationContext(), id)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.logo111)
                .setAutoCancel(true);
        return builder;
    }

    public Notification.Builder getNotification_25(String title, String content) {
        ViseLog.d("触发了getNotification_25 ");
        builder = new Notification.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.mipmap.logo111)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_SOUND);
        return builder;

    }

    Notification notification;

    public Notification sendNotification(String id, String name, int notificationid, String title, String content) {
        if (Build.VERSION.SDK_INT >= 26) {
            ViseLog.d("ss");
            createNotificationChannel(id, name);
            notification = getChannelNotification
                    (id, title, content).build();
            getManager().notify(notificationid, notification);
            return notification;
        } else {
            ViseLog.d("ss1");
            notification = getNotification_25(title, content).build();
            getManager().notify(notificationid, notification);
            return notification;
        }
    }

    Notification.Builder builder;

    public Notification.Builder Getbuilder() {
        Notification.Builder builders = builder;
        if (Build.VERSION.SDK_INT >= 26) {
            return builders;
        } else {
            return builders;
        }
    }

    public Notification Getnotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            return notification;
        } else {
            return notification;
        }
    }


}
