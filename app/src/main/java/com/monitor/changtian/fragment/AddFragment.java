package com.monitor.changtian.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.adapter.MyGrideViewAdapter;
import com.monitor.changtian.adapter.PopupWindowAdapter;
import com.monitor.changtian.adapter.SampleCategoryItemAdapter;
import com.monitor.changtian.adapter.SampleTypeAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.EventBus.VisibilityEvent;
import com.monitor.changtian.bean.PhotoModel;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.bean.SampleInfoDataBean;
import com.monitor.changtian.bean.SampleSubmitBean;
import com.monitor.changtian.bean.SampleType;
import com.monitor.changtian.presenter.AddPresenter;
import com.monitor.changtian.utils.BackgroundDarkPopupWindow;
import com.monitor.changtian.utils.DateUtil;
import com.monitor.changtian.utils.StringUtils;
import com.monitor.changtian.view.AddView;
import com.monitor.changtian.widght.AutoHeightGridView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
//import com.monitor.zxing.activity.CaptureActivity;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.fragment_add)
public class AddFragment extends BaseFragment implements AddView, SampleCategoryItemAdapter.OnEditValueChangedListener {


    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    TextView add_tv_name;
    @ViewById(R.id.ll_bg)
    LinearLayout ll_bg;
    TextView add_tv_weather;
    TextView add_tv_conNum;
    @ViewById(R.id.ll_type)  //选择类型
            LinearLayout ll_type;
    @ViewById(R.id.add_tv_type)
    TextView add_tv_type;
    @ViewById(R.id.ll_barnum)
    LinearLayout ll_barnum;
    MyGrideViewAdapter myGrideViewAdapter;
    SampleCategoryItemAdapter sampleCategoryItemAdapter;

    /**
     * Presenter
     */
    private PopupWindowAdapter popupWindowAdapter;
    private AddPresenter addPresenter;
    private List<SampleInfoDataBean> testData = new ArrayList<>();
    /**
     * 数据适配器
     */
    private ArrayAdapter<String> testDataAdapter;
    LinearLayout ll_popn, ll_foot;
    TextView tv_bar_code;
    RecyclerView recycler_image, recycler_video;
    SuperTextView stv_submit;
    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();
    GridImageAdapter adapter_image, adapter_video;

    @AfterViews
    void init() {

        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        addPresenter = new AddPresenter(this, getActivity());
        /**
         * 获取类型列表
         */

        sampleTypeAdapter = new SampleTypeAdapter(R.layout.dialog_items);
        sampleCategoryItemAdapter = new SampleCategoryItemAdapter(R.layout.sample_items_info);
        sampleCategoryItemAdapter.setmEditListenter(this);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(sampleCategoryItemAdapter);

        /***
         * 添加头布局
         */
        View view1 = getActivity().getLayoutInflater().inflate(R.layout.add_rv_head, (ViewGroup) rv_list.getParent(), false);
        sampleCategoryItemAdapter.addHeaderView(view1);
        ll_popn = view1.findViewById(R.id.ll_popn);
        add_tv_weather = view1.findViewById(R.id.add_tv_weather);
        add_tv_conNum = view1.findViewById(R.id.add_tv_conNum);
        add_tv_name = view1.findViewById(R.id.add_tv_name);
        tv_bar_code = view1.findViewById(R.id.tv_bar_code);

        OnHeadcliks();
        /**
         * 添加尾布局
         */
        View view = getActivity().getLayoutInflater().inflate(R.layout.add_rv_foot, (ViewGroup) rv_list.getParent(), false);
        sampleCategoryItemAdapter.addFooterView(view);
        ll_foot = view.findViewById(R.id.ll_foot);
        stv_submit = view.findViewById(R.id.stv_submit);


        recycler_image = (RecyclerView) view.findViewById(R.id.recycler_image);
        FullyGridLayoutManager manager_image = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recycler_image.setLayoutManager(manager_image);
        adapter_image = new GridImageAdapter(getActivity(), onAddPicClickListener, "image");
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
                PictureSelector.create(AddFragment.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
            }
        });

        recycler_video = (RecyclerView) view.findViewById(R.id.recycler_video);
        FullyGridLayoutManager manager_video = new FullyGridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        recycler_video.setLayoutManager(manager_video);
        adapter_video = new GridImageAdapter(getActivity(), onAddPicClickListener, "video");
        adapter_video.setList(selectList_video);
        adapter_video.setSelectMax(9);
        recycler_video.setAdapter(adapter_video);
        adapter_video.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_video.get(position);
                String pictureType = media.getPictureType();
                PictureSelector.create(AddFragment.this).externalPictureVideo(media.getPath());
            }
        });

        OnFootCliks();
        String data = "{Id:\"\",typeCode:\"SampleCategory\",DataValue:\"\"} ";
        addPresenter.GetDicData(data);
    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(String type) {

            switch (type) {
                case "video":
                    PictureSelector.create(AddFragment.this)
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
                    PictureSelector.create(AddFragment.this)
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
                    PictureSelector.create(AddFragment.this)
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
            }
        }
    }

    /**
     * 头布局点击事件
     *
     * @param
     */
    public void OnHeadcliks() {
        tv_bar_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });
    }

    /**
     *
     */
    public void OnFootCliks() {
        stv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv_submit();
            }
        });
    }

    public void add_tv_name() {
        String data = "{SampleId:\"\",ProjectName:\"\",ContractNum:\"\",loginId:\"admin\",sdate:\"\",edate:\"\",categoryid:\"" + categoryid + "\",accords:\"\"}";
        addPresenter.GetSampleInfoData(data);

    }

    AlertDialog dia;


    /**
     * 列表 dialog
     */
    public void ShowDialog(int layoutId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(layoutId, null);
        builder.setView(view);
        dia = builder.show();
        dia.setCanceledOnTouchOutside(false);
        RecyclerView recyclerView = view.findViewById(R.id.select);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        popupWindowAdapter = new PopupWindowAdapter(R.layout.popup_text_item);
        popupWindowAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SampleId = popupWindowAdapter.getData().get(position).getSampleId();
                //填充基本信息
                add_tv_name.setText(popupWindowAdapter.getData().get(position).getProjectName());
                add_tv_conNum.setText(popupWindowAdapter.getData().get(position).getContractNum());
                add_tv_weather.setText(popupWindowAdapter.getData().get(position).getWeatherCondition());
                dia.dismiss();
            }
        });

        recyclerView.setAdapter(popupWindowAdapter);
        popupWindowAdapter.addData(testData);

    }


    BackgroundDarkPopupWindow popupWindow;

    private void showPopupWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.select);

        popupWindow = new BackgroundDarkPopupWindow(add_tv_name, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        popupWindow.setDarkColor(Color.parseColor("#9e9e9e"));//颜色
        popupWindow.showAsDropDown(add_tv_name);

    }

    String SampleId = "";
    String SampleOnlyNum = "";
    String SampleNum = "";
    String loginId = "admin";
    String remark = "";
    String details = "";
    private List<LocalMedia> selectList_All = new ArrayList<>();

    public void stv_submit() {


        if (ISEmpty("请扫描二维码", tv_bar_code.getText().toString().trim())) {
            return;
        } else {
            SampleNum = tv_bar_code.getText().toString().trim();
        }
        selectList_All = new ArrayList<>();
        selectList_All.addAll(selectList_image);
        selectList_All.addAll(selectList_video);

        List<File> imageFile1 = new ArrayList<>();
        for (int i = 0; i < selectList_All.size(); i++) {
            if (!StringUtils.isUrl(selectList_All.get(i).getPath())) {
                imageFile1.add(new File(selectList_All.get(i).getPath()));
            }
        }

        List<SampleSubmitBean> beanList = new ArrayList<>();
        for (int i = 0; i < sampleCategoryItemAdapter.getData().size(); i++) {

            SampleSubmitBean sampleSubmitBean = new SampleSubmitBean();
            sampleSubmitBean.setItemid(sampleCategoryItemAdapter.getData().get(i).getItemsid());
            sampleSubmitBean.setItemvalue(sampleCategoryItemAdapter.getData().get(i).getItemvalue());
            beanList.add(sampleSubmitBean);
        }

        details = JSON.toJSONString(beanList);

        String data = "{SampleId:\"" + SampleId + "\",SampleOnlyNum:\"" + SampleOnlyNum + "\",SampleNum:\"" + SampleNum + "\",loginId:\"" + loginId + "\",remark:\"" + remark + "\",details:" + details + " }";
//        addPresenter.submitInfo(data, imageFile1, 0);
        addPresenter.AddSampleDetailsInfo(data, imageFile1);
    }

    //添加图片
    ArrayList<PhotoModel> minitDatas = new ArrayList<>();
    List<PhotoModel> photosModel = new ArrayList<>();
    ArrayList<String> photos = new ArrayList<>();

    //隐藏布局
    public void Water_Visibility() {
        //隐藏图片
        selectList_image.clear();
        selectList_video.clear();
        selectList_All.clear();
        photosModel.clear();
        photos.clear();
        tv_bar_code.setText("");
        add_tv_name.setText("");
        add_tv_weather.setText("");
        add_tv_conNum.setText("");
        add_tv_type.setText("");
        ll_popn.setVisibility(View.GONE);
        ll_foot.setVisibility(View.GONE);
        sampleCategoryItemAdapter.getData().clear();
        sampleCategoryItemAdapter.notifyDataSetChanged();
        adapter_video.notifyDataSetChanged();
        adapter_image.notifyDataSetChanged();

    }

    @Subscribe
    public void Visibility(VisibilityEvent event) {
        ViseLog.d("lsls", "123");
        if (event != null) {

            //初始化
            Water_Visibility();
            //隐藏布局

        }
    }

//    @Click(R.id.tv_time)
//    public void tv_time() {
//
//        showSelectDate(tv_time);
//    }


    //日历日期选择。
    Calendar selectCalendar;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startTime;

    private void showSelectDate(final TextView time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
        datePicker.setMinDate(new Date().getTime());
        datePicker.setMaxDate(DateUtil.getStringToDate("2300-01-01", "yyyy-MM-dd"));
        datePickerDialog.setTitle("开始时间");
        datePickerDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    String[] single_list = {};
    AlertDialog dia_selector;

    //    List<SampleType> sampleTypes = new ArrayList<>();
    SampleTypeAdapter sampleTypeAdapter;


    String categoryid = "";

    //选择类型
    @Click(R.id.add_tv_type)
    public void add_tv_type() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_selector_item, null);
        builder.setView(view);
        RecyclerView recyclerView = view.findViewById(R.id.rv_list_diolog);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(sampleTypeAdapter);
        dia_selector = builder.show();
        Window window = dia_selector.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        //获取通知栏高度  重要的在这，获取到通知栏高度
        int notificationBar = Resources.getSystem().getDimensionPixelSize(
                Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
        //获取控件 textview 的绝对坐标,( y 轴坐标是控件上部到屏幕最顶部（不包括控件本身）)
        //location [0] 为x绝对坐标;location [1] 为y绝对坐标
        int[] location = new int[2];
        add_tv_type.getLocationInWindow(location); //获取在当前窗体内的绝对坐标
        add_tv_type.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标

        wlp.x = 0; //对 dialog 设置 x 轴坐标
        wlp.y = location[1] + add_tv_type.getHeight() - notificationBar; //对dialog设置y轴坐标
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        sampleTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Water_Visibility();


                add_tv_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_tv_name();
                    }
                });

                ll_popn.setVisibility(View.VISIBLE);
                ll_foot.setVisibility(View.VISIBLE);
                add_tv_type.setText(sampleTypeAdapter.getData().get(position).getDataValue());
                categoryid = sampleTypeAdapter.getData().get(position).getId() + "";
                //获取对应填写信息列表
                sampleCategoryItemsDataBeanList = new ArrayList<>();
                String data = "{itemsid:\"\",categoryid:\"" + categoryid + "\"}";
                addPresenter.GetSampleCategoryItemsData(data);
                //点击了一个选项之后 关闭 并且查询对应项目信息
                dia_selector.dismiss();
            }
        });
    }


    @Subscribe
    public void BarCodeEv(BarCodeEvent event) {
        if (event != null) {
            tv_bar_code.setText(event.getCodeNum());

        }
    }


    @Override
    public void onTypeData(List<SampleType> sampleType) {
        if (sampleType != null) {
            sampleTypeAdapter.addData(sampleType);
        }
    }

    @Override
    public void GetSampleInfoData(List<SampleInfoDataBean> sampleInfoDataBeans) {


        if (sampleInfoDataBeans != null && sampleInfoDataBeans.size() > 0) {

            testData = new ArrayList<>();
            testData = sampleInfoDataBeans;
            ShowDialog(R.layout.proname_item);
        }
    }

    AutoHeightGridView gridView;

    List<SampleCategoryItemsDataBean> sampleCategoryItemsDataBeanList = new ArrayList<>();

    @Override
    public void GetSampleCategoryItemsData(List<SampleCategoryItemsDataBean> sampleCategoryItemsDataBeans) {
        sampleCategoryItemsDataBeanList = new ArrayList<>();
        sampleCategoryItemsDataBeanList.addAll(sampleCategoryItemsDataBeans);
        sampleCategoryItemAdapter.setNewData(sampleCategoryItemsDataBeanList);
    }

    @Override
    public void onMessage(ResultBean message) {

        if (message.getResult().equals("1")) {
            msg("成功");
            Water_Visibility();

        } else {
            msg(message.getErrormessage());
        }
    }

    @Override
    public void Value(Editable editable, int position) {
        SampleCategoryItemsDataBean sampleCategoryItemsDataBean = sampleCategoryItemAdapter.getData().get(position - 1);
        if (editable.toString() != null) {
            sampleCategoryItemsDataBean.setItemvalue(editable.toString());
        } else {
            sampleCategoryItemsDataBean.setItemvalue("");
        }

    }
}
