package com.monitor.accuse;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.monitor.changtian.R;
import com.monitor.changtian.adapter.AwaitAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.GetqualitycontrolBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.AccusePresenter;
import com.monitor.changtian.view.AccuseView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_await_accuse)
public class AwaitAccuseActivity extends BaseActvity implements AccuseView {


    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    AwaitAdapter awaitAdapter;
    AccusePresenter accusePresenter;

    @Extra
    String schemeid;

    @AfterViews
    void init() {
        initImageBackText("质控详情");
        accusePresenter = new AccusePresenter(this, this);
        awaitAdapter = new AwaitAdapter(R.layout.awaitaccuse_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(awaitAdapter);
        initData();
    }

    public void initData() {
        String data = "{cqcid:\"\",pcid:\"\",schemeid:\""+schemeid+"\",pointsid:\"\",contractstatus:\"\"} ";
        accusePresenter.Getqualitycontrol(data);
    }

    List<GetqualitycontrolBean> qualits = new ArrayList<>();

    @Override
    public void Getqualitycontrol(List<GetqualitycontrolBean> getqualitycontrolBeans) {
        qualits = new ArrayList<>();
        GetqualitycontrolBean getqualitycontrolBean = new GetqualitycontrolBean();
        getqualitycontrolBean.setQualitycontrolname("质控类型");
        getqualitycontrolBean.setPointsaddress("点位");
//        getqualitycontrolBean.setNumbers("样品数量");
        qualits.add(getqualitycontrolBean);
        qualits.addAll(getqualitycontrolBeans);
        awaitAdapter.setNewData(qualits);
    }

    @Override
    public void Addqualitycontrol(ResultBean resultBean) {

    }

    @Override
    public void OnError(String s) {

    }
}
