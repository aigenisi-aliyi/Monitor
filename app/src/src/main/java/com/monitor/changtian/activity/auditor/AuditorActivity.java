package com.monitor.changtian.activity.auditor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.AuditorAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleInfoCompreData;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.AuditorPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EActivity(R.layout.activity_auditor)
public class AuditorActivity extends BaseActvity implements SubmitView<SampleInfoCompreData> {

    private AuditorAdapter auditorAdapter;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;

    @Extra
    String SampleId;
    AuditorPresenter auditorPresenter;

    TextView tv_pro_title, tv_pro_num;
    String loginId = "admin";

    String inSampleId = "";

    String AuditOpinion = "";

    @AfterViews
    void init() {
        initImageBackText("审核");

        String insid = getIntent().getStringExtra("sid");
        if (SampleId == null) {
            inSampleId = insid;
        } else {
            inSampleId = SampleId;
        }

        auditorPresenter = new AuditorPresenter(this, this);
        String data = "{SampleId:\"" + inSampleId + "\"}";
        auditorPresenter.GetSampleInfoCompreData(data);


        auditorAdapter = new AuditorAdapter(R.layout.audits_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(auditorAdapter);


        View viewhead = this.getLayoutInflater().inflate(R.layout.auditor_head, (ViewGroup) rv_list.getParent(), false);
        auditorAdapter.addHeaderView(viewhead);
        tv_pro_title = viewhead.findViewById(R.id.tv_pro_title);
        tv_pro_num = viewhead.findViewById(R.id.tv_pro_num);


        View viewfoot = this.getLayoutInflater().inflate(R.layout.auditor_foot, (ViewGroup) rv_list.getParent(), false);
        auditorAdapter.addFooterView(viewfoot);
        EditText et_remark = viewfoot.findViewById(R.id.et_remark);

        AuditOpinion = et_remark.getText().toString().trim();
        SuperTextView stv_true = viewfoot.findViewById(R.id.stv_true);
        SuperTextView stv_false = viewfoot.findViewById(R.id.stv_false);

        stv_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        stv_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Ispass = "1";
                String data = "{SampleId:\"" + inSampleId + "\",loginId:\"" + loginId + "\",AuditOpinion:\"" + AuditOpinion + "\",types:\"44\",Ispass:\"" + Ispass + "\"}";
                auditorPresenter.AddSampleAudit(data);

            }
        });
        stv_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ispass = "0";
                String data = "{SampleId:\"" + inSampleId + "\",loginId:\"" + loginId + "\",AuditOpinion:\"" + AuditOpinion + "\",types:\"44\",Ispass:\"" + Ispass + "\"}";
                auditorPresenter.AddSampleAudit(data);

            }
        });


        auditorAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_au_edit:

                        ArrayList<SampleInfoCompreData.SampleDetailsBean.SampleDetailItemsBean> aa = new ArrayList();
                        aa.addAll(auditorAdapter.getData().get(position).getSampleDetailItems());

//                        ArrayList<SampleInfoCompreData.SamplePhotosBean> photosBeans = new ArrayList();

                        AuditorInfoActivity_.intent(AuditorActivity.this).sampleDetailItemsBeans(aa).samplePhotosBeans(photosBeans).start();
                        break;
                }
            }
        });
    }

    ArrayList<SampleInfoCompreData.SamplePhotosBean> photosBeans = new ArrayList();

    @Override
    public void onData(SampleInfoCompreData sampleInfoCompreData) {
        if (sampleInfoCompreData != null) {
            tv_pro_title.setText(sampleInfoCompreData.getSampleInfo().getProjectName());
            tv_pro_num.setText(sampleInfoCompreData.getSampleInfo().getContractNum());
        }

        if (sampleInfoCompreData.getSamplePhotos().size() > 0) {
            photosBeans.addAll(sampleInfoCompreData.getSamplePhotos());
        }

        SampleInfoCompreData.SampleDetailsBean sampleDetailsBean = new SampleInfoCompreData.SampleDetailsBean();
        sampleDetailsBean.setSampleNum("编号");
        sampleDetailsBean.setUsername("名称");
        sampleDetailsBean.setCreatetime("时间");
        sampleDetailsBean.setUserid("操作");
        auditorAdapter.addData(sampleDetailsBean);
        auditorAdapter.addData(sampleInfoCompreData.getSampleDetails());
    }

    @Override
    public void onMessage(ResultBean t) {

        if (t.getResult().equals("1")) {
            msg("成功");
        } else {
            msg(t.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {
        msg(s);
    }
}
