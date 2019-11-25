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
import com.monitor.changtian.presenter.DetectionSchemeAuditPresenter;
import com.monitor.changtian.view.DetectionSchemeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:  方案已审核
 */
@EFragment(R.layout.fragment_detectionscheme_main)
public class DetectionRechecktrialFragment extends BaseFragment implements DetectionSchemeView {
    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    DetectionSchemeAuditPresenter detectionSchemeAuditPresenter;
    DetectionSchemeAuditAdapter detectionSchemeAuditAdapter;

    @AfterViews
    void init() {
        detectionSchemeAuditPresenter = new DetectionSchemeAuditPresenter(this, getActivity());
        detectionSchemeAuditAdapter = new DetectionSchemeAuditAdapter(R.layout.detection_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(detectionSchemeAuditAdapter);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
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
                startActivity(new Intent(getActivity(), DetectionAuditActivity.class).putExtra("type", "1").putExtra("schid", detectionSchemeAuditAdapter.getData().get(position).getSchemeid()));
            }
        });
        AddEmptyView(detectionSchemeAuditAdapter, rv_list);
        setEmptylistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initData() {
        String data = "{schemeid:\"\",projectid:\"\",schemeStatus:\"2,3\",IsUrgent:\"\"}";
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
}
