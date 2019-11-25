package com.monitor.sample;

import android.content.Intent;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.ZBarActivity_;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.EventBus.BarCodeEvent;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.presenter.SampleCodePresenter;
import com.monitor.changtian.presenter.SamplePresenter;
import com.monitor.changtian.view.SampleCodeView;
import com.monitor.changtian.view.SampleView;
//import com.monitor.zxing.activity.CaptureActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * 样品界面
 */
@EActivity(R.layout.activity_sample)
public class SampleActivity extends BaseActvity implements SampleView, SampleCodeView {

    SamplePresenter samplePresenter;
    SampleCodePresenter sampleCodePresenter;

    @Extra
    String title;

    @AfterViews
    void init() {

        initImageBackText(title);
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        sampleCodePresenter = new SampleCodePresenter(this, this);
        samplePresenter = new SamplePresenter(this, this);

    }

    @Click(R.id.ll_onclik)
    public void ll_onclik() {
        SampleInfoActivity_.intent(this).start();
    }

    /**
     * 扫描
     */
    @Click(R.id.iv_scan)
    public void iv_scan() {
//        startActivity(new Intent(this, CaptureActivity.class));
        ZBarActivity_.intent(this).start();
    }

    String code = "";

    @Subscribe
    public void BarCodeEv(BarCodeEvent event) {
        if (event != null) {
            ShowDialogtitle("请稍等...", this);
            code = event.getCodeNum();


            switch (title) {

                case "收样":
                    /**
                     * 查询样品状态包装等信息
                     */
                    String data_sample = "{onlynumber:\"" + code + "\"} ";
                    samplePresenter.GetsampleInfoByonlynumber(data_sample);
                    break;

                case "领样":


                    String data = "{id:\"" + code + "\"}";
                    samplePresenter.GetSampleInfoByAssignmentRecord(data);

                    break;
            }


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus7
    }

    @Override
    public void OnGetsamplemanageInfo(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans) {
        DissDialog();
        if (getsamplemanageInfoBeans != null) {
            if (getsamplemanageInfoBeans.size() > 0) {
//                GetSampleActivity_.intent(this).getsamplemanageInfoBean(getsamplemanageInfoBeans.get(0)).start();
            } else {
//                //送样状态填写对应送样信息
//
//                msg("当前样品不存在!");
            }
        } else {
            msg("当前样品不存在!");
        }
    }

    @Override
    public void OnMessage(ResultBean resultBean) {

    }

    @Override
    public void OnAllPerson(List<AllUserInfo> userInfos) {

    }

    @Override
    public void OnSampleStatus(SampleStatusBean sampleStatusBeans) {

        DissDialog();
        if (sampleStatusBeans.getSamplingamount() == null) {
            msg("当前样品不存在!");
        } else {

            if (sampleStatusBeans.getStatevaluename() != null) {
                if (sampleStatusBeans.getStatevaluename().equals("审核未通过")) {
                    msg("当前样品已失效!");
                }  else if(sampleStatusBeans.getStatevaluename().equals("已校对")){
                    msg("当前样品未审核!");
                } else {
                    ReceiveSampleActivity_.intent(this).code(code).start();
                }
            } else {
                ReceiveSampleActivity_.intent(this).code(code).start();
            }
        }
    }

    @Override
    public void OnRoomList(List<RoomListBean> roomListBeans) {

    }

    /**
     * 领取样品
     *
     * @param sampleInfoByAssignmentRecordBean
     */
    @Override
    public void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean) {

        DissDialog();

        if (sampleInfoByAssignmentRecordBean != null) {
            GetSampleActivity_.intent(this).ids(code).sampleInfoByAssignmentRecordBean(sampleInfoByAssignmentRecordBean).start();
        }

    }

    @Override
    public void OnSampleCode(ResultBean resultBean) {

        if (resultBean.getResult().length() > 0) {
            code = resultBean.getResult();
            /**
             * 查询样品状态包装等信息
             */
            String data_sample = "{onlynumber:\"" + code + "\"} ";
            samplePresenter.GetsampleInfoByonlynumber(data_sample);
        } else {
            DissDialog();
            msg("当前条码不存在!");
        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}
