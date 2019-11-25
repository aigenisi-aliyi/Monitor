package com.monitor.accuse;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.AccuseAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.FinanceMainPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Contractstatus_DAIFUWEI_YIWANJIE;
import static com.monitor.changtian.constant.PublicConstant.PageNum;

/**
 * Created by ken on 2018/8/3.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.activity_accuse)
public class AwaitAccuseFragment extends BaseFragment implements
        SubmitView<List<ProjectcontractBean>> {
    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    AccuseAdapter accuseAdapter;
    FinanceMainPresenter financeMainPresenter;
    //设置分页
    private int page = 1;
    //判断下一页是否存在数据
    private boolean onLoadMore = false;
    String querystring = "";

    @AfterViews
    void init() {
        financeMainPresenter = new FinanceMainPresenter(this, getActivity());
        accuseAdapter = new AccuseAdapter(R.layout.detection_item);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(accuseAdapter);
        initData(page, querystring);
        accuseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (accuseAdapter.getData().get(position).getIsqualitycontrol() != null) {

                    if (accuseAdapter.getData().get(position).getIsqualitycontrol().equals("2") ||
                            accuseAdapter.getData().get(position).getIsqualitycontrol().equals("3") ||
                            accuseAdapter.getData().get(position).getIsqualitycontrol().equals("0")) {
                        msg("当前方案暂无质控信息!");
                    } else {
                        AwaitAccuseActivity_.intent(getActivity()).schemeid(accuseAdapter.getData().get(position).getSchemeid()).start();

                    }
                }
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

    public void initData(int page, String querystring) {
        String data = "{id:\"\",projectid:\"\",schemeid:\"\",querystring:\"" + querystring + "\",contractstatus:\"" + Contractstatus_DAIFUWEI_YIWANJIE + "\",sdate:\"\",edate:\"\",smoney:\"\",emoney:\"\",pagenumber:\"" + page + "\",isqualitycontrol:\"2\",numbers:\"" + PageNum + "\"} ";
//        String data = "{id:\"\",projectid:\"\",schemeid:\"\",querystring:\"" + querystring + "\",contractstatus:\"" + Contractstatus_DAIFUWEI_YIWANJIE + "\",sdate:\"\",edate:\"\",smoney:\"\",emoney:\"\",pagenumber:\"" + page + "\",isoperation:\"" + 0 + "\",numbers:\"" + PageNum + "\"} ";
        financeMainPresenter.Getprojectcontract(data);
    }


    @Override
    public void onData(List<ProjectcontractBean> projectcontractBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (page == 1) {
            accuseAdapter.setNewData(projectcontractBeans);
            onLoadMore = false;
        } else {

            if (projectcontractBeans.size() == 0 && projectcontractBeans != null) {
                onLoadMore = true;
            }
            refreshLayout.finishRefresh();
            accuseAdapter.addData(projectcontractBeans);
        }

        if (accuseAdapter.getData().size() == 0) {
            AddEmptyView(accuseAdapter, rv_list);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    page = 1;
                    querystring = "";
                    initData(page, querystring);
                }
            });
        }

        accuseAdapter.notifyDataSetChanged();
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
}