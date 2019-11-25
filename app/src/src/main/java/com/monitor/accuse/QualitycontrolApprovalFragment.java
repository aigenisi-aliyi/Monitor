package com.monitor.accuse;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.AccuseAdapter;
import com.monitor.changtian.adapter.QualitycontrolApprovalAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.PushMessage;
import com.monitor.changtian.bean.EventBus.QualityEvent;
import com.monitor.changtian.bean.ProjectTestListBean;
import com.monitor.changtian.bean.QueryTestRecordBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestProjectCycleListBean;
import com.monitor.changtian.presenter.QualitycontrolApprovalPresenter;
import com.monitor.changtian.view.QualitycontrolApprovalView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by Administrator on 2018/12/19.
 */

/**
 * 质控审批界面
 */

@EFragment(R.layout.quality_control_approval)
public class QualitycontrolApprovalFragment extends BaseFragment implements QualitycontrolApprovalView {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    QualitycontrolApprovalPresenter qualitycontrolApprovalPresenter;
    QualitycontrolApprovalAdapter qualitycontrolApprovalAdapter;

    @AfterViews
    void init() {
        //注册EvetnBus；
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        qualitycontrolApprovalPresenter = new QualitycontrolApprovalPresenter(this);
        qualitycontrolApprovalAdapter = new QualitycontrolApprovalAdapter(R.layout.detection_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(qualitycontrolApprovalAdapter);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });
        qualitycontrolApprovalAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                /**
                 * 跳转到样品审核列表界面
                 */
                QualitycontrolApprovalListActivity_.intent(getActivity()).tpcid(qualitycontrolApprovalAdapter.getData().get(position).getTpcid()).start();
            }
        });
    }

    public void initData() {
        String data = "{col1:\"0\",projectid:\"\",taskNumber:\"\"}";
        qualitycontrolApprovalPresenter.GetTestProjectCycleList(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void OnGetTestProjectCycleList(List<TestProjectCycleListBean> testProjectCycleListBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (testProjectCycleListBeans.size() > 0) {
            qualitycontrolApprovalAdapter.setNewData(testProjectCycleListBeans);
        } else {
            qualitycontrolApprovalAdapter.setNewData(testProjectCycleListBeans);
            AddEmptyView(qualitycontrolApprovalAdapter, mRecyclerView);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });
        }
    }

    @Override
    public void OnProjectTestListBean(List<ProjectTestListBean> projectTestListBeans) {

    }

    @Override
    public void OnQueryTestRecordBeans(List<QueryTestRecordBean> queryTestRecordBeans) {

    }

    @Override
    public void OnMessage(ResultBean resultBean) {

    }

    @Override
    public void OnError(String s) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


    @Subscribe
    public void QualityEventss(QualityEvent event) {
        initData();
    }
}
