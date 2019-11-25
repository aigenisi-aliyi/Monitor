package com.monitor.taskallocation;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.SampleProofreadActivity_;
import com.monitor.changtian.adapter.SampleProofreadAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.GetfieldsamplingDetailListBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.SampleProofreadPresenter;
import com.monitor.changtian.view.SampleProofreadView;
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

import static com.monitor.changtian.constant.PublicConstant.AUDIT;

/**
 * Created by ken on 2018/8/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.inspection_item)
public class TaskAuditFragment extends BaseFragment implements SampleProofreadView {
    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    SampleProofreadPresenter sampleProofreadPresenter;
    SampleProofreadAdapter sampleProofreadAdapter;

    String manageloginname = MyApplication.getInstance().getUser();

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        sampleProofreadPresenter = new SampleProofreadPresenter(this, getActivity());
        sampleProofreadAdapter = new SampleProofreadAdapter(R.layout.sampleproofread_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(sampleProofreadAdapter);
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

        sampleProofreadAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SampleProofreadActivity_.intent(TaskAuditFragment.this).stutas(sampleProofreadAdapter.getData().get(position).getStatevalue()).type(AUDIT).sampid(sampleProofreadAdapter.getData().get(position).getSampdetailid()).start();
            }
        });
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        refreshLayout.getLayout().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                initData();
//                refreshLayout.setNoMoreData(false);
//            }
//        }, 500);
//    }

    public void initData() {
        String data = "{sampid:\"\",sampdetailid:\"\",onlynumber:\"\",statevalue:\"2,3,4\",proofreader:\"\",manageloginname:\"" + manageloginname + "\",proofreaderloginname:\"\"}";
        sampleProofreadPresenter.fieldsamplingDetailList(data);
    }
    @Override
    public void OnList(List<GetfieldsamplingDetailListBean> getfieldsamplingDetailListBeans) {

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }

        if (getfieldsamplingDetailListBeans.size() > 0) {
            sampleProofreadAdapter.setNewData(getfieldsamplingDetailListBeans);
        } else {
            sampleProofreadAdapter.setNewData(getfieldsamplingDetailListBeans);
            AddEmptyView(sampleProofreadAdapter, mRecyclerView);
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

    /**
     * 更新列表
     *
     * @param event
     */
    @Subscribe
    public void UpdateList(UpdateEvent event) {
        if (event != null) {
            if(event.getType().equals("taskAudit")){
                initData();
            }
        }
    }
}
