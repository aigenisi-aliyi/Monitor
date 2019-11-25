package com.monitor.changtian.activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.table.TableData;
import com.bin.david.form.listener.OnColumnItemClickListener;
import com.monitor.changtian.R;
import com.monitor.changtian.base.BaseActvity;
import com.monitor.changtian.bean.BlackTable;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 烟气黑度表格
 */

@EActivity(R.layout.activity_black)
public class BlackTableActivity extends BaseActvity implements OnColumnItemClickListener<String>, BaseActvity.Rightlistener {

    @ViewById(R.id.id_yanqiheidu)
    SmartTable id_yanqiheidu;

    @Extra
    boolean isWd;

    @AfterViews
    void init() {
        initRightOnclikText("烟气黑度统计表", "保存", this);
        initTable();
    }

    ArrayList<BlackTable> yanqiData = new ArrayList<>();

    List<Double> doubles = new ArrayList<>();

    @Override
    public void onClick(Column<String> column, String value, String s, int position) {
        //每个输入框点击事件
        selectYanQiData(column.getFieldName(), position);
    }

    private static int columnCount = 1;
    private boolean isAdd = false;
    @Click(R.id.id_bt_add)
    public void id_bt_add() {
        if (isAdd && columnCount <= 5) {
            List<BlackTable> key = new ArrayList<>();
            BlackTable table = new BlackTable(yanqiData.size());
            key.add(table);
            id_yanqiheidu.addData(key, true);
            isAdd = false;
            columnCount++;
        } else if (columnCount > 5) {
            isAdd = true;
            msg("烟气黑度表，数据已完整！" + columnCount);
            Log.i("onClick", "不满足新增行条件");
        }else {
            isAdd = true;
            msg("不满足新增条件，烟气黑度等级已达到标准限值！");
            Log.i("onClick", "不满足新增行条件");
        }
    }



    private void selectYanQiData(final String column, final int position) {
        OptionsPickerView pvOptions = new OptionsPickerView.Builder(BlackTableActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //用户选择等级
                if ("min0".equals(column)) {
                    yanqiData.get(position).setMin0(doubles.get(options1) + "");
                    if (yanqiData.get(position).getMin0().contains("5.0")) {
                        isAdd = true;
                    }
                    id_yanqiheidu.notifyDataChanged();
                } else if ("min15".equals(column)) {
                    yanqiData.get(position).setMin15(doubles.get(options1) + "");
                    id_yanqiheidu.notifyDataChanged();
                } else if ("min30".equals(column)) {
                    yanqiData.get(position).setMin30(doubles.get(options1) + "");
                    id_yanqiheidu.notifyDataChanged();
                    if (isWd && position == yanqiData.size() - 1) {
                        isAdd = true;
                    }
                } else if ("min45".equals(column)) {
                    yanqiData.get(position).setMin45(doubles.get(options1) + "");
                    id_yanqiheidu.notifyDataChanged();
                    if (!isWd && position == yanqiData.size() - 1) {
                        isAdd = true;
                    }
                }
            }
        })
                .setTitleText("选择烟气黑度等级")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(true)// default is true
                .build();
        pvOptions.setPicker(doubles);//一级选择器
        pvOptions.show();
    }

    /**
     * 出现烟气黑度，需要进行表格设置
     */
    private void initTable() {
        doubles.add(0.0);
        doubles.add(0.25);
        doubles.add(0.5);
        doubles.add(0.75);
        doubles.add(1.0);
        doubles.add(1.25);
        doubles.add(1.5);
        doubles.add(1.75);
        doubles.add(2.0);
        doubles.add(2.25);
        doubles.add(2.5);
        doubles.add(2.75);
        doubles.add(3.0);
        doubles.add(3.25);
        doubles.add(3.5);
        doubles.add(3.75);
        doubles.add(4.0);
        doubles.add(4.25);
        doubles.add(4.5);
        doubles.add(4.75);
        doubles.add(5.0);

        yanqiData.clear();
        yanqiData.add(new BlackTable(0));
        Column<String> id = new Column<>("分秒", "id");
        Column<String> min0 = new Column<>("0", "min0");
        min0.setOnColumnItemClickListener(this);
        Column<String> min30 = new Column<>("30", "min30");
        min30.setOnColumnItemClickListener(this);
//        Column<String> id_bt_add = new Column<>("新增一行", "id_bt_add");
        if (isWd) {
            TableData<BlackTable> tableData = new TableData<>("", yanqiData, id, min0, min30);
            id_yanqiheidu.setTableData(tableData);
        } else {
            Column<String> min15 = new Column<>("15", "min15");
            min15.setOnColumnItemClickListener(this);
            Column<String> min45 = new Column<>("45", "min45");
            min45.setOnColumnItemClickListener(this);
            TableData<BlackTable> tableData = new TableData<>("", yanqiData, id, min0, min15, min30, min45);
            id_yanqiheidu.setTableData(tableData);
        }
        id_yanqiheidu.getConfig().setShowXSequence(false);
        id_yanqiheidu.getConfig().setShowYSequence(false);
        id_yanqiheidu.getConfig().setColumnTitleStyle(new FontStyle(50, Color.BLUE));
        id_yanqiheidu.getConfig().setContentStyle(new FontStyle(40, Color.BLACK));
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        id_yanqiheidu.getConfig().setMinTableWidth(width);
    }

    @Override
    public void rightlistener() {
        Intent intent = getIntent();
        intent.putParcelableArrayListExtra("yanqiData", yanqiData);
        setResult(100, intent);
        finish();
    }
}
