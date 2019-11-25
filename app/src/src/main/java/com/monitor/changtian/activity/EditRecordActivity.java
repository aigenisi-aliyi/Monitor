package com.monitor.changtian.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.allen.library.SuperTextView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.EditRecordAdapter;
import com.monitor.changtian.adapter.GridImageAdapter;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.EventBus.UpdataEvent;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleCategoryItemsDataBean;
import com.monitor.changtian.bean.SampleDetailsAndItemsData;
import com.monitor.changtian.bean.SampleInfoDataBean;
import com.monitor.changtian.bean.SampleSubmitBean;
import com.monitor.changtian.bean.SampleType;
import com.monitor.changtian.presenter.AddPresenter;
import com.monitor.changtian.utils.StringUtils;
import com.monitor.changtian.view.AddView;
import com.monitor.changtian.widght.AutoHeightGridView;
import com.monitor.changtian.widght.FullyGridLayoutManager;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

@EActivity(R.layout.activity_edit_record)
public class EditRecordActivity extends BaseActvity implements AddView, EditRecordAdapter.OnEditValueChangedListener {


    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    @Extra
    String SampleId;
    @Extra
    String SampleNum;
    @Extra
    String detailid;
    @Extra
    ArrayList<SampleDetailsAndItemsData.SampleDetailItemsBean> sampleDetailItemsBeans;

    @Extra
    ArrayList<SampleDetailsAndItemsData.PhotosBean> photosBeans;
    EditRecordAdapter editRecordAdapter;
    List<SampleCategoryItemsDataBean> mList = new ArrayList<>();
    LinearLayout ll_foot;
    AutoHeightGridView gridView;
    SuperTextView stv_submit;
    private AddPresenter addPresenter;
    RecyclerView recycler_image, recycler_video;
    GridImageAdapter adapter_image, adapter_video;
    private List<LocalMedia> selectList_image = new ArrayList<>();
    private List<LocalMedia> selectList_video = new ArrayList<>();
    private List<LocalMedia> selectList_All = new ArrayList<>();

    @AfterViews
    void init() {

        initImageBackText("编辑采样记录");
        initView();
        initData();
        addPresenter = new AddPresenter(this, this);
        editRecordAdapter = new EditRecordAdapter(R.layout.sample_items_info);
        editRecordAdapter.setmEditListenter(this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(editRecordAdapter);
        editRecordAdapter.addData(sampleDetailItemsBeans);
        /**
         * 添加尾布局
         */
        View view = this.getLayoutInflater().inflate(R.layout.add_rv_foot, (ViewGroup) rv_list.getParent(), false);
        editRecordAdapter.addFooterView(view);
        ll_foot = view.findViewById(R.id.ll_foot);
        ll_foot.setVisibility(View.VISIBLE);
        gridView = view.findViewById(R.id.gridView);
        stv_submit = view.findViewById(R.id.stv_submit);
//        initPhotos();

        for (int i = 0; i < photosBeans.size(); i++) {
            LocalMedia s = new LocalMedia();
            s.setPath(PHOTOS_URL + photosBeans.get(i).getFujian());


            if (photosBeans.get(i).getFujian().indexOf(".mp4") == -1) {
                //不包含是图片
                selectList_image.add(s);
            } else {
                //是视频
                selectList_video.add(s);
            }
        }
        recycler_image = (RecyclerView) view.findViewById(R.id.recycler_image);
        FullyGridLayoutManager manager_image = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_image.setLayoutManager(manager_image);
        adapter_image = new GridImageAdapter(this, onAddPicClickListener, "image");
        adapter_image.setList(selectList_image);
        adapter_image.setSelectMax(0);
        recycler_image.setAdapter(adapter_image);
        adapter_image.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_image.get(position);
                String pictureType = media.getPictureType();
                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                // 预览图片
                PictureSelector.create(EditRecordActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, selectList_image);
            }
        });

        recycler_video = (RecyclerView) view.findViewById(R.id.recycler_video);
        FullyGridLayoutManager manager_video = new FullyGridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recycler_video.setLayoutManager(manager_video);
        adapter_video = new GridImageAdapter(this, onAddPicClickListener, "video");
        adapter_video.setList(selectList_video);
        adapter_video.setSelectMax(0);
        recycler_video.setAdapter(adapter_video);
        adapter_video.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                LocalMedia media = selectList_video.get(position);
                String pictureType = media.getPictureType();
                PictureSelector.create(EditRecordActivity.this).externalPictureVideo(media.getPath());
            }
        });



        OnFootCliks();
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(String type) {

            switch (type) {
                case "video":
                    PictureSelector.create(EditRecordActivity.this)
                            .openGallery(PictureMimeType.ofVideo())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(true)

                            .recordVideoSecond(150)
                            .glideOverride(160, 160)
                            .previewEggs(true)
                            .selectionMedia(selectList_video)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case "Audio":
                    PictureSelector.create(EditRecordActivity.this)
                            .openGallery(PictureMimeType.ofAudio())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
                            .minSelectNum(1)
                            .selectionMode(true ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)
                            .previewImage(true)
                            .previewVideo(true)
                            .enablePreviewAudio(true) // 是否可播放音频
                            .isCamera(true)
                            .glideOverride(160, 160)
                            .previewEggs(true)

                            .selectionMedia(selectList_image)
                            .forResult(PictureConfig.CHOOSE_REQUEST);
                    break;
                case "image":
                    PictureSelector.create(EditRecordActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .theme(R.style.picture_default_style)
                            .maxSelectNum(9)
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
                    break;
            }

        }
    };

    /**
     *
     */
    public void OnFootCliks() {
        stv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stv_submit();
            }
        });
    }

    String SampleOnlyNum = "";

    String remark = "";
    String details = "";

    public void stv_submit() {


        ViseLog.d("selectList_video:" + selectList_video.size());
        ViseLog.d("selectList_image:" + selectList_image.size());
        selectList_All = new ArrayList<>();
        selectList_All.addAll(selectList_image);
        selectList_All.addAll(selectList_video);

        List<File> imageFile1 = new ArrayList<>();
        for (int i = 0; i < selectList_All.size(); i++) {
            if (!StringUtils.isUrl(selectList_All.get(i).getPath())) {
                imageFile1.add(new File(selectList_All.get(i).getPath()));
            }

        }

        List<SampleSubmitBean> beanList = new ArrayList<>();
        for (int i = 0; i < editRecordAdapter.getData().size(); i++) {

            SampleSubmitBean sampleSubmitBean = new SampleSubmitBean();
            sampleSubmitBean.setItemid(editRecordAdapter.getData().get(i).getItemid());
            sampleSubmitBean.setItemvalue(editRecordAdapter.getData().get(i).getItemvalue());
            beanList.add(sampleSubmitBean);
        }

        details = JSON.toJSONString(beanList);
        String data = "{detailid:\"" + detailid + "\",SampleId:\"" + SampleId + "\",SampleOnlyNum:\"" + SampleOnlyNum + "\",SampleNum:\"" + SampleNum + "\",loginId:\"" + MyApplication.getInstance().getUser() + "\",remark:\"" + remark + "\",details:" + details + " }";
        addPresenter.UpdateSampleDetailsInfo(data, imageFile1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择
                    selectList_video = PictureSelector.obtainMultipleResult(data);
                    adapter_video.setList(selectList_video);
                    adapter_video.notifyDataSetChanged();
                    break;
                case 666:

                    selectList_image = PictureSelector.obtainMultipleResult(data);
                    adapter_image.setList(selectList_image);
                    adapter_image.notifyDataSetChanged();
                    break;
            }
        }
    }


    public void initData() {
        if (sampleDetailItemsBeans != null) {
            mList.clear();
            for (int i = 0; i < sampleDetailItemsBeans.size(); i++) {
                SampleCategoryItemsDataBean sampleCategoryItemsDataBean = new SampleCategoryItemsDataBean();
                sampleCategoryItemsDataBean.setItemsvalue(sampleDetailItemsBeans.get(i).getItemvalue());
                sampleCategoryItemsDataBean.setItemsname(sampleDetailItemsBeans.get(i).getItemsInfo());
                mList.add(sampleCategoryItemsDataBean);
            }
        }


    }

    public void initView() {


    }

    @Override
    public void Value(Editable editable, int position) {
        SampleDetailsAndItemsData.SampleDetailItemsBean sampleCategoryItemsDataBean = editRecordAdapter.getData().get(position);
        if (editable.toString() != null) {
            sampleCategoryItemsDataBean.setItemvalue(editable.toString());
        } else {
            sampleCategoryItemsDataBean.setItemvalue("");
        }
    }

    @Override
    public void onTypeData(List<SampleType> sampleType) {

    }

    @Override
    public void GetSampleInfoData(List<SampleInfoDataBean> sampleInfoDataBeans) {

    }

    @Override
    public void GetSampleCategoryItemsData(List<SampleCategoryItemsDataBean> sampleCategoryItemsDataBeans) {

    }

    @Override
    public void onMessage(ResultBean message) {

        if (message.getResult().equals("1")) {
            msg("成功");
            finish();
            EventBus.getDefault().post(new UpdataEvent("更新"));
        } else {
            msg(message.getErrormessage());
        }
    }
}
