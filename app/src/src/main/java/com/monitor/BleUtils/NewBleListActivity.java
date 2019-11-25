package com.monitor.BleUtils;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MeteorologicalAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.MeteorologicalEvent;
import com.monitor.changtian.bean.EventBus.NewBleEvent;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 3012设备BLE
 */
@EActivity(R.layout.activity_meteorological)
public class NewBleListActivity extends BaseActvity {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    MeteorologicalAdapter meteorologicalAdapter;

    @AfterViews
    void init() {
        initImageBackText("设备列表");
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        meteorologicalAdapter = new MeteorologicalAdapter(R.layout.item);
        rv_list.setAdapter(meteorologicalAdapter);
//        BleManager.getInstance().disconnectAllDevice();
//        openGPSSettings();
        initBla();
        checkPermissions();
        meteorologicalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().post(new NewBleEvent("蓝牙数据", meteorologicalAdapter.getData().get(position)));
                finish();
            }
        });
    }


    @Click(R.id.cf_imageButton)
    public void cf_imageButton() {
        ShowDialogtitle_cancel("正在扫描...", NewBleListActivity.this);
        startScan();
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        if (checkGPSIsOpen()) {
            initBla();
        } else {
            //没有打开则弹出对话框
            new AlertDialog.Builder(this)
                    .setTitle("打开定位功能")
                    .setMessage("请打开定位功能,否则无法正常使用蓝牙连接!")
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
                                }
                            })

                    .setPositiveButton(R.string.setting,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, 10);
                                }
                            })

                    .setCancelable(false)
                    .show();

        }
    }

    private static final int REQUEST_CODE_PERMISSION_LOCATION = 2;

    private void checkPermissions() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();  //打开蓝牙，需要BLUETOOTH_ADMIN权限
        }

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        List<String> permissionDeniedList = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted(permission);
            } else {
                permissionDeniedList.add(permission);
            }
        }
        if (!permissionDeniedList.isEmpty()) {
            String[] deniedPermissions = permissionDeniedList.toArray(new String[permissionDeniedList.size()]);
            ActivityCompat.requestPermissions(this, deniedPermissions, REQUEST_CODE_PERMISSION_LOCATION);
        }
    }


    private void onPermissionGranted(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkGPSIsOpen()) {
                    new AlertDialog.Builder(this)
                            .setTitle(R.string.notifyTitle)
                            .setMessage(R.string.gpsNotifyMsg)
                            .setNegativeButton(R.string.cancel,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                            .setPositiveButton(R.string.setting,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
                                        }
                                    })

                            .setCancelable(false)
                            .show();
                } else {
                    initS();
                    ShowDialogtitle_cancel("正在扫描...", NewBleListActivity.this);
                    startScan();
                }
                break;
        }
    }


    private static final int REQUEST_CODE_OPEN_GPS = 1;

    public void initBla() {
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setConnectOverTime(20000)
                .setOperateTimeout(5000);
//        initS();
    }

    public void initS() {
        String str_name = "";
        UUID[] serviceUuids = null;
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
//              .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
                .setDeviceName(true, str_name)   // 只扫描指定广播名的设备，可选
                .setAutoConnect(false)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(3000)              // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {
                ShowDialogtitle_cancel("正在扫描...", NewBleListActivity.this);
                initS();
                startScan();
            }
        }
    }


    private void startScan() {
        Log.d("lsls", "startScan");
        final List<BleDevice> ss = new ArrayList<>();
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
//                Log.d("lsls", success + "");
//                initS();
                Log.d("lsls", success + "");
                if(!success){
                    DissDialog_cancel();
                   msg("请打开蓝牙,再重试!");
                }
            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
                super.onLeScan(bleDevice);
                Log.d("lsls", "onLeScan");
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                ss.add(bleDevice);
                meteorologicalAdapter.setNewData(ss);
                meteorologicalAdapter.notifyDataSetChanged();
            }
            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                ViseLog.d("onScanFinished" + scanResultList.size());
                DissDialog_cancel();
                if (scanResultList != null && scanResultList.size() > 0) {
                    meteorologicalAdapter.setNewData(scanResultList);
                } else {
//                    msg("请确认设备暂无连接!");
//                    btn_online.setText("请重试");
                    AddEmptyView(meteorologicalAdapter, rv_list);
                    /**
                     * 添加空布局展示列表
                     */
                    setEmptylistener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ShowDialogtitle_cancel("正在扫描...", NewBleListActivity.this);
                            startScan();
                        }
                    });
                }
            }
        });
    }
}
