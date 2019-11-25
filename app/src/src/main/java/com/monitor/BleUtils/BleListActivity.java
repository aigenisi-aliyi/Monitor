package com.monitor.BleUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.BleListAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.BleEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EActivity(R.layout.activity_ble_list)
public class BleListActivity extends BaseActvity {
    private BluetoothAdapter blueToothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;

    String BleName = "AWA00315900";
    BleListAdapter bleListAdapter;
    private List<BluetoothDevice> deviceList = new ArrayList<BluetoothDevice>();
    BluetoothDevice onclikDev;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    boolean isOnlin = true;
    private BluetoothGattCallback bluetoothGattCallback;//蓝牙连接状态回调

    @AfterViews
    void init() {
        initImageBackText("蓝牙设备连接");
        // 获取蓝牙适配器
        blueToothAdapter = BluetoothAdapter.getDefaultAdapter();
        bleListAdapter = new BleListAdapter(R.layout.item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(bleListAdapter);
        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);//搜索发现设备
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);//状态改变
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);//行动扫描模式改变了
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//动作状态发生了变化
        registerReceiver(mReceiver, intent);
        initBleInfo();
        bleListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                if (bleListAdapter.getData().get(position).getBondState() == BluetoothDevice.BOND_NONE) {
                    ShowDialogtitle("请稍等...", BleListActivity.this);
//                    ShowDialog(MainActivity.this);
                    try {
                        Method method = BluetoothDevice.class.getMethod("createBond");
                        Log.e(getPackageName(), "开始配对");
                        method.invoke(bleListAdapter.getData().get(position));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    DissDialog();
                    //跳转系统蓝牙配对
//                    openSetting();

                }
                //表示配对成功
                if (bleListAdapter.getData().get(position).getBondState() == BluetoothDevice.BOND_BONDED) {
                    if (isOnlin) {

                        ShowDialogtitle("请稍等...", BleListActivity.this);
                        onclikDev = bleListAdapter.getData().get(position);
                        test(bleListAdapter.getData().get(position));
                    } else {
                        DissDialog();
                        msg("已经连接,无需重复连接!");
                    }

                }
            }
        });


    }

    BluetoothSocket socket = null;

    public void test(BluetoothDevice device) {

        try {
            // 蓝牙串口服务对应的UUID。如使用的是其它蓝牙服务，需更改下面的字符串
            UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            socket = device.createRfcommSocketToServiceRecord(MY_UUID);
        } catch (Exception e) {
            Log.d("log", "获取Socket失败");
            Toast.makeText(getApplicationContext(), "获取Socket失败", Toast.LENGTH_SHORT).show();
            return;
        }
        blueToothAdapter.cancelDiscovery();
        try {
            // Connect the device through the socket. This will block
            // until it succeeds or throws an exception
            socket.connect();
            DissDialog();
            isOnlin = false;
            Log.d("log", "连接成功");
            Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT).show();
            BluetoothUtils.setBluetoothSocket(socket);
            EventBus.getDefault().post(new BleEvent("关闭", socket));
            // 连接成功，返回主界面
            finish();
        } catch (IOException connectException) {
            DissDialog();
            // Unable to connect; close the socket and get out
            Log.d("log", "连接失败");
            Toast.makeText(getApplicationContext(), "连接失败\n请确定设备支持,并且重试", Toast.LENGTH_SHORT).show();
            try {
                socket.close();
            } catch (IOException closeException) {
            }
            return;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {

            Log.d("lsls", "11");
            ShowDialogtitle("请稍等...", this);
            if (blueToothAdapter == null) {
                Toast.makeText(getApplicationContext(), "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            }
            //请求开启蓝牙
            if (!blueToothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            //开始扫描
            startScan();

//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Log.d("lsls", "11");
//                    /**
//                     *要执行的操作
//                     */
//                    DissDialog();
//                    cancelScan();
//                }
//            }, 50000);//3秒后执行Runnable中的run方法

        }
    }

    /**
     * 开始扫描蓝牙设备
     */
    private void startScan() {
        if (blueToothAdapter != null) {
            blueToothAdapter.startDiscovery();
        }
    }

    /**
     * 取消扫描
     */
    private void cancelScan() {
        if (blueToothAdapter != null) {
            blueToothAdapter.cancelDiscovery();
        }
    }


    @Click(R.id.cf_imageButton)
    public void cf_imageButton() {
        startScan();
        initBleInfo();
    }

    public void initBleInfo() {


        if (blueToothAdapter.isDiscovering())
            blueToothAdapter.cancelDiscovery();
        blueToothAdapter.startDiscovery();
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        isOnlin = true;
        ShowDialogtitle("请稍等...", BleListActivity.this);
        if (blueToothAdapter == null) {
            msg("该设备不支持蓝牙");
        }

        if (blueToothAdapter == null || !blueToothAdapter.isEnabled()) {//是否开启蓝牙
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 11);
        } else {//获取已配对蓝牙
            Set<BluetoothDevice> devices = blueToothAdapter.getBondedDevices();
            deviceList = new ArrayList<BluetoothDevice>();
            for (final BluetoothDevice mDevice : devices) {
                deviceList.add(mDevice);
                bleListAdapter.setNewData(deviceList);
            }
            DissDialog();
            //跳转系统蓝牙配对
//            openSetting();
//        }
//
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Log.d("lsls", "11");
//                    /**
//                     *要执行的操作
//                     */
//                    DissDialog();
//                    cancelScan();
//                }
//            }, 50000);//3秒后执行Runnable中的run方法
        }


    }

    /**
     * 跳转到系统蓝牙界面
     */
    private void openSetting() {
        //跳转到系统蓝牙界面
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
        startActivityForResult(intent, 11);
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {

            Log.d("LSLS", intent.getAction());
            if (intent.getAction().equals(BluetoothDevice.ACTION_FOUND)) {
                DissDialog();
                Log.e(getPackageName(), "找到新设备了");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                Toast.makeText(getApplicationContext(), device.getName() + "\n" + device.getAddress(), Toast.LENGTH_SHORT).show();
//                deviceList = new ArrayList<>();
////                if(device.getName().equals("AWA00315900")){
//                deviceList.add(device);
//                bleListAdapter.setNewData(deviceList);

                Iterator<BluetoothDevice> iterator = deviceList.iterator();
                while (iterator.hasNext()) {
                    if (device.getAddress().equals(iterator.next().getAddress()))
                        iterator.remove();  //如果多次扫描到同一台设备，则移除之前的设备
                }
                deviceList.add(device);
                bleListAdapter.setNewData(deviceList);
//                blueToothAdapter.cancelDiscovery();//这句话是停止扫描蓝牙

            } else if (intent.getAction().equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
                DissDialog();
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_NONE:
                        Log.e(getPackageName(), "取消配对");
                        DissDialog();
                        break;
                    case BluetoothDevice.BOND_BONDING:
                        Log.e(getPackageName(), "配对中");
                        break;
                    case BluetoothDevice.BOND_BONDED:
                        Toast.makeText(getApplicationContext(), "配对成功,请点击搜索蓝牙", Toast.LENGTH_SHORT).show();
                        Log.e(getPackageName(), "配对成功");
                        DissDialog();
                        initBleInfo();
                        break;
                }
            } else {
                DissDialog();
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mReceiver);


    }
}
