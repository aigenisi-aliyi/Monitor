package com.monitor.finance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.FinanceListBean;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.BuyWayBean;
import com.monitor.changtian.bean.DetectionSchemeInfoBean;
import com.monitor.changtian.bean.ProjectcontractBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.BaseEditPresenter;
import com.monitor.changtian.presenter.FinancePresenter;
import com.monitor.changtian.presenter.FinanceSamplePresenter;
import com.monitor.changtian.presenter.TaskContractInfoPresenter;
import com.monitor.changtian.utils.StringUtils;
import com.monitor.changtian.view.BaseEditView;
import com.monitor.changtian.view.ClearEditText;
import com.monitor.changtian.view.FinanceSampleView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 财务界面
 */
@EActivity(R.layout.activity_finance)
public class FinanceActivity extends BaseActvity implements FinanceSampleView, SubmitView<List<FinanceListBean>>, BaseEditView<List<BuyWayBean>> {


    @ViewById(R.id.tv_titles)
    TextView tv_titles;
    @ViewById(R.id.edit_money)
    ClearEditText edit_money;
    @ViewById(R.id.et_remark)
    EditText et_remark;
    @ViewById(R.id.tv_time)
    TextView tv_time;
    @ViewById(R.id.tv_photos)
    TextView tv_photos;

    @ViewById(R.id.tv_contractname)
    TextView tv_contractname;
    @ViewById(R.id.tv_contractcode)
    TextView tv_contractcode;
    @ViewById(R.id.tv_schemename)
    TextView tv_schemename;
    @ViewById(R.id.tv_signDate)
    TextView tv_signDate;
    @ViewById(R.id.tv_latestpaydate)
    TextView tv_latestpaydate;
    @ViewById(R.id.tv_totalmoney)
    TextView tv_totalmoney;
    @ViewById(R.id.tv_AdvanceCharge)
    TextView tv_AdvanceCharge;
    @ViewById(R.id.tv_moneyss)
    TextView tv_moneyss;

    @ViewById(R.id.ll_AccountNumber)
    LinearLayout ll_AccountNumber;
    @ViewById(R.id.tv_AccountNumber)
    TextView tv_AccountNumber;
    String projectid = "";
    FinancePresenter financePresenter;
    BaseEditPresenter baseEditPresenter;
    ArrayList<BuyWayBean> buyWayBeanArrayList = new ArrayList<>();
    @Extra
    ProjectcontractBean projectcontractBean;

    TaskContractInfoPresenter taskContractInfoPresenter;
    @Extra
    String status;

    FinanceSamplePresenter financeSamplePresenter;

    @AfterViews
    void init() {

        if (status != null) {
            initImageBackText("待付尾款");
        } else {
            initImageBackText("待付款");
        }
        financeSamplePresenter = new FinanceSamplePresenter(this, this);
        financePresenter = new FinancePresenter(this, this);
        baseEditPresenter = new BaseEditPresenter(this, this);
        initData();
        initView();
    }

    public void initView() {
        if (status != null) {
            tv_moneyss.setText("待付尾款:");
            tv_AdvanceCharge.setText("￥" + projectcontractBean.getTailmoney());
        } else {
            tv_AdvanceCharge.setText("￥" + projectcontractBean.getAdvanceCharge());
        }

        tv_contractcode.setText("No: " + projectcontractBean.getContractcode());
        tv_contractname.setText(projectcontractBean.getContractname());
        tv_schemename.setText(projectcontractBean.getSchemename());
        tv_signDate.setText("签订:" + projectcontractBean.getSignDate());
        if (projectcontractBean.getCompletetime() != null) {
            tv_latestpaydate.setText("最迟:" + projectcontractBean.getCompletetime().substring(0, 10));
        }

        tv_totalmoney.setText("￥" + projectcontractBean.getTotalmoney());

        edit_money.setText(tv_AdvanceCharge.getText().toString().trim().replaceAll("￥", ""));
    }


    public void initData() {
        String data = "{Id:\"\",typeCode:\"buyWay\",DataValue:\"\"}";
        baseEditPresenter.GetBaseData(data);
        String datas = "{schemeid:\"" + projectcontractBean.getSchemeid() + "\"}";
        financeSamplePresenter.GetDetectionSchemeInfo(datas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    String content_id = "";

    /**
     * 选择类型
     */
    @Click(R.id.tv_titles)
    public void tv_titles() {

        hintKbTwo();
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String content = buyWayBeanArrayList.get(options1).getPickerViewText();
                content_id = buyWayBeanArrayList.get(options1).getId() + "";
                /***
                 * 如果当前id= -999
                 * 便是自定义
                 * */
                if (content_id.equals("-999")) {
                    EditPaymentWayActivity_.intent(FinanceActivity.this).start();
                } else {
                    tv_titles.setText(content);
                }
            }
        })
                .setTitleText("付款方式")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(buyWayBeanArrayList);//一级选择器
        pvOptions.show();
    }

    /**
     * 选择时间
     */
    @Click(R.id.tv_time)
    public void tv_time() {
        hintKbTwo();
        showSelectDate("到账日期", tv_time);
    }

    private List<LocalMedia> selectList_image = new ArrayList<>();

    /**
     * 上传图片
     */
    @Click(R.id.tv_photos)
    public void tv_photos() {
        PictureSelector.create(FinanceActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_default_style)
                .maxSelectNum(1)
                .minSelectNum(1)
                .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                .previewImage(true)
                .previewVideo(true)
                .enablePreviewAudio(true) // 是否可播放音频
                .isCamera(true)
                .glideOverride(160, 160)
                .previewEggs(true)
                .compress(true)
                .selectionMedia(selectList_image)
                .forResult(666);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 666:
                    selectList_image = PictureSelector.obtainMultipleResult(data);
                    tv_photos.setText(selectList_image.get(0).getPath());
                    break;
            }
        }
    }

    String receivetype = "";
    String receivemoney = "";
    String PaymentDate = "";
    String remark = "";

    /**
     * 确认收款
     */
    @Click(R.id.stv_submit)
    public void stv_submit() {

//        if (ISEmpty("请选择付款方式", tv_titles.getText().toString().trim())) {
//            return;
//        } else {
//            receivetype = content_id;
//        }

        if (tv_titles.getText().toString().trim().length() > 0) {
            receivetype = content_id;
        } else {
            receivetype = "";
        }


        if (ISEmpty("请输入金额", edit_money.getText().toString().trim())) {
            return;
        } else {
            receivemoney = edit_money.getText().toString().trim();
        }

        if (ISEmpty("请选择收款日期", tv_time.getText().toString().trim())) {
            return;
        } else {
            PaymentDate = tv_time.getText().toString().trim();
        }

        remark = et_remark.getText().toString().trim();

        List<File> imageFile1 = new ArrayList<>();
        for (int i = 0; i < selectList_image.size(); i++) {
            if (!StringUtils.isUrl(selectList_image.get(i).getPath())) {
                imageFile1.add(new File(selectList_image.get(i).getPath()));
            }
        }

        if (status != null) {
            if (Integer.parseInt(receivemoney) < projectcontractBean.getTailmoney()) {
                msg("输入金额不能小于待付尾款金额!");
                return;
            }
        } else {
            if (Integer.parseInt(receivemoney) < Double.parseDouble(projectcontractBean.getAdvanceCharge())) {
                msg("输入金额不能小于预付款金额!");
                return;
            }
        }


        initDialog(imageFile1);

    }

    public void initDialog(final List<File> imageFile1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("确认收款");
        builder.setMessage("      请仔细核对收款金额!");
        //    设置一个NegativeButton
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ShowDialogtitle("请稍等...", FinanceActivity.this);
                financePresenter.AddMoneyRecord(MyApplication.getInstance().getUser(), projectcontractBean.getId(), receivetype, receivemoney, PaymentDate, remark, imageFile1);
            }
        });


        builder.show();
    }

    /**
     * @param buyWayBeans
     */
    @Override
    public void Getinfo(List<BuyWayBean> buyWayBeans) {
        buyWayBeanArrayList.clear();
        buyWayBeanArrayList.addAll(buyWayBeans);
        BuyWayBean buyWayBean = new BuyWayBean();
        buyWayBean.setId(-999);
        buyWayBean.setName("付款方式");
        buyWayBean.setDataValue("编辑");
        buyWayBeanArrayList.add(buyWayBean);


    }

    @Override
    public void Addinfo(ResultBean resultBean) {

    }

    @Override
    public void Remoinfo(ResultBean resultBean) {

    }


    @Override
    public void onData(List<FinanceListBean> financeListBeans) {

    }

    @Override
    public void onMessage(ResultBean t) {

        DissDialog();

        if (t != null) {
            if (t.getResult().equals("1")) {
                msg("确认成功");
                finish();
            } else {
                msg(t.getErrormessage());
            }
        }
    }

    @Override
    public void OnGetDetectionSchemeInfo(List<DetectionSchemeInfoBean> list) {

        if (list.size() > 0) {
            if (list.get(0).getAccountNumber().length() > 0) {
                ll_AccountNumber.setVisibility(View.VISIBLE);
                tv_AccountNumber.setText(list.get(0).getAccountNumber());
            }
        }
    }

    @Override
    public void OnError(String s) {
        DissDialog();
    }


}
