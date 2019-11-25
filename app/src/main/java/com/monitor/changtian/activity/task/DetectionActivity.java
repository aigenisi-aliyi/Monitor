package com.monitor.changtian.activity.task;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.DetectionSchemeDataBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.interfaces.SubmitView;
import com.monitor.changtian.presenter.TaskPresenter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static com.monitor.changtian.constant.PublicConstant.PHOTOS_URL;

/**
 * 检测方案详情
 */
@EActivity(R.layout.activity_detection)
public class DetectionActivity extends BaseActvity implements SubmitView<List<DetectionSchemeDataBean>> {

    @Extra
    String schemeid;
    TaskPresenter taskPresenter;
    String schemes = "";

    @ViewById(R.id.tv_de_name)
    TextView tv_de_name;
    @ViewById(R.id.tv_de_way)
    TextView tv_de_way;
    @ViewById(R.id.tv_de_content)
    TextView tv_de_content;
    @ViewById(R.id.tv_de_pic)
    ImageView tv_de_pic;

    @AfterViews
    void init() {
        initImageBackText(getString(R.string.detection_info));
        if (schemeid != null) {
            schemes = schemeid;
        }
        taskPresenter = new TaskPresenter(this, this);
        initData();
    }

    public void initData() {
        String data = "{schemeid:\"" + schemes + " \",projectid:\"\",schemeStatus:\"\",iscomplete:\"\"}";
        taskPresenter.GetDetectionSchemeData(data);
    }

    @Override
    public void onData(List<DetectionSchemeDataBean> detectionSchemeDataBean) {

        if (detectionSchemeDataBean != null) {
            tv_de_name.setText(detectionSchemeDataBean.get(0).getSchemeName());
            tv_de_way.setText(detectionSchemeDataBean.get(0).getBuyWayname());
            tv_de_content.setText(detectionSchemeDataBean.get(0).getIntroduction());
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.color.color_f6)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(DetectionActivity.this)
                    .load(PHOTOS_URL + detectionSchemeDataBean.get(0).getFujian())
                    .apply(options)
                    .into(tv_de_pic);
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
