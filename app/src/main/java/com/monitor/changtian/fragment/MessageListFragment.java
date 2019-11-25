package com.monitor.changtian.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.MessageListAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.MessageListBean;
import com.monitor.changtian.presenter.MessageListPresenter;
import com.monitor.changtian.utils.DateUtil;
import com.monitor.changtian.view.MessageListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ken on 2018/9/25.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

@EFragment(R.layout.messagelist_fragment)
public class MessageListFragment extends BaseFragment implements MessageListView {


    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @ViewById(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ClassicsHeader mClassicsHeader;
    MessageListAdapter messageListAdapter;
    MessageListPresenter messageListPresenter;
    String startTimesss = "", endTime = "";
    String qurey = "";

    @AfterViews
    void init() {
        messageListPresenter = new MessageListPresenter(this, getActivity());
        messageListAdapter = new MessageListAdapter(R.layout.press_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(messageListAdapter);
        mClassicsHeader = (ClassicsHeader) refreshLayout.getRefreshHeader();
        mClassicsHeader.setEnableLastTime(false);
        startTimesss = setStatetime(-30)+" 00:00";
        endTime = format.format(new Date())+" 23:59";
        qurey = "";
        initData(qurey, startTimesss, endTime);
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
                        qurey = "";
                        initData(qurey, startTimesss, endTime);
                        refreshLayout.setNoMoreData(false);
                    }
                }, 500);
            }
        });


    }

    public void initData(String qurey, String startTime, String endTime) {
        ShowDialogtitle("加载中...", getActivity());
        String data = "{loginName:\"" + MyApplication.getInstance().getUser() + "\",mtypes:\"\",content:\"" + qurey + "\",userid:\"\",subid:\"\",stime:\"" + startTime + "\",etime:\"" + endTime + "\"}";
        messageListPresenter.GetMsgLists(data);

    }

    /**
     * 悬浮按钮点击事件
     */
    String statrtimes, endtime;

    @Click(R.id.cf_imageButton)
    public void cf_imageButton() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.BDAlertDialog);
        final AlertDialog dia = builder.show();
        builder.setCancelable(false);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.seach_item, null);
        final LinearLayout setting_ll_starttime = (LinearLayout) view.findViewById(R.id.setting_ll_starttime);
        LinearLayout setting_ll_endtime = (LinearLayout) view.findViewById(R.id.setting_ll_endtime);
        final TextView setting_tv_starttime = (TextView) view.findViewById(R.id.setting_tv_starttime);
        final TextView setting_tv_endtime = (TextView) view.findViewById(R.id.setting_tv_endtime);
        final EditText edit_query = view.findViewById(R.id.edit_query);
        setting_ll_starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectDate(setting_tv_starttime);
            }
        });
        setting_ll_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (setting_tv_starttime.getText().toString().equals("请选择开始时间")) {
                    msg("请选择开始时间");
                    dia.dismiss();
                } else {
                    showSelectDate1(setting_tv_endtime);
                    endtime = setting_tv_endtime.getText().toString().trim();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dia.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (edit_query.getText().toString().length() > 0) {
                    qurey = edit_query.getText().toString();
                } else {
                    qurey = "";
                }

                if (setting_tv_starttime.getText().toString().length() > 0) {
                    statrtimes = setting_tv_starttime.getText().toString().trim()+" 00:00";
                } else {
                    statrtimes = "";
                }

                if (setting_tv_endtime.getText().toString().length() > 0) {
                    endtime = setting_tv_endtime.getText().toString().trim()+" 23:59";
                } else {
                    endtime = "";
                }

                initData(qurey, statrtimes, endtime);
                dia.dismiss();

            }
        });
        builder.setView(view);
        builder.show();

    }


    @Override
    public void OnMessageList(List<MessageListBean> messageListBeans) {
        DissDialog();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
        if (messageListBeans != null) {
            if (messageListBeans.size() > 0) {
                messageListAdapter.setNewData(messageListBeans);
            } else {
                messageListAdapter.setNewData(messageListBeans);
                AddEmptyView(messageListAdapter, mRecyclerView);
                setEmptylistener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startTimesss = setStatetime(-180);
                        endTime = format.format(new Date());
                        qurey = "";
                        initData(qurey, startTimesss, endTime);
                    }
                });
            }
        }

    }

    @Override
    public void OnError(String s) {
        DissDialog();
        if (refreshLayout != null) {
            refreshLayout.finishRefresh();
        }
    }


    //日历日期选择。
    Calendar selectCalendar;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date startTime;
    private void showSelectDate(final TextView time) {
        //选择病历日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
//        datePicker.setMinDate(new Date().getTime());
        datePicker.setMaxDate(DateUtil.getStringToDate("2300-01-01", "yyyy-MM-dd"));
        datePickerDialog.setTitle("开始时间");
        datePickerDialog.show();
    }
    private void showSelectDate1(final TextView time) {
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

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
        if (times != null) {
            datePicker.setMinDate(DateUtil.getStringToDate(times, "yyyy-MM-dd"));
        }
        datePicker.setMaxDate(DateUtil.getStringToDate("2300-01-01", "yyyy-MM-dd"));
        datePickerDialog.setTitle("结束时间");
        datePickerDialog.show();
    }
    public String setStatetime(int DataTime) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, DataTime);
        Date monday = c.getTime();
        String preMonday = format.format(monday);
        return preMonday;
    }

}
