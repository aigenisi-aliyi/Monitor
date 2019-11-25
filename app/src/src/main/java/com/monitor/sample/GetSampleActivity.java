package com.monitor.sample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.accuse.AddAccuseActivity;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.RepertoryPresenter;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.adapter.ConsumablesDatasAdapter;
import com.monitor.changtian.adapter.PreInfoDataadapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.EventBus.AddAccEvent;
import com.monitor.changtian.bean.FaceItemBean;
import com.monitor.changtian.bean.GetSampleEvent;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.SamplePresenter;
import com.monitor.changtian.view.RepertoryView;
import com.monitor.changtian.view.SampleView;
import com.monitor.finance.FinanceMainActivity;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_get_sample)
public class GetSampleActivity extends BaseActvity implements SampleView, RepertoryView, SubmitView<List<EquipmentDataBean>> {

    @ViewById(R.id.tv_code)
    TextView tv_code;
    @ViewById(R.id.tv_roomName)
    TextView tv_roomName;
    @ViewById(R.id.tv_qu)
    TextView tv_qu;
    @ViewById(R.id.tv_addressinfo)
    TextView tv_addressinfo;
    @ViewById(R.id.tv_value)
    TextView tv_value;

    @ViewById(R.id.tv_data)
    RadioGroup tv_data;

    @ViewById(R.id.rb_normal)
    RadioButton rb_normal;

    @ViewById(R.id.rb_abnormal)
    RadioButton rb_abnormal;
    @Extra
    SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean;


    @ViewById(R.id.ll_liang)
    LinearLayout ll_liang;
    @ViewById(R.id.ll_ma)
    LinearLayout ll_ma;
    @ViewById(R.id.ll_yihao)
    LinearLayout ll_yihao;

    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;

    @ViewById(R.id.tv_selectperson)
    TextView tv_selectperson;
    SamplePresenter samplePresenter;

    RepertoryPresenter repertoryPresenter;

    ConsumablesDatasAdapter consumablesDatasAdapter;

    @ViewById(R.id.et_status)
    EditText et_status;

    @Extra
    String ids;

    @AfterViews
    void init() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initImageBackText("样品领取");
        initView();
        consumablesDatasAdapter = new ConsumablesDatasAdapter(R.layout.nopoint_select_sing);
        samplePresenter = new SamplePresenter(this, this);
        repertoryPresenter = new RepertoryPresenter(this, this, this);
        initData();
        tv_data.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    /**
                     * 是
                     */
                    case R.id.rb_normal:

                        ll_liang.setVisibility(View.GONE);
                        ll_ma.setVisibility(View.GONE);
                        ll_yihao.setVisibility(View.GONE);
                        break;
                    /**
                     * 否
                     */
                    case R.id.rb_abnormal:
                        ll_liang.setVisibility(View.VISIBLE);
                        ll_ma.setVisibility(View.VISIBLE);
                        ll_yihao.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    public void initView() {
        tv_code.setText(sampleInfoByAssignmentRecordBean.getCol1());
        tv_roomName.setText(sampleInfoByAssignmentRecordBean.getFactorname());
        tv_qu.setText(sampleInfoByAssignmentRecordBean.getMethodname());
        tv_addressinfo.setText(sampleInfoByAssignmentRecordBean.getAddress());
        tv_value.setText(sampleInfoByAssignmentRecordBean.getAmountvalue());

    }

    @Click(R.id.iv_bar_code)
    public void iv_bar_code() {
        ZBarActivity_.intent(GetSampleActivity.this).type("领样").start();
    }

    AlertDialog analysis_diass;


    @ViewById(R.id.tv_selectperson1)
    TextView tv_selectperson1;

    String caid = "";

    @Click(R.id.tv_selectperson1)
    public void tv_selectperson1() {


        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = dicDataBeanss.get(options1).getPickerViewText();
                caid = dicDataBeanss.get(options1).getId() + "";
                tv_selectperson1.setText(content);

                List<EquipmentDataBean> equipmentDataBeans = new ArrayList<>();
                consumablesDatasAdapter.setNewData(equipmentDataBeans);
                consumablesDatasAdapter.setSelection(-1);
                tv_selectperson.setText("");
                ShowDialogtitle("请稍等...", GetSampleActivity.this);
                String data = "{id:\"\",names:\"\",IsEnabled:\"1\",Consumablestype:\"\",sdate:\"\",edate:\"\",categoryid:\"" + caid + "\"}";
                repertoryPresenter.GetConsumablesData(data);

            }
        })
                .setTitleText("选择样品类型")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(dicDataBeanss);//一级选择器
        pvOptions.show();

    }

    /**
     * 选择易耗品
     */
    @Click(R.id.tv_selectperson)
    public void tv_selectperson() {


        if (tv_selectperson1.getText().toString().trim().length() == 0) {
            msg("请选择易耗品分类");
            return;
        }


        final AlertDialog.Builder builder = new AlertDialog.Builder(GetSampleActivity.this);
        View views = LayoutInflater.from(GetSampleActivity.this).inflate(R.layout.analysis_item, null);
        builder.setView(views);
        analysis_diass = builder.show();
        TextView tv_title = views.findViewById(R.id.tv_title);
        tv_title.setText("选择检测因子");
        RecyclerView rv_list = views.findViewById(R.id.rv_list);
        Button btn_submit = views.findViewById(R.id.btn_submit);
        rv_list.setLayoutManager(new GridLayoutManager(GetSampleActivity.this, 3));
        rv_list.setAdapter(consumablesDatasAdapter);
        consumablesDatasAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                consumablesDatasAdapter.setSelection(position);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idss = "";
                String texts = "";
                for (int i = 0; i < consumablesDatasAdapter.getData().size(); i++) {
                    if (consumablesDatasAdapter.getData().get(i).isSelected() == true) {
                        consumableid = consumablesDatasAdapter.getData().get(i).getId();
                        texts = consumablesDatasAdapter.getData().get(i).getNames();
                    }
                }
                tv_selectperson.setText(texts);
                analysis_diass.dismiss();
            }
        });


        Button btn_false = views.findViewById(R.id.btn_false);
        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                analysis_diass.dismiss();
            }
        });

    }

    @Click(R.id.stv_submit)
    public void stv_submit() {


        if (rb_normal.isChecked() || rb_abnormal.isChecked()) {

            if (rb_normal.isChecked()) {
                isall = "1";
            } else if (rb_abnormal.isChecked()) {
                isall = "0";
            }

        } else {
            msg("请选择是否全部领取");
            return;
        }


        if (isall.equals("0")) {
            if (tv_bar_code.getText().toString().length() == 0) {
                msg("请扫描新的条码");
                return;
            }
            if (tv_selectperson.getText().toString().trim().length() == 0) {
                msg("请选择易耗品");
                return;
            }
            if (et_status.getText().toString().trim().length() == 0) {
                msg("请输入领取量");
                return;
            }

            id = ids;
            newcode = tv_bar_code.getText().toString().trim();
            usedamount = et_status.getText().toString().trim();
            loginId = MyApplication.getInstance().getUser();
        } else {
            id = ids;
            newcode = tv_bar_code.getText().toString().trim();
            usedamount = et_status.getText().toString().trim();
            loginId = MyApplication.getInstance().getUser();
        }

        AgainDialog("确认领取该样品吗？");
    }

    public void AgainTrue() {

        Submit(id, newcode, consumableid, usedamount, isall, loginId);
    }


    String id = "";
    String newcode = "";
    String consumableid = "";
    String usedamount = "";
    String isall = "";
    String loginId = "";


    public void Submit(String id, String newcode, String consumableid, String usedamount, String isall, String loginId) {

        ShowDialogtitle("请稍等...", this);
        String data = "{id:\"" + id + "\",newcode:\"" + newcode + "\",consumableid:\"" + consumableid + "\",usedamount:\"" + usedamount + "\",isall:\"" + isall + "\",loginId:\"" + loginId + "\"}";
        samplePresenter.CollarSample(data);
    }

    public void initData() {

        String data = "{Id:\"\",typeCode:\"Consumablescategory\",DataValue:\"\"}";
        repertoryPresenter.GetDevData(data);

    }


    @Override
    public void OnGetsamplemanageInfo(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans) {

    }

    @Override
    public void OnMessage(ResultBean resultBean) {

        DissDialog();
        if (resultBean.getResult().equals("1")) {
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }
    }

    List<AllUserInfo> userInfoList = new ArrayList<>();

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {

        userInfoList.clear();
        userInfoList.addAll(userInfos);
    }

    @Override
    public void OnSampleStatus(SampleStatusBean sampleStatusBeans) {

    }

    @Override
    public void OnRoomList(List<RoomListBean> roomListBeans) {

    }

    @Override
    public void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean) {

    }

    @Override
    public void onData(List<EquipmentDataBean> equipmentDataBeans) {

        DissDialog();
        if (equipmentDataBeans.size() > 0) {
            consumablesDatasAdapter.setNewData(equipmentDataBeans);
        }
    }

    @Override
    public void onMessage(ResultBean t) {


        DissDialog();
        if (t.getResult().equals("1")) {
            msg("成功");
            finish();
        } else {
            msg(t.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


    @Subscribe
    public void GetSampleEventsss(GetSampleEvent event) {
        if (event != null) {
            tv_bar_code.setText(event.getCodeNum());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    List<DicDataBean> dicDataBeanss = new ArrayList<>();

    @Override
    public void OnDicDataBean(List<DicDataBean> dicDataBeans) {
        dicDataBeanss = new ArrayList<>();
        dicDataBeanss.addAll(dicDataBeans);
    }
}
