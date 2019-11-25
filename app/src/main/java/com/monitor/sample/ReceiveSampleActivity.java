package com.monitor.sample;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.presenter.QueryBasicsPresenter;
import com.monitor.changtian.presenter.SamplePresenter;
import com.monitor.changtian.view.QueryBasicsView;
import com.monitor.changtian.view.SampleView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 样品录入
 */
@EActivity(R.layout.activity_receive_sample)
public class ReceiveSampleActivity extends BaseActvity implements SampleView,QueryBasicsView {


    @ViewById(R.id.tv_selectperson)
    TextView tv_selectperson;
    @ViewById(R.id.et_value)
    EditText et_value;
    @ViewById(R.id.tv_room)
    TextView tv_room;
    @ViewById(R.id.et_addressInfo)
    EditText et_addressInfo;
    SamplePresenter samplePresenter;
    @ViewById(R.id.et_code)
    TextView et_code;
    @ViewById(R.id.et_remark)
    EditText et_remark;
    @ViewById(R.id.tv_unit)
    TextView tv_unit;
    @Extra
    String code; // 样品编号
    @ViewById(R.id.tv_sampingPacking)
    TextView tv_sampingPacking;  //包装
    @ViewById(R.id.tv_sampingStatus)
    TextView tv_sampingStatus;  //状态
    @ViewById(R.id.tv_samplemode)
    TextView tv_samplemode;  //来源
    @ViewById(R.id.stv_false)
    SuperTextView stv_false;
    @ViewById(R.id.et_jushou)
    EditText et_jushou;
    @ViewById(R.id.ll_baozhuang)
    LinearLayout ll_baozhuang;
    @ViewById(R.id.ll_zhuangtai)
    LinearLayout ll_zhuangtai;
    @ViewById(R.id.iv_image2)
    ImageView iv_image2;
    QueryBasicsPresenter queryBasicsPresenter;

    @AfterViews
    void init() {
//        initToolbar("录入样品");
        initImageBackText("样品录入");
        if (code != null) {
            et_code.setText(code);
        }
        samplePresenter = new SamplePresenter(this, this);
        queryBasicsPresenter = new QueryBasicsPresenter(this,this);
        initData();
    }

    public void initData() {
        String Person_data = "{loginName:\"\",querystring:\"\",rolename:\"\"}";
        samplePresenter.GetAllUserInfo(Person_data);
        /**
         * 查询样品室
         */
        String data = "{id:\"\",roomname:\"\",roomtype:\"282\"} ";
        samplePresenter.GetRoomList(data);

        /**
         * 查询样品状态包装等信息
         */
        String data_sample = "{onlynumber:\"" + code + "\"} ";
        samplePresenter.GetsampleInfoByonlynumber(data_sample);
    }

    List<RoomListBean> roomListBeanArrayList = new ArrayList<>();

    String roomid = "";

    @Click(R.id.tv_room)
    public void tv_room() {
        hintKbTwo();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(ReceiveSampleActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = roomListBeanArrayList.get(options1).getPickerViewText();
                sampleroomid = roomListBeanArrayList.get(options1).getId() + "";
                tv_room.setText(content);
            }
        })
                .setTitleText("请选择样品室")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(roomListBeanArrayList);//一级选择器
        pvOptions.show();
    }

    /**
     * 提交按钮；
     */
    String samplenumber = "";
    String userid = "";
    String type = "";
    String sampleroomid = "";
    String address = "";
    String amountvalue = "";
    String remark = "";

    @Click(R.id.stv_login)
    public void stv_login() {

        if (et_code.getText().toString().trim().length() == 0) {
            msg("请输入样品编号");
            return;
        } else {
            samplenumber = et_code.getText().toString().trim();
        }
        if (tv_selectperson.getText().toString().trim().length() == 0) {
            msg("请选择送样人");
            return;
        }
        if (et_value.getText().toString().trim().length() == 0) {
            msg("请输入送样量");
            return;
        } else {
            amountvalue = et_value.getText().toString().trim() + " " + tv_unit.getText().toString().trim();
        }
        if (tv_room.getText().toString().trim().length() == 0) {
            msg("请选择样品室");
            return;
        }

        if (et_addressInfo.getText().toString().trim().length() == 0) {
            msg("请输入具体位置");
            return;
        } else {
            address = et_addressInfo.getText().toString().trim();
        }
        remark = et_remark.getText().toString().trim();
        /**
         * 二次弹框确认
         */
        issubmit = true;
        AgainDialog("确认接收样品信息吗?");
    }

    boolean issubmit;

    @Click(R.id.stv_false)
    public void stv_false() {

        if (et_code.getText().toString().trim().length() == 0) {
            msg("请输入样品编号");
            return;
        } else {
            samplenumber = et_code.getText().toString().trim();
        }

        if (tv_selectperson.getText().toString().trim().length() == 0) {
            msg("请选择送样人");
            return;
        }


        if (et_jushou.getText().toString().trim().length() == 0) {
            msg("请输入拒收原因");
            return;
        } else {
            reason = et_jushou.getText().toString().trim();
        }
        remark = et_remark.getText().toString().trim();
        issubmit = false;
        AgainDialog("确认要拒收样品信息吗?");
    }


    String reason = "";

    public void AgainTrue() {
        if (issubmit) {
            ShowDialogtitle("请稍等...", this);
            String data = "{samplenumber:\"" + samplenumber + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",userid:\"" + userid + "\",type:\"1\",sampleroomid:\"" + sampleroomid + "\",address:\"" + address + "\",amountvalue:\"" + amountvalue + "\",remark:\"" + remark + "\"}";
            samplePresenter.Addsamplemanage(data);
        } else {
            ShowDialogtitle("请稍等...", this);
            String data = "{onlynumber:\"" + samplenumber + "\",reason:\"" + reason + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",remark:\"" + remark + "\",sendsampleuser:\"" + userid + "\"}";
            samplePresenter.Addsamplerejection(data);
        }

    }


    /**
     * 选择领样人
     */
    @Click(R.id.tv_selectperson)
    public void tv_selectperson() {
        hintKbTwo();

        if(userInfoList.size()>0){
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(ReceiveSampleActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String content = userInfoList.get(options1).getPickerViewText();
                    userid = userInfoList.get(options1).getPersonid() + "";
                    tv_selectperson.setText(content);
                }
            })
                    .setTitleText("请选择送样人")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(true)// default is true
                    .build();
            pvOptions.setPicker(userInfoList);//一级选择器
            pvOptions.show();
        }else{
            OptionsPickerView pvOptions = new OptionsPickerView.Builder(ReceiveSampleActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String content = userInfoListss.get(options1).getPickerViewText();
                    userid = userInfoListss.get(options1).getId() + "";
                    tv_selectperson.setText(content);
                }
            })
                    .setTitleText("请选择送样人")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(true)// default is true
                    .build();
            pvOptions.setPicker(userInfoListss);//一级选择器
            pvOptions.show();
        }




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

    List<SampleStatusBean.TaskpersonsBean> userInfoList = new ArrayList<>();
    List<AllUserInfo> userInfoListss = new ArrayList<>();

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {
        userInfoListss.clear();
        userInfoListss.addAll(userInfos);
    }

    @Override
    public void OnAllDevice(List<EquipmentDataBean> equipmentDataBeans) {

    }

    @Override
    public void OnSampleStatus(SampleStatusBean sampleStatusBeans) {


        if (sampleStatusBeans != null) {

            if (sampleStatusBeans.getTaskpersons() != null) {
                if (sampleStatusBeans.getTaskpersons().size() > 0) {
                    userInfoList.clear();
                    userInfoList.addAll(sampleStatusBeans.getTaskpersons());
                }
            } else {

                String Person_data = "{loginName:\"\",querystring:\"\",rolename:\"\"}";
                queryBasicsPresenter.GetAllUserInfo(Person_data);
            }

            if (sampleStatusBeans.getIsinvalid() != null) {
                if (sampleStatusBeans.getIsinvalid().equals("0")) {

                    if (sampleStatusBeans.getStatevalue() != null) {
                        if (sampleStatusBeans.getStatevalue().equals("1")) {
                            iv_image2.setVisibility(View.VISIBLE);
                            iv_image2.setImageResource(R.drawable.tobecollated);
                        } else if (sampleStatusBeans.getStatevalue().equals("2")) {
                            iv_image2.setVisibility(View.VISIBLE);
                            iv_image2.setImageResource(R.drawable.collated);
                        } else if (sampleStatusBeans.getStatevalue().equals("3")) {
                            iv_image2.setVisibility(View.VISIBLE);
                            iv_image2.setImageResource(R.drawable.audover);
                        } else if (sampleStatusBeans.getStatevalue().equals("4")) {
                            iv_image2.setVisibility(View.VISIBLE);
                            iv_image2.setImageResource(R.drawable.audpause);
                        } else {
                            iv_image2.setVisibility(View.GONE);
                        }
                    }
                } else {
                    iv_image2.setVisibility(View.VISIBLE);
                    iv_image2.setImageResource(R.mipmap.sx);
                }
            } else {
                iv_image2.setVisibility(View.GONE);
            }
            if (sampleStatusBeans.getSampingPacking() != null) {

                if (sampleStatusBeans.getSampingPacking().length() > 0) {
                    tv_sampingPacking.setText(sampleStatusBeans.getSampingPacking());
                    ll_baozhuang.setVisibility(View.VISIBLE);
                } else {
                    ll_baozhuang.setVisibility(View.GONE);
                }
            } else {
                ll_baozhuang.setVisibility(View.GONE);
            }


            if (sampleStatusBeans.getSampingStatus() != null) {
                if (sampleStatusBeans.getSampingStatus().length() > 0) {
                    tv_sampingStatus.setText(sampleStatusBeans.getSampingStatus());
                    ll_zhuangtai.setVisibility(View.VISIBLE);
                } else {
                    ll_zhuangtai.setVisibility(View.GONE);
                }
            } else {
                ll_zhuangtai.setVisibility(View.GONE);
            }


            et_value.setText(sampleStatusBeans.getSamplingamount());
            tv_unit.setText(sampleStatusBeans.getSamplingunitname());
            et_remark.setText(sampleStatusBeans.getRemark());
            if (sampleStatusBeans.getSamplemode() == 0) {
                tv_samplemode.setText("现场采样");
            } else {
                tv_samplemode.setText("客户送样");
            }
        } else {
            tv_samplemode.setText("");
            tv_sampingStatus.setText("");
            tv_sampingPacking.setText("");
        }
    }


    @Override
    public void OnRoomList(List<RoomListBean> roomListBeans) {

        roomListBeanArrayList = new ArrayList<>();
        roomListBeanArrayList.addAll(roomListBeans);
    }

    @Override
    public void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean) {

    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}
