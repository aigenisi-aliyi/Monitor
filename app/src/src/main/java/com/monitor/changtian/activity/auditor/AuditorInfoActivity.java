package com.monitor.changtian.activity.auditor;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.AuditorInfoAdapter;
import com.monitor.changtian.adapter.SamplePhotosAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.SampleInfoCompreData;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;


@EActivity(R.layout.activity_auditor_info)
public class AuditorInfoActivity extends BaseActvity {

    @Extra
    ArrayList<SampleInfoCompreData.SampleDetailsBean.SampleDetailItemsBean> sampleDetailItemsBeans;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    AuditorInfoAdapter auditorInfoAdapter;
    @Extra
    ArrayList<SampleInfoCompreData.SamplePhotosBean> samplePhotosBeans;
    RecyclerView rv_lists;
    SamplePhotosAdapter samplePhotosAdapter;

    private ArrayList<String> photos=new ArrayList<>();

    @AfterViews
    void init() {

        initImageBackText("采样详情");
        auditorInfoAdapter = new AuditorInfoAdapter(R.layout.auditor_info_item);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(auditorInfoAdapter);
        auditorInfoAdapter.addData(sampleDetailItemsBeans);
        View view = this.getLayoutInflater().inflate(R.layout.photos_item, (ViewGroup) rv_list.getParent(), false);
        auditorInfoAdapter.addFooterView(view);
        rv_lists = view.findViewById(R.id.rv_listsssss);
        rv_lists.setLayoutManager(new GridLayoutManager(this, 5));
        samplePhotosAdapter = new SamplePhotosAdapter(R.layout.photos_items, this);
        rv_lists.setAdapter(samplePhotosAdapter);
        samplePhotosAdapter.addData(samplePhotosBeans);
        ViseLog.d("samplePhotosBeans:" + samplePhotosBeans.size());

        samplePhotosAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                photos.clear();

                for (int i = 0; i < samplePhotosAdapter.getData().size(); i++) {
                    photos.add(PHOTOS_URL+samplePhotosAdapter.getData().get(i).getFujian());
                }
//                PhotoLookActivity_.intent(AuditorInfoActivity.this).Photolist(photos).position(position).start();

            }
        });
    }
}
