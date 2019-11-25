package com.monitor.changtian.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * 烟气黑度测量值
 */
public class BlackTable implements Parcelable{
    private int id;
    private String  min0;
    private String  min15;
    private String  min30;
    private String  min45;

    public BlackTable() {
    }

    public BlackTable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMin0(String min0) {
        this.min0 = min0;
    }

    public String getMin0() {
        return min0;
    }

    public String getMin15() {
        return min15;
    }

    public void setMin15(String min15) {
        this.min15 = min15;
    }

    public String getMin30() {
        return min30;
    }

    public void setMin30(String min30) {
        this.min30 = min30;
    }

    public String getMin45() {
        return min45;
    }

    public void setMin45(String min45) {
        this.min45 = min45;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**把javanbean中的数据写到Parcel
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(min0);
        dest.writeString(min15);
        dest.writeString(min30);
        dest.writeString(min45);
    }

    /**
     * 添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口
     */
    public static final Parcelable.Creator<BlackTable> CREATOR = new Parcelable.Creator<BlackTable>() {
        @Override
        public BlackTable createFromParcel(Parcel source) {//从Parcel中读取数据，返回person对象
            BlackTable info = new BlackTable();
            info.id = source.readInt();
            info.min0 = source.readString();
            info.min15 = source.readString();
            info.min30 = source.readString();
            info.min45 = source.readString();
            return info;
        }

        @Override
        public BlackTable[] newArray(int size) {
            return new BlackTable[size];
        }
    };
}
