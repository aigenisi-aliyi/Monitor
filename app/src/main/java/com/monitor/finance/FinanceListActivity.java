package com.monitor.finance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.FinanceListBean;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.FinanceAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.FinancePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_finance_list)
public class FinanceListActivity extends BaseActvity implements SubmitView<List<FinanceListBean>> {

    FinancePresenter financePresenter;

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    FinanceAdapter financeAdapter;

    @AfterViews
    void init() {
//        initNullBtnTitlebar("财务");
        financePresenter = new FinancePresenter(this, this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        financeAdapter = new FinanceAdapter(R.layout.finance_item);
        rv_list.setAdapter(financeAdapter);
        initData();

        financeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_look:
                        FinanceMoneyRecordActivity_.intent(FinanceListActivity.this).start();
                        break;
                    case R.id.ll_info:
//                        FinanceActivity_.intent(FinanceListActivity.this).projectid(financeAdapter.getData().get(position).getId()).start();
                        break;
                }
            }
        });
    }

    public void initData() {
        String data = "{id:\"\",names:\"\",CustomerCode:\"\",ProjectStatus:\"\",InputPerson:\"\",sdate:\"\",edate:\"\"}";
        financePresenter.GetProjectData(data);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isTaskRoot()) {
                long now = new Date().getTime();
                if (now - mLastBackTime < TIME_DIFF) {
                    return super.onKeyDown(keyCode, event);
                } else {
                    mLastBackTime = now;
                    msg("再按一次返回键退出");
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onData(List<FinanceListBean> financeListBeans) {
        financeAdapter.addData(financeListBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {
        msg(s);
    }


}
