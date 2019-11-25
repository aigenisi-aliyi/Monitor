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
import com.monitor.changtian.bean.EventBus.UpdateEvent;
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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2018/8/8.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:  异常处置
 */

@EFragment(R.layout.fragment_detectionscheme_main)
public class AuditTaskFragment extends BaseFragment implements TaskInfoAllView {

    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    TaskInfoAllPresenter taskInfoAllPresenter;
    TaskInfoAllAdapter taskInfoAllAdapter;

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
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


                if (taskInfoAllAdapter.getData().get(position).getIsstoppass().length()==0) {
                    ArrayList<String> photos = new ArrayList<>();
                    for (int i = 0; i < taskInfoAllAdapter.getData().get(position).getFujians().size(); i++) {
                        photos.add(taskInfoAllAdapter.getData().get(position).getFujians().get(i).getFujian());
                    }
                    startActivity(new Intent(getActivity(), TaskCorrelationinfoActivity.class).putStringArrayListExtra("photos", photos).putExtra("isstoppass", "1").putExtra("yijian", taskInfoAllAdapter.getData().get(position).getStopreason()).putExtra("status", "2").putExtra("taskid", taskInfoAllAdapter.getData().get(position).getId()).putExtra("coid", taskInfoAllAdapter.getData().get(position).getId()).putExtra("schid", taskInfoAllAdapter.getData().get(position).getSchemeid()));
                } else {
//                    List<TaskInfoAllBean.FujiansBean> photoListEvents = new ArrayList<>();
//                    photoListEvents.addAll(taskInfoAllAdapter.getData().get(position).getFujians());
//                    ViseLog.d("456:" + photoListEvents.size());
//                    EventBus.getDefault().post(new PhotoListEvent(photoListEvents));

                    ArrayList<String> photos = new ArrayList<>();
                    for (int i = 0; i < taskInfoAllAdapter.getData().get(position).getFujians().size(); i++) {
                        photos.add(taskInfoAllAdapter.getData().get(position).getFujians().get(i).getFujian());
                    }
                    startActivity(new Intent(getActivity(), TaskCorrelationinfoActivity.class).putStringArrayListExtra("photos", photos).putExtra("isstoppass", "0").putExtra("yijian", taskInfoAllAdapter.getData().get(position).getStopreason()).putExtra("status", "2").putExtra("taskid", taskInfoAllAdapter.getData().get(position).getId()).putExtra("coid", taskInfoAllAdapter.getData().get(position).getId()).putExtra("schid", taskInfoAllAdapter.getData().get(position).getSchemeid()));
                }
            }
        });
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        initData();
//    }

    public void initData() {

        String data = "{id:\"\",conid:\"\",sampingloginId:\"\",isstopandaudit:\"1\",taskstatus:\"\"}";
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
            if(event.getType().equals("Audittask")){
                initData();
            }
        }
    }
}
