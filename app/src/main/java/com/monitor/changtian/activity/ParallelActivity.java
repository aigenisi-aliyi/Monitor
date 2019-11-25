package com.monitor.changtian.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.adapter.Sampleinfo_proAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.EventBus.AddSampleNumEvent;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.TaskinfoView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

@EActivity(R.layout.activity_parallel)
public class ParallelActivity extends BaseActvity implements TaskinfoView {
    TaskinfoPresenter taskinfoPresenter;
    @Extra
    String onlynumber;
    @ViewById(R.id.rv_list_pro)//分析项目
            RecyclerView rv_list_pro;
    @ViewById(R.id.et_samplename)
    TextView et_samplename;
    @ViewById(R.id.tv_bar_code)
    TextView tv_bar_code;
    Sampleinfo_proAdapter sampleinfo_proAdapter;


    @AfterViews
    void init() {
        initImageBackText("样品信息");
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        initData();
    }

    public void initData() {
        ShowDialogtitle("加载中...", this);
        String data = "{sampid:\"\",sampdetailid:\"\",onlynumber:\"" + onlynumber + "\",pointsid:\"\"}";
        taskinfoPresenter.GetfieldsamplingDetail(data);
        initRecycleView();
    }

    public void initRecycleView() {

        /**
         * 分析项目
         */
        sampleinfo_proAdapter = new Sampleinfo_proAdapter(R.layout.cai_item);
        rv_list_pro.setLayoutManager(new GridLayoutManager(ParallelActivity.this, 3));
        rv_list_pro.setAdapter(sampleinfo_proAdapter);

    }


    /**
     * 关联
     */
    @Click(R.id.stv_audit_true)
    public void stv_audit_true() {
        AgainDialog("确认要关联该样品吗?");
    }

    /**
     * 确定
     */
    public void AgainTrue() {
        finish();
        EventBus.getDefault().post(new AddSampleNumEvent(onlynumber));
    }

    /**
     * 取消
     */
    @Click(R.id.stv_false)
    public void stv_false() {

        finish();

    }

    @Override
    public void OnGetTasksInfomation(List<TasksInfomationBean> tasksInfomationBeans) {

    }

    @Override
    public void OnPack(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnUnit(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnStyle(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void onMessage(ResultBean message) {

    }

    @Override
    public void OnFieldsamplingInfo(List<FieldsamplingInfo> fieldsamplingInfos) {

    }

    @Override
    public void OnEndFieldsampling(ResultBean message) {

    }

    @Override
    public void OnGetfieldsamplingDetailList(List<FieldsamplingDetailListBean> fieldsamplingDetailListBeans) {

    }

    @Override
    public void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

        DissDialog();
        if (fieldsamplingDetailBeans != null) {
            sampleinfo_proAdapter.setNewData(fieldsamplingDetailBeans.get(0).getNoscenefactors());
            initView(fieldsamplingDetailBeans.get(0));
        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }

    @Override
    public void OnSoilhumidity(List<DicDataBean> dicDataBeans) {

    }

    @Override
    public void OnSoiltexture(List<DicDataBean> dicDataBeans) {

    }

    public void initView(FieldsamplingDetailBean fieldsamplingDetailBeans) {
        tv_bar_code.setText(fieldsamplingDetailBeans.getOnlynumber());
        et_samplename.setText(fieldsamplingDetailBeans.getSampingname());
    }
}
