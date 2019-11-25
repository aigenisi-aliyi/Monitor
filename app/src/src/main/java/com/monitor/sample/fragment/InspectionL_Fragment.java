package com.monitor.sample.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.GetsamplemanageInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.RoomListBean;
import com.monitor.changtian.bean.SampleInfoByAssignmentRecordBean;
import com.monitor.changtian.bean.SampleStatusBean;
import com.monitor.changtian.presenter.SamplePresenter;
import com.monitor.changtian.view.SampleView;
import com.monitor.sample.adapter.InspectionDAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.inspection_item)
public class InspectionL_Fragment extends BaseFragment implements SampleView {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    SamplePresenter samplePresenter;
    InspectionDAdapter inspectionDAdapter;

    @AfterViews
    void init() {

        samplePresenter = new SamplePresenter(this, getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        inspectionDAdapter = new InspectionDAdapter(R.layout.ts_contractinfo_item);
        mRecyclerView.setAdapter(inspectionDAdapter);
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

        AddEmptyView(inspectionDAdapter, mRecyclerView);
        setEmptylistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    public void initData() {
        String data = "{smid:\"\",samplenumber:\"\",sampleroomid:\"\",samplearea:\"4\",sdate:\"\",edate:\"\"}";
        samplePresenter.GetsamplemanageInfo(data);
    }

    @Override
    public void OnGetsamplemanageInfo(List<GetsamplemanageInfoBean> getsamplemanageInfoBeans) {

        if (getsamplemanageInfoBeans != null) {
            if (refreshLayout != null) {
                refreshLayout.finishRefresh();
                inspectionDAdapter.setNewData(getsamplemanageInfoBeans);
            }

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

    }

    @Override
    public void OnRoomList(List<RoomListBean> roomListBeans) {

    }

    @Override
    public void OnGetSampleInfoByAssignmentRecord(SampleInfoByAssignmentRecordBean sampleInfoByAssignmentRecordBean) {

    }

    @Override
    public void OnError(String s) {

    }
}
