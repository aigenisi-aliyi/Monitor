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

import java.util.List;

/**
 * Created by ken on 2018/9/27.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * へ　　　　／|
 * 　　/＼7　　∠＿/
 * 　 /　│　　 ／　／
 * 　│　Z ＿,＜　／　　 /`ヽ
 * 　 │　　　　　ヽ　　 /　　〉
 * 　Y　　　　　`　 /　　/
 * 　ｲ●　､　●　　⊂⊃〈　　/
 * 　()　 へ　　　　|　＼〈
 * 　　>ｰ ､_　 ィ　 │ ／／
 * 　 / へ　　 /　ﾉ＜| ＼＼
 * 　 ヽ_ﾉ　　(_／　 │／／
 * 　　7　　　　　　　|／
 * 　　＞―r￣￣`ｰ―＿
 */

public class SingleAdapter extends RecyclerView.Adapter {
    private List<ResultBean.RolejarryBean> datas;

    private int selected = -1;
    public Context mContext;

    public SingleAdapter(Context mContext, List<ResultBean.RolejarryBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
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
        if (holder instanceof SingleViewHolder) {
            final SingleViewHolder viewHolder = (SingleViewHolder) holder;
            String name = datas.get(position).getRoleName();
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
                name = datas.get(i).getRoleName();
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
