package com.monitor.changtian.fragment;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.task.TaskActivity;
import com.monitor.changtian.adapter.TaskAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.TaskEvent;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TaskListBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.TaskPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by ken on 2018/4/23.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function: 任务
 */
@EFragment(R.layout.inspection_item)
public class CurtainFragment extends BaseFragment implements SubmitView<List<TaskListBean>> {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    TaskAdapter taskAdapter;
    TaskPresenter taskPresenter;
    //设置分页
    private int page = 1;
    //判断下一页是否存在数据
    private boolean onLoadMore = false;

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        taskPresenter = new TaskPresenter(this, getActivity());
        taskAdapter = new TaskAdapter(R.layout.fragment_task_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(taskAdapter);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        initData(page);
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });

        /**
         * 上啦
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        page++;
                        initData(page);
                        refreshLayout.setNoMoreData(false);
//                        if (onLoadMore) {
//                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
//                        } else {
//                            refreshLayout.finishLoadMore();
//                        }
                    }
                }, 500);
            }
        });

        taskAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    /**
                     * 查看详情
                     */
                    case R.id.ll_infolook:
//                        EventBus.getDefault().postSticky(new TaskEvent(taskAdapter.getItem(position), taskAdapter.getData().get(position).getId()));
                        startActivity(new Intent(getActivity(), TaskActivity.class).putExtra("taskid", taskAdapter.getData().get(position).getId()));
                        break;
                }

            }
        });
    }

    /**
     * 请求数据
     */
    public void initData(int page) {
        String data = "{id:\"\",conid:\"\",pagenumber:\""+page+"\",numbers:\"7\",sampingloginId:\"" + MyApplication.getInstance().getUser() + "\",taskstatus:\"1,3\"}";
        taskPresenter.GetTaskList(data);
    }

    @Override
    public void onData(List<TaskListBean> taskBeans) {


        if (page == 1) {
            taskAdapter.setNewData(taskBeans);
            onLoadMore = false;
            if (refreshLayout != null) {
                refreshLayout.finishRefresh();
            }
        } else {
            taskAdapter.addData(taskBeans);
            if (taskBeans.size() == 0 && taskBeans != null) {
                onLoadMore = true;
                refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            }else{
                refreshLayout.finishLoadMore();
            }

            if (refreshLayout != null) {
                refreshLayout.finishRefresh();
            }
        }
        if (taskAdapter.getData().size() == 0) {
            AddEmptyView(taskAdapter, mRecyclerView);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    page = 1;
//                    querystring = "";
//                    initData(page, querystring);
                    refreshLayout.autoRefresh();//自动刷新
                    refreshLayout.autoLoadMore();//自动加载
                }
            });
        }
        taskAdapter.notifyDataSetChanged();





    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        }
        page = page-1;
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
            if (event.getType().equals("SampleList")) {
                page = 1;
                initData(page);
            }
        }
    }
}
