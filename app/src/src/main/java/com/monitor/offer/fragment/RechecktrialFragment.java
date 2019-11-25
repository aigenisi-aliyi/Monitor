package com.monitor.offer.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.FirsttrialAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.GetSchemePriceDetailListBean;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.OfferPresenter;
import com.monitor.changtian.view.OfferView;
import com.monitor.offer.OfferinfoActivity_;
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
 * Created by ken on 2018/7/30.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:    复审列表
 */

@EFragment(R.layout.fragment_rechecktrial)
public class RechecktrialFragment extends BaseFragment implements OfferView {

    OfferPresenter offerPresenter;
    FirsttrialAdapter firsttrialAdapter;
    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;

    @AfterViews
    void init() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        offerPresenter = new OfferPresenter(this, getActivity());
        firsttrialAdapter = new FirsttrialAdapter(R.layout.c_offer_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(firsttrialAdapter);
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
                        ShowDialogtitle("加载中...", getActivity());
                        initData();
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });
        firsttrialAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                /**
                 * 查看详情
                 */
                OfferinfoActivity_.intent(getActivity()).type("1").id(firsttrialAdapter.getData().get(position).getId()).sqstatus("5").start();
            }
        });
    }


    public void initData() {

        String data = "{schemeid:\"\",id:\"\",sqstatus:\"5\"}";
        offerPresenter.GetSchemeQuotationInfo(data);
    }

    @Override
    public void OnOfferList(List<GetSchemeQuotationInfoBean> getSchemeQuotationInfoBeans) {

        DissDialog();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (getSchemeQuotationInfoBeans != null) {

            if (getSchemeQuotationInfoBeans.size() > 0) {
                firsttrialAdapter.setNewData(getSchemeQuotationInfoBeans);
                firsttrialAdapter.notifyDataSetChanged();
            } else {
                firsttrialAdapter.setNewData(getSchemeQuotationInfoBeans);
                AddEmptyView(firsttrialAdapter, rv_list);
                setEmptylistener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initData();
                    }
                });
            }

        }
    }

    @Override
    public void OnOfferTrial(ResultBean resultBean) {

    }

    @Override
    public void OnOffertrialinfo(List<GetSchemePriceDetailListBean> getSchemePriceDetailListBeans) {

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
            if(event.getType().equals("Rechecktrial")){
                initData();
            }
        }
    }

}

