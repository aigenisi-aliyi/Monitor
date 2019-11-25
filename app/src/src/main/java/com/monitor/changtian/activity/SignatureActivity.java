package com.monitor.changtian.activity;

import android.content.Intent;
import android.net.Uri;

import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.presenter.AddPhotosPresenter;
import com.monitor.changtian.utils.FileUtils;
import com.monitor.changtian.view.AddPhotosView;
import com.venusic.handwrite.view.HandWriteView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import static com.monitor.changtian.constant.PublicConstant.SIGN_PHOTOS_PATH;

@EActivity(R.layout.activity_signature)
public class SignatureActivity extends BaseActvity implements BaseActvity.Rightlistener, AddPhotosView {

    @ViewById(R.id.hw_sign)
    HandWriteView hw_sign;

    @Extra
    String fsid;

    AddPhotosPresenter addPhotosPresenter;

    @AfterViews
    void init() {
        initRightOnclikText("签名", "确定", this);
        addPhotosPresenter = new AddPhotosPresenter(this, this);
        hw_sign.setPaintWidth(2, 15);
        hw_sign.setPaintColor(this.getResources().getColor(R.color.blak));
    }


    @Click(R.id.cf_imageButton)
    public void cf_imageButton() {
        hw_sign.clear();
    }

    @Override
    public void rightlistener() {
        if (hw_sign.isSign()) {
            try {

                if (FileUtils.checkFilePathExists(SIGN_PHOTOS_PATH)) {
                    hw_sign.save(SIGN_PHOTOS_PATH + "sgin.png", true, 10, false);
                } else {
                    File fenlif = new File(SIGN_PHOTOS_PATH);
                    fenlif.mkdirs();
                    hw_sign.save(SIGN_PHOTOS_PATH + "sgin.png", true, 10, false);
                }

                ShowDialogtitle("请稍等...",this);
                addPhotosPresenter.AddfujianInfo(MyApplication.getInstance().getUser(), "4", "1", fsid, new File(SIGN_PHOTOS_PATH + "sgin.png"));

            } catch (Exception e) {

            }
        } else {
            msg("请先签名,然后再提交!");
        }
    }

    @Override
    public void OnAddPhotos(ResultBean resultBean) {
        DissDialog();
        if (resultBean.getResult().equals("1")) {
            // 发送广播
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(SIGN_PHOTOS_PATH + "sgin.png"))));
            msg("成功");
            finish();
        } else {
            msg(resultBean.getErrormessage());
        }

    }

    @Override
    public void OnError(String s) {

    }
}
