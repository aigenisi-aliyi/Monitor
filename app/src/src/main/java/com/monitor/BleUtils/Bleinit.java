package com.monitor.BleUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.monitor.changtian.utils.LogAndToastUtil;

/**
 * Created by ken on 2018/9/20.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class Bleinit {

    private Context context;
    private BluetoothAdapter blueToothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    //判断蓝牙是否打开 初始化蓝牙信息
    public void initBle(Context context) {

        blueToothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);//搜索发现设备
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//状态改变
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//行动扫描模式改变了
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//动作状态发生了变化
        if (blueToothAdapter == null) {
            LogAndToastUtil.toast(context,"该设备不支持蓝牙");
        }
        //请求开启蓝牙
        if (!blueToothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        if (blueToothAdapter.isDiscovering())
            blueToothAdapter.cancelDiscovery();
        blueToothAdapter.startDiscovery();
    }
}
