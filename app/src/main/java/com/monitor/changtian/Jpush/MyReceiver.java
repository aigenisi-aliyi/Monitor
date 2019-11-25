package com.monitor.changtian.Jpush;

/**
 * Created by ken on 2018/5/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class MyReceiver  {
//    private static final String TAG = "JPush";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    private Context context;
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        this.context = context;
//        Bundle bundle = intent.getExtras();
////        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//
//        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
//            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
//            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
//            //send the Registration Id to your server...
//        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            processCustomMessage(context, bundle);
//            //推送的内容
//            String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            //推送的标识
//            String funCode = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            ViseLog.d(funCode + extras);
//
//        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
//            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
//            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
//            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//
//
//        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
//            ViseLog.d("用户点击打开了通知");
//            processCustomMessage(context, bundle);
//            //推送的内容
//            String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            //推送的标识
//            String funCode = bundle.getString(JPushInterface.EXTRA_EXTRA);
//
//
//        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
//            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
//            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
//
//        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
//            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
//            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
//        } else {
//            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
//        }
//    }
//
//    // 打印所有的 intent extra 数据
//    private static void printBundle(Bundle bundle) {
////        ...//省略了
//    }
//
//    //send msg to MainActivity
//    private void processCustomMessage(Context context, Bundle bundle) {
//        //  ...//省略了
//    }
}
