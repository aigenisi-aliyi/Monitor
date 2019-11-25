package com.monitor.changtian.bean;

import java.io.Serializable;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class CarDataListBean implements Serializable {


    /**
     * vid : 4f751a98-28ff-4f9d-b30d-1b829804acaa
     * vlicense : 吉A169830
     * vnum : 7
     * vtypes : 通讯车
     * vtypesid : 51
     * vitem : 54
     * vitemName : 五座车
     * brand : 151
     * brandname : 江铃全顺
     */

    private String vid;
    private String vlicense;
    private String vnum;
    private String vtypes;
    private String vtypesid;
    private String vitem;
    private String vitemName;
    private String brand;
    private String brandname;
    private boolean isChoice;
    private boolean isChoice_save;

    public boolean isChoice_save() {
        return isChoice_save;
    }

    public void setChoice_save(boolean choice_save) {
        isChoice_save = choice_save;
    }

    public boolean isChoice() {
        return isChoice;
    }


    public void setChoice(boolean choice) {
        isChoice = choice;
    }


    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getVlicense() {
        return vlicense;
    }

    public void setVlicense(String vlicense) {
        this.vlicense = vlicense;
    }

    public String getVnum() {
        return vnum;
    }

    public void setVnum(String vnum) {
        this.vnum = vnum;
    }

    public String getVtypes() {
        return vtypes;
    }

    public void setVtypes(String vtypes) {
        this.vtypes = vtypes;
    }

    public String getVtypesid() {
        return vtypesid;
    }

    public void setVtypesid(String vtypesid) {
        this.vtypesid = vtypesid;
    }

    public String getVitem() {
        return vitem;
    }

    public void setVitem(String vitem) {
        this.vitem = vitem;
    }

    public String getVitemName() {
        return vitemName;
    }

    public void setVitemName(String vitemName) {
        this.vitemName = vitemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
}

