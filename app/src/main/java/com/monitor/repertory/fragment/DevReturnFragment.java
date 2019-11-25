package com.monitor.repertory.fragment;

import android.content.Context;
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
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.DevReturnAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.EquipInOutStockDataBean;
import com.monitor.changtian.bean.GetConsumablesDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.ReceivePresenter;
import com.monitor.changtian.view.ReceiveView;
import com.monitor.repertory.ReturnActivity_;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/8/28.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:  设备归还
 */
@EFragment(R.layout.fragment_dec)
public class DevReturnFragment extends BaseFragment implements ReceiveView {

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    ReceivePresenter receivePresenter;
    DevReturnAdapter devReturnAdapter;

    @ViewById(R.id.et_content)
    EditText et_content;
    String querystring = "";

    @AfterViews
    void init() {
        receivePresenter = new ReceivePresenter(this, getActivity());
        devReturnAdapter = new DevReturnAdapter(R.layout.dev_return_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(devReturnAdapter);
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
                        initData("");
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });
        devReturnAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ReturnActivity_.intent(getActivity()).type("dev").equipInOutStockDataBean(devReturnAdapter.getData().get(position)).start();

            }
        });

        initEditText();
    }

    @Click(R.id.tv_oncliks)
    public void tv_oncliks() {

        if (et_content.getText().toString().trim() != null) {
            querystring = et_content.getText().toString().trim();
            initData(querystring);
        } else {
            msg("搜索内容不能为空!");
        }

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
                    if (et_content.getText().toString().trim() != null) {
                        querystring = et_content.getText().toString().trim();
                        initData(querystring);
                    } else {
                        msg("搜索内容不能为空!");
                    }

                    return true;
                }
                return false;
            }
        });
    }


    public void initData(String querts) {
        /**
         * 查询领用的设备
         */
        String data_dev = "{id:\"\",equipid:\"\",types:\"1\",optionuser:\"\",taskid:\"\",optionuserLoginName:\"" + querts + "\",isreturn:\"0\"}";
        receivePresenter.GetEquipInOutStockData(data_dev);
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.autoRefresh();//自动加载

    }

    @Override
    public void OnQueryDevList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if(equipInOutStockDataBeans.size()>0){
            devReturnAdapter.setNewData(equipInOutStockDataBeans);
        }else{
            devReturnAdapter.setNewData(equipInOutStockDataBeans);
            AddEmptyView(devReturnAdapter, mRecyclerView);
            setEmptylistener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    querystring = "";
                    initData(querystring);
                }
            });
        }
    }

    @Override
    public void OnQueryConList(List<EquipInOutStockDataBean> equipInOutStockDataBeans) {

    }

    @Override
    public void OnQuerConsumablesDataBean(List<GetConsumablesDataBean> consumablesDataBeans) {

    }

    @Override
    public void OnReceiveSubmit(ResultBean resultBean) {

    }

    @Override
    public void OnError(String s) {
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }
}
