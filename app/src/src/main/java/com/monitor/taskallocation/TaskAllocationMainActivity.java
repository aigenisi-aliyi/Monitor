package com.monitor.taskallocation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.FinanceMainPresenter;
import com.monitor.taskallocation.adapter.TaskAllocationAdapter;
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

import static com.monitor.changtian.constant.PublicConstant.Contractstatus_DAIFUWEI_YIWANJIE;
import static com.monitor.changtian.constant.PublicConstant.PageNum;

@EFragment(R.layout.activity_task_allocation_main)
public class TaskAllocationMainActivity extends BaseFragment implements SubmitView<List<ProjectcontractBean>> {
    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    TaskAllocationAdapter taskAllocationAdapter;
    FinanceMainPresenter financeMainPresenter;
    //设置分页
    private int page = 1;
    //判断下一页是否存在数据
    private boolean onLoadMore = false;
    @ViewById(R.id.et_content)
    EditText et_content;
    String querystring = "";

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        financeMainPresenter = new FinanceMainPresenter(this, getActivity());
        taskAllocationAdapter = new TaskAllocationAdapter(R.layout.task_main_item);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(taskAllocationAdapter);
        initEditText();
//        initData(page, querystring);
        taskAllocationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Task_ContractInfoActivity_.intent(TaskAllocationMainActivity.this).projectcontractBean(taskAllocationAdapter.getData().get(position)).start();
                startActivity(new Intent(getActivity(), TaskCorrelationinfoActivity.class).putExtra("Samplemodename", taskAllocationAdapter.getData().get(position).getSamplemodename()).putExtra("status", "0").putExtra("coid", taskAllocationAdapter.getData().get(position).getId()).putExtra("schid", taskAllocationAdapter.getData().get(position).getSchemeid()));
            }
        });
/**
 * 下拉
 */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        querystring = "";
                        initData(page, querystring);
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
                        querystring = "";
                        initData(page, querystring);
                        refreshLayout.setNoMoreData(false);
                        if (onLoadMore) {
                            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                        } else {
                            refreshLayout.finishLoadMore();
                        }
                    }
                }, 500);
            }
        });

    }

    public void initEditText() {
        et_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    ((InputMethodManager) et_content.getContext().getSystemService(
                            Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                            getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    page = 1;
                    querystring = et_content.getText().toString().trim();
                    initData(page, querystring);

                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        querystring = "";
        initData(page, querystring);
    }

    public void initData(int page, String querystring) {
        String data = "{id:\"\",projectid:\"\",schemeid:\"\",isqualitycontrol:\"0,1,2\",projectmanagerLoginName:\""+ MyApplication.getInstance().getUser()+"\",querystring:\"" + querystring + "\",contractstatus:\"0,1,2,3\",sdate:\"\",edate:\"\",smoney:\"\",emoney:\"\",pagenumber:\"" + page + "\",numbers:\"" + PageNum + "\"} ";
        financeMainPresenter.Getprojectcontract(data);
    }

    @Override
    public void onData(List<ProjectcontractBean> projectcontractBeans) {


        if (page == 1) {
            taskAllocationAdapter.setNewData(projectcontractBeans);
            onLoadMore = false;
            if (refreshLayout != null) {
                refreshLayout.finishRefresh();
            }
        } else {
            taskAllocationAdapter.addData(projectcontractBeans);
            if (projectcontractBeans.size() == 0 && projectcontractBeans != null) {
                onLoadMore = true;
            }
            if (refreshLayout != null) {
                refreshLayout.finishRefresh();
            }
        }
        if (taskAllocationAdapter.getData().size() == 0) {
            AddEmptyView(taskAllocationAdapter, rv_list);
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
        taskAllocationAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMessage(ResultBean t) {

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
            if(event.getType().equals("TaskAllocationMain")){
                page = 1;
                querystring = "";
                initData(page, querystring);
            }
        }
    }
}
