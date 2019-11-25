package com.monitor.offer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.OfferinfoAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.CloseEvent;
import com.monitor.changtian.bean.EventBus.UpdateEvent;
import com.monitor.changtian.bean.GetSchemePriceDetailListBean;
import com.monitor.changtian.bean.GetSchemeQuotationInfoBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.OfferPresenter;
import com.monitor.changtian.view.OfferView;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 方案详情
 */
@EActivity(R.layout.activity_offerinfo)
public class OfferinfoActivity extends BaseActvity implements OfferView {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    OfferPresenter offerPresenter;
    GetSchemeQuotationInfoBean getSchemeQuotationInfoBean;

    @Extra
    String sqstatus;
    @Extra
    String id;
    @Extra
    String type;
    OfferinfoAdapter offerinfoAdapter;
    TextView tv_schemename, tv_time, tv_money, tv_realprice;
    LinearLayout ll_reclprice;
    @ViewById(R.id.edit_money)
    EditText edit_money;
    @ViewById(R.id.et_remark)
    EditText et_remark;
    @ViewById(R.id.ll_types)
    LinearLayout ll_types;
    @ViewById(R.id.ll_submit)
    LinearLayout ll_submit;


    @AfterViews
    void init() {
        initImageBackText("报价详情");
        if (getIntent().getStringExtra("sqstatus") != null) {
            sqstatus = getIntent().getStringExtra("sqstatus");
        }
        if (getIntent().getStringExtra("type") != null) {
            type = getIntent().getStringExtra("type");
        }
        if (getIntent().getStringExtra("id") != null) {
            id = getIntent().getStringExtra("id");
        }
        /**
         * 类型是1表示复审不需要输入优惠金额
         */
        if (type.equals("1")) {
            ll_types.setVisibility(View.GONE);
        }
        if (type.equals("2")) {
            ll_types.setVisibility(View.GONE);
            ll_submit.setVisibility(View.GONE);
            et_remark.setVisibility(View.GONE);
        }

        offerPresenter = new OfferPresenter(this, this);
        initData();
        offerinfoAdapter = new OfferinfoAdapter(R.layout.offerinfo_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(offerinfoAdapter);

        View view1 = OfferinfoActivity.this.getLayoutInflater().inflate(R.layout.offerinfo_head, (ViewGroup) rv_list.getParent(), false);
        offerinfoAdapter.addHeaderView(view1);
        ll_reclprice = view1.findViewById(R.id.ll_reclprice);

        tv_realprice = view1.findViewById(R.id.tv_realprice);
        tv_schemename = view1.findViewById(R.id.tv_schemename);
        tv_schemename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg(tv_schemename.getText().toString().trim());
            }
        });

        tv_time = view1.findViewById(R.id.tv_time);
        tv_money = view1.findViewById(R.id.tv_money);
        if (type.equals("1")) {
            ViseLog.d("1111");
            ll_reclprice.setVisibility(View.VISIBLE);
            tv_money.setPaintFlags(tv_money.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else if (type.equals("2")) {
            ViseLog.d("2222");
            ll_reclprice.setVisibility(View.VISIBLE);
            tv_money.setPaintFlags(tv_money.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            ViseLog.d("000");
            ll_reclprice.setVisibility(View.GONE);
        }

        RecyclerView rv_list = view1.findViewById(R.id.rv_list);
        offerinfoAdapter = new OfferinfoAdapter(R.layout.offerinfo_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(offerinfoAdapter);

    }

    /**
     * 通过
     */
    String AuditOpinion = "";
    String realprice = "";

    @Click(R.id.stv_true)
    public void stv_true() {


        if (type.equals("0")) {

            if (et_remark.getText().length() > 0) {
                AuditOpinion = et_remark.getText().toString().trim();
            }

            if (edit_money.getText().toString().length() > 0) {
                realprice = edit_money.getText().toString().trim();
            } else {
                msg("优惠金额不能为空!");
                return;
            }
            Dialogs("1");
        } else {
            if (et_remark.getText().length() > 0) {
                AuditOpinion = et_remark.getText().toString().trim();
            }
            Dialogs("1");
        }


    }

    /**
     * 不通过
     */
    @Click(R.id.stv_false)
    public void stv_false() {
        if (type.equals("0")) {
            if (et_remark.getText().toString().trim().length() == 0) {
                msg("意见不能为空!");
                return;
            } else {
                AuditOpinion = et_remark.getText().toString().trim();
            }
            if (edit_money.getText().toString().length() > 0) {
                realprice = edit_money.getText().toString().trim();
            }
            Dialogs("0");
        } else {
            if (et_remark.getText().toString().trim().length() == 0) {
                msg("意见不能为空!");
                return;
            } else {
                AuditOpinion = et_remark.getText().toString().trim();
            }
            Dialogs("0");
        }


    }

    public void Dialogs(final String types) {

        /**
         * 二次弹框确认
         */
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认执行本次操作吗?");
        builder.setMessage("      请仔细核对相关信息!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (type.equals("0")) {
                    if (types.equals("0")) {
                        ShowDialogtitle("请稍等...", OfferinfoActivity.this);
                        String data = "{id:\"" + getSchemeQuotationInfoBean.getId() + "\",loginId:\""+ MyApplication.getInstance().getUser()+"\",AuditOpinion:\"" + AuditOpinion + "\",Ispass:\"0\",realprice:\"" + realprice + "\"} ";
                        offerPresenter.GetOffer(data);
                    } else if (types.equals("1")) {
                        ShowDialogtitle("请稍等...", OfferinfoActivity.this);
                        String data = "{id:\"" + getSchemeQuotationInfoBean.getId() + "\",loginId:\""+ MyApplication.getInstance().getUser()+"\",AuditOpinion:\"" + AuditOpinion + "\",Ispass:\"1\",realprice:\"" + realprice + "\"} ";
                        offerPresenter.GetOffer(data);
                    }
                } else {
                    if (types.equals("0")) {
                        ShowDialogtitle("请稍等...", OfferinfoActivity.this);
                        String data = "{id:\"" + getSchemeQuotationInfoBean.getId() + "\",loginId:\""+ MyApplication.getInstance().getUser()+"\",AuditOpinion:\"" + AuditOpinion + "\",Ispass:\"0\"} ";
                        offerPresenter.GetOffered(data);
                    } else if (types.equals("1")) {
                        ShowDialogtitle("请稍等...", OfferinfoActivity.this);
                        String data = "{id:\"" + getSchemeQuotationInfoBean.getId() + "\",loginId:\""+ MyApplication.getInstance().getUser()+"\",AuditOpinion:\"" + AuditOpinion + "\",Ispass:\"1\"} ";
                        offerPresenter.GetOffered(data);
                    }
                }

            }
        });
        builder.show();

    }


    private void initData() {
        ShowDialogtitle("加载中...", this);

        String data = "{schemeid:\"\",id:\"" + id + "\",sqstatus:\"" + sqstatus + "\"}";
        offerPresenter.GetSchemeQuotationInfo(data);


    }

    @Override
    public void OnOfferList(List<GetSchemeQuotationInfoBean> getSchemeQuotationInfoBeans) {
        getSchemeQuotationInfoBean = getSchemeQuotationInfoBeans.get(0);
        String datas = "{schemeid:\"\",sqid:\"" + getSchemeQuotationInfoBean.getId() + "\",categoryid:\"\",types:\"\",pointsid:\"\"}";
        offerPresenter.GetSchemePriceDetailList(datas);
        tv_schemename.setText(getSchemeQuotationInfoBean.getSchemename());
        tv_time.setText(getSchemeQuotationInfoBean.getCreatetime());
        tv_money.setText("报价金额:" + getSchemeQuotationInfoBean.getSumtotal());
        if (getSchemeQuotationInfoBean.getDiscountnumber().length() > 0) {
            tv_realprice.setText("最终优惠后金额:" + getSchemeQuotationInfoBean.getDiscountnumber());
        } else {
            tv_realprice.setText("建议优惠后金额:" + getSchemeQuotationInfoBean.getFirsttrialprice());
        }

    }

    @Override
    public void OnOfferTrial(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            EventBus.getDefault().post(new UpdateEvent("Firsttrial"));
            EventBus.getDefault().post(new UpdateEvent("Rechecktrial"));
            EventBus.getDefault().post(new CloseEvent("Detection"));
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }

    }

    @Override
    public void OnOffertrialinfo(List<GetSchemePriceDetailListBean> getSchemePriceDetailListBeans) {

        DissDialog();
        offerinfoAdapter.setNewData(getSchemePriceDetailListBeans);
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }
}
