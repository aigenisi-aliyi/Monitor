package com.monitor.detectionschemeaudit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.DetectionSchemeAuditAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.DetectionSchemeBean;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.presenter.DetectionSchemeAuditPresenter;
import com.monitor.changtian.view.DetectionSchemeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by ken on 2018/7/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>    方案审核
 * Function:
 */

@EFragment(R.layout.fragment_detectionscheme_main)
public class DetectionSchemeAuditFragment extends BaseFragment implements DetectionSchemeView {

    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    DetectionSchemeAuditPresenter detectionSchemeAuditPresenter;
    DetectionSchemeAuditAdapter detectionSchemeAuditAdapter;

    @AfterViews
    void init() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        detectionSchemeAuditPresenter = new DetectionSchemeAuditPresenter(this, getActivity());
        detectionSchemeAuditAdapter = new DetectionSchemeAuditAdapter(R.layout.detection_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(detectionSchemeAuditAdapter);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        initData();
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
                        ShowDialogtitle("加载中...", getActivity());
                        initData();
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });
        detectionSchemeAuditAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), DetectionAuditActivity.class).putExtra("type", "0").putExtra("schid", detectionSchemeAuditAdapter.getData().get(position).getSchemeid()));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
    public void initData() {
        String data = "{schemeid:\"\",projectid:\"\",schemeStatus:\"1\",IsUrgent:\"\"}";
        detectionSchemeAuditPresenter.GetDetectionScheme(data);
    }

    @Override
    public void OnDetectionSchemeList(List<DetectionSchemeBean> detectionSchemeBeans) {
        DissDialog();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (detectionSchemeBeans.size() > 0) {
            detectionSchemeAuditAdapter.setNewData(detectionSchemeBeans);
        } else {
            detectionSchemeAuditAdapter.setNewData(detectionSchemeBeans);
            AddEmptyView(detectionSchemeAuditAdapter, rv_list);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initData();
                }
            });
        }

    }


    @Override
    public void OnError(String s) {
        DissDialog();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
    /**
     * 更新列表
     *
     * @param event
     */
    @Subscribe
    public void UpdateList(UpdateEvent event) {
        if (event != null) {
            if(event.getType().equals("DetectionSchemeAudit")){
                initData();
            }
        }
    }
}
