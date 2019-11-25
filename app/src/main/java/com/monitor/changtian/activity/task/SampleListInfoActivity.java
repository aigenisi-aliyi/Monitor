package com.monitor.changtian.activity.task;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.AddSampleInfoActivity_;
import com.monitor.changtian.adapter.SampleListInfoAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.FieldsamplingDetailBean;
import com.monitor.changtian.bean.FieldsamplingDetailListBean;
import com.monitor.changtian.bean.FieldsamplingInfo;
import com.monitor.changtian.bean.JumpEvent;
import com.monitor.changtian.bean.JumpFrequencyEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.bean.TasksInfomationBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.TaskPresenter;
import com.monitor.changtian.presenter.TaskinfoPresenter;
import com.monitor.changtian.view.TaskinfoView;
import com.monitor.changtian.widght.CustomFAB;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

@EActivity(R.layout.activity_sample_list_info)
public class SampleListInfoActivity extends BaseActvity implements TaskinfoView, SubmitView<List<TaskListBean>> {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    TaskinfoPresenter taskinfoPresenter;
    @Extra
    String pointsid;
//    @Extra
//    String isstationarysource;
    @Extra
    String currentnumber;
    @Extra
    String taskid;
    @Extra
    String status;
    @Extra
    String IsEnd;
    @Extra
    String ishadden;

    @Extra
    int potions_index;
    SampleListInfoAdapter sampleListInfoAdapter;
    @ViewById(R.id.cf_imageButton)
    CustomFAB cf_imageButton;

    TaskPresenter taskPresenter;


    @AfterViews
    void init() {
        initImageBackText("第" + currentnumber + "频次样品记录列表");

//        msg(ishadden + "");


        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        taskPresenter = new TaskPresenter(this, this);
        taskinfoPresenter = new TaskinfoPresenter(this, this);
        sampleListInfoAdapter = new SampleListInfoAdapter(R.layout.samplelist_info_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(sampleListInfoAdapter);
//        initData();
        sampleListInfoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * 查看详情
                 */
                SampleinfoActivity_.intent(SampleListInfoActivity.this).sampid(sampleListInfoAdapter.getData().get(position).getSampdetailid()).start();
            }
        });
    }

    @Click(R.id.cf_imageButton)
    public void cf_imageButton() {
        AddSampleInfoActivity_.intent(this).taskid(taskid).POINTSIDS(pointsid).CURRENTNUMBER(currentnumber).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initData();
    }

    public void initData() {
        ShowDialogtitle("加载中...", SampleListInfoActivity.this);

        String data1 = "{id:\"" + taskid + "\",conid:\"\",pagenumber:\"\",numbers:\"\",sampingloginId:\"" + MyApplication.getInstance().getUser() + "\",taskstatus:\"1,3\"}";
        taskPresenter.GetTaskList(data1);


        String data = "{sampid:\"\" ,pointsid:\"" + pointsid + "\",taskid:\"" + taskid + "\",currentnumber:\"" + currentnumber + "\",sampdetailid:\"\",onlynumber:\"\"}";
        taskinfoPresenter.GetfieldsamplingDetailList(data);
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
    public void onData(List<TaskListBean> taskListBeans) {
        if (taskListBeans.size() > 0) {

            for (int i = 0; i < taskListBeans.get(0).getPoints().size(); i++) {
                if (pointsid.equals(taskListBeans.get(0).getPoints().get(i).getPointsid())) {
                    if (status != null) {
                        cf_imageButton.setVisibility(View.GONE);
                    } else {
                        if (taskListBeans.get(0).getPoints().get(i).getIshadden().equals("1")) {
                            cf_imageButton.setVisibility(View.GONE);
                        } else {
                            for (int a = 0; a < taskListBeans.get(0).getPoints().get(i).getFrequencys().size(); a++) {
                                if (currentnumber.equals(taskListBeans.get(0).getPoints().get(i).getFrequencys().get(a).getFrequencyname())) {
                                    if (taskListBeans.get(0).getPoints().get(i).getFrequencys().get(a).getIsEnd().equals("1")) {
                                    } else {
                                        cf_imageButton.setVisibility(View.VISIBLE);
                                    }
                                }
                            }
                        }
                    }
                }
            }


        }


//        if (status != null) {
//            cf_imageButton.setVisibility(View.GONE);
//        } else {
//
//            if (ishadden.equals("1")) {
//                cf_imageButton.setVisibility(View.GONE);
//            } else {
//                if (IsEnd.equals("1")) {
//
//                } else {
//                    cf_imageButton.setVisibility(View.VISIBLE);
//                }
//            }
//
//        }


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

        ViseLog.d("asdadad");
        DissDialog();
        if (fieldsamplingDetailListBeans.size() > 0) {
            sampleListInfoAdapter.setNewData(fieldsamplingDetailListBeans);
        } else {
            sampleListInfoAdapter.setNewData(fieldsamplingDetailListBeans);
        }

    }

    @Override
    public void OnGetfieldsamplingDetail(List<FieldsamplingDetailBean> fieldsamplingDetailBeans) {

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


    @Subscribe
    public void JumpFrequce(JumpFrequencyEvent event) {

        if (event != null) {

            int crr = Integer.parseInt(currentnumber);
            if (event.getPointsBeanList().get(potions_index - 1).getFrequencys().get(crr - 1).getIsEnd().equals("1")) {
                cf_imageButton.setVisibility(View.GONE);
            } else {
                cf_imageButton.setVisibility(View.VISIBLE);
            }
        }

    }
}
