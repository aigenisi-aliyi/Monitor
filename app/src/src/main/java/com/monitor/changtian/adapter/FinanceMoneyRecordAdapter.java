package com.monitor.changtian.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.monitor.changtian.R;
import com.monitor.changtian.bean.MoneyRecordBean;

import java.text.SimpleDateFormat;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

/**
 * Created by ken on 2018/6/26.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:  MoneyRecordBean
 */

public class FinanceMoneyRecordAdapter extends BaseQuickAdapter<MoneyRecordBean, BaseViewHolder> {
    private Context context;

    public FinanceMoneyRecordAdapter(Context context, int layoutResId) {
        super(layoutResId);
        this.context = context;
    }

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void convert(BaseViewHolder helper, final MoneyRecordBean item) {
//
//        helper.setText(R.id.tv_contractcode, item.getContractcode());
//        helper.setText(R.id.tv_contractname, item.getContractname());
        helper.setText(R.id.tv_receivetypename, item.getReceivetypename());
        helper.setText(R.id.tv_receivemoney, item.getReceivemoney());
        helper.setText(R.id.tv_remark, item.getRemark());
        helper.setText(R.id.tv_receiveDate, item.getPaymentDate().substring(0, 10));
        ImageView image_info = helper.getView(R.id.image_info);
        if (item.getFiles().length() > 0) {
            Glide.with(context)
                    .load(PHOTOS_URL + item.getFiles())
                    .into(image_info);
        } else {
            Glide.with(context)
                    .load(R.drawable.nopng11)
                    .into(image_info);
        }
        helper.addOnClickListener(R.id.image_info);

        TextView tv_receiveDate = helper.getView(R.id.tv_receiveDate);
        TextView tv_receivetypename = helper.getView(R.id.tv_receivetypename);
        TextView tv_remark = helper.getView(R.id.tv_remark);
        TextView tv_receivemoney = helper.getView(R.id.tv_receivemoney);

        tv_receiveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog("付款时间:","\n\t\t\t"+ item.getPaymentDate().substring(0, 10));
            }
        });

        tv_receivetypename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog("付款方式:", "\n\t\t\t"+ item.getReceivetypename());
            }
        });

        tv_remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog("备注:","\n\t\t\t"+  item.getRemark());
            }
        });

        tv_receivemoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog("金额:","\n\t\t\t"+  item.getReceivemoney());
            }
        });

    }


    public void initDialog(String strTitle, String strContent) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle(strTitle);
        normalDialog.setMessage(strContent);

        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do

                    }
                });
        // 显示
        normalDialog.show();
    }
}