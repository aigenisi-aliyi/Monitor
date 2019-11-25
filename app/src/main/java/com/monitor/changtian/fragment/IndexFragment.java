package com.monitor.changtian.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.accuse.AccuseMainFragment_;
import com.monitor.changtian.MyApplication;
import com.monitor.changtian.R;
import com.monitor.changtian.activity.task.EquipmentreturnActivity_;
import com.monitor.changtian.adapter.IconAdapter;
import com.monitor.changtian.base.BaseFragment;
import com.monitor.changtian.bean.AllUserInfo;
import com.monitor.changtian.bean.IconBean;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.TestEvent;
import com.monitor.changtian.bean.UserPermissionBean;
import com.monitor.changtian.utils.GlideImageLoader;
import com.monitor.detectionschemeaudit.DetectionSchemeAuditMainFragment_;
import com.monitor.finance.FinanceMainActivity_;
import com.monitor.offer.OfferFragment_;
import com.monitor.sample.GetSampleActivity_;
import com.monitor.sample.SampleActivity_;
import com.monitor.taskallocation.TaskMainFragment_;
import com.sunfusheng.marqueeview.MarqueeView;
import com.vise.log.ViseLog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ken on 2018/8/10.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

@EFragment(R.layout.fragment_index)
public class IndexFragment extends BaseFragment implements OnBannerListener {
    Banner banner;
    MarqueeView marqueeView;
    private ArrayList<Integer> list_path;
    private ArrayList<String> list_title;
    @ViewById(R.id.rv_list)
    RecyclerView rv_list; // 图标显示
    IconAdapter iconAdapter;
    List<IconBean> iconBeans = new ArrayList<>();


    ResultBean perUserInfo = MyApplication.getInstance().getResultInfo(); //获取用户信息

    @AfterViews
    void init() {

        iconAdapter = new IconAdapter(R.layout.icon_item, getActivity());
        rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        rv_list.setAdapter(iconAdapter);

        if(perUserInfo!=null){
            rolejarry.addAll(perUserInfo.getRolejarry());
        }

        initData();
        View view1 = getActivity().getLayoutInflater().inflate(R.layout.icon_head, (ViewGroup) rv_list.getParent(), false);
        iconAdapter.addHeaderView(view1);
        banner = view1.findViewById(R.id.banner);
        marqueeView = view1.findViewById(R.id.marqueeView);
        initBanner();
        List<String> info = new ArrayList<>();
        info.add("关于护城河水质采样的样品未通过,请重新取样");
        info.add("重新采样！");
        info.add("赶快去采样！");
        info.add("立马去采样!");
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);
        iconAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (iconAdapter.getData().get(position).getStatus().equals("1")) {
                    /**
                     * 财务
                     */
                    if (iconAdapter.getData().get(position).getName().equals("财务")) {
                        FinanceMainActivity_.intent(getActivity()).start();
                    }
                    /**
                     * 报价
                     */
                    if (iconAdapter.getData().get(position).getName().equals("报价")) {
                        OfferFragment_.intent(getActivity()).start();
                    }
                    /**
                     * 分配
                     */
                    if (iconAdapter.getData().get(position).getName().equals("分配")) {
                        TaskMainFragment_.intent(getActivity()).start();
                    }
                    /**
                     *采样
                     */
                    if (iconAdapter.getData().get(position).getName().equals("采样")) {
                        SampleTaskMainFragment_.intent(getActivity()).start();
                    }
                    /**
                     *方案
                     */
                    if (iconAdapter.getData().get(position).getName().equals("方案")) {
                        DetectionSchemeAuditMainFragment_.intent(getActivity()).start();
                    }
                    /**
                     *质控
                     */
                    if (iconAdapter.getData().get(position).getName().equals("质控")) {
                        AccuseMainFragment_.intent(getActivity()).start();
                    }
                    /**
                     *收样
                     */
                    if (iconAdapter.getData().get(position).getName().equals("收样")) {
                        SampleActivity_.intent(getActivity()).title("收样").start();
                    }
                    /**
                     * 设备归还
                     */
                    if (iconAdapter.getData().get(position).getName().equals("设备归还")) {
                        EquipmentreturnActivity_.intent(getActivity()).start();
                    }
                    /**
                     * 样品领取
                     */
                    if (iconAdapter.getData().get(position).getName().equals("领样")) {
                        SampleActivity_.intent(getActivity()).title("领样").start();
                    }


                } else {
                    msg("暂无权限");
                }
            }
        });
    }

    /**
     * 加载图标数据
     */
    public void initData() {
        iconBeans = new ArrayList<>();
        IconBean iconBean1 = new IconBean("财务", R.mipmap.money_h, "0", 0);
        IconBean iconBean2 = new IconBean("采样", R.mipmap.sample_h, "0", 0);
        IconBean iconBean3 = new IconBean("方案", R.mipmap.plansh_h, "0", 0);
        IconBean iconBean4 = new IconBean("报价", R.mipmap.baojia_h, "0", 0);
        IconBean iconBean5 = new IconBean("质控", R.mipmap.quality1_h, "0", 0);
        IconBean iconBean6 = new IconBean("分配", R.mipmap.task2_h, "0", 0);
        IconBean iconBean7 = new IconBean("收样", R.mipmap.ku_h, "0", 0);
        IconBean iconBean8 = new IconBean("设备归还", R.mipmap.m_back_h, "0", 0);
        IconBean iconBean9 = new IconBean("领样", R.mipmap.lingyang_h, "0", 0);
        iconBeans.add(iconBean1);
        iconBeans.add(iconBean2);
        iconBeans.add(iconBean3);
        iconBeans.add(iconBean4);
        iconBeans.add(iconBean5);
        iconBeans.add(iconBean6);
        iconBeans.add(iconBean7);
        iconBeans.add(iconBean8);
        iconBeans.add(iconBean9);

        for (int i = 0; i < rolejarry.size(); i++) {
            if (rolejarry.get(i).getRoleName().indexOf("财务") != -1) {
                iconBeans.get(0).setStatus("1");
                iconBeans.get(0).setIcon_url(R.mipmap.money);
                iconBeans.get(0).setSort(-6);
            } else if (rolejarry.get(i).getRoleName().indexOf("方案") != -1) {
                iconBeans.get(2).setStatus("1");
                iconBeans.get(2).setIcon_url(R.mipmap.plansh);
                iconBeans.get(2).setSort(-8);
            } else if (rolejarry.get(i).getRoleName().indexOf("报价") != -1) {
                iconBeans.get(3).setStatus("1");
                iconBeans.get(3).setIcon_url(R.mipmap.baojia);
                iconBeans.get(3).setSort(-7);
            } else if (rolejarry.get(i).getRoleName().indexOf("现场采样") != -1) {
                iconBeans.get(1).setStatus("1");
                iconBeans.get(1).setIcon_url(R.mipmap.sample);
                iconBeans.get(1).setSort(-3);
            } else if (rolejarry.get(i).getRoleName().indexOf("质控") != -1) {
                iconBeans.get(4).setStatus("1");
                iconBeans.get(4).setIcon_url(R.mipmap.quality1);
                iconBeans.get(4).setSort(-5);
            } else if (rolejarry.get(i).getRoleName().indexOf("项目负责人") != -1) {
                iconBeans.get(5).setStatus("1");
                iconBeans.get(5).setIcon_url(R.mipmap.task2);
                iconBeans.get(5).setSort(-4);
            } else if (rolejarry.get(i).getRoleName().indexOf("样品管理") != -1) {
                ViseLog.d(rolejarry.get(i).getRoleName());
                iconBeans.get(6).setStatus("1");
                iconBeans.get(6).setIcon_url(R.mipmap.ku);
                iconBeans.get(6).setSort(-2);
            } else if (rolejarry.get(i).getRoleName().indexOf("设备管理") != -1) {
                ViseLog.d(rolejarry.get(i).getRoleName());
                iconBeans.get(7).setStatus("1");
                iconBeans.get(7).setIcon_url(R.mipmap.m_back);
                iconBeans.get(7).setSort(-1);
            } else if (rolejarry.get(i).getRoleName().indexOf("实验人员") != -1) {
                ViseLog.d(rolejarry.get(i).getRoleName());
                iconBeans.get(8).setStatus("1");
                iconBeans.get(8).setIcon_url(R.mipmap.lingyang);
                iconBeans.get(8).setSort(-1);
            }
        }
        //对分组出来数据进行排序
        Collections.sort(iconBeans, new Comparator<IconBean>() {
            @Override
            public int compare(IconBean o1, IconBean o2) {

                if (o1.getSort() > o2.getSort()) {
                    return 1;
                }
                return -1;
            }
        });
        iconAdapter.setNewData(iconBeans);

    }

    public void initBanner() {
        //放图片地址的集合
        list_path = new ArrayList<>();
        //放标题的集合
        list_title = new ArrayList<>();
        list_path.add(R.mipmap.banner_pic1_01);
        list_path.add(R.mipmap.banner_pic1_02);
        list_path.add(R.mipmap.banner_pic1_03);
        list_title.add("");
        list_title.add("");
        list_title.add("");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。

        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        ViseLog.d("你点了第" + position + "张轮播图");
    }

    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
//        //开始轮播
        banner.startAutoPlay();
        marqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        marqueeView.stopFlipping();
        banner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    List<ResultBean.RolejarryBean> rolejarry = new ArrayList<>();


}


