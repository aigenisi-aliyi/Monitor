package com.monitor.taskallocation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.TaskInfoAllAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.TaskInfoAllBean;
import com.monitor.changtian.presenter.TaskInfoAllPresenter;
import com.monitor.changtian.view.TaskInfoAllView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>    再分配
 * Function:
 */
@EFragment(R.layout.fragment_detectionscheme_main)
public class RedistributionFragment extends BaseFragment implements TaskInfoAllView {

    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    TaskInfoAllPresenter taskInfoAllPresenter;
    TaskInfoAllAdapter taskInfoAllAdapter;

    @AfterViews
    void init() {
        taskInfoAllPresenter = new TaskInfoAllPresenter(this, getActivity());
        taskInfoAllAdapter = new TaskInfoAllAdapter(R.layout.detection_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(taskInfoAllAdapter);
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
        taskInfoAllAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), TaskCorrelationinfoActivity.class).putExtra("status", "1").putExtra("coid", taskInfoAllAdapter.getData().get(position).getConid()).putExtra("schid", taskInfoAllAdapter.getData().get(position).getSchemeid()));
            }
        });
    }

    public void initData() {
        String data = "{id:\"\",conid:\"\",sampingloginId:\"\",taskstatus:\"2,5\"}";
        taskInfoAllPresenter.GetTasksInfoAudit(data);
    }

    @Override
    public void OnGetTasksInfoAudit(List<TaskInfoAllBean> taskInfoAllBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (taskInfoAllBeans.size() > 0) {
            taskInfoAllAdapter.setNewData(taskInfoAllBeans);
        } else {
            taskInfoAllAdapter.setNewData(taskInfoAllBeans);
            AddEmptyView(taskInfoAllAdapter, rv_list);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshLayout.autoRefresh();//自动刷新
                    refreshLayout.autoLoadMore();//自动加载
                }
            });
        }

    }

    @Override
    public void OnError(String s) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }
}
