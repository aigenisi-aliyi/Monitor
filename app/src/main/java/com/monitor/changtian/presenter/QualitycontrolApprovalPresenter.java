package com.monitor.changtian.presenter;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.observer.CommonObserver;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;
import com.monitor.changtian.network.ApiService;
import com.monitor.changtian.view.QualitycontrolApprovalView;
import com.monitor.changtian.view.QueryBasicsView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/12/19.
 */

public class QualitycontrolApprovalPresenter {

    private QualitycontrolApprovalView qualitycontrolApprovalView;

    public QualitycontrolApprovalPresenter(QualitycontrolApprovalView qualitycontrolApprovalView) {
        this.qualitycontrolApprovalView = qualitycontrolApprovalView;
    }


    public void GetTestProjectCycleList(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetTestProjectCycleList(data)
                .compose(Transformer.<List<TestProjectCycleListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<TestProjectCycleListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        qualitycontrolApprovalView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<TestProjectCycleListBean> testProjectCycleListBeans) {
                        if (testProjectCycleListBeans != null) {
                            qualitycontrolApprovalView.OnGetTestProjectCycleList(testProjectCycleListBeans);
                        }
                    }
                });
    }

    public void GetprojectTestList(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .GetprojectTestList(data)
                .compose(Transformer.<List<ProjectTestListBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<ProjectTestListBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        qualitycontrolApprovalView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<ProjectTestListBean> testProjectCycleListBeans) {
                        if (testProjectCycleListBeans != null) {
                            qualitycontrolApprovalView.OnProjectTestListBean(testProjectCycleListBeans);
                        }
                    }
                });
    }

    public void QueryTestRecord(String data) {
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .QueryTestRecord(data)
                .compose(Transformer.<List<QueryTestRecordBean>>switchSchedulers())
                .subscribe(new CommonObserver<List<QueryTestRecordBean>>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        qualitycontrolApprovalView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(List<QueryTestRecordBean> queryTestRecordBeans) {
                        if (queryTestRecordBeans != null) {
                            qualitycontrolApprovalView.OnQueryTestRecordBeans(queryTestRecordBeans);
                        }
                    }
                });
    }

    public void qcApproveprojectTest(String data) {

        Map<String, RequestBody> partMap = new HashMap<>();
        RequestBody dataBody = RequestBody.create(MediaType.parse("text/plain"), new String(data));
        partMap.put("data", dataBody);
        RxHttpUtils
                .getSInstance()
                .createSApi(ApiService.class)
                .qcApproveprojectTest(partMap)
                .compose(Transformer.<ResultBean>switchSchedulers())
                .subscribe(new CommonObserver<ResultBean>() {
                    @Override
                    protected boolean isHideToast() {
                        return false;
                    }

                    @Override
                    protected void onError(String s) {
                        qualitycontrolApprovalView.OnError(s);
                    }

                    @Override
                    protected void onSuccess(ResultBean resultBean) {
                        if (resultBean != null) {
                            qualitycontrolApprovalView.OnMessage(resultBean);
                        }
                    }
                });

    }


}
