package com.monitor.finance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.BuyWayBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.BaseEditPresenter;
import com.monitor.changtian.view.BaseEditView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_edit_payment_way)
public class EditPaymentWayActivity extends BaseActvity implements BaseEditView<List<BuyWayBean>> {

    BaseEditPresenter baseEditPresenter;
    ArrayList<BuyWayBean> buyWayBeanArrayList = new ArrayList<>();
    @ViewById(R.id.labels)
    LabelsView labelsView;
    @ViewById(R.id.tv_add)
    TextView tv_add;
    @ViewById(R.id.tv_delect)
    TextView tv_delect;

    @AfterViews
    void init() {
        initImageBackText("编辑付款类型");
        tv_delect.setText("删除");
        tv_delect.setTextColor(Color.RED);
        baseEditPresenter = new BaseEditPresenter(this, this);
        initData();
        //标签的选中监听
        labelsView.setOnLabelSelectChangeListener(new LabelsView.OnLabelSelectChangeListener() {
            @Override
            public void onLabelSelectChange(TextView label, Object data, boolean isSelect, int position) {
                //label是被选中的标签，data是标签所对应的数据，isSelect是是否选中，position是标签的位置。

                if (isSelect) {
                    positions = position;
                } else {
                    positions = -1;
                }

//                msg(buyWayBeanArrayList.get(position).getDataValue());

            }
        });
    }

    @Click(R.id.tv_delect)
    public void tv_delect() {

        if (positions != -1) {

            //label是被选中的标签，data是标签所对应的数据，isSelect是是否选中，position是标签的位置。
            String datas = "{Id:\"" + buyWayBeanArrayList.get(positions).getId() + "\"}";
            baseEditPresenter.DeleteDicData(datas);
        } else {
            msg("请选择要删除的类型");
        }


    }

    @Click(R.id.tv_add)
    public void tv_add() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("请输入内容")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (et.getText().toString().trim() != null) {
                            String DataValue = et.getText().toString().trim();
                            String data = " {typeCode:\"buyWay\",DataCode:\"\",DataValue:\"" + DataValue + "\",sortId:1}";
                            baseEditPresenter.AddBaseData(data);
                        } else {
                            msg("请输入内容");
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }


    public void initData() {
        String data = "{Id:\"\",typeCode:\"buyWay\",DataValue:\"\"}";
        baseEditPresenter.GetBaseData(data);
    }


    int positions = -1;

    @Override
    public void Getinfo(List<BuyWayBean> buyWayBeans) {
        buyWayBeanArrayList.clear();
        buyWayBeanArrayList.addAll(buyWayBeans);
        labelsView.setLabels(buyWayBeanArrayList, new LabelsView.LabelTextProvider<BuyWayBean>() {
            @Override
            public CharSequence getLabelText(TextView label, int position, BuyWayBean data) {
                return data.getDataValue();
            }
        });

    }

    @Override
    public void Addinfo(ResultBean resultBean) {
        initData();
    }

    @Override
    public void Remoinfo(ResultBean resultBean) {
        tv_delect.setText("删除");
        initData();
    }
}
