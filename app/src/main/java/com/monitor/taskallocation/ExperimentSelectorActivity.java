package com.monitor.taskallocation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.monitor.changtian.FactorsExperimenterDataBean;
import com.monitor.changtian.R;
import com.monitor.changtian.adapter.ExperimentAdapter;
import com.monitor.changtian.bean.EventBus.ExperimentEvent;
import com.vise.log.ViseLog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.dialog_experiment_item)
public class ExperimentSelectorActivity extends AppCompatActivity {

    @ViewById(R.id.rv_list)
    RecyclerView rv_list;
    ExperimentAdapter experimentAdapter;
    @Extra
    ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeanArrayList;

    @Extra
    String pointsid;

    @Extra
    ArrayList<FactorsExperimenterDataBean> factorsExperimenterDataBeans;

    @AfterViews
    void init() {


//        addPresenter = new TaskAddPresenter(this, this);
//        String data = "{pointsid:\"" + pointsid + "\"} ";
//        addPresenter.GetFactorsExperimenterData(data);
        experimentAdapter = new ExperimentAdapter(factorsExperimenterDataBeans, R.layout.task_experiment_item, this);
        rv_list.setLayoutManager(new GridLayoutManager(ExperimentSelectorActivity.this, 2));
        rv_list.setAdapter(experimentAdapter);

        if (factorsExperimenterDataBeans.size() > 0) {
            experimentAdapter.setNewData(factorsExperimenterDataBeans);
            ViseLog.d("1111");
        } else {
            experimentAdapter.setNewData(factorsExperimenterDataBeanArrayList);
            ViseLog.d("222");
        }

        experimentAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                OptionsPickerView pvOptions = new OptionsPickerView.Builder(ExperimentSelectorActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        //从 UserBean 里面取值  然后保存在 UserBeanSave里面
                        String content = experimentAdapter.getData().get(position).getUsers().get(options1).getPickerViewText();
                        int ids = experimentAdapter.getData().get(position).getUsers().get(options1).getId();
                        List<FactorsExperimenterDataBean.UsersBeanSave> saveList = new ArrayList<>();
                        FactorsExperimenterDataBean.UsersBeanSave save = new FactorsExperimenterDataBean.UsersBeanSave();
                        save.setId(ids);
                        save.setUserName(content);
                        saveList.add(save);
                        ViseLog.d(saveList.size());
                        experimentAdapter.getData().get(position).setUser_save(saveList);
                        experimentAdapter.getData().get(position).setIsshow(false);
                        experimentAdapter.notifyDataSetChanged();


                        for (int i = position + 1; i < experimentAdapter.getData().size(); i++) {

                            for (int j = 0; j < experimentAdapter.getData().get(i).getUsers().size(); j++) {

                                if (content.equals(experimentAdapter.getData().get(i).getUsers().get(j).getUserName()))
                                {
                                    List<FactorsExperimenterDataBean.UsersBeanSave> saveList1 = new ArrayList<>();
                                    FactorsExperimenterDataBean.UsersBeanSave save1 = new FactorsExperimenterDataBean.UsersBeanSave();
                                    save1.setId(ids);
                                    save1.setUserName(content);
                                    saveList1.add(save);
                                    experimentAdapter.getData().get(position+1).setUser_save(saveList);
                                    experimentAdapter.getData().get(position+1).setIsshow(false);
                                    experimentAdapter.notifyDataSetChanged();
                                }else{
                                    List<FactorsExperimenterDataBean.UsersBeanSave> saveList2 = new ArrayList<>();
                                    FactorsExperimenterDataBean.UsersBeanSave save2 = new FactorsExperimenterDataBean.UsersBeanSave();
                                    save2.setId(experimentAdapter.getData().get(i).getUsers().get(0).getId());
                                    save2.setUserName(experimentAdapter.getData().get(i).getUsers().get(0).getUserName());
                                    saveList2.add(save2);
                                    experimentAdapter.getData().get(position+1).setUser_save(saveList2);
                                    experimentAdapter.getData().get(position+1).setIsshow(false);
                                    experimentAdapter.notifyDataSetChanged();
                                }

                            }


                        }


//                        /**
//                         * 推荐
//                         */
//                        for (int i = position+ 1; i < experimentAdapter.getData().size(); i++) {
//                            for (int j = i; j < experimentAdapter.getData().get(i).getUsers().size(); j++) {
//                                if (content.equals(experimentAdapter.getData().get(i).getUsers().get(j).getUserName())) {
//                                    ViseLog.d("2222");
//                                    List<FactorsExperimenterDataBean.UsersBeanSave> saveList1 = new ArrayList<>();
//                                    FactorsExperimenterDataBean.UsersBeanSave save1 = new FactorsExperimenterDataBean.UsersBeanSave();
//                                    save1.setId(experimentAdapter.getData().get(i).getUsers().get(j).getId());
//                                    save1.setUserName(experimentAdapter.getData().get(i).getUsers().get(j).getUserName());
//                                    saveList1.add(save1);
//                                    experimentAdapter.getData().get(position).setUser_save(saveList1);
//                                } else {
//                                    ViseLog.d("33333");
//                                    List<FactorsExperimenterDataBean.UsersBeanSave> saveList2 = new ArrayList<>();
//                                    FactorsExperimenterDataBean.UsersBeanSave save2 = new FactorsExperimenterDataBean.UsersBeanSave();
//                                    save2.setId(experimentAdapter.getData().get(i).getUsers().get(0).getId());
//                                    save2.setUserName(experimentAdapter.getData().get(i).getUsers().get(0).getUserName());
//                                    saveList2.add(save2);
//                                    experimentAdapter.getData().get(position).setUser_save(saveList2);
//
//                                }
//                            }
//                        }

                    }
                })
                        .setTitleText("人员选择")
                        .setDividerColor(Color.BLACK)
                        .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                        .setContentTextSize(20)
                        .setOutSideCancelable(true)// default is true
                        .build();
                pvOptions.setPicker(experimentAdapter.getData().get(position).getUsers());//一级选择器
                pvOptions.show();

            }
        });
    }

    @Click(R.id.btn_submit)
    public void btn_submit() {
        finish();
        ArrayList<FactorsExperimenterDataBean> eventlist = new ArrayList<>();
        eventlist.addAll(experimentAdapter.getData());
        EventBus.getDefault().postSticky(new ExperimentEvent(eventlist));
    }


    @Click(R.id.btn_false)
    public void btn_false() {
        finish();
    }


//
//    OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
//        @Override
//        public void onOptionsSelect(int options1, int options2, int options3, View v) {
//            //返回的分别是三个级别的选中位置
//            String content = item.getUsers().get(options1).getPickerViewText();
//            String ids = item.getUsers().get(options1).getId() + "";
//            tv_name.setText(content);
//            /**
//             * 选择之后循环 然后判断当前人物
//             */
////                            for (int i = helper.getLayoutPosition()+1; i < ExperimentAdapter.this.getData().size(); i++) {
////                                for (int j = i; j < ExperimentAdapter.this.getData().get(i).getUsers().size(); j++) {
////                                    if (content.equals(ExperimentAdapter.this.getData().get(i).getUsers().get(j).getUserName())) {
////                                        ViseLog.d("2222");
////                                        tv_name.setText(ExperimentAdapter.this.getData().get(i).getUsers().get(j).getUserName());
////                                    } else {
////                                        ViseLog.d("33333");
////                                        tv_name.setText(ExperimentAdapter.this.getData().get(i).getUsers().get(0).getUserName());
////                                    }
////                                }
////                        }
//
//        }
//    })
//            .setTitleText("人员选择")
//            .setDividerColor(Color.BLACK)
//            .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//            .setContentTextSize(20)
//            .setOutSideCancelable(true)// default is true
//            .build();
//                        pvOptions.setPicker(item.getUsers());//一级选择器
//                        pvOptions.show();
}
