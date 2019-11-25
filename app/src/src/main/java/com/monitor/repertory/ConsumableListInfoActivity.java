package com.monitor.repertory;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.R;
import com.monitor.changtian.activity.RepertoryPresenter;
import com.monitor.changtian.adapter.ConsumableinfoAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_consumable_list_info)
public class ConsumableListInfoActivity extends BaseActvity implements SubmitView<List<EquipmentDataBean>> {

    @Extra
    String EquiptypeId;
    @Extra
    String EquiName;
    RepertoryPresenter repertoryPresenter;
    ConsumableinfoAdapter consumableinfoAdapter;

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;

    @AfterViews
    void init() {

//
//        initImageBackText(EquiName);
//        repertoryPresenter = new RepertoryPresenter(this, this);
//        consumableinfoAdapter = new ConsumableinfoAdapter(R.layout.consumable_info_item);
//        rv_list.setLayoutManager(new LinearLayoutManager(this));
//        rv_list.setAdapter(consumableinfoAdapter);
//        initData();
//
//
//        AddEmptyView(consumableinfoAdapter, rv_list);
//        setEmptylistener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initData();
//            }
//        });
//

    }

    public void initData() {
        String data = "{id:\"\",names:\"\",IsEnabled:\"\",Consumablestype:\"\",sdate:\"\",edate:\"\",categoryid:\""+EquiptypeId+"\"}";
        repertoryPresenter.GetConsumablesData(data);
    }


    @Override
    public void onData(List<EquipmentDataBean> equipmentDataBeans) {
        consumableinfoAdapter.setNewData(equipmentDataBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}
