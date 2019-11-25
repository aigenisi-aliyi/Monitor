package com.monitor.finance;

import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.FinanceMainAdapter;
import com.monitor.changtian.base.FinanceBaseActivity;
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
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.Contractstatus_DAIFUWEI;
import static com.monitor.changtian.constant.PublicConstant.PageNum;

@EActivity(R.layout.activity_dfw)
public class DfwActivity extends FinanceBaseActivity implements SubmitView<List<ProjectcontractBean>> {


    @ViewById(R.id.recyclerView)
    RecyclerView rv_list;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    FinanceMainPresenter financeMainPresenter;
    FinanceMainAdapter financeMainAdapter;
    //设置分页
    private int page = 1;
    //判断下一页是否存在数据
    private boolean onLoadMore = false;
    @ViewById(R.id.tv_starts)
    TextView tv_starts;
    @ViewById(R.id.tv_end)
    TextView tv_end;
    @ViewById(R.id.et_mix)
    EditText et_mix;
    @ViewById(R.id.et_max)
    EditText et_max;
    @ViewById(R.id.et_content)
    EditText et_content;
    @ViewById(R.id.stv_reset)
    SuperTextView stv_reset;
    @ViewById(R.id.stv_true)
    SuperTextView stv_true;
    @ViewById(R.id.dl_lout)
    DrawerLayout dl_lout;
    @ViewById(R.id.ll_right)
    LinearLayout ll_right;

    @AfterViews
    void init() {
        financeMainPresenter = new FinanceMainPresenter(this, this);
        financeMainAdapter = new FinanceMainAdapter(R.layout.df_item);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(financeMainAdapter);
        initData(page);
        financeMainAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FinanceActivity_.intent(DfwActivity.this).status("2").projectcontractBean(financeMainAdapter.getData().get(position)).start();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        querystring = "";
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
                        querystring = "";
                        initData(page);
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


    public void initData(int page) {
        String data = "{id:\"\",projectid:\"\",schemeid:\"\",querystring:\"\",contractstatus:\"" + Contractstatus_DAIFUWEI + "\",sdate:\"\",edate:\"\",smoney:\"\",emoney:\"\",pagenumber:\"" + page + "\",numbers:\"" + PageNum + "\"} ";
        financeMainPresenter.Getprojectcontract(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();//自动刷新
    }

    /**
     * 开始时间
     */
    @Click(R.id.tv_starts)
    public void tv_starts() {
        showSelectDate(tv_starts);
    }

    /**
     * 结束时间
     */
    @Click(R.id.tv_end)
    public void tv_end() {

        if (tv_starts.getText().toString().length() > 0) {
            showSelectDate1(tv_end);
        } else {
            msg("请先选择开始时间");
        }

    }


    /**
     * 搜索
     */
    @Click(R.id.iv_menu)
    public void iv_menu() {
        dl_lout.openDrawer(ll_right);

    }

    /**
     * 重置
     */
    @Click(R.id.stv_reset)
    public void stv_reset() {
        tv_starts.setText("");
        tv_end.setText("");
        et_mix.setText("");
        et_max.setText("");
        et_content.setText("");
    }

    String querystring = "";
    String sdate = "";
    String edate = "";
    String smoney = "";
    String emoney = "";

    /**
     * 查询
     */
    @Click(R.id.stv_true)
    public void stv_true() {
        dl_lout.closeDrawer(ll_right);
        querystring = et_content.getText().toString().trim();
        sdate = tv_starts.getText().toString().trim();
        edate = tv_end.getText().toString().trim();
        smoney = et_mix.getText().toString().trim();
        emoney = et_max.getText().toString().trim();
        page = 1;
        String data = "{id:\"\",projectid:\"\",schemeid:\"\",querystring:\"" + querystring + "\",contractstatus:\"" + Contractstatus_DAIFUWEI + "\",sdate:\"" + sdate + "\",edate:\"" + edate + "\",smoney:\"" + smoney + "\",emoney:\"" + emoney + "\",pagenumber:\"" + page + "\",numbers:\"" + PageNum + "\"} ";
        financeMainPresenter.Getprojectcontract(data);
    }


    @Click(R.id.iv_back)
    public void iv_back() {
        onBackPressed();
    }

    @Override
    public void onData(List<ProjectcontractBean> projectcontractBeans) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (page == 1) {
            financeMainAdapter.setNewData(projectcontractBeans);
            onLoadMore = false;

        } else {
            financeMainAdapter.addData(projectcontractBeans);
            if (projectcontractBeans.size() == 0 && projectcontractBeans != null) {
                onLoadMore = true;
            }

        }
        if (financeMainAdapter.getData().size() == 0) {
            AddEmptyView(financeMainAdapter, rv_list);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refreshLayout.autoRefresh();//自动刷新
                }
            });
        }
        financeMainAdapter.notifyDataSetChanged();
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
