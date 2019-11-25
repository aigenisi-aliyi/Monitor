package com.monitor.repertory;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.monitor.changtian.R;
import com.monitor.changtian.activity.RepertoryPresenter;
import com.monitor.changtian.adapter.EquipmentinfoAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EquipmentDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_equipment_list_info)
public class EquipmentListInfoActivity extends BaseActvity implements SubmitView<List<EquipmentDataBean>> {

    @Extra
    String EquiptypeId;
    @Extra
    String EquiName;
    RepertoryPresenter repertoryPresenter;
    EquipmentinfoAdapter equipmentinfoAdapter;

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;

    @AfterViews
    void init() {

//        initImageBackText(EquiName);
//        repertoryPresenter = new RepertoryPresenter(this, this);
//        equipmentinfoAdapter = new EquipmentinfoAdapter(R.layout.equipment_info_item);
//        rv_list.setLayoutManager(new LinearLayoutManager(this));
//        rv_list.setAdapter(equipmentinfoAdapter);
//        initData();
//        AddEmptyView(equipmentinfoAdapter, rv_list);
//        setEmptylistener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initData();
//            }
//        });
    }

    public void initData() {
        String data = "{id:\"\",names:\"\",EquipmentNumber:\"\",buyDates:\"\",buyeDated:\"\",productionUnit:\"\",RegulationNumber:\"\",ExpireDates:\"\",ExpireDated:\"\",equiptype:\"\",IsEnabled:\"\",categoryid:\""+EquiptypeId+"\"}";
        repertoryPresenter.GetEquipmentData(data);
    }


    @Override
    public void onData(List<EquipmentDataBean> equipmentDataBeans) {
        equipmentinfoAdapter.setNewData(equipmentDataBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}
