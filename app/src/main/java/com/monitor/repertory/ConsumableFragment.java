package com.monitor.repertory;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.RepertoryPresenter;
import com.monitor.changtian.adapter.EquipmentAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.DicDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by ken on 2018/7/9.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */
@EFragment(R.layout.fragment_equipment)
public class ConsumableFragment extends BaseFragment implements SubmitView<List<DicDataBean>> {
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    RepertoryPresenter repertoryPresenter;
    EquipmentAdapter equipmentAdapter;

    @AfterViews
    void init() {

//        repertoryPresenter = new RepertoryPresenter(this, getActivity());
//        equipmentAdapter = new EquipmentAdapter(R.layout.equipment_item);
//        rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        rv_list.setAdapter(equipmentAdapter);
//        initData();
//        equipmentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                ConsumableListInfoActivity_.intent(getActivity()).EquiName(equipmentAdapter.getData().get(position).getDataValue()).EquiptypeId(equipmentAdapter.getData().get(position).getId()+"").start();
//            }
//        });
//
//        AddEmptyView(equipmentAdapter, rv_list);
//        setEmptylistener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initData();
//            }
//        });
    }

    public void initData() {

        String data = "{Id:\"\",typeCode:\"Consumablescategory\",DataValue:\"\"}";
        repertoryPresenter.GetDevData(data);
    }

    @Override
    public void onData(List<DicDataBean> dicDataBeans) {
        equipmentAdapter.setNewData(dicDataBeans);
    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {

    }
}