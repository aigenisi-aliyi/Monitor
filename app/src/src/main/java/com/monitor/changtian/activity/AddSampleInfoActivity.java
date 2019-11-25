package com.monitor.changtian.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.allen.library.utils.ToastUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.weather.LocalWeatherForecastResult;
import com.amap.api.services.weather.LocalWeatherLive;
import com.amap.api.services.weather.LocalWeatherLiveResult;
import com.amap.api.services.weather.WeatherSearch;
import com.amap.api.services.weather.WeatherSearchQuery;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.clj.fastble.utils.HexUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.BleUtils.BleListActivity_;
import com.monitor.BleUtils.BluetoothUtils;
import com.monitor.BleUtils.ConnectedThread;
import com.monitor.BleUtils.MeteorologicalActivity_;
import com.monitor.BleUtils.NewBleListActivity_;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.Add_Sample_qixiang_valueAdapter;
import com.monitor.changtian.adapter.Add_Sample_valueAdapter;
import com.monitor.changtian.adapter.Add_sampAdapter;
import com.monitor.changtian.adapter.AnalysisAdapter;
import com.monitor.changtian.adapter.ConsumableAdapter;
import com.monitor.changtian.adapter.ConsumableListAdapter;
import com.monitor.changtian.adapter.DeviceAdapter;
import com.monitor.changtian.adapter.DeviceListAdapter;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AssociationAdapter;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipsBean;
import com.monitor.changtian.bean.EventBus.AddSampleNumEvent;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.EventBus.BleEvent;
import com.monitor.changtian.bean.EventBus.EquipsBeanEvent;
import com.monitor.changtian.bean.EventBus.MeteorologicalEvent;
import com.monitor.changtian.bean.EventBus.NewBleEvent;
import com.monitor.changtian.bean.EventBus.ParallelEvent;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.JumpEvent;
import com.monitor.changtian.bean.PreDataBean;
import com.monitor.changtian.bean.PreInfoDataBean;
import com.monitor.changtian.bean.ReagensBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.monitor.changtian.bean.SampleTypeBean;
import com.monitor.changtian.bean.ScenefactorsBean;
import com.monitor.changtian.bean.SchemeFidsBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.AddAccusePresenter;
import com.monitor.changtian.presenter.SampleCodePresenter;
import com.monitor.changtian.presenter.SampleTypePresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.utils.DeleteFileUtil;
import com.monitor.changtian.utils.StringUtils;
import com.monitor.changtian.view.AddAccuseView;
import com.monitor.changtian.view.SampleCodeView;
import com.monitor.changtian.view.SampleTypeView;
import com.monitor.changtian.view.TaskinfoView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.feezu.liuli.timeselector.TimeSelector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@EActivity(R.layout.add_sample_item)
public class AddSampleInfoActivity extends BaseActvity implements AddAccuseView, SampleCodeView, SampleTypeView, BaseActvity.Rightlistener, WeatherSearch.OnWeatherSearchListener, TaskinfoView, Add_Sample_valueAdapter.OnEditValueChangedListener, Add_Sample_qixiang_valueAdapter.OnEditValueChangedListener_qx, ConsumableAdapter.OnEditValueChangedListener_Con {


    /**
     * 计算距离最近的展示在顶部。
     * 高德地图计算两点之间的距离
     * <p>
     * 传入经纬度 返回以米为单位.
     * <p>
     * LatLng s=new LatLng();
     * float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);
     */


    @ViewById(R.id.ll_fenxi)
    LinearLayout ll_fenxi;
    @ViewById(R.id.ll_sample_ty)
    LinearLayout ll_sample_ty;
    @Extra
    String taskid;
    @Extra
    String POINTSIDS;
    @Extra
    String CURRENTNUMBER;
    @ViewById(R.id.rv_list_value)
    RecyclerView rv_list_value;
    Add_Sample_valueAdapter sample_valueAdapter;
    TaskinfoPresenter taskinfoPresenter;
    @ViewById(R.id.recycler_image)
    RecyclerView recycler_image;
    @ViewById(R.id.recycler_video)
    RecyclerView recycler_video;
    @ViewById(R.id.rv_list_consumable)
    RecyclerView rv_list_consumable;
    @ViewById(R.id.rv_list_dev)
    RecyclerView rv_list_dev;
    @ViewById(R.id.et_temp)
    EditText et_temp;
    // 天气
    @ViewById(R.id.et_status)
    TextView et_status;
    @ViewById(R.id.et_mix)
    TextView et_mix;
    @ViewById(R.id.et_max)
    TextView et_max;
    @ViewById(R.id.btn_red)
    Button btn_red;
    @ViewById(R.id.btn_look)
    Button btn_look;
    @ViewById(R.id.ll_sample_type)
    LinearLayout ll_sample_type;
    @ViewById(R.id.tv_caiyperson)
    TextView tv_caiyperson;
    @ViewById(R.id.btn_online)
    Button btn_online;
    @ViewById(R.id.ll_weather)
    LinearLayout ll_weather;
    @ViewById(R.id.tv_jiaoperson)
    TextView tv_jiaoperson;
    @ViewById(R.id.et_test)
    EditText et_test;
    //    @ViewById(R.id.rb_noz)
    //    RadioButton rb_noz;
    //    @ViewById(R.id.rb_z)
    //    RadioButton rb_z;
    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();
    GridImageAdapter adapter_image, adapter_video;
    private LocalWeatherLive weatherlive;
    private WeatherSearchQuery mquery;
    private WeatherSearch mweathersearch;
    AMapLocationClient mLocationClient = null;
    public AMapLocationClientOption mLocationOption = null;
    AnalysisAdapter analysisAdapter;
    ConsumableListAdapter consumableListAdapter;
    DeviceListAdapter deviceListAdapter;

    Add_sampAdapter add_sampAdapter;
    @ViewById(R.id.tv_analysis)
    TextView tv_analysis;
    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;
    AlertDialog device_dia;
    AlertDialog analysis_dia;
    AlertDialog consumable_dia;
    AlertDialog samp_dia;
    ConsumableAdapter consumableAdapter;
    DeviceAdapter deviceAdapter;
    private TimeSelector timeSelector_mix, timeSelector_max;
    @ViewById(R.id.tv_data)
    RadioGroup tv_data;
    String strzkid = "";
    @Extra
    String fsidss;

    @ViewById(R.id.tv_potion)
    TextView tv_potion;
    @ViewById(R.id.et_names)
    EditText et_names;

    @ViewById(R.id.btn_red_qixiang)
    Button btn_red_qixiang;


    @ViewById(R.id.tv_soilhumidity)
    TextView tv_soilhumidity;
    @ViewById(R.id.tv_soiltexture)
    TextView tv_soiltexture;
    @ViewById(R.id.et_tr_way)
    EditText et_tr_way; //采样方式输入
    @ViewById(R.id.et_soilsampletool)
    EditText et_soilsampletool; //采样方式工具
    @ViewById(R.id.et_soilvegetation)
    EditText et_soilvegetation; //耕作情况
    @ViewById(R.id.et_soilcolor)
    EditText et_soilcolor; //土壤颜色
    @ViewById(R.id.ll_tr)
    LinearLayout ll_tr;
    @ViewById(R.id.ll_sample_status)
    LinearLayout ll_sample_status;
    @ViewById(R.id.ll_blue_dev)
    LinearLayout ll_blue_dev;

    @ViewById(R.id.ll_parallel)
    LinearLayout ll_parallel;
    @ViewById(R.id.tv_parallel)
    TextView tv_parallel;
    @ViewById(R.id.iv_parallel)
    ImageView iv_parallel;

    SampleTypePresenter sampleTypePresenter;
    SampleCodePresenter sampleCodePresenter;

    @ViewById(R.id.tv_Identification)
    TextView tv_Identification;
    @ViewById(R.id.ll_sample_Identification)
    LinearLayout ll_sample_Identification;
    AddAccusePresenter addAccusePresenter;
    @ViewById(R.id.rv_list_qixiang)
    RecyclerView rv_list_qixiang;
    Add_Sample_qixiang_valueAdapter add_sample_qixiang_valueAdapter;

    @ViewById(R.id.tv_add_Association)
    TextView tv_add_Association;
    @ViewById(R.id.ll_Association)
    LinearLayout ll_Association;

    AssociationAdapter associationAdapter;
    @ViewById(R.id.rv_list_Association)
    RecyclerView rv_list_Association;
    BluetoothAdapter bluetoothAdapter;


    /**
     * 测试传统蓝牙连接
     */
    @ViewById(R.id.tv_lianjie)
    TextView tv_lianjie;

    @Click(R.id.tv_lianjie)
    public void tv_lianjie() {
        BleListActivity_.intent(this).start();
    }


    public LocationClient mLocClient = null;
    private MyLocationListener myListener = new MyLocationListener();


    @AfterViews
    void init() {

        weatherList.add("晴");
        weatherList.add("阴");
        weatherList.add("多云");
        weatherList.add("小雨");
        weatherList.add("中雨");
        weatherList.add("大雨");
        weatherList.add("暴雨");
        weatherList.add("雷电");
        weatherList.add("大风（风速大于5m/s）");


        initRightOnclikText("结果录入", "保存", this);
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        sampleCodePresenter = new SampleCodePresenter(this, this);
        sampleTypePresenter = new SampleTypePresenter(this, this);
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        addAccusePresenter = new AddAccusePresenter(this);
        ShowDialogtitle("加载中...", AddSampleInfoActivity.this);
        String data = "{taskid:\"" + taskid + "\"}";
        String data_pack = "{Id:\"\",typeCode:\"sampingPacking\",DataValue:\"\"}";
        taskinfoPresenter.OnPack(data_pack);
        String data_unit = "{Id:\"\",typeCode:\"unit\",DataValue:\"\"}";
        taskinfoPresenter.OnUnit(data_unit);
        String data_way = "{Id:\"\",typeCode:\"samplestyle\",DataValue:\"\"}";
        taskinfoPresenter.OnStyle(data_way);
        taskinfoPresenter.GetTasksInfomation(data);
        sample_valueAdapter = new Add_Sample_valueAdapter(R.layout.add_sample_value_item);
        add_sample_qixiang_valueAdapter = new Add_Sample_qixiang_valueAdapter(R.layout.add_sample_value_item);
        /**
         * 现场测定
         */
        rv_list_value.setLayoutManager(new LinearLayoutManager(this));
        rv_list_value.setAdapter(sample_valueAdapter);
        /**
         * 气象参数
         */

        //        RecyclerView的使用必须调用setLayoutManager来设置布局管理器
        rv_list_qixiang.setLayoutManager(new LinearLayoutManager(this));  //设置为线性布局管理器 垂直布局
        rv_list_qixiang.setAdapter(add_sample_qixiang_valueAdapter);
        add_sample_qixiang_valueAdapter.setmEditListenter(this);
        sample_valueAdapter.setmEditListenter(this);


        /**
         * 关联
         */
        associationAdapter = new AssociationAdapter(R.layout.association_item);
        rv_list_Association.setLayoutManager(new LinearLayoutManager(this));
        rv_list_Association.setAdapter(associationAdapter);
        associationAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, final int position) {


                AlertDialog.Builder builder = new AlertDialog.Builder(AddSampleInfoActivity.this);
                builder.setTitle("确定删除该关联样品吗？");
                builder.setMessage("      请仔细核对相关信息!");
                //    设置一个NegativeButton
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        associationAdapter.remove(position);
                        associationAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();

                return false;
            }
        });

        analysisAdapter = new AnalysisAdapter(R.layout.nopoint_select_sing);
        add_sampAdapter = new Add_sampAdapter(R.layout.nopoint_select_item);
        consumableListAdapter = new ConsumableListAdapter(R.layout.nopoint_select_item);
        consumableAdapter = new ConsumableAdapter(R.layout.add_sample_value_item);
        consumableAdapter.setmEditListenter(this);
        deviceListAdapter = new DeviceListAdapter(R.layout.nopoint_select_item);
        rv_list_consumable.setLayoutManager(new LinearLayoutManager(AddSampleInfoActivity.this));
        rv_list_consumable.setAdapter(consumableAdapter);
        deviceAdapter = new DeviceAdapter(this, R.layout.device_item);
        rv_list_dev.setLayoutManager(new LinearLayoutManager(AddSampleInfoActivity.this));
        rv_list_dev.setAdapter(deviceAdapter);
        deviceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ArrayList<TasksInfomationBean.SamplinguserBean> userInfoLists = new ArrayList<>();
                userInfoLists.addAll(samplinguserBeans_ALL);

                switch (view.getId()) {
                    //测量前
                    case R.id.stv_add_record:
                        CalibrationActivity_.intent(AddSampleInfoActivity.this).title("测前校准记录").pos(position).userInfoLists(userInfoLists).equipsBean(deviceAdapter.getData().get(position)).start();
                        break;
                    //测量后
                    case R.id.stv_add_record1:
                        CalibrationActivity_.intent(AddSampleInfoActivity.this).title("测后校准记录").pos(position).userInfoLists(userInfoLists).equipsBean(deviceAdapter.getData().get(position)).start();
                        break;
                    //查看记录信息
                    case R.id.ll_look:

                        if (equipsBeans.size() > 0) {
                            CalibrationInfoActivity_.intent(AddSampleInfoActivity.this).equipsBeans(equipsBeans).start();
                        } else {
                            msg("暂无记录,请添加记录!");
                        }

                        break;
                }


            }
        });
        /**
         * 定位地图
         */
        getCurrentLocationLatLng();
        /**
         * 初始化照片和视频
         */
        initaccessory();

        /**
         * 初始化时间选择
         */

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeSelector_mix = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                et_mix.setText(time);
                initPickData(time);
            }
        }, format.format(new Date()), "2300-01-01 00:00");
        timeSelector_mix.setIsLoop(false);
        timeSelector_mix.setMode(TimeSelector.MODE.YMDHM);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();  //打开蓝牙，需要BLUETOOTH_ADMIN权限
        }
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            atPointW = location.getLatitude();//获取纬度
            atPointJ = location.getLongitude();//获取经度
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        }
    }

    /**
     * 选择点位信息
     */

    @Click(R.id.tv_potion)
    public void tv_potion() {

//        /**
//         * 获取当前点位信息
//         */
//        final LatLng startL = new LatLng(atPointW, atPointJ);
//
//        //对分组出来数据进行排序
//        Collections.sort(pointsBeans, new Comparator<TasksInfomationBean.PointsBean>() {
//            @Override
//            public int compare(TasksInfomationBean.PointsBean o1, TasksInfomationBean.PointsBean o2) {
//
//                float date1 = AMapUtils.calculateLineDistance(startL, new LatLng(Double.parseDouble(o1.getLatitude()), Double.parseDouble(o1.getLatitude())));
//                float date2 = AMapUtils.calculateLineDistance(startL, new LatLng(Double.parseDouble(o2.getLatitude()), Double.parseDouble(o2.getLatitude())));
//                if (date1 < date2) {
//                    return 1;
//                }
//                return -1;
//            }
//       });


    }

    /**
     * 连接设备
     */
    @ViewById(R.id.btn_blue_connect)
    Button btn_blue_connect;

    @Click(R.id.btn_blue_connect)
    public void btn_blue_connect() {


        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
        } else {
            if (btn_blue_connect.getText().equals("已连接")) {
                TYPE = "3012";
                AgainDialog("确认断开当前设备吗?");
            } else {
//            BleListActivity_.intent(this).start();
                NewBleListActivity_.intent(this).start();
            }

        }


    }


    private ConnectedThread mConnectedThread;
    String Devtype = "";
    final byte[] buffers = new byte[999999];
    StringBuffer stringBuffersss = new StringBuffer();
    //保存BLE数据
    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

    /**
     * 保存噪声数据
     */
    ByteArrayOutputStream stream_Zao = new ByteArrayOutputStream();
    BluetoothSocket sockets = null;

    @Subscribe
    public void closeactivity(BleEvent event) {

        if (event != null) {
            sockets = event.getSocket();
            Devtype = "GB3012";
            btn_blue_connect.setText("已连接成功");
            btn_red.setVisibility(View.VISIBLE);
            btn_look.setVisibility(View.VISIBLE);
            //回到主界面后检查是否已成功连接蓝牙设备
            if (BluetoothUtils.getBluetoothSocket() == null || mConnectedThread != null) {
                Log.d("lll", "1111");
                return;
            }
            //已连接蓝牙设备，则接收数据，并显示到接收区文本框
            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {

                    super.handleMessage(msg);
                    switch (msg.what) {
                        case ConnectedThread.MESSAGE_READ:
                            stream_Zao.write((byte[]) msg.obj, 0, msg.arg1);
                            break;
                    }
                }
            };
            //启动蓝牙数据收发线程
            mConnectedThread = new ConnectedThread(BluetoothUtils.getBluetoothSocket(), handler);
            mConnectedThread.start();
        }
    }

    /**
     * 土壤湿度
     */
    @Click(R.id.tv_soilhumidity)
    public void tv_soilhumidity() {
        hintKbTwo();
        initpickview(basicslist_soilhumidity, tv_soilhumidity, "选择土壤湿度");
    }

    /**
     * 土壤质地
     */
    @Click(R.id.tv_soiltexture)
    public void tv_soiltexture() {

        hintKbTwo();
        initpickview(basicslist_soiltexture, tv_soiltexture, "选择土壤质地");
    }

    @ViewById(R.id.tv_way1)
    TextView tv_way1;
    List<TasksInfomationBean.PointsBean.ChargesBean> chargesBeanList = new ArrayList<>();

    @Click(R.id.tv_way1)
    public void tv_way1() {


        if (chargesBeanList.size() > 0) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String content = chargesBeanList.get(options1).getPickerViewText();
                    strzkid = chargesBeanList.get(options1).getQualitycontrol();
                    tv_way1.setText(content);

                    if (content.indexOf("平行") != -1) {
                        ll_parallel.setVisibility(View.VISIBLE);
                    } else {
                        ll_parallel.setVisibility(View.GONE);
                        tv_parallel.setText("");
                    }

                }
            })
                    .setTitleText("选择样品类型")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(true)// default is true
                    .build();
            pvOptions.setPicker(chargesBeanList);//一级选择器
            pvOptions.show();
        } else {
            msg("请先选择点位信息");
        }


    }

    /**
     * 选择包装
     */
    @ViewById(R.id.tv_pack)
    TextView tv_pack;

    List<DicDataBean> basicslist_pack = new ArrayList<>();
    List<DicDataBean> basicslist_unit = new ArrayList<>();
    List<DicDataBean> basicslist_way = new ArrayList<>();
    List<DicDataBean> basicslist_soilhumidity = new ArrayList<>();
    List<DicDataBean> basicslist_soiltexture = new ArrayList<>();

    @Click(R.id.tv_pack)
    public void tv_pack() {

        hintKbTwo();
        initpickview(basicslist_pack, tv_pack, "选择包装");
    }

    public void initpickview(final List<DicDataBean> dicDataBeans, final TextView view, final String title) {

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = dicDataBeans.get(options1).getPickerViewText();
                switch (title) {
                    case "选择包装":
                        sampingPacking = dicDataBeans.get(options1).getId() + "";
                        break;
                    case "选择单位":
                        samplingunit = dicDataBeans.get(options1).getId() + "";
                        break;
                    case "选择采样方式":
                        samplestyle = dicDataBeans.get(options1).getId() + "";
                        break;
                    case "选择土壤质地":
                        soiltexture = dicDataBeans.get(options1).getId() + "";
                        break;
                    case "选择土壤湿度":
                        soilhumidity = dicDataBeans.get(options1).getId() + "";
                        break;
                }

                view.setText(content);
            }
        })
                .setTitleText(title)
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(dicDataBeans);//一级选择器
        pvOptions.show();
    }

    /**
     * 连接气象仪器
     */
    private static final int REQUEST_CODE_OPEN_GPS = 1;
    private String TYPE = "";

    @Click(R.id.btn_online)
    public void btn_online() {


        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
        } else {

            if (btn_online.getText().toString().equals("已连接")) {
                TYPE = "QX";
                AgainDialog("确认断开当前设备吗?");
            } else {

                /**
                 * 跳转到气象仪蓝牙列表
                 */
//            BleManager.getInstance().disconnect(qxBle);
                MeteorologicalActivity_.intent(this).start();
            }
        }


    }

    public void AgainTrue() {
        if (TYPE.length() > 0) {
            /**
             * 关闭气象仪
             */
            if (TYPE.equals("QX")) {
                BleManager.getInstance().disconnect(bleDevicesss);
                btn_red_qixiang.setVisibility(View.GONE);
                btn_online.setText("连接");
            } else if (TYPE.equals("3012")) {
                /**
                 *关闭3012设备
                 */
                BleManager.getInstance().disconnect(bleDevice_3012);
                btn_blue_connect.setText("连接");
                btn_red.setVisibility(View.GONE);
                btn_look.setVisibility(View.GONE);
            }
        }
    }

    @Subscribe
    public void MeteorologicalEvents(MeteorologicalEvent event) {
        if (event != null) {
            qxBle = event.getBleDevice();
            ViseLog.d("MeteorologicalEvents:" + event.getBleDevice());
            ShowDialogtitle_cancel("正在连接...", AddSampleInfoActivity.this);
            connect("气象", event.getBleDevice());
        }
    }

    @Subscribe
    public void NewBleEvents(NewBleEvent event) {
        if (event != null) {
            ViseLog.d("NewBleEvents:" + event.getBleDevice());
            qxBle = event.getBleDevice();
            ShowDialogtitle_cancel("正在连接...", AddSampleInfoActivity.this);
            connect(typeid.equals("48") ? "awa6228+" : "3012", event.getBleDevice());
        }
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


    public void initBla() {
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setConnectOverTime(20000)
                .setOperateTimeout(5000);
//        ViseLog.d("lslslss", BleManager.getInstance().isSupportBle() + "");
        if (!BleManager.getInstance().isSupportBle()) {
            Toast.makeText(this, "关闭", Toast.LENGTH_SHORT).show();
        } else {
            //打开蓝牙
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_OPEN_GPS);
        }
        initS();
    }

    BleDevice qxBle = null;

    public void initS() {
        String str_name = "MTSeriBleBE";
        UUID[] serviceUuids = null;
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
                .setDeviceName(true, str_name)   // 只扫描指定广播名的设备，可选
                .setAutoConnect(true)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(3000)
                // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    private void startScan() {
        ShowDialogtitle_cancel("正在连接...", AddSampleInfoActivity.this);
        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                //                Log.d("lsls", success + "");
                //                initS();
                Log.d("lsls", success + "");
            }

            @Override
            public void onLeScan(BleDevice bleDevice) {
                super.onLeScan(bleDevice);
            }

            @Override
            public void onScanning(BleDevice bleDevice) {

            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                ViseLog.d("onScanFinished" + scanResultList.size());
                DissDialog_cancel();
                if (scanResultList != null && scanResultList.size() > 0) {

                    //connect(scanResultList.get(0));
                    qxBle = scanResultList.get(0);
                    btn_online.setText("已连接");
                    btn_red_qixiang.setVisibility(View.VISIBLE);
                } else {
                    //msg("请确认设备暂无连接!");
                    btn_online.setText("请重试");
                }
            }
        });
    }

    //00001000-0000-1000-8000-00805f9b34fb
//00001002-0000-1000-8000-00805f9b34fb
    @Click(R.id.btn_red_qixiang)
    public void btn_red_qixiang() {

        if (bleDevicesss != null) {
            /**
             *先断开
             */
            //            BleManager.getInstance().disconnect(qxBle);
            /**
             * 再次重连接
             */
            ShowDialogtitle_cancel("正在读取...", AddSampleInfoActivity.this);
            //            connect(qxBle);
            readss(bleDevicesss);


            //判断读取的文件中的 时间 是否符合噪声标准
            compareTime();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (byteArrayList.size() > 0) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < byteArrayList.size(); i++) {
                            stringBuffer.append(byteArrayList.get(i));
                        }
                        try {
                            initBlaRead(stringBuffer);

                        } catch (Exception e) {
                            msg("请重新读取");
                        }
                    }
                    DissDialog_cancel();
                }
            }, 3000);//5
        }

    }

//    //判断读取的文件中的 时间 是否符合噪声标准
//    void judge_VoiceTestTime() {
//
//        // 获取当前时间
//
//        //  获取文件中时间
//
//
//        //  比较时间
//    }

    //判断气象仪中的 风速 是否符合噪声标准

    String windSpeed = "";

    void judgeVoice_WindSpeed() {
        double aa = Double.parseDouble(windSpeed);
        if (aa > 5) {
            msg("气象仪中的风速不符合噪声监测标准！");
        }
        //  比较时间
    }


    public void readss(BleDevice bleDevice) {
        byteArrayList = new ArrayList<>();
        if (bleDevice != null) {

            BleManager.getInstance().write(
                    bleDevice,
                    "0000f1f0-0000-1000-8000-00805f9b34fb",
                    "0000f1f1-0000-1000-8000-00805f9b34fb",
                    HexUtil.hexStringToBytes("3A30313434303042420D0A"),
                    new BleWriteCallback() {
                        @Override
                        public void onWriteSuccess(int current, int total, byte[] justWrite) {
                            // 发送数据到设备成功（分包发送的情况下，可以通过方法中返回的参数可以查看发送进度）
                            Log.d("lslsls", "onWriteSuccess");
                        }

                        @Override
                        public void onWriteFailure(BleException exception) {
                            // 发送数据到设备失败
                            Log.d("lslsls", "onWriteFailure");
                        }
                    });

            BleManager.getInstance().notify(
                    bleDevice,
                    "0000f1f0-0000-1000-8000-00805f9b34fb",
                    "0000f1f2-0000-1000-8000-00805f9b34fb",
                    new BleNotifyCallback() {

                        @Override
                        public void onNotifySuccess() {
                            // 打开通知操作成功
                            Log.d("lsls", "onNotifySuccess");
//                                    DissDialog_cancel();
                        }

                        @Override
                        public void onNotifyFailure(BleException exception) {
                            // 打开通知操作失败
                            Log.d("lsls", exception.toString());
                        }

                        @Override
                        public void onCharacteristicChanged(byte[] data) {
                            // 打开通知后，设备发过来的数据将在这里出现

                            byteArrayList.add(new String(data));
                            for (int i = 0; i < data.length; i++) {
                                arrayList.add(data[i]);
                            }
                        }
                    });
        }
    }

    String lookSrt = "";
    String fileTime = "";

    String temp_factor = "";

    @Click(R.id.btn_red)
    public void btn_red() {

//        msg("byteStream.size:" + byteStream.size());
//        createFileWithByte(byteStream.toByteArray());


        if (stream_Zao.size() > 0) {

//            msg("stream_Zao.size:" + stream_Zao.size());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                }
            }, 3000);//3秒后执行Runnable中的run方法

        }


        if (byteStream.size() > 0) {
            if (isTrueDate == true) {

                ShowDialogtitle("请稍等...", AddSampleInfoActivity.this);
                Handler handler = new Handler();
//                msg("byteStream.size:" + byteStream.size());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        /**
                         * 读取文件存储的数据
                         */
                        try {
                            /**
                             *要执行的操作
                             */
                            DissDialog();
                            createFileWithByte(byteStream.toByteArray());
                            if (read(AddSampleInfoActivity.this, Environment.getExternalStorageDirectory().getPath() + "/3012File.txt").length() > 0) {
                                String aa = read(AddSampleInfoActivity.this, Environment.getExternalStorageDirectory().getPath() + "/3012File.txt");
                                if (aa.length() > 0) {
                                    lookSrt = aa;
                                    String fileTime = aa;
//                                    connect(typeid.equals("48") ? "awa6228+" : "3012", event.getBleDevice());

                                    if (typeid.equals("48")) {
                                        String[] sp = aa.split("Leq,T=");
                                        String SPL = sp[1].split("SEL")[0];
                                        String SPL_A = sp[1].split("dB")[0];
                                        sample_valueAdapter.getData().get(0).setValue(SPL_A);
                                        sample_valueAdapter.notifyDataSetChanged();
                                    }
                                    //  读取文件中的时间
                                    String[] time = fileTime.split("\n");
                                    String strTimeVoice = time[3].split(" ")[1];
                                    int hour_voice = Integer.parseInt(strTimeVoice.split(":")[0]);

                                    String strSystemTime = getTime();
                                    String system_time = strSystemTime.split(" ")[1];
                                    int system_hour = Integer.parseInt(system_time.split(":")[0]);

                                    //  从检测因子中 获取含有 昼 夜 字样

                                    for (int i = 0; i < sample_valueAdapter.getItemCount(); i++) {
                                        temp_factor = sample_valueAdapter.getData().get(i).getFactorname();
                                        int res = temp_factor.indexOf("昼");
                                        if (temp_factor.indexOf("昼")!=-1){
                                            if (system_hour < 22 && system_hour > 6) {
                                                if (hour_voice > 6 && hour_voice < 22) {
                                                } else {
                                                    ToastUtils.showToast("请在6-22点之间测量即时上传结果11111！" + system_hour);
                                                }
                                            }

                                        } else{
                                            if (system_hour > 22 || system_hour < 6) {
                                                if (hour_voice > 22 || hour_voice < 6) {
                                                } else {
                                                    ToastUtils.showToast("请在22-6点之间测量11111！" + system_time);
                                                }
                                            } else {
                                                ToastUtils.showToast("请在22-6点之间测量11111！" + system_time);
                                            }
                                        }
                                    }

                                    String ll = aa.substring(aa.indexOf("开始时间")).replaceAll("\u001B1\b\u001B8", "\n");
//                                    lookSrt = ll;
                                    /**
                                     *清空 byteStream,保证数据只读取一次。
                                     */
                                    byteStream = new ByteArrayOutputStream();
                                    try {
                                        if (sample_valueAdapter.getData().size() > 0) {
                                            for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {
                                                if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("含湿量") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "含 湿 量"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("大气压") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "大 气 压"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("计压") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "计    压"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("计温") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "计    温"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("跟踪率") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "跟 踪 率"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("含氧量") != -1) {
                                                    sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "含 氧 量"));
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("SO₂") != -1) {
                                                    if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("浓度") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "SO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001浓度"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("折算") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "SO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001折算"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("排放") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "SO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001排放"));
                                                    }
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("NO₂") != -1) {
                                                    if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("浓度") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001浓度"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("折算") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001折算"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("排放") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NO\u001B6\u001BW\u0002\u001Cr\u00012\u001B8\u001BW\u0001排放"));
                                                    }
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("CO") != -1) {
                                                    if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("浓度") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "CO 浓度"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("折算") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "CO 折算"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("排放") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "CO 排放"));
                                                    }
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("NOx") != -1) {
                                                    if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("浓度") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NOx浓度"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("折算") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NOx折算"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("排放") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "NOx排放"));
                                                    }
                                                } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("平均") != -1) {
                                                    if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("动压") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "平均动压"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("静压") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "平均静压"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("烟温") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "平均烟温"));
                                                    } else if (sample_valueAdapter.getData().get(i).getFactorname().indexOf("流速") != -1) {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, "平均流速"));
                                                    }
                                                } else {
                                                    try {
                                                        sample_valueAdapter.getData().get(i).setValue(Indexofss(ll, sample_valueAdapter.getData().get(i).getFactorname()));
                                                    } catch (Exception e) {
                                                        sample_valueAdapter.getData().get(i).setValue("");
                                                    }
                                                }
                                                sample_valueAdapter.notifyDataSetChanged();
                                            }
                                        }

                                    } catch (Exception e) {
                                    }
                                } else {
                                    msg("数据格式不正确!");
                                }
                            } else {
                                msg("设备数据文件不存在");
                            }
                        } catch (Exception e) {
//                            msg("该设备暂不支持");
                        }
                    }
                }, 3000);//3秒后执行Runnable中的run方法
            } else {
                DissDialog();
                msg("请稍等数据暂时未接收完毕!");
            }
        } else {
            DissDialog();
            msg("请确保数据已经发送!");
        }
    }

    /**
     * 查看数据文件
     */
    @Click(R.id.btn_look)
    public void btn_look() {


//        String aa = read(AddSampleInfoActivity.this, Environment.getExternalStorageDirectory().getPath() + "/2018-10-29.txt");
//        String ll = aa.substring(aa.indexOf("开始时间")).replaceAll("\u001B1\b\u001B8", "\n");
//        lookSrt = ll;

        if (lookSrt.length() > 0) {
            LookContentActivity_.intent(this).lookstr(lookSrt).start();
        } else {
            msg("请先读取数据,然后查看文件!");
        }
    }


    /**
     * 根据byte数组生成文件
     *
     * @param bytes 生成文件用到的byte数组
     */

    private void createFileWithByte(byte[] bytes) {
        // TODO Auto-generated method stub
        /**
         * 创建File对象，其中包含文件所在的目录以及文件的命名
         */
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/3012File.txt");
        // 创建FileOutputStream对象
        FileOutputStream outputStream = null;
        // 创建BufferedOutputStream对象
        BufferedOutputStream bufferedOutputStream = null;
        try {
            // 如果文件存在则删除
            if (file.exists()) {
                file.delete();
            }
            // 在文件系统中根据路径创建一个新的空文件
            file.createNewFile();
            // 获取FileOutputStream对象
            outputStream = new FileOutputStream(file);
            // 获取BufferedOutputStream对象
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            // 往文件所在的缓冲输出流中写byte数据
            bufferedOutputStream.write(bytes);
            // 刷出缓冲输出流，该步很关键，要是不执行flush()方法，那么文件的内容是空的。
            bufferedOutputStream.flush();
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
        } finally {
            // 关闭创建的流对象
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }


    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            default:
                code = "GBK";
        }

        return code;
    }


    public static void writeStr2Txt(String content, String
            path) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取文本文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String read(Context context, String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream in = new FileInputStream(file);

            return readInStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readInStream(InputStream inStream) {
        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, length);
            }
            outStream.close();
            inStream.close();
//            return  outStream.toString();
            return new String(outStream.toString("GBK"));
        } catch (IOException e) {
            Log.i("FileTest", e.getMessage());
        }
        return null;
    }


    @Click(R.id.tv_ceshi)
    public void tv_ceshi() {

    }

    BleDevice bleDevicesss = null;
    BleDevice bleDevice_3012 = null;
    ArrayList<String> byteArrayList = new ArrayList<>();
    ArrayList<Byte> arrayList = new ArrayList<>();
    Boolean isTrueDate = false;

    private void connect(final String type, final BleDevice bleDevice) {
        BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                Log.d("lslsls", "开始连接");
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {

                if (type.equals("气象")) {
                    btn_red_qixiang.setVisibility(View.GONE);
                    btn_red_qixiang.setText("连接失败");
                    msg("请检查设备是否开启!");
                } else {
                    btn_blue_connect.setText("连接失败");
                    btn_red.setVisibility(View.GONE);
                    btn_look.setVisibility(View.GONE);
                }
                DissDialog_cancel();
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                if (type.equals("气象")) {
                    btn_online.setText("已连接");
                    btn_red_qixiang.setVisibility(View.VISIBLE);
                    btn_red_qixiang.setText("读取数据");
                    bleDevicesss = bleDevice;
                }
                if (type.equals("awa6228+")) {
                    bleDevice_3012 = bleDevice;
                    BleManager.getInstance().notify(
                            bleDevice_3012,
                            "0000fff0-0000-1000-8000-00805f9b34fb",
                            "0000fff1-0000-1000-8000-00805f9b34fb",

                            new BleNotifyCallback() {
                                @Override
                                public void onNotifySuccess() {
                                    // 打开通知操作成功
                                    Log.d("lsls", "onNotifySuccess");
                                    btn_blue_connect.setText("已连接");
                                    btn_red.setVisibility(View.VISIBLE);
                                    btn_look.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onNotifyFailure(BleException exception) {
                                    // 打开通知操作失败
                                    Log.d("lsls", exception.toString());
                                }

                                @Override
                                public void onCharacteristicChanged(byte[] data) {
                                    // 打开通知后，设备发过来的数据将在这里出现
//                        byteArrayList.add(new String(data));
//                        for (int i = 0; i < data.length; i++) {
//                            arrayList.add(data[i]);
//                        }


                                    try {
                                        String res = new String(data, "UTF-8");
                                        isTrueDate = true;
//                                        }
                                        ViseLog.d(res);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        byteStream.write(data);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    // convert output stream to string
                                    String str = byteStream.toString();
                                    int size = byteStream.size();

                                    // print
                                    System.out.print(size + ":");
                                    System.out.println(str);
                                    System.out.println(data[data.length - 1]);
//                                    ViseLog.d("ssssss"+new String(data));

                                }
                            });
                } else {
                    bleDevice_3012 = bleDevice;
                    BleManager.getInstance().notify(
                            bleDevice_3012,
                            "00001000-0000-1000-8000-00805f9b34fb",
                            "00001002-0000-1000-8000-00805f9b34fb",

                            new BleNotifyCallback() {
                                @Override
                                public void onNotifySuccess() {
                                    // 打开通知操作成功
                                    Log.d("lsls", "onNotifySuccess");
                                    btn_blue_connect.setText("已连接");
                                    btn_red.setVisibility(View.VISIBLE);
                                    btn_look.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onNotifyFailure(BleException exception) {
                                    // 打开通知操作失败
                                    Log.d("lsls", exception.toString());
                                }

                                @Override
                                public void onCharacteristicChanged(byte[] data) {
                                    // 打开通知后，设备发过来的数据将在这里出现
//                        byteArrayList.add(new String(data));
//                        for (int i = 0; i < data.length; i++) {
//                            arrayList.add(data[i]);
//                        }


                                    try {
                                        String res = new String(data, "GBK");
                                        if (res.indexOf("结束") != -1) {
                                            isTrueDate = true;
                                        }
                                        ViseLog.d(res);
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        byteStream.write(data);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
//                                    ViseLog.d("ssssss"+new String(data));
                                }
                            });
                }
                DissDialog_cancel();
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {

                if (isActiveDisConnected) {
                    ViseLog.d("断开了");
                    gatt.close();
                    gatt.disconnect();
                    if (TYPE.equals("QX")) {
                        BleManager.getInstance().disconnect(bleDevicesss);
                    } else if (TYPE.equals("3012")) {
                        /**
                         *关闭3012设备
                         */
                        BleManager.getInstance().disconnect(bleDevice_3012);
                    }
                    //                    BleManager.getInstance().c
                    //                    Toast.makeText(MainActivity.this, getString(R.string.active_disconnected), Toast.LENGTH_LONG).show();
                } else {
                    ViseLog.d("没断开");
                }


            }


        });


    }


    /**
     * 获取系统当前时间
     */
    private String getTime() {
        Date d1 = new Date();
        return d1.toLocaleString();
    }

    /**
     * 比较系统当前时间 和 文件中 时间
     */

    String file = "\n" +
            "\u001B1 \u001BK? \u0010\u001F\f\u0003 \u0010\u001F\u0010  \u0001\u0001\u0001\u0001  \u0001\u0001\u0001\u0001\u0001\u0001\u0001   \u0001\u0001\u0001\u0001     \u0003\u0003   \u0010\u001F\u0010\u0010\u0010\b\u0007   \u0003\u001C\u0007   \u0018\u0010\u0010\u001F\u0010\u0010\u0018   \u0003\u001C\u0007            \u0007\b\u0010\u0010\b\u0007  \u0007\b\u0010\u0010\b\u0007  \u0007\b\u0010\u0010\b\u0007  \b\b\u001F                                                                                                                                    \u001BK? \u0004? ??  ?DDD?\u0004? ? ? 鳧DDD?    \f\f   \u0004?\u0004\u0004\b? \u0004<腀@?\u0004  \u0004?   \u0004<腀@?\u0004\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001 ?\u0004\u0004\b?  ?\u0004\u0004\b?  ?\u0004\u0004\b?  \u0004\u0004?\u0004                                                                                                                                  \n" +
            "\u001B1\u0003\u001B1\u0003               \n" +
            "2019-07-15 15:59:00\n" +
            "Stat.-One    \n" +
            "R: 20dB~132dB Ts=00h01m00s\n" +
            "Statistics: A F\n" +
            "Leq,T= 67.3dB SEL  = 85.1dB\n" +
            "Lmax = 79.2dB Lmin = 47.5dB\n" +
            "L 5  = 73.6dB L10  = 72.2dB\n" +
            "L50  = 62.4dB L90  = 50.4dB\n" +
            "L95  = 49.0dB SD   =  7.6dB";

    private void compareTime() {
        if (categoryid.equals("48")) {
            /**
             **读取出来噪声文件中 时 分 秒
             */
            String[] time = file.split("\n");
            String strTimeVoice = time[3].split(" ")[1];
            int hour_voice = Integer.parseInt(strTimeVoice.split(":")[0]);
//            int hour_voice = Integer.parseInt(time_voice.split(":")[0]);
//            int minite_voice = Integer.parseInt(time_voice.split(":")[1]);
//            int second_voice = Integer.parseInt(time_voice.split(":")[2]);

            //  取出系统时间的 时 分 秒

        }
    }


    /**
     * 选择单位
     */
    @ViewById(R.id.tv_unit)
    TextView tv_unit;

    @Click(R.id.tv_unit)
    public void tv_unit() {
        hintKbTwo();
        initpickview(basicslist_unit, tv_unit, "选择单位");
    }

    /**
     * 天气选项数组
     */

    private ArrayList<String> weatherList = new ArrayList<>();


    /**
     * 选择采样人员
     */
    @Click(R.id.tv_caiyperson)
    public void tv_caiyperson() {

        hintKbTwo();

        final AlertDialog.Builder builder = new AlertDialog.Builder(AddSampleInfoActivity.this);
        View view = LayoutInflater.from(AddSampleInfoActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        samp_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        Button btn_submit = view.findViewById(R.id.btn_submit);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("选择采样人员");
        rv_list.setLayoutManager(new GridLayoutManager(AddSampleInfoActivity.this, 4));
        rv_list.setAdapter(add_sampAdapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                final StringBuffer stringBufferid = new StringBuffer();
                for (int i = 0; i < add_sampAdapter.getData().size(); i++) {
                    if (add_sampAdapter.getData().get(i).isChoice()) {
                        add_sampAdapter.getData().get(i).setChoice_save(true);
                        stringBufferid.append(add_sampAdapter.getData().get(i).getUserid() + ",");
                        stringBuffernames.append(add_sampAdapter.getData().get(i).getUsername() + ",");
                    } else {
                        stringBuffernames.append("");
                        stringBufferid.append("");
                        add_sampAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                    tv_caiyperson.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                    sampinguser = stringBufferid.toString().substring(0, stringBufferid.toString().length() - 1);
                } else {
                    tv_caiyperson.setText("");
                    sampinguser = "";
                }
                samp_dia.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                samp_dia.dismiss();
            }
        });


    }

    List<TasksInfomationBean.SamplinguserBean> samplinguserBeans = new ArrayList<>();
    List<TasksInfomationBean.SamplinguserBean> samplinguserBeans_ALL = new ArrayList<>();
    String proofreader = "";

    /**
     * 选择天气by：aigenisi
     */

    String categoryid;
    int index;

    @Click(R.id.et_status)
    public void et_status() {

        hintKbTwo();

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddSampleInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = weatherList.get(options1);
                index = options1;
                et_status.setText(content);
                judge_weather();
            }
        })
                .setTitleText("选择天气")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(weatherList);//一级选择器
        pvOptions.show();


    }

    //判断天气情况是否符合噪声现场测定
    void judge_weather() {
        if (index > 2) {
            //通过点位再判断该点位为噪声点位
            if (categoryid.equals("48")) {
                //   弹出消息 该点位为噪声点位 “测定条件不符合标准规定！”
                msg("测定气象条件不符合噪声标准规定！");
            }
        }
    }


    @Click(R.id.tv_jiaoperson)
    public void tv_jiaoperson() {

        hintKbTwo();
        if (samplinguserBeans.size() > 0) {
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(AddSampleInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String content = samplinguserBeans.get(options1).getPickerViewText();
                    proofreader = samplinguserBeans.get(options1).getUserid() + "";
                    tv_jiaoperson.setText(content);
                }
            })
                    .setTitleText("选择校对人")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(true)// default is true
                    .build();
            pvOptions.setPicker(samplinguserBeans);//一级选择器
            pvOptions.show();
        } else {
            msg("暂无校对人!");
        }

    }

    /**
     * 选择采样方式
     */
    @ViewById(R.id.tv_way)
    TextView tv_way;

    @Click(R.id.tv_way)
    public void tv_way() {
        hintKbTwo();
        initpickview(basicslist_way, tv_way, "选择采样方式");
    }

    /**
     * 开始时间选择
     */
    @Click(R.id.et_mix)
    public void et_mix() {
        timeSelector_mix.show();
    }

    public void initPickData(String datatime) {

        timeSelector_max = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                et_max.setText(time);
            }
        }, datatime, "2300-01-01 00:00");
        timeSelector_max.setIsLoop(false);
        timeSelector_max.setMode(TimeSelector.MODE.YMDHM);
    }

    /**
     * 结束时间选择
     */
    @Click(R.id.et_max)
    public void et_max() {

        if (et_mix.getText().toString().trim() != null && et_mix.getText().length() > 0) {

            timeSelector_max.show();
        } else {
            msg("请先选择开始时间");
        }

    }


    /**
     * 初始化照片和视频
     */
    public void initaccessory() {
        FullyGridLayoutManager manager_image = new FullyGridLayoutManager(AddSampleInfoActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recycler_image.setLayoutManager(manager_image);
        adapter_image = new GridImageAdapter(AddSampleInfoActivity.this, onAddPicClickListener, "image");
        adapter_image.setList(selectList_image);
        adapter_image.setSelectMax(9);
        recycler_image.setAdapter(adapter_image);
        adapter_image.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_image.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                // 预览图片
                PictureSelector.create(AddSampleInfoActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
            }
        });

        FullyGridLayoutManager manager_video = new FullyGridLayoutManager(AddSampleInfoActivity.this, 4, GridLayoutManager.VERTICAL, false);
        recycler_video.setLayoutManager(manager_video);
        adapter_video = new GridImageAdapter(AddSampleInfoActivity.this, onAddPicClickListener, "video");
        adapter_video.setList(selectList_video);
        adapter_video.setSelectMax(9);
        recycler_video.setAdapter(adapter_video);
        adapter_video.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_video.get(position);
                String pictureType = media.getPictureType();
                PictureSelector.create(AddSampleInfoActivity.this).externalPictureVideo(media.getPath());
            }
        });

    }

    /**
     * 选择分析项目
     */
    String SampleTYPE = "";

    @Click(R.id.tv_analysis)
    public void tv_analysis() {

        if (analysisAdapter.getData().size() > 0) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(AddSampleInfoActivity.this);
            View view = LayoutInflater.from(AddSampleInfoActivity.this).inflate(R.layout.analysis_item, null);
            builder.setView(view);
            analysis_dia = builder.show();
            RecyclerView rv_list = view.findViewById(R.id.rv_list);
            Button btn_submit = view.findViewById(R.id.btn_submit);
            rv_list.setLayoutManager(new GridLayoutManager(AddSampleInfoActivity.this, 1));
            rv_list.setAdapter(analysisAdapter);
            analysisAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    analysisAdapter.setSelection(position);
                }
            });

            btn_submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final StringBuffer stringBuffernames = new StringBuffer();
                    final StringBuffer stringBufferids = new StringBuffer();

                    for (int i = 0; i < analysisAdapter.getData().size(); i++) {
                        if (analysisAdapter.getData().get(i).isSelected() == true) {

                            sampletype = analysisAdapter.getData().get(i).getId();
                            SampleTYPE = analysisAdapter.getData().get(i).getName();

                            for (int a = 0; a < analysisAdapter.getData().get(i).getFactors().size(); a++) {
                                stringBuffernames.append(analysisAdapter.getData().get(i).getFactors().get(a).getFactorname() + ",");
                                stringBufferids.append(analysisAdapter.getData().get(i).getFactors().get(a).getFid() + ",");
                            }
                        } else {
                            stringBuffernames.append("");
                            stringBufferids.append("");
                        }
                    }
                    if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                        tv_analysis.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
                        analysisitems = stringBufferids.toString().substring(0, stringBufferids.toString().length() - 1);
                    } else {
                        tv_analysis.setText("");
                        analysisitems = "";
                    }
                    if (SampleTYPE.indexOf("平行") != -1) {
                        ll_parallel.setVisibility(View.VISIBLE);
                    } else {
                        ll_parallel.setVisibility(View.GONE);
                    }
                    analysis_dia.dismiss();

                }
            });

            Button btn_false = view.findViewById(R.id.btn_false);
            btn_false.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    analysis_dia.dismiss();
                }
            });

        } else {
            msg("请先选择点位信息");
        }


    }

    private List<LocalMedia> selectList_All = new ArrayList<>();

    /**
     * 点击二维码扫描
     */
    @Click(R.id.iv_bar_code)
    public void iv_bar_code() {
//        startActivity(new Intent(AddSampleInfoActivity.this, CaptureActivity.class));


        ZBarActivity_.intent(this).start();
    }

    String codea = "";

    @Subscribe
    public void BarCodeEv(BarCodeEvent event) {
        if (event != null) {
//            ShowDialogtitle("请稍等...", AddSampleInfoActivity.this);
//            codea = "tv_bar_code";
//            String data = "{samplecode:\"" + event.getCodeNum() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
//            sampleCodePresenter.Getsamplecode(data);
            tv_bar_code.setText(event.getCodeNum());
        }
    }

    /**
     * 平行杨扫描
     */
    @Click(R.id.iv_parallel)
    public void iv_parallel() {
//        startActivity(new Intent(AddSampleInfoActivity.this, CaptureActivity.class).putExtra("type", "平行"));
        ZBarActivity_.intent(this).type("平行").start();

    }


    String onlynumberPar = "";

    List<String> CodeList = new ArrayList<>();

    @Subscribe
    public void ParallelEv(ParallelEvent event) {
        if (event != null) {


            switch (event.getType()) {
                case "平行":
                    codea = "tv_parallel";
                    tv_parallel.setText(event.getCodeNum());
                    break;
                case "关联":
                    codea = "关联";
                    if (CodeList.size() > 0) {
                        for (int i = 0; i < CodeList.size(); i++) {
                            if (event.getCodeNum().equals(CodeList.get(i))) {
                                msg("样品编码重复");
                                isadd = true;
                                ViseLog.d("111");
                                break;
                            } else {
                                isadd = false;
                            }

                        }

                        if (!isadd) {
                            CodeList.add(event.getCodeNum());
                            associationAdapter.setNewData(CodeList);
                            associationAdapter.notifyDataSetChanged();
                        }
                    } else {

                        if (event.getCodeNum().equals(tv_bar_code.getText().toString())) {
                            msg("样品编码重复");
                        } else {
                            CodeList.add(event.getCodeNum());
                            associationAdapter.setNewData(CodeList);
                            associationAdapter.notifyDataSetChanged();
                        }

                    }
                    break;
            }

//            ShowDialogtitle("请稍等...", AddSampleInfoActivity.this);
//            String data = "{samplecode:\"" + event.getCodeNum() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
//            sampleCodePresenter.Getsamplecode(data);


//            if (resultBean.getResult() != null) {
//                if (codea.equals("tv_bar_code")) {
//                    tv_bar_code.setText(resultBean.getResult());
//                } else if (codea.equals("tv_parallel")) {
//
//                    tv_parallel.setText(resultBean.getResult());
////                onlynumberPar = resultBean.getResult();
////                String data = "{samplecode:\"" + resultBean.getResult() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
////                sampleTypePresenter.GetsampleInfoBysamplecode(data);
//                } else if (codea.equals("关联")) {
//
            //
//                }
//            } else
//
//            {
//                msg(resultBean.getErrormessage());
//            }


        }
    }

    @Subscribe
    public void AddSampleNumEv(AddSampleNumEvent event) {
        if (event != null) {

            tv_parallel.setText(event.getCodeNum());

//            codea = "tv_parallel";
//            String data = "{samplecode:\"" + event.getCodeNum() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
//            sampleCodePresenter.Getsamplecode(data);
        }
    }


    /**
     * 设备
     */
    @Click(R.id.tv_dev)
    public void tv_dev() {

        hintKbTwo();
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddSampleInfoActivity.this);
        View view = LayoutInflater.from(AddSampleInfoActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        device_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("选择设备");
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddSampleInfoActivity.this, 2));
        rv_list.setAdapter(deviceListAdapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                List<TasksInfomationBean.EquipsBean> equipsBeans = new ArrayList<>();
                for (int i = 0; i < deviceListAdapter.getData().size(); i++) {
                    if (deviceListAdapter.getData().get(i).isChoice()) {
                        deviceListAdapter.getData().get(i).setChoice_save(true);
                        equipsBeans.add(deviceListAdapter.getData().get(i));
                    } else {
                        stringBuffernames.append("");
                        deviceListAdapter.getData().get(i).setChoice_save(false);
                    }
                }

                deviceAdapter.setType(typeid);
                deviceAdapter.setNewData(equipsBeans);
                device_dia.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                device_dia.dismiss();
            }
        });


    }

    /**
     * 耗材
     */
    @Click(R.id.tv_consumable)
    public void tv_consumable() {
        hintKbTwo();
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddSampleInfoActivity.this);
        View view = LayoutInflater.from(AddSampleInfoActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(view);
        consumable_dia = builder.show();
        RecyclerView rv_list = view.findViewById(R.id.rv_list);
        TextView tv_title = view.findViewById(R.id.tv_title);
        tv_title.setText("选择耗材");
        Button btn_submit = view.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(AddSampleInfoActivity.this, 4));
        rv_list.setAdapter(consumableListAdapter);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuffer stringBuffernames = new StringBuffer();
                List<TasksInfomationBean.ConsumablesBean> consumablesBeans = new ArrayList<>();
                for (int i = 0; i < consumableListAdapter.getData().size(); i++) {
                    if (consumableListAdapter.getData().get(i).isChoice()) {
                        consumableListAdapter.getData().get(i).setChoice_save(true);
                        stringBuffernames.append(consumableListAdapter.getData().get(i).getNames() + ",");
                        consumablesBeans.add(consumableListAdapter.getData().get(i));
                    } else {
                        consumableListAdapter.getData().get(i).setChoice_save(false);
                    }
                }
                consumableAdapter.setNewData(consumablesBeans);
                consumable_dia.dismiss();
            }
        });

        Button btn_false = view.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumable_dia.dismiss();
            }
        });

    }

    /**
     * 关联样品
     */
    @Click(R.id.tv_add_Association)
    public void tv_add_Association() {

        if (tv_bar_code.length() > 0) {
//            startActivity(new Intent(AddSampleInfoActivity.this, CaptureActivity.class).putExtra("type", "关联"));
            ZBarActivity_.intent(this).type("关联").start();
        } else {
            msg("请先扫描原码!");
        }


    }

    String onlynumber = "";
    String sampingstatus = "";
    String sampingPacking = ""; //包装ID
    String starttime = "";
    String endtime = "";
    String sampinguser = MyApplication.getInstance().getUserCode();// 采样人
    String samplingamount = "";//采样量
    String samplingunit = "";//采样单位
    String samplestyle = ""; //采样方式
    String sampletype = "";//采样类型
    String weathercondition = ""; //天气
    String temperature = "";//气温
    String analysisitems = "";//分析项目
    String remark = "";//备注
    String scenefactors = "";//现场出值
    String reagens = "";//试剂
    String equips = "";
    String fsid = "";

    String sampingname = ""; //样品名称
    String pretestvalue = "";//噪声前
    String posttestvalue = "";//噪声后
    String pointsid = "";//点位ID

    String soiltexture = "";
    String soilhumidity = "";
    String soilsampletool = "";// 工具
    String soilvegetation = ""; //耕作情况
    String soilcolor = "";//颜色

    String parallelsample = ""; //平行样编码
    @ViewById(R.id.et_statuss)
    EditText et_statuss;
    @ViewById(R.id.et_sampleValue)
    EditText et_sampleValue;

    boolean issubmit_x = true;
    boolean issubmit_qx = true;
    boolean issubmit_shij = true;

    String relationIdentification = "";

    String currentnumber = "";
    String longitude = "";
    String latitude = "";


    String File_QX = "";
    String File_3012 = "";
    String File_Voice = "";
    List<File> imageFile1 = new ArrayList<>();
    List<File> imageFile_video = new ArrayList<>();

    /**
     * 提交按钮
     */
    @Override
    public void rightlistener() {

//        et_tr_way

        /**
         * 传附件
         */
        imageFile1 = new ArrayList<>();
//        selectList_All = new ArrayList<>();
        selectList_All.addAll(selectList_image);
        for (int i = 0; i < selectList_All.size(); i++) {
            if (!StringUtils.isUrl(selectList_All.get(i).getPath())) {
                imageFile1.add(new File(selectList_All.get(i).getPath()));
            }
        }

        for (int i = 0; i < selectList_video.size(); i++) {
            if (!StringUtils.isUrl(selectList_video.get(i).getPath())) {
                imageFile_video.add(new File(selectList_video.get(i).getPath()));
            }
        }


        StringBuffer stringBuffer_Gl = new StringBuffer();
        for (int i = 0; i < associationAdapter.getData().size(); i++) {
            stringBuffer_Gl.append(associationAdapter.getData().get(i) + ",");
        }

        if (stringBuffer_Gl.toString().length() > 0) {
            relationIdentification = stringBuffer_Gl.toString().substring(0, stringBuffer_Gl.toString().length() - 1);
        } else {
            relationIdentification = "";
        }


        /**
         * 传气象文件
         */
        if (DeleteFileUtil.checkFileExists(Environment.getExternalStorageDirectory().getPath() + "/QxFile.txt")) {

            File_QX = Environment.getExternalStorageDirectory().getPath() + "/QxFile.txt";
        } else {
            File_QX = "";
        }


        /**
         * 传3012文件
         */
        if (DeleteFileUtil.checkFileExists(Environment.getExternalStorageDirectory().getPath() + "/3012File.txt")) {

            File_3012 = Environment.getExternalStorageDirectory().getPath() + "/3012File.txt";
        } else {
            File_3012 = "";
        }

        /**
         * 传噪声
         */
        if (DeleteFileUtil.checkFileExists(Environment.getExternalStorageDirectory().getPath() + "/VoiceFile.txt")) {

            File_Voice = Environment.getExternalStorageDirectory().getPath() + "/VoiceFile.txt";
        } else {
            File_Voice = "";
        }

        if (et_mix.getText().toString().trim().length() == 0) {
            msg("请选择开始时间!");
            return;
        }
        if (et_max.getText().toString().trim().length() == 0) {
            msg("请选择结束时间!");
            return;
        }

        if (tv_jiaoperson.getText().toString().trim().length() == 0) {
            msg("请选择校对人");
            return;
        }

        weathercondition = et_status.getText().toString().trim();
        temperature = et_temp.getText().toString().trim();
        if (submit_status.equals("1")) {

            if (tv_bar_code.getText().toString().trim().length() == 0) {
                msg("条码信息不能为空!");
                return;
            }

            if (et_names.getText().toString().trim().length() == 0) {
                msg("样品名称不能为空!");
                return;
            } else {
                sampingname = et_names.getText().toString().trim();
            }
//            if (tv_way1.getText().toString().trim().length() == 0) {
//                msg("样品类型不能为空!");
//                return;
//            }
            if (et_sampleValue.getText().toString().trim().length() == 0) {
                msg("采样量不能为空!");
                return;
            } else {
                samplingamount = et_sampleValue.getText().toString().trim();
            }


            if (ll_sample_ty.getVisibility() == View.GONE) {

                if (et_tr_way.getText().toString().trim() == null) {
                    msg("采样方式不能为空");
                    return;
                } else {
                    samplestyle = et_tr_way.getText().toString().trim();
                }
            } else if (ll_sample_ty.getVisibility() == View.VISIBLE) {
                if (tv_way.getText().toString().trim().length() == 0) {
                    msg("请选择采样方式!");
                    return;
                }
            }


            if (ll_parallel.getVisibility() == View.VISIBLE) {
                if (tv_parallel.getText().toString().trim().length() == 0) {
                    msg("请关联样品");
                    return;
                } else {
                    //平行样关联编码
                    parallelsample = tv_parallel.getText().toString().trim();
                }
            } else {
                parallelsample = "";
            }


//          et_soilsampletool; //采样方式工具
//          et_soilvegetation; //耕作情况
//          et_soilcolor; //土壤颜色


            if (fsidss != null) {
                fsid = fsidss;
            } else {
                fsid = "";
            }

            onlynumber = tv_bar_code.getText().toString().trim();
            sampingstatus = et_statuss.getText().toString().trim();
            starttime = et_mix.getText().toString().trim();
            endtime = et_max.getText().toString().trim();
            soilsampletool = et_soilsampletool.getText().toString().trim();
            soilvegetation = et_soilvegetation.getText().toString().trim();
            soilcolor = et_soilcolor.getText().toString().trim();


            longitude = atPointJ + "";
            latitude = atPointW + "";
            currentnumber = CURRENTNUMBER;


//            if (tv_way1.getText().equals("正常")) {
//                sampletype = "0";
//            } else {
//                sampletype = strzkid;
//            }

            /**
             * 获取现场出值
             */
            List<ScenefactorsBean> scenefactorsBeanList = new ArrayList<>();
            for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {
                ScenefactorsBean scenefactorsBean = new ScenefactorsBean();
                scenefactorsBean.setFid(sample_valueAdapter.getData().get(i).getFid());
                scenefactorsBean.setMeasuredvalue(sample_valueAdapter.getData().get(i).getValue());
                scenefactorsBean.setFtmid(sample_valueAdapter.getData().get(i).getAnalysisMethod());
                scenefactorsBeanList.add(scenefactorsBean);
            }

            for (int i = 0; i < add_sample_qixiang_valueAdapter.getData().size(); i++) {
                ScenefactorsBean scenefactorsBean = new ScenefactorsBean();
                scenefactorsBean.setFid(add_sample_qixiang_valueAdapter.getData().get(i).getFid());
                scenefactorsBean.setMeasuredvalue(add_sample_qixiang_valueAdapter.getData().get(i).getValue());
                scenefactorsBean.setFtmid(add_sample_qixiang_valueAdapter.getData().get(i).getAnalysisMethod());
                scenefactorsBeanList.add(scenefactorsBean);
            }


            /**
             * 判断现场检测是否有项目  没有 就不判断提交
             */
            if (sample_valueAdapter.getData().size() > 0) {
                for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {

                    if (sample_valueAdapter.getData().get(i).getValue() == null) {
                        issubmit_x = false;
                        break;
                    } else {
                        issubmit_x = true;
                    }
                }
                if (!issubmit_x) {
                    msg("请确保现场测定数据完整!");
                    return;
                }
            }

            for (int i = 0; i < add_sample_qixiang_valueAdapter.getData().size(); i++) {

                if (add_sample_qixiang_valueAdapter.getData().get(i).getValue() == null) {
                    issubmit_qx = false;
                    break;
                } else {
                    issubmit_qx = true;
                }
            }
            if (!issubmit_qx) {
                msg("请确保气象数据完整!");
                return;
            }


            scenefactors = JSON.toJSONString(scenefactorsBeanList);
            /**
             * 获取试剂的值
             */
            List<ReagensBean> reagensBeanList = new ArrayList<>();
            for (int i = 0; i < consumableAdapter.getData().size(); i++) {
                ReagensBean reagensBean = new ReagensBean();
                reagensBean.setReagentid(consumableAdapter.getData().get(i).getId());
                reagensBean.setDosage(consumableAdapter.getData().get(i).getValue());
                reagensBean.setRemark("");
                reagensBeanList.add(reagensBean);
            }


            if (consumableAdapter.getData().size() > 0) {

                for (int i = 0; i < consumableAdapter.getData().size(); i++) {

                    if (consumableAdapter.getData().get(i).getValue() == null) {
                        issubmit_shij = false;
                        break;
                    } else {
                        issubmit_shij = true;
                    }
                }
            }

            if (!issubmit_shij) {
                msg("请确保耗材数据完整!");
                return;
            }

            reagens = JSON.toJSONString(reagensBeanList);


//            ArrayList<EquipsBean> equipsBeans11 = new ArrayList<>();
//            for (int i = 0; i < deviceAdapter.getData().size(); i++) {
//                EquipsBean equipsBeanss = new EquipsBean();
//                equipsBeanss.setEquipid(deviceAdapter.getData().get(i).getId());
//                equipsBeans11.add(equipsBeanss);
//            }
//            equips = JSON.toJSONString(equipsBeans11);


            ArrayList<EquipsBean> equipsBeans11 = new ArrayList<>();
            for (int i = 0; i < deviceAdapter.getData().size(); i++) {
                EquipsBean equipsBeanss = new EquipsBean();
                equipsBeanss.setEquipid(deviceAdapter.getData().get(i).getId());
                List<EquipsBean.CalibrationsBean> calibrationsBeanList1 = new ArrayList<>();
                if (deviceAdapter.getData().get(i).getCalibrations() != null) {
                    int sizes = deviceAdapter.getData().get(i).getCalibrations().size();
                    for (int j = 0; j < sizes; j++) {
                        EquipsBean.CalibrationsBean calibrationsBean1 = new EquipsBean.CalibrationsBean();
                        calibrationsBean1.setCalibrationquipid(deviceAdapter.getData().get(i).getCalibrations().get(j).getCalibrationquipid());
                        calibrationsBean1.setOperationuser(deviceAdapter.getData().get(i).getCalibrations().get(j).getOperationuser());
                        calibrationsBean1.setOperationtime(deviceAdapter.getData().get(i).getCalibrations().get(j).getOperationtime());
                        calibrationsBean1.setPremeasurement(deviceAdapter.getData().get(i).getCalibrations().get(j).getPremeasurement());
                        calibrationsBeanList1.add(calibrationsBean1);
                    }
                }

                equipsBeanss.setCalibrations(calibrationsBeanList1);
                equipsBeans11.add(equipsBeanss);
            }
            equips = JSON.toJSONString(equipsBeans11);

            /**
             * 获取设备维护相关的值
             */

        } else {

            longitude = atPointJ + "";
            latitude = atPointW + "";
            currentnumber = CURRENTNUMBER;
            sampletype = "0";
            starttime = et_mix.getText().toString().trim();
            endtime = et_max.getText().toString().trim();
            /**
             * 获取现场出值
             */
            List<ScenefactorsBean> scenefactorsBeanList = new ArrayList<>();
            for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {
                ScenefactorsBean scenefactorsBean = new ScenefactorsBean();
                scenefactorsBean.setFid(sample_valueAdapter.getData().get(i).getFid());
                scenefactorsBean.setMeasuredvalue(sample_valueAdapter.getData().get(i).getValue());
                scenefactorsBean.setFtmid(sample_valueAdapter.getData().get(i).getAnalysisMethod());
                scenefactorsBeanList.add(scenefactorsBean);
            }
            for (int i = 0; i < add_sample_qixiang_valueAdapter.getData().size(); i++) {
                ScenefactorsBean scenefactorsBean = new ScenefactorsBean();
                scenefactorsBean.setFid(add_sample_qixiang_valueAdapter.getData().get(i).getFid());
                scenefactorsBean.setMeasuredvalue(add_sample_qixiang_valueAdapter.getData().get(i).getValue());
                scenefactorsBean.setFtmid(add_sample_qixiang_valueAdapter.getData().get(i).getAnalysisMethod());
                scenefactorsBeanList.add(scenefactorsBean);
            }

            for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {
                if (sample_valueAdapter.getData().get(i).getValue() == null) {
                    issubmit_x = false;
                    break;
                } else {
                    issubmit_x = true;
                }
            }
            if (!issubmit_x) {
                msg("请确保现场测定数据完整!");
                return;
            }
            for (int i = 0; i < add_sample_qixiang_valueAdapter.getData().size(); i++) {
                if (add_sample_qixiang_valueAdapter.getData().get(i).getValue() == null) {
                    issubmit_qx = false;
                    break;
                } else {
                    issubmit_qx = true;
                }
            }
            if (!issubmit_qx) {
                msg("请确保气象数据完整!");
                return;
            }

//            if (typeid.equals("48") || typeid.equals("52") || typeid.equals("53")) {
//                if (deviceAdapter.getData().size() == 0) {
//                    msg("请选择现场设备,并填写校准记录");
//                    return;
//                } else {
//                    if (equipsBeans.size() < 2) {
//                        msg("请保证校准记录完整!");
//                        return;
//                    }
//                }
//            }

            ArrayList<EquipsBean> equipsBeans11 = new ArrayList<>();
            for (int i = 0; i < deviceAdapter.getData().size(); i++) {
                EquipsBean equipsBeanss = new EquipsBean();
                equipsBeanss.setEquipid(deviceAdapter.getData().get(i).getId());
                List<EquipsBean.CalibrationsBean> calibrationsBeanList1 = new ArrayList<>();


                if (deviceAdapter.getData().get(i).getCalibrations() != null) {
                    int sizes = deviceAdapter.getData().get(i).getCalibrations().size();
                    for (int j = 0; j < sizes; j++) {
                        EquipsBean.CalibrationsBean calibrationsBean1 = new EquipsBean.CalibrationsBean();
                        calibrationsBean1.setCalibrationquipid(deviceAdapter.getData().get(i).getCalibrations().get(j).getCalibrationquipid());
                        calibrationsBean1.setOperationuser(deviceAdapter.getData().get(i).getCalibrations().get(j).getOperationuser());
                        calibrationsBean1.setPremeasurement(deviceAdapter.getData().get(i).getCalibrations().get(j).getPremeasurement());
                        calibrationsBean1.setOperationtime(deviceAdapter.getData().get(i).getCalibrations().get(j).getOperationtime());
                        calibrationsBeanList1.add(calibrationsBean1);
                    }
                }
                equipsBeanss.setCalibrations(calibrationsBeanList1);
                equipsBeans11.add(equipsBeanss);
            }
            equips = JSON.toJSONString(equipsBeans11);


            scenefactors = JSON.toJSONString(scenefactorsBeanList);
        }


        ViseLog.d(equips);

        /**
         * 二次弹框确认
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认提交采样信息吗?");
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ShowDialogtitle("请稍等...", AddSampleInfoActivity.this);

                String data = "{taskid:\"" + taskid + "\",currentnumber:\"" + currentnumber + "\",longitude:\"" + longitude + "\",latitude:\"" + latitude + "\",relationIdentification:\"" + relationIdentification + "\",sampingname:\"" + sampingname + "\",pointsid:\"" + pointsid + "\",pretestvalue:\"" + pretestvalue + "\",posttestvalue:\"" + posttestvalue + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",fsid:\"" + fsid + "\",onlynumber:\"" + onlynumber + "\",sampingstatus:\"" + sampingstatus + "\",sampingPacking:\"" + sampingPacking + "\",starttime:\"" + starttime + "\",endtime:\"" + endtime + "\",sampinguser:\"" + sampinguser + "\",\n" +
                        "samplingamount:\"" + samplingamount + "\",soilsampletool:\"" + soilsampletool + "\",soilvegetation:\"" + soilvegetation + "\",soilcolor:\"" + soilcolor + "\",soiltexture:\"" + soiltexture + "\",soilhumidity:\"" + soilhumidity + "\",samplingunit:\"" + samplingunit + "\",samplestyle:\"" + samplestyle + "\",sampletype:\"" + sampletype + "\",weathercondition:\"" + weathercondition + "\",temperature:\"" + temperature + "\",analysisitems:\"" + analysisitems + "\",remark:\"" + remark + "\",proofreader:\"" + proofreader + "\" ,scenefactors:" + scenefactors + " ,reagens:\"" + reagens + "\",parallelsample:\"" + parallelsample + "\",equips:" + equips + "}";
                taskinfoPresenter.submitInfo(data, imageFile1, imageFile_video, File_QX, File_3012);
                ViseLog.d(JSON.toJSONString(imageFile1));
            }
        });
        builder.show();


    }


    public ArrayList<TasksInfomationBean.EquipsBean.CalibrationsBean> equipsBeans = new ArrayList<>();

    @Subscribe
    public void exEvent(EquipsBeanEvent event) {
        TasksInfomationBean.EquipsBean.CalibrationsBean calibrationsBean = new TasksInfomationBean.EquipsBean.CalibrationsBean();
        calibrationsBean.setCalibrationquipid(event.getCalibrationsBeanArrayList().get(0).getCalibrations().get(0).getCalibrationquipid());
        calibrationsBean.setOperationuser(event.getCalibrationsBeanArrayList().get(0).getCalibrations().get(0).getOperationuser());
//        calibrationsBean.setPostmeasurement(event.getCalibrationsBeanArrayList().get(0).getCalibrations().get(0).getPostmeasurement());
        calibrationsBean.setOperationtime(event.getCalibrationsBeanArrayList().get(0).getCalibrations().get(0).getOperationtime());
        calibrationsBean.setPremeasurement(event.getCalibrationsBeanArrayList().get(0).getCalibrations().get(0).getPremeasurement());
        calibrationsBean.setNames(event.getNames());
        calibrationsBean.setDevname(event.getDevname());
        calibrationsBean.setInnames(event.getInnames());
        List<TasksInfomationBean.EquipsBean.CalibrationsBean> calibrationsBeanList = new ArrayList<>();
        calibrationsBeanList.add(calibrationsBean);
        equipsBeans.addAll(calibrationsBeanList);

        if (event.getType().equals("前")) {
            deviceAdapter.getData().get(event.getPos()).setBeforemeasurement(event.getType());
        } else {
            deviceAdapter.getData().get(event.getPos()).setAftermeasurement(event.getType());
        }
        ViseLog.d("sss:" + JSON.toJSONString(equipsBeans));
        deviceAdapter.getData().get(event.getPos()).setCalibrations(equipsBeans);
        deviceAdapter.notifyDataSetChanged();


    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(String type) {
            switch (type) {
                case "video":
                    PictureSelector.create(AddSampleInfoActivity.this)
                            .openGallery(PictureMimeType.ofVideo())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(true)

                            .recordVideoSecond(150)
                            .glideOverride(160, 160)
                            .previewEggs(true)
                            .selectionMedia(selectList_video)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case "Audio":
                    PictureSelector.create(AddSampleInfoActivity.this)
                            .openGallery(PictureMimeType.ofAudio())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(true)
                            .glideOverride(160, 160)
                            .previewEggs(true)
                            .selectionMedia(selectList_image)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case "image":
                    PictureSelector.create(AddSampleInfoActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(true)
                            .glideOverride(160, 160)
                            .previewEggs(true)
                            .compress(true)
                            .selectionMedia(selectList_image)
                            .forResult(666);
                    break;
            }

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList_video = PictureSelector.obtainMultipleResult(data);
                    adapter_video.setList(selectList_video);
                    adapter_video.notifyDataSetChanged();
                    break;
                case 666:
                    selectList_image = PictureSelector.obtainMultipleResult(data);
                    adapter_image.setList(selectList_image);
                    adapter_image.notifyDataSetChanged();
                    break;
                case 10:
//                    msg("SSS");
                    initBla();
                    break;
            }
        }

        if (requestCode == REQUEST_CODE_OPEN_GPS) {
            if (checkGPSIsOpen()) {

                startScan();
            }
        }
    }

    private boolean checkGPSIsOpen() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null)
            return false;
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    /**
     * 初始化定位并设置定位回调监听
     */
    private void getCurrentLocationLatLng() {

        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();


        //初始化定位
        mLocationClient = new AMapLocationClient(this);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //只会使用网络定位
        /* mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);*/
        //只使用GPS进行定位
        /*mLocationOption.setLocationMode(AMapLocationMode.Device_Sensors);*/
        // 设置为单次定位 默认为false
        mLocationOption.setOnceLocation(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。默认连续定位 切最低时间间隔为1000ms
        mLocationOption.setInterval(10000);
        //设置是否返回地址信息（默认返回地址信息）
        /*mLocationOption.setNeedAddress(true);*/
        //关闭缓存机制 默认开启 ，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存,不区分单次定位还是连续定位。GPS定位结果不会被缓存。
        /*mLocationOption.setLocationCacheEnable(false);*/
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    double atPointJ;
    double atPointW;

    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    atPointW = amapLocation.getLatitude();//获取纬度
//                    atPointJ = amapLocation.getLongitude();//获取经度
                    amapLocation.getAccuracy();//获取精度信息
                    amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    amapLocation.getCountry();//国家信息
                    amapLocation.getProvince();//省信息
                    amapLocation.getCity();//城市信息
                    amapLocation.getDistrict();
                    amapLocation.getStreet();//街道信息
                    amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                    initWeather(amapLocation.getCity());
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }

        }
    };

    public void initWeather(String city) {
        mquery = new WeatherSearchQuery(city, WeatherSearchQuery.WEATHER_TYPE_LIVE);//检索参数为城市和天气类型，实时天气为1、天气预报为2
        mweathersearch = new WeatherSearch(this);
        mweathersearch.setOnWeatherSearchListener(this);
        mweathersearch.setQuery(mquery);
        mweathersearch.searchWeatherAsyn(); //异步搜索
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7

        BleManager.getInstance().disconnectAllDevice();
        BleManager.getInstance().disconnect(qxBle);
        BleManager.getInstance().destroy();

        equipsBeans = new ArrayList<>();
        mLocClient.stop();

        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        if (sockets != null) {
            try {
                ViseLog.d("111");
                sockets.close();
//                if(mConnectedThread!=null){
//                    mConnectedThread.interrupt();
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    List<TasksInfomationBean.PointsBean> pointsBeans = new ArrayList<>();

    @Override
    public void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans) {

        DissDialog();
//        if (tasksInfomationBeans.get(0).getQualityControlname().length() > 0) {
//            ll_sample_type.setVisibility(View.VISIBLE);
//            strzkid = tasksInfomationBeans.get(0).getQualityControl();
//            rb_z.setText(tasksInfomationBeans.get(0).getQualityControlname());
//        }
//        sample_valueAdapter.setNewData(tasksInfomationBeans.get(0).getPoints().);
//        analysisAdapter.setNewData(tasksInfomationBeans.get(0).getFactors());
        pointsBeans = new ArrayList<>();
        pointsBeans.addAll(tasksInfomationBeans.get(0).getPoints());

        pointsid = POINTSIDS;
        for (int i = 0; i < pointsBeans.size(); i++) {
            if (pointsBeans.get(i).getPointsid().equals(POINTSIDS)) {


                initViewSS(i);
                break;
            }
        }


        consumableListAdapter.setNewData(tasksInfomationBeans.get(0).getConsumables());
        deviceListAdapter.setNewData(tasksInfomationBeans.get(0).getEquips());
        samplinguserBeans = new ArrayList<>();
        samplinguserBeans_ALL = new ArrayList<>();
        add_sampAdapter.setNewData(tasksInfomationBeans.get(0).getSamplinguser());
        for (int i = 0; i < tasksInfomationBeans.get(0).getSamplinguser().size(); i++) {
            if (!tasksInfomationBeans.get(0).getSamplinguser().get(i).getLoginname().equals(MyApplication.getInstance().getUser())) {
                samplinguserBeans.add(tasksInfomationBeans.get(0).getSamplinguser().get(i));
            }
            samplinguserBeans_ALL.add(tasksInfomationBeans.get(0).getSamplinguser().get(i));
        }


    }

    String typeid = "";


    public void initViewSS(int options1) {


        tv_potion.setText(pointsBeans.get(options1).getPickerViewText());
        /**
         * 土壤
         */
        typeid = pointsBeans.get(options1).getCategoryid();

        if (pointsBeans.get(options1).getCategoryid().equals("47")) {
            ll_tr.setVisibility(View.VISIBLE);
            ll_sample_ty.setVisibility(View.GONE);
            ll_sample_status.setVisibility(View.GONE);
            ll_Association.setVisibility(View.GONE);
            /**
             * 查询土壤质地
             */
            String data_hum = "{Id:\"\",typeCode:\"soilhumidity\",DataValue:\"\",DataCode:\"\",querystring:\"\"} ";
            taskinfoPresenter.OnSoilhumidity(data_hum);
            String data_soiltexture = "{Id:\"\",typeCode:\"soiltexture\",DataValue:\"\",DataCode:\"\",querystring:\"\"} ";
            taskinfoPresenter.OnSoiltexture(data_soiltexture);
            /**
             * 查询土壤湿度
             */
        }
        /**
         * 水 没有采样方式
         */
        else if (pointsBeans.get(options1).getCategoryid().equals("38") ||
                pointsBeans.get(options1).getCategoryid().equals("39") ||
                pointsBeans.get(options1).getCategoryid().equals("40") ||
                pointsBeans.get(options1).getCategoryid().equals("41") ||
                pointsBeans.get(options1).getCategoryid().equals("42")) {
            ll_sample_ty.setVisibility(View.GONE);
            ll_tr.setVisibility(View.GONE);
            ll_sample_status.setVisibility(View.VISIBLE);
            ll_blue_dev.setVisibility(View.GONE);
            ll_Association.setVisibility(View.GONE);
            ll_weather.setVisibility(View.VISIBLE);
        }
        /**
         * 气
         */
        else if (pointsBeans.get(options1).getCategoryid().equals("43") ||
                pointsBeans.get(options1).getCategoryid().equals("44") ||
                pointsBeans.get(options1).getCategoryid().equals("45") ||
                pointsBeans.get(options1).getCategoryid().equals("46")) {
            ll_sample_ty.setVisibility(View.VISIBLE);
            ll_blue_dev.setVisibility(View.VISIBLE);
            ll_tr.setVisibility(View.GONE);
            ll_sample_status.setVisibility(View.GONE);
            ll_baoz.setVisibility(View.GONE);//气体隐藏包装
            ll_weather.setVisibility(View.VISIBLE);

            ll_Association.setVisibility(View.VISIBLE);
        }
        /**
         * 噪声
         */
        else if (pointsBeans.get(options1).getCategoryid().equals("48")) {
            categoryid = "48";
            ll_blue_dev.setVisibility(View.VISIBLE);
            //  关联样品
            ll_Association.setVisibility(View.GONE);
            ll_sample_ty.setVisibility(View.GONE);
            ll_tr.setVisibility(View.GONE);
            ll_sample_status.setVisibility(View.GONE);
            ll_weather.setVisibility(View.VISIBLE);
        } else {

            ll_Association.setVisibility(View.GONE);
            ll_sample_ty.setVisibility(View.GONE);
            ll_tr.setVisibility(View.GONE);
            ll_sample_status.setVisibility(View.GONE);
            ll_blue_dev.setVisibility(View.GONE);
            ll_weather.setVisibility(View.GONE);
        }


        /**
         * 查询因子分组
         */
        String data_y = "{pointsid:\"" + POINTSIDS + "\",taskid:\"" + taskid + "\",currentnumber:\"" + CURRENTNUMBER + "\",types:\"0\"}";
        addAccusePresenter.GetSampleInfoByPointInfo(data_y);

        /**
         * 填充现场出值
         */
        if (pointsBeans.get(options1).getScenefactors().size() > 0) {
            /**
             * 判断气象和正常区分
             */


            List<TasksInfomationBean.PointsBean.ScenefactorsBean> scenefactorsBeans = new ArrayList<>();
            List<TasksInfomationBean.PointsBean.ScenefactorsBean> scenefactorsBeans1 = new ArrayList<>();

            for (int i = 0; i < pointsBeans.get(options1).getScenefactors().size(); i++) {
                if (pointsBeans.get(options1).getScenefactors().get(i).getFactorname().equals("温度")) {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans.add(scenefactorsBean);
                } else if (pointsBeans.get(options1).getScenefactors().get(i).getFactorname().equals("湿度")) {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans.add(scenefactorsBean);
                } else if (pointsBeans.get(options1).getScenefactors().get(i).getFactorname().equals("大气压")) {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans.add(scenefactorsBean);
                } else if (pointsBeans.get(options1).getScenefactors().get(i).getFactorname().equals("风速")) {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans.add(scenefactorsBean);
                } else if (pointsBeans.get(options1).getScenefactors().get(i).getFactorname().equals("风向")) {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans.add(scenefactorsBean);
                } else {
                    TasksInfomationBean.PointsBean.ScenefactorsBean scenefactorsBean = new TasksInfomationBean.PointsBean.ScenefactorsBean();
                    scenefactorsBean.setFactorname(pointsBeans.get(options1).getScenefactors().get(i).getFactorname());
                    scenefactorsBean.setContentid(pointsBeans.get(options1).getScenefactors().get(i).getContentid());
                    scenefactorsBean.setValue(pointsBeans.get(options1).getScenefactors().get(i).getValue());
                    scenefactorsBean.setFid(pointsBeans.get(options1).getScenefactors().get(i).getFid());
                    scenefactorsBean.setAnalysisMethod(pointsBeans.get(options1).getScenefactors().get(i).getAnalysisMethod());
                    scenefactorsBean.setMethodname(pointsBeans.get(options1).getScenefactors().get(i).getMethodname());
                    scenefactorsBeans1.add(scenefactorsBean);
                }
            }
            sample_valueAdapter.setNewData(scenefactorsBeans1);
            add_sample_qixiang_valueAdapter.setNewData(scenefactorsBeans);

        }
        if (pointsBeans.get(options1).getCharges().size() > 0) {
            TasksInfomationBean.PointsBean.ChargesBean chargesBean = new TasksInfomationBean.PointsBean.ChargesBean();
            chargesBean.setCqcid("-9999999");
            chargesBean.setQualitycontrolname("正常");
            chargesBeanList.add(chargesBean);
            chargesBeanList.addAll(pointsBeans.get(options1).getCharges());
        } else {
            TasksInfomationBean.PointsBean.ChargesBean chargesBean = new TasksInfomationBean.PointsBean.ChargesBean();
            chargesBean.setCqcid("-9999999");
            chargesBean.setQualitycontrolname("正常");
            chargesBeanList.add(chargesBean);
        }

        /**
         * 查询上次提交的数据
         */

        ShowDialogtitle("加载中...", AddSampleInfoActivity.this);
        String data = "{sampid:\"\",sampdetailid:\"\",createdate:\"" + format.format(new Date()) + "\",currentnumber:\"" + CURRENTNUMBER + "\",onlynumber:\"\",pointsid:\"" + pointsid + "\",taskid:\"" + taskid + "\"}";
        taskinfoPresenter.GetfieldsamplingDetail(data);
    }

    @Override
    public void OnPack(List<DicDataBean> dicDataBeans) {
        basicslist_pack.clear();
        basicslist_pack.addAll(dicDataBeans);
    }

    @Override
    public void OnUnit(List<DicDataBean> dicDataBeans) {
        basicslist_unit.clear();
        basicslist_unit.addAll(dicDataBeans);
    }

    @Override
    public void OnStyle(List<DicDataBean> dicDataBeans) {
        basicslist_way.clear();
        basicslist_way.addAll(dicDataBeans);
    }

    @Override
    public void onMessage(ResultBean message) {
        DissDialog();
        if (message.getResult().equals("1")) {
            msg("成功");
            DeleteFileUtil.delete(Environment.getExternalStorageDirectory().getPath() + "/QxFile.txt");
            DeleteFileUtil.delete(Environment.getExternalStorageDirectory().getPath() + "/3012File.txt");
            finish();
            EventBus.getDefault().post(new JumpEvent("更新数据"));
            equipsBeans = new ArrayList<>();

        } else {

            selectList_All = new ArrayList<>();
            imageFile1 = new ArrayList<>();
            imageFile_video = new ArrayList<>();
            msg(message.getErrormessage());
        }
    }

    @Override
    public void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos) {

    }

    @Override
    public void OnEndFieldsampling(ResultBean message) {

    }

    @Override
    public void OnGetfieldsamplingDetailList
            (List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {

    }

    @Override
    public void OnGetfieldsamplingDetail
            (List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

        /**
         * 返回上次提交
         *
         *   String data = "{taskid:\"" + taskid + "\",
         *   sampingname:\"" + sampingname + "\",
         *   pointsid:\"" + pointsid + "\",pretestvalue:\""
         *   + pretestvalue + "\",posttestvalue:\""
         *   + posttestvalue + "\",loginId:\"" +
         *   MyApplication.getInstance().getUser() + "\",
         *   fsid:\"" + fsid + "\",onlynumber:\"" + onlynumber +
         *   "\",sampingstatus:\"" + sampingstatus + "\",sampingPacking:\""
         *   + sampingPacking + "\",starttime:\"" + starttime + "\",endtime:\""
         *   + endtime + "\",sampinguser:\"" + sampinguser + "\",\n" +
         "samplingamount:\"" + samplingamount + "\",samplingunit:\"" +
         samplingunit + "\",samplestyle:\"" + samplestyle + "\",
         sampletype:\"" + sampletype + "\",weathercondition:\""
         + weathercondition + "\",temperature:\"" + temperature +
         "\",analysisitems:\"" + analysisitems + "\",remark:\"" + remark + "\",
         scenefactors:" + scenefactors + ",reagens:" + reagens + ",equips:" + equips + "}";

         */
        DissDialog();
        if (fieldsamplingDetailBeans.size() > 0) {

            /**
             * 判断隐藏或者显示 连接设备
             */

            if (fieldsamplingDetailBeans.get(0).getCategoryid().equals("43") ||
                    fieldsamplingDetailBeans.get(0).getCategoryid().equals("44") ||
                    fieldsamplingDetailBeans.get(0).getCategoryid().equals("45") ||
                    fieldsamplingDetailBeans.get(0).getCategoryid().equals("46")) {
//                ll_blue_dev.setVisibility(View.GONE);
//                ll_weather.setVisibility(View.GONE);
                List<TasksInfomationBean.PointsBean.ScenefactorsBean> scenefactorsBeanLi = new ArrayList<>();
                sample_valueAdapter.setNewData(scenefactorsBeanLi);
                sample_valueAdapter.notifyDataSetChanged();
            } else {
                if (fieldsamplingDetailBeans.get(0).getScenefactors().size() > 0) {
                    for (int i = 0; i < sample_valueAdapter.getData().size(); i++) {
                        for (int j = 0; j < fieldsamplingDetailBeans.get(0).getScenefactors().size(); j++) {
                            if (sample_valueAdapter.getData().get(i).getFid().equals(fieldsamplingDetailBeans.get(0).getScenefactors().get(j).getFid())) {
                                sample_valueAdapter.getData().get(i).setValue("");
                            }
                        }
                        sample_valueAdapter.notifyDataSetChanged();
                    }
//                    List<TasksInfomationBean.PointsBean.ScenefactorsBean> scenefactorsBeanLi = new ArrayList<>();
//                    sample_valueAdapter.setNewData(scenefactorsBeanLi);
//                    sample_valueAdapter.notifyDataSetChanged();
                }
            }
            //土壤颜色
            et_soilcolor.setText(fieldsamplingDetailBeans.get(0).getSoilcolor());
            //耕作情况
            et_soilvegetation.setText(fieldsamplingDetailBeans.get(0).getSoilvegetation());
            //工具
            et_soilsampletool.setText(fieldsamplingDetailBeans.get(0).getSoilsampletool());
            //样品名称
            et_names.setText(fieldsamplingDetailBeans.get(0).getSampingname());
            //样品状态
            et_statuss.setText(fieldsamplingDetailBeans.get(0).getSampingstatus());
            //样品包装
            sampingPacking = fieldsamplingDetailBeans.get(0).getSampingPacking();
            tv_pack.setText(fieldsamplingDetailBeans.get(0).getSampingPackingname());


            et_temp.setText(fieldsamplingDetailBeans.get(0).getTemperature());
            et_status.setText(fieldsamplingDetailBeans.get(0).getWeathercondition());

            //采样人
            final StringBuffer stringBuffernames = new StringBuffer();
            final StringBuffer stringBufferid = new StringBuffer();
            for (int i = 0; i < fieldsamplingDetailBeans.get(0).getSampingusers().size(); i++) {
                stringBufferid.append(fieldsamplingDetailBeans.get(0).getSampingusers().get(i).getId() + ",");
                stringBuffernames.append(fieldsamplingDetailBeans.get(0).getSampingusers().get(i).getUserName() + ",");
            }
            if (stringBuffernames.toString() != null && stringBuffernames.toString().length() > 0) {
                tv_caiyperson.setText(stringBuffernames.toString().substring(0, stringBuffernames.toString().length() - 1));
//                sampinguser = stringBufferid.toString().substring(0, stringBufferid.toString().length() - 1);
            } else {
                tv_caiyperson.setText("");
                sampinguser = "";
            }
            //采样量
            et_sampleValue.setText(fieldsamplingDetailBeans.get(0).getSamplingamount());
            //采样单位
            samplingunit = fieldsamplingDetailBeans.get(0).getSamplingunit();
            //采样方式
            et_tr_way.setText(fieldsamplingDetailBeans.get(0).getSamplestylename());
            if (et_tr_way.getText().length() > 0) {
                tv_way.setText("");
                samplestyle = et_tr_way.getText().toString().trim();
            } else {
                tv_way.setText(fieldsamplingDetailBeans.get(0).getSamplestylename());
                samplestyle = fieldsamplingDetailBeans.get(0).getSamplestyle();
            }


        }
    }

    @Override
    public void OnSampleType(SampleTypeBean sampleTypeBean) {

        if (sampleTypeBean != null) {
            if (sampleTypeBean.getSampletype() != null) {
                if (sampleTypeBean.getSampletype().equals("0")) {
                    ParallelActivity_.intent(AddSampleInfoActivity.this).onlynumber(onlynumberPar).start();
                } else {
                    msg("当前样品不是原样,请扫描原样");
                }
            }
        }


    }

    /**
     * 解密
     *
     * @param resultBean
     */

    boolean isadd = false;

    @Override
    public void OnSampleCode(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult() != null) {
            if (codea.equals("tv_bar_code")) {
                tv_bar_code.setText(resultBean.getResult());
            } else if (codea.equals("tv_parallel")) {

                tv_parallel.setText(resultBean.getResult());
//                onlynumberPar = resultBean.getResult();
//                String data = "{samplecode:\"" + resultBean.getResult() + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\"}";
//                sampleTypePresenter.GetsampleInfoBysamplecode(data);
            } else if (codea.equals("关联")) {

                if (CodeList.size() > 0) {


                    for (int i = 0; i < CodeList.size(); i++) {
                        if (resultBean.getResult().equals(CodeList.get(i))) {
                            msg("样品编码重复");
                            isadd = true;
                            ViseLog.d("111");
                            break;
                        } else {
                            isadd = false;
                        }

                    }

                    if (!isadd) {
                        CodeList.add(resultBean.getResult());
                        associationAdapter.setNewData(CodeList);
                        associationAdapter.notifyDataSetChanged();
                    }


                } else {

                    if (resultBean.getResult().equals(tv_bar_code.getText().toString())) {
                        msg("样品编码重复");
                    } else {


                        CodeList.add(resultBean.getResult());
                        associationAdapter.setNewData(CodeList);
                        associationAdapter.notifyDataSetChanged();
                    }

                }

            }
        } else {
            msg(resultBean.getErrormessage());
        }

    }


    @Override
    public void Onbasicslist_quality(List<DicDataBean> dicDataBeans) {


    }

    String submit_status = "";

    /**
     * 选择因子分类信息
     */
    @Override
    public void OnSampleInfoByPointInfo(SampleInfoByPointInfoBean sampleInfoByPointInfoBean) {

        /**
         * 填充分析项目
         */

        if (sampleInfoByPointInfoBean == null) {

            submit_status = "0";
            initVisibility();

//            msg("111");
        } else {

            if (sampleInfoByPointInfoBean.getDetails() != null) {
                if (sampleInfoByPointInfoBean.getDetails().size() > 0) {
                    submit_status = "1";
                    analysisAdapter.setNewData(sampleInfoByPointInfoBean.getDetails());

                } else {
                    submit_status = "0";
                    initVisibility();
//                    msg("22");
                }
            } else {
                submit_status = "0";
                initVisibility();
//                msg("33");
            }
        }
    }

    @Override
    public void OnGetPreData(PreDataBean preDataBean) {

    }

    @Override
    public void OnGetPreInfoData(List<PreInfoDataBean> preInfoDataBeans) {

    }

    @Override
    public void OnSchemeFidsData(List<SchemeFidsBean> schemeFidsBeans) {

    }

    @ViewById(R.id.ll_base)
    LinearLayout ll_base;
    @ViewById(R.id.ll_haocai)
    LinearLayout ll_haocai;
    @ViewById(R.id.ll_fujian)
    LinearLayout ll_fujian;
    @ViewById(R.id.ll_dian)
    LinearLayout ll_dian;
    @ViewById(R.id.ll_saoma)
    LinearLayout ll_saoma;
    @ViewById(R.id.ll_yangpinname)
    LinearLayout ll_yangpinname;
    @ViewById(R.id.ll_baoz)
    LinearLayout ll_baoz;
    @ViewById(R.id.ll_caiyangliang)
    LinearLayout ll_caiyangliang;

    /**
     * 隐藏部分布局
     */

    public void initVisibility() {
        ll_fenxi.setVisibility(View.GONE);
        ll_sample_Identification.setVisibility(View.GONE);
        ll_sample_type.setVisibility(View.GONE);
        ll_sample_ty.setVisibility(View.GONE);
        ll_caiyangliang.setVisibility(View.GONE);
        ll_baoz.setVisibility(View.GONE);
        ll_yangpinname.setVisibility(View.GONE);
        ll_saoma.setVisibility(View.GONE);
        ll_dian.setVisibility(View.GONE);
        ll_fenxi.setVisibility(View.GONE);
        ll_haocai.setVisibility(View.GONE);
        ll_fujian.setVisibility(View.GONE);
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    /**
     * 返回土壤湿度
     *
     * @param dicDataBeans
     */
    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

        basicslist_soilhumidity.clear();
        basicslist_soilhumidity.addAll(dicDataBeans);
    }

    /**
     * 返回土壤质地
     *
     * @param dicDataBeans
     */
    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {
        basicslist_soiltexture.clear();
        basicslist_soiltexture.addAll(dicDataBeans);
    }

    @Override
    public void onWeatherLiveSearched(LocalWeatherLiveResult weatherLiveResult, int i) {
        if (i == AMapException.CODE_AMAP_SUCCESS) {
            if (weatherLiveResult != null && weatherLiveResult.getLiveResult() != null) {
                weatherlive = weatherLiveResult.getLiveResult();
                et_temp.setText(weatherlive.getTemperature());
                et_status.setText(weatherlive.getWeather());
            } else {

//                msg("11");
            }
        } else {
//            msg(i + "");
        }
    }

    @Override
    public void onWeatherForecastSearched(LocalWeatherForecastResult localWeatherForecastResult,
                                          int i) {

    }

    @Override
    public void Value(Editable editable, int position) {
        TasksInfomationBean.PointsBean.ScenefactorsBean sampleCategoryItemsDataBean = sample_valueAdapter.getData().get(position);
        if (editable.toString() != null) {
            sampleCategoryItemsDataBean.setValue(editable.toString());
        } else {
            sampleCategoryItemsDataBean.setValue("");
        }
    }

    @Override
    public void Value_con(Editable editable, int position) {
        TasksInfomationBean.ConsumablesBean sampleCategoryItemsDataBean = consumableAdapter.getData().get(position);
        if (editable.toString() != null) {
            sampleCategoryItemsDataBean.setValue(editable.toString());
        } else {
            sampleCategoryItemsDataBean.setValue("");
        }

    }

    public void initBlaRead(StringBuffer stringBuffer) {
        String dizhi = stringBuffer.substring(1, 3);
        String cmd = stringBuffer.substring(3, 5);
        String DataNum = stringBuffer.substring(5, 7);
        String zhuangtai = stringBuffer.substring(9, 11) + stringBuffer.substring(7, 9);
        /**
         * 风向
         */
        StringBuffer stringBufferWindDirect = new StringBuffer();
        stringBufferWindDirect.append(stringBuffer.substring(11, 13));
        stringBufferWindDirect.append(stringBuffer.substring(13, 15));
        int WindDirect = Integer.parseInt(stringBufferWindDirect.toString(), 16);
        Log.d("风向", WindDirect + "");
        /**
         * 风速
         */
        int WindSpeed0 = Integer.parseInt(stringBuffer.substring(21, 23), 16);
        int WindSpeed1 = Integer.parseInt(stringBuffer.substring(19, 21), 16);
        int WindSpeed2 = Integer.parseInt(stringBuffer.substring(17, 19), 16);
        int WindSpeed3 = Integer.parseInt(stringBuffer.substring(15, 17), 16);


        BigDecimal b = new BigDecimal(GetFloatValue(WindSpeed0, WindSpeed1, WindSpeed2, WindSpeed3));
        float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        float WindSpeed = f1;
//        windSpeed = WindSpeed;
        Log.d("风速", WindSpeed + "");
        /**
         * 温度
         */
        int Temperature0 = Integer.parseInt(stringBuffer.substring(29, 31), 16);
        int Temperature1 = Integer.parseInt(stringBuffer.substring(27, 29), 16);
        int Temperature2 = Integer.parseInt(stringBuffer.substring(25, 27), 16);
        int Temperature3 = Integer.parseInt(stringBuffer.substring(23, 25), 16);
        float Temperature = GetFloatValue(Temperature0, Temperature1, Temperature2, Temperature3);
        Log.d("温度", Temperature + "");
        /**
         * 湿度
         */
        int Humidity0 = Integer.parseInt(stringBuffer.substring(37, 39), 16);
        int Humidity1 = Integer.parseInt(stringBuffer.substring(35, 37), 16);
        int Humidity2 = Integer.parseInt(stringBuffer.substring(33, 35), 16);
        int Humidity3 = Integer.parseInt(stringBuffer.substring(31, 33), 16);
        float Humidity = GetFloatValue(Humidity0, Humidity1, Humidity2, Humidity3);
        Log.d("湿度", Humidity + "");

        /**
         * 气压
         */
        int AbsoluationPression0 = Integer.parseInt(stringBuffer.substring(45, 47), 16);
        int AbsoluationPression1 = Integer.parseInt(stringBuffer.substring(43, 45), 16);
        int AbsoluationPression2 = Integer.parseInt(stringBuffer.substring(41, 43), 16);
        int AbsoluationPression3 = Integer.parseInt(stringBuffer.substring(39, 41), 16);
        float AbsoluationPression = GetFloatValue(AbsoluationPression0, AbsoluationPression1, AbsoluationPression2, AbsoluationPression3);
        Log.d("气压", AbsoluationPression + "");
        /**
         * 角度
         */
        StringBuffer stringBufferCompass = new StringBuffer();
        stringBufferCompass.append(stringBuffer.substring(47, 49));
        stringBufferCompass.append(stringBuffer.substring(49, 51));
        int Compass = Integer.parseInt(stringBufferCompass.toString(), 16);
        Log.d("角度", Compass + "");
//        msg("角度:" + Compass + "");
        /**
         * 降雨状态
         */
//                    int RainFallState = Integer.parseInt(stringBuffer.substring(51, 53), 16);
//                    byte[] RainFallStatebyte = {(byte) RainFallState};
        String ss = String.valueOf(stringBuffer.substring(51, 53));
        Log.d("降雨状态", ss + "");
        /**
         * 降雨强度
         */
        int RainFallIntensity0 = Integer.parseInt(stringBuffer.substring(59, 61), 16);
        int RainFallIntensity1 = Integer.parseInt(stringBuffer.substring(57, 59), 16);
        int RainFallIntensity2 = Integer.parseInt(stringBuffer.substring(55, 57), 16);
        int RainFallIntensity3 = Integer.parseInt(stringBuffer.substring(53, 55), 16);
        float ainFallIntensity = GetFloatValue(RainFallIntensity0, RainFallIntensity1, RainFallIntensity2, RainFallIntensity3);
        Log.d("降雨强度", ainFallIntensity + "");

        /**
         * 累积雨量
         */
        int RainFallSum0 = Integer.parseInt(stringBuffer.substring(67, 69), 16);
        int RainFallSum1 = Integer.parseInt(stringBuffer.substring(65, 67), 16);
        int RainFallSum2 = Integer.parseInt(stringBuffer.substring(63, 65), 16);
        int RainFallSum3 = Integer.parseInt(stringBuffer.substring(61, 63), 16);
        float RainFallSum = GetFloatValue(RainFallSum0, RainFallSum1, RainFallSum2, RainFallSum3);
        Log.d("累积雨量", RainFallSum + "");
        /**
         * 航速
         */
        int GPSSpeed0 = Integer.parseInt(stringBuffer.substring(79, 81), 16);
        int GPSSpeed1 = Integer.parseInt(stringBuffer.substring(77, 79), 16);
        int GPSSpeed2 = Integer.parseInt(stringBuffer.substring(75, 77), 16);
        int GPSSpeed3 = Integer.parseInt(stringBuffer.substring(73, 75), 16);
        float GPSSpeed = GetFloatValue(GPSSpeed0, GPSSpeed1, GPSSpeed2, GPSSpeed3);
        Log.d("航速", GPSSpeed + "");
        /**
         * 航向
         */
        StringBuffer stringBufferGPSCourse = new StringBuffer();
        stringBufferGPSCourse.append(stringBuffer.substring(81, 83));
        stringBufferGPSCourse.append(stringBuffer.substring(83, 85));
        int GPSCourse = Integer.parseInt(stringBufferGPSCourse.toString(), 16);
        Log.d("lsls", GPSCourse + "");
        /**
         * 经度
         */
        int GPSLongitude0 = Integer.parseInt(stringBuffer.substring(91, 93), 16);
        int GPSLongitude1 = Integer.parseInt(stringBuffer.substring(89, 91), 16);
        int GPSLongitude2 = Integer.parseInt(stringBuffer.substring(87, 89), 16);
        int GPSLongitude3 = Integer.parseInt(stringBuffer.substring(85, 87), 16);
        float GPSLongitude = GetFloatValue(GPSLongitude0, GPSLongitude1, GPSLongitude2, GPSLongitude3);
        Log.d("经度", GPSLongitude + "");
        /**
         * 纬度
         */
        int GPSLatitude0 = Integer.parseInt(stringBuffer.substring(99, 101), 16);
        int GPSLatitude1 = Integer.parseInt(stringBuffer.substring(97, 99), 16);
        int GPSLatitude2 = Integer.parseInt(stringBuffer.substring(95, 97), 16);
        int GPSLatitude3 = Integer.parseInt(stringBuffer.substring(93, 95), 16);
        float GPSLatitude = GetFloatValue(GPSLatitude0, GPSLatitude1, GPSLatitude2, GPSLatitude3);
        Log.d("纬度", GPSLatitude + "");
        /**
         * 粉尘浓度
         */
        int Dusty0 = Integer.parseInt(stringBuffer.substring(107, 109), 16);
        int Dusty1 = Integer.parseInt(stringBuffer.substring(105, 107), 16);
        int Dusty2 = Integer.parseInt(stringBuffer.substring(103, 105), 16);
        int Dusty3 = Integer.parseInt(stringBuffer.substring(101, 103), 16);
        float Dusty = GetFloatValue(Dusty0, Dusty1, Dusty2, Dusty3);
        Log.d("粉尘浓度", Dusty + "");
        /**
         * 能见度
         */
        int MOR0 = Integer.parseInt(stringBuffer.substring(115, 117), 16);
        int MOR1 = Integer.parseInt(stringBuffer.substring(113, 115), 16);
        int MOR2 = Integer.parseInt(stringBuffer.substring(111, 113), 16);
        int MOR3 = Integer.parseInt(stringBuffer.substring(109, 111), 16);
        float MOR = GetFloatValue(MOR0, MOR1, MOR2, MOR3);
        Log.d("能见度", MOR + "");
        /**
         * 照度
         */
        int LD0 = Integer.parseInt(stringBuffer.substring(123, 125), 16);
        int LD1 = Integer.parseInt(stringBuffer.substring(121, 123), 16);
        int LD2 = Integer.parseInt(stringBuffer.substring(119, 121), 16);
        int LD3 = Integer.parseInt(stringBuffer.substring(117, 119), 16);
        float LD = GetFloatValue(LD0, LD1, LD2, LD3);
        Log.d("照度", LD + "");
        /**
         * 日辐射量
         */
        int RD0 = Integer.parseInt(stringBuffer.substring(131, 133), 16);
        int RD1 = Integer.parseInt(stringBuffer.substring(129, 131), 16);
        int RD2 = Integer.parseInt(stringBuffer.substring(127, 129), 16);
        int RD3 = Integer.parseInt(stringBuffer.substring(125, 127), 16);
        float RD = GetFloatValue(RD0, RD1, RD2, RD3);
        Log.d("日辐射量", RD + "");
        /**
         * 太阳辐射功率
         */
        int PD0 = Integer.parseInt(stringBuffer.substring(139, 141), 16);
        int PD1 = Integer.parseInt(stringBuffer.substring(137, 139), 16);
        int PD2 = Integer.parseInt(stringBuffer.substring(135, 137), 16);
        int PD3 = Integer.parseInt(stringBuffer.substring(133, 135), 16);
        float PD = GetFloatValue(PD0, PD1, PD2, PD3);
        Log.d("太阳辐射功率", PD + "");
        /**
         * 真实风向
         */
        int RealWindDirect0 = Integer.parseInt(stringBuffer.substring(147, 149), 16);
        int RealWindDirect1 = Integer.parseInt(stringBuffer.substring(145, 147), 16);
        int RealWindDirec2 = Integer.parseInt(stringBuffer.substring(143, 145), 16);
        int RealWindDirect3 = Integer.parseInt(stringBuffer.substring(141, 143), 16);
        float RealWindDirect = GetFloatValue(RealWindDirect0, RealWindDirect1, RealWindDirec2, RealWindDirect3);

        Log.d("真实风向", RealWindDirect + "");

        /**
         * 海拔高度
         */
        int Altitude0 = Integer.parseInt(stringBuffer.substring(155, 157), 16);
        int Altitude1 = Integer.parseInt(stringBuffer.substring(153, 155), 16);
        int Altitude2 = Integer.parseInt(stringBuffer.substring(151, 153), 16);
        int Altitude3 = Integer.parseInt(stringBuffer.substring(149, 151), 16);
        float Altitude = GetFloatValue(Altitude0, Altitude1, Altitude2, Altitude3);
        Log.d("海拔高度", Altitude + "");
        /***
         * 真实风速
         */
        Log.d("ceshi:", stringBuffer.substring(163, 165) + stringBuffer.substring(161, 163) + stringBuffer.substring(159, 161) + stringBuffer.substring(157, 159));
        int RealWindSpeed0 = Integer.parseInt(stringBuffer.substring(163, 165), 16);
        int RealWindSpeed1 = Integer.parseInt(stringBuffer.substring(161, 163), 16);
        int RealWindSpeed2 = Integer.parseInt(stringBuffer.substring(159, 161), 16);
        int RealWindSpeed3 = Integer.parseInt(stringBuffer.substring(157, 159), 16);


        BigDecimal b1 = new BigDecimal(GetFloatValue(RealWindSpeed0, RealWindSpeed1, RealWindSpeed2, RealWindSpeed3));
        float f2 = b1.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        float RealWindSpeed = f2;

        Log.d("真实风速", RealWindSpeed + "");
        String aa = "风向" + WindDirect + "°" + ","
                + "风速" + WindSpeed + "(m/s)" + ","
                + "温度" + Temperature + "(℃)" + ","
                + "湿度" + Humidity + "%" + ","
                + "气压" + AbsoluationPression + "(hPa)" + ","
                + "角度" + Compass + "°" + ","
                + "降雨状态" + ss + ","
                + "降雨强度" + ainFallIntensity + ","
                + "累积雨量" + RainFallSum + ","
                + "航速" + GPSSpeed + "(m/h)" + ","
                + "航向" + GPSCourse + ","
                + "经度" + GPSLongitude + ","
                + "纬度" + GPSLatitude + ","
                + "粉尘浓度" + Dusty + ","
                + "能见度" + MOR + ","
                + "光照度" + LD + ","
                + "日辐射量" + RD + ","
                + "太阳辐射功率" + PD + ","
                + "真实风向" + RealWindDirect + ","
                + "海拔高度" + Altitude + "," +
                "真实风速" + RealWindSpeed + "";
        Log.d("数据", aa);


        writeStr2Txt(aa, Environment.getExternalStorageDirectory().getPath() + "/QxFile.txt");
        for (int i = 0; i < add_sample_qixiang_valueAdapter.getData().size(); i++) {
            if (add_sample_qixiang_valueAdapter.getData().get(i).getFactorname().equals("湿度")) {
                add_sample_qixiang_valueAdapter.getData().get(i).setValue(Humidity + "");
            } else if (add_sample_qixiang_valueAdapter.getData().get(i).getFactorname().equals("大气压")) {
                add_sample_qixiang_valueAdapter.getData().get(i).setValue(AbsoluationPression + "");
            } else if (add_sample_qixiang_valueAdapter.getData().get(i).getFactorname().equals("风速")) {
                add_sample_qixiang_valueAdapter.getData().get(i).setValue(RealWindSpeed + "");
                windSpeed = add_sample_qixiang_valueAdapter.getData().get(i).getValue();
            } else if (add_sample_qixiang_valueAdapter.getData().get(i).getFactorname().equals("风向")) {
                add_sample_qixiang_valueAdapter.getData().get(i).setValue(WindDirect + "");
            } else if (add_sample_qixiang_valueAdapter.getData().get(i).getFactorname().equals("温度")) {
                add_sample_qixiang_valueAdapter.getData().get(i).setValue(Temperature + "");
            }
            add_sample_qixiang_valueAdapter.notifyDataSetChanged();
        }
        judgeVoice_WindSpeed();
    }

    /**
     * 传值 然后返回对应的float 数据
     *
     * @param data1
     * @param data2
     * @param data3
     * @param data4
     * @return
     */
    public float GetFloatValue(int data1, int data2, int data3, int data4) {

        byte[] RealWindSpeedbyte = {(byte) data1, (byte) data2, (byte) data3, (byte) data4};
        return byte2float(RealWindSpeedbyte, 0);
    }

    /**
     * byte转浮点
     *
     * @param b
     * @param index
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

//    public String Indexofss(String ll, String istitle) {
//
//        /**
//         +         * 截取第一次根据 istitle+: 截取  例如 标况体积: 截取到的内容就等于 从标况体积: 开始
//         +         *
//         +         * 然后第二次根据  fistStr 截取 到 第一个 "\n" 【因为上文 我把格式 转成了 \n】 由于indexof 的特性 只会截取到 第一个指定的字符
//         +         *
//         +         * 然后第三次根据 secondStr 截取到 ":"之后  拿到结果；
//         +         *
//         +         * 由于截取到":"之后会包含":" 最终结果 在从第一位往后截取 拿到最终结果;
//         +         *
//         +         */
//        String fistStr = ll.substring(ll.indexOf(istitle + ":"));
//        String secondStr = fistStr.substring(0, fistStr.indexOf("\n"));
//        String endStr = secondStr.substring(secondStr.indexOf(":")).substring(1);
//        String ENDSrt = Float.parseFloat(endStr.substring(0, endStr.indexOf(" "))) + "";
//        return ENDSrt;
//    }

    public String Indexofss(String ll, String istitle) {

        /**
         * 截取第一次根据 istitle+: 截取  例如 标况体积: 截取到的内容就等于 从标况体积: 开始
         *
         * 然后第二次根据  fistStr 截取 到 第一个 "\n" 【因为上文 我把格式 转成了 \n】 由于indexof 的特性 只会截取到 第一个指定的字符
         *
         * 然后第三次根据 secondStr 截取到 ":"之后  拿到结果；
         *
         * 由于截取到":"之后会包含":" 最终结果 在从第一位往后截取 拿到最终结果;
         *
         */
        String ENDSrt = "";
        ViseLog.d("llls" + istitle);
        try {
            if (ll.indexOf(istitle) != -1) {
                if (ll.indexOf(istitle + ":") != -1) {
                    String fistStr = ll.substring(ll.indexOf(istitle + ":"));
                    ViseLog.d("123:" + fistStr);
                    String secondStr = fistStr.substring(0, fistStr.indexOf("\n"));
                    ViseLog.d("456:" + secondStr);
                    String endStr = secondStr.substring(secondStr.indexOf(":")).substring(1);
                    ViseLog.d("llls" + endStr);
                    ENDSrt = Float.parseFloat(endStr.substring(0, endStr.indexOf(" "))) + "";
                    ViseLog.d("llls" + ENDSrt);
                } else {
                    String fistStr = ll.substring(ll.indexOf(istitle));
                    ViseLog.d("123:" + fistStr);
                    String secondStr = fistStr.substring(0, fistStr.indexOf("\n"));
                    ViseLog.d("456:" + secondStr);
                    String endStr = secondStr.substring(secondStr.indexOf(istitle)).substring(istitle.length() + 1);
                    ViseLog.d("llls" + endStr);
                    ENDSrt = Float.parseFloat(endStr.substring(0, endStr.indexOf(" "))) + "";
                    ViseLog.d("llls" + ENDSrt);
                }
            } else {
                ViseLog.d("121313");
//                msg("请仔细检查3012仪器的内容与现场测定数据是否一致!");
                ENDSrt = "";
            }
        } catch (Exception e) {
            ENDSrt = "";
        }
        return ENDSrt;
    }

    /**
     * 气象
     *
     * @param editable
     * @param position
     */
    @Override
    public void Value_qixiang(Editable editable, int position) {
        TasksInfomationBean.PointsBean.ScenefactorsBean sampleCategoryItemsDataBean = add_sample_qixiang_valueAdapter.getData().get(position);
        if (editable.toString() != null) {
            sampleCategoryItemsDataBean.setValue(editable.toString());
        } else {
            sampleCategoryItemsDataBean.setValue("");
        }
    }
}
