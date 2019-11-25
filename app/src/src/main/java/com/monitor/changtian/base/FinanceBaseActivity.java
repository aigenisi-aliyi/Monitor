package com.monitor.changtian.base;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.utils.DateUtil;
import com.monitor.changtian.utils.LogAndToastUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ken on 2018/6/29.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class FinanceBaseActivity extends AppCompatActivity {



    //日历日期选择。
    Calendar selectCalendar;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startTime;

    public void msg(String msg) {
        LogAndToastUtil.toast(this, msg);
    }
    public  void showSelectDate(final TextView time) {
        //选择病历日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i2);
                calendar.set(Calendar.DAY_OF_MONTH, i3);
                time.setText("" + format.format(calendar.getTime()));
                selectCalendar = calendar;
                startTime = calendar.getTime();
            }
        }, year, month, day);

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(DateUtil.getStringToDate("1979-01-01", "yyyy-MM-dd"));
        datePicker.setMaxDate(new Date().getTime());
        datePickerDialog.setTitle("开始时间");
        datePickerDialog.show();
    }

    public void showSelectDate1(final TextView time) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String times = null;
        if (startTime != null) {
            times = format1.format(startTime);
        }

        //选择病历日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i2);
                calendar.set(Calendar.DAY_OF_MONTH, i3);
                time.setText("" + format.format(calendar.getTime()));
                selectCalendar = calendar;
            }
        }, year, month, day);

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(DateUtil.getStringToDate(times, "yyyy-MM-dd"));
        datePicker.setMaxDate(DateUtil.getStringToDate("2300-01-01", "yyyy-MM-dd"));
        datePickerDialog.setTitle("结束时间");
        datePickerDialog.show();
    }



    /**
     * 设置空布局
     */
    View view;

    public void AddEmptyView(BaseQuickAdapter mainAdapter, RecyclerView recyclerView) {
        view = this.getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        mainAdapter.setEmptyView(view);
    }

    /**
     * @param listener 空布局点击事件
     */
    public void setEmptylistener(View.OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }

    }


}
