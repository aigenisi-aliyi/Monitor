package com.monitor.finance;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.FinanceMoneyRecordAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.MoneyRecordBean;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.FinancePresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

@EActivity(R.layout.activity_finance_money_record)
public class FinanceMoneyRecordActivity extends BaseActvity implements SubmitView<List<MoneyRecordBean>> {


    @Extra
    String contractid;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    FinancePresenter financePresenter;
    FinanceMoneyRecordAdapter financeMoneyRecordAdapter;

    TextView tv_contractcode, tv_contractname, tv_schemename, tv_totalmoney;

    @Extra
    ProjectcontractBean projectcontractBean;

    @AfterViews
    void init() {
        initImageBackText("收款明细");
        financePresenter = new FinancePresenter(this, this);
        financeMoneyRecordAdapter = new FinanceMoneyRecordAdapter(this, R.layout.finance_moneyrecord_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(financeMoneyRecordAdapter);
        initData();

        financeMoneyRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (financeMoneyRecordAdapter.getData().get(position).getFiles().length() > 0) {
                    List<LocalMedia> selectList_image = new ArrayList<>();
                    LocalMedia s = new LocalMedia();
                    s.setPath(PHOTOS_URL + financeMoneyRecordAdapter.getData().get(position).getFiles());
                    selectList_image.add(s);
                    PictureSelector.create(FinanceMoneyRecordActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(0, selectList_image);
                } else {
                    msg("暂无图片!");
                }
            }
        });
        /**
         * 添加头布局
         */
        View view = FinanceMoneyRecordActivity.this.getLayoutInflater().inflate(R.layout.financemoney_head, (ViewGroup) rv_list.getParent(), false);
        financeMoneyRecordAdapter.addHeaderView(view);
        tv_contractcode = view.findViewById(R.id.tv_contractcode);
        tv_contractname = view.findViewById(R.id.tv_contractname);
        tv_totalmoney = view.findViewById(R.id.tv_totalmoney);
        tv_schemename = view.findViewById(R.id.tv_schemename);

        tv_contractcode.setText("No: "+projectcontractBean.getContractcode());
        tv_contractname.setText(projectcontractBean.getContractname());
        tv_totalmoney.setText(projectcontractBean.getTotalmoney());
        tv_schemename.setText(projectcontractBean.getSchemename());

    }

    public void initData() {
        String data = "{contractid:\"" + contractid + "\",contractcode:\"\",contractname:\"\",receivetype:\"\",sdate:\"\",edate:\"\"}";
        financePresenter.GetMoneyRecord(data);
    }

    @Override
    public void onData(List<MoneyRecordBean> moneyRecordBeans) {

        if (moneyRecordBeans.size() > 0) {
            financeMoneyRecordAdapter.addData(moneyRecordBeans);
        } else {

        }

    }

    @Override
    public void onMessage(ResultBean t) {

    }

    @Override
    public void OnError(String s) {
        msg(s);
    }
}
