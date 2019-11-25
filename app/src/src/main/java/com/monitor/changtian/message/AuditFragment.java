package com.monitor.changtian.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.auditor.AuditorActivity_;
import com.monitor.changtian.adapter.AuditAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.MessageBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.MessagePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/5/14.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>           审批
 * Function:
 */

@EFragment(R.layout.fragment_audit)
public class AuditFragment extends BaseFragment implements SubmitView<List<MessageBean>> {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    AuditAdapter auditAdapter;
    MessagePresenter messagePresenter;
    String type = "2";

    @AfterViews
    void init() {

        auditAdapter = new AuditAdapter(R.layout.audit_item);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(auditAdapter);
        messagePresenter = new MessagePresenter(this, getActivity());
        initData();
        AddEmptyView(auditAdapter, rv_list);
        setEmptylistener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });

        auditAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                AuditorActivity_.intent(AuditFragment.this).SampleId(auditAdapter.getData().get(position).getSubid()).start();
            }
        });
    }

    public void initData() {
        String data = "{loginName:\"admin\",types:\"" + type + "\"}";
        messagePresenter.GetMsgList(data);

    }

    @Override
    public void onData(List<MessageBean> messageBeans) {
        auditAdapter.addData(messageBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}
