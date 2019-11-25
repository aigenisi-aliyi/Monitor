package com.monitor.changtian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.TasksInfomationBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * 校准记录
 */
@EActivity(R.layout.activity_calibration_info)
public class CalibrationInfoActivity extends BaseActvity {


    @ViewById(R.id.tv_dev_i)
    TextView tv_dev_i;
    @ViewById(R.id.tv_dev_person)
    TextView tv_dev_person;
    @ViewById(R.id.tv_dev_dev)
    TextView tv_dev_dev;
    @ViewById(R.id.tv_dev_topvalue)
    TextView tv_dev_topvalue;

    @ViewById(R.id.tv_dev_i1)
    TextView tv_dev_i1;
    @ViewById(R.id.tv_dev_person1)
    TextView tv_dev_person1;
    @ViewById(R.id.tv_dev_dev1)
    TextView tv_dev_dev1;
    @ViewById(R.id.tv_dev_topvalue1)
    TextView tv_dev_topvalue1;

    @ViewById(R.id.ll_items1)
    LinearLayout ll_items1;

    @Extra
    ArrayList<TasksInfomationBean.EquipsBean.CalibrationsBean> equipsBeans;

    @AfterViews
    void init() {

        initImageBackText("校准记录");

        if(equipsBeans!=null){
            if (equipsBeans.size() > 0) {
                if (equipsBeans.size() == 1) {
                    tv_dev_i.setText(equipsBeans.get(0).getInnames());
                    tv_dev_person.setText(equipsBeans.get(0).getNames());
                    tv_dev_dev.setText(equipsBeans.get(0).getDevname());
                    tv_dev_topvalue.setText(equipsBeans.get(0).getPremeasurement());
                } else {

                    tv_dev_i.setText(equipsBeans.get(0).getInnames());
                    tv_dev_person.setText(equipsBeans.get(0).getNames());
                    tv_dev_dev.setText(equipsBeans.get(0).getDevname());
                    tv_dev_topvalue.setText(equipsBeans.get(0).getPremeasurement());

                    ll_items1.setVisibility(View.VISIBLE);
                    tv_dev_i1.setText(equipsBeans.get(0).getInnames());
                    tv_dev_person1.setText(equipsBeans.get(1).getNames());
                    tv_dev_dev1.setText(equipsBeans.get(1).getDevname());
                    tv_dev_topvalue1.setText(equipsBeans.get(1).getPremeasurement());
                }
            }

        }



    }
}
