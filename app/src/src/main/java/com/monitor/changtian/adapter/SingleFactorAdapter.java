package com.monitor.changtian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.monitor.changtian.R;
import com.monitor.changtian.bean.ResultBean;
import com.monitor.changtian.bean.SampleInfoByPointInfoBean;
import com.vise.log.ViseLog;

import java.util.List;

/**
 * Created by Administrator on 2018/10/17.
 */

public class SingleFactorAdapter extends RecyclerView.Adapter {
    private List<SampleInfoByPointInfoBean.DetailsBean> datas;

    private int selected = -1;
    public Context mContext;

    public List<SampleInfoByPointInfoBean.DetailsBean> getDatas() {
        return datas;
    }

    public void setDatas(List<SampleInfoByPointInfoBean.DetailsBean> datas) {
        this.datas = datas;
    }

    public SingleFactorAdapter(Context mContext) {
        this.mContext = mContext;
    }

    private SingleAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(SingleAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public void setSelection(int position) {
        this.selected = position;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SingleAdapter.SingleViewHolder) {
            final SingleAdapter.SingleViewHolder viewHolder = (SingleAdapter.SingleViewHolder) holder;
            String name = datas.get(position).getName();
            ViseLog.d("ssssss");
            viewHolder.mCheckBox.setText(name);
            if (selected == position) {
                viewHolder.mCheckBox.setTextColor(mContext.getResources().getColor(R.color.white));
                viewHolder.mCheckBox.setChecked(true);
                viewHolder.itemView.setSelected(true);
                datas.get(position).setSelected(true);
            } else {
                viewHolder.mCheckBox.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
                viewHolder.mCheckBox.setChecked(false);
                viewHolder.itemView.setSelected(false);
                datas.get(position).setSelected(false);
            }

            if (mOnItemClickLitener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(viewHolder.itemView, viewHolder.getAdapterPosition());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public String GetCheckStr() {
        String name = "";
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).isSelected() == true) {
                name = datas.get(i).getName();
            }
        }
        return name;
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {
        TextView mTvName;
        CheckBox mCheckBox;

        public SingleViewHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    public interface OnItemClickLitener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
}
