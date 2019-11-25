package com.monitor.finance;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.StatisticsMoneyQueryBean;
import com.monitor.changtian.bean.StatisticsQueryBean;
import com.monitor.changtian.presenter.FinanceStatisticsPresenter;
import com.monitor.changtian.view.FinanceStatisticsView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * 财务主界面
 */

@EActivity(R.layout.activity_finance_main)
public class FinanceMainActivity extends BaseActvity implements FinanceStatisticsView {

    @ViewById(R.id.tv_today_money)
    TextView tv_today_money;
    @ViewById(R.id.tv_coNum)
    TextView tv_coNum;
    @ViewById(R.id.tv_co_moneyNum)
    TextView tv_co_moneyNum;
    @ViewById(R.id.tv_disabled_money)
    TextView tv_disabled_money;

    @ViewById(R.id.ll_vis)
    LinearLayout ll_vis;
    @ViewById(R.id.pie_sex)
    PieChartView pie_sex;
    FinanceStatisticsPresenter financeStatisticsPresenter;

    @ViewById(R.id.rb_week)
    RadioButton rb_week;

    @ViewById(R.id.rb_today)
    RadioButton rb_today;

    @ViewById(R.id.rb_mouth)
    RadioButton rb_mouth;


    @ViewById(R.id.tv_data)
    RadioGroup tv_data;

    @AfterViews
    void init() {
        ShowDialogtitle("加载中...",this);
        rb_today.setChecked(true);
        financeStatisticsPresenter = new FinanceStatisticsPresenter(this, this);
        financeStatisticsPresenter.StatisticsQuery1();
        String data1 = "{sdate:\"" + format.format(new Date()) + "\",edate:\"" + format.format(new Date()) + "\"}";
        financeStatisticsPresenter.StatisticsQuery(data1);


        tv_data.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_week:
                        ShowDialogtitle("加载中...",FinanceMainActivity.this);
                        String data = "{sdate:\"" + setStatetime(-7) + "\",edate:\"" + format.format(new Date()) + "\"}";
                        financeStatisticsPresenter.StatisticsQuery(data);
                        break;
                    case R.id.rb_today:
                        ShowDialogtitle("加载中...",FinanceMainActivity.this);
                        String data1 = "{sdate:\"" + format.format(new Date()) + "\",edate:\"" + format.format(new Date()) + "\"}";
                        financeStatisticsPresenter.StatisticsQuery(data1);
                        break;
                    case R.id.rb_mouth:
                        ShowDialogtitle("加载中...",FinanceMainActivity.this);
                        String data2 = "{sdate:\"" + setStatetime(-30) + "\",edate:\"" + format.format(new Date()) + "\"}";
                        financeStatisticsPresenter.StatisticsQuery(data2);
                        break;

                }
            }
        });

    }


    /**
     * 待付款
     */
    @Click(R.id.ll_df)
    public void ll_df() {

        DfActivity_.intent(this).start();
    }

    /**
     * 待付尾款
     */
    @Click(R.id.ll_dfw)
    public void ll_dfw() {

        DfwActivity_.intent(this).start();
    }

    /**
     * 已结款
     */
    @Click(R.id.ll_yj)
    public void ll_yj() {

        YjActivity_.intent(this).start();
    }

    @Override
    public void OnStatisticsQueryBean(StatisticsQueryBean statisticsQueryBean) {
        DissDialog();
        tv_today_money.setText(statisticsQueryBean.getCurrentdaytotalmoney() + "");
        tv_coNum.setText(statisticsQueryBean.getWeeknumber() + "");
        tv_co_moneyNum.setText(statisticsQueryBean.getTotalTailMoney() + "");
        tv_disabled_money.setText(statisticsQueryBean.getWeekInvalidmoney() + "");
    }

    @Override
    public void StatisticsMoneyQueryBean(List<StatisticsMoneyQueryBean> statisticsMoneyQueryBeans) {
        DissDialog();
        if (statisticsMoneyQueryBeans.size() > 0) {

            ll_vis.setVisibility(View.GONE);
            pie_sex.setVisibility(View.VISIBLE);

            if (statisticsMoneyQueryBeans.size() == 1) {
                initDataMaleFeMaleRatio(Float.parseFloat(statisticsMoneyQueryBeans.get(0).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(0).getContractstatusname()
                        , 0, ""
                        , 0, ""
                        , 0, "");
            } else if (statisticsMoneyQueryBeans.size() == 2) {
                initDataMaleFeMaleRatio(Float.parseFloat(statisticsMoneyQueryBeans.get(0).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(0).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(1).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(1).getContractstatusname(),
                        0, ""
                        , 0, "");
            } else if (statisticsMoneyQueryBeans.size() == 3) {
                initDataMaleFeMaleRatio(Float.parseFloat(statisticsMoneyQueryBeans.get(0).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(0).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(1).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(1).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(2).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(2).getContractstatusname()
                        , 0, "");
            } else if (statisticsMoneyQueryBeans.size() == 4) {
                initDataMaleFeMaleRatio(Float.parseFloat(statisticsMoneyQueryBeans.get(0).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(0).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(1).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(1).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(2).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(2).getContractstatusname()
                        , Float.parseFloat(statisticsMoneyQueryBeans.get(3).getRatiovalue().replaceAll("%", ""))
                        , statisticsMoneyQueryBeans.get(3).getContractstatusname());
            }
        } else {
            ll_vis.setVisibility(View.VISIBLE);
            pie_sex.setVisibility(View.GONE);
        }

    }

    @Override
    public void onError(String s) {

        DissDialog();
    }


    public void initDataMaleFeMaleRatio(float data1, String str1, float data2, String str2, float data3, String str3, float data4, String str4) {
        pie_sex.setCircleFillRatio(0.8f);//设置饼状图占整个view的比例
        pie_sex.setChartRotationEnabled(false);
        int sliceNum = 1;
        List<SliceValue> sliceValues = new ArrayList<>();
        for (int i = 0; i < sliceNum; i++) {

            SliceValue sliceValue1 = new SliceValue((float) data1, this
                    .getResources().getColor(R.color.red_btn));
            sliceValue1.setLabel(str1 + "\n" + sliceValue1.getValue() + "%");
            SliceValue sliceValue2 = new SliceValue((float) data2, this
                    .getResources().getColor(R.color.color_light_blue));
            sliceValue2.setLabel(str2 + "\n" + sliceValue2.getValue() + "%");
            SliceValue sliceValue3 = new SliceValue((float) data3, this
                    .getResources().getColor(R.color.dull_blue));
            sliceValue3.setLabel(str3 + "\n" + sliceValue3.getValue() + "%");
            SliceValue sliceValue4 = new SliceValue((float) data4, this
                    .getResources().getColor(R.color.dull_blue));
            sliceValue4.setLabel(str4 + "\n" + sliceValue4.getValue() + "%");

            sliceValues.add(sliceValue1);
            sliceValues.add(sliceValue2);
            sliceValues.add(sliceValue3);
            sliceValues.add(sliceValue4);
        }
        PieChartData pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);//显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);//不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);//占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(false);//是否是环形显示
        pieChardata.setValues(sliceValues);//填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);//设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);//设置环形的大小级别
//        pieChardata.setCenterText1("总共");//环形中间的文字1
//        pieChardata.setCenterText1Color(Color.BLACK);//文字颜色
//        pieChardata.setCenterText1FontSize(14);//文字大小
//        pieChardata.setCenterText2("100");
//        pieChardata.setCenterText2Color(Color.BLACK);
//        pieChardata.setCenterText2FontSize(18);
        /**这里也可以自定义你的字体   Roboto-Italic.ttf这个就是你的字体库*/
//      Typeface tf = Typeface.createFromAsset(this.getAssets(), "Roboto-Italic.ttf");
//      data.setCenterText1Typeface(tf);
        pie_sex.setPieChartData(pieChardata);
        pie_sex.setValueSelectionEnabled(true);//选择饼图某一块变大
        pie_sex.setAlpha(0.9f);//设置透明度
        pie_sex.setCircleFillRatio(1f);//设置饼图大小
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public String setStatetime(int DataTime) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, DataTime);
        Date monday = c.getTime();
        String preMonday = format.format(monday);
        return preMonday;
    }


}
