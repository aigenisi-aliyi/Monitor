package com.monitor.changtian.bean;

/**
 * 后台返回设备类型信息：用于判断是否需要进行设备校准
 * @author dl
 * @data:2019/11/10
 */
public class EquipTypeBean {

    /**
     * {"result":"0", 0成功--type类型非空，1失败--type类型为空
     * "errormessage":"查询成功/查询失败",
     * "type":"xxx2031xxx", 设备类型，判断依据
     * "CollarState":0, 领用状态（0否，1是）
     * "barCode":"xxxxxx" 设备类型，返回数据必须有这个数据。
     * }
     */

    private String result;
    private String errormessage;
    private String type;
    private String barCode;
    private int CollarState;


    public EquipTypeBean() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getCollarState() {
        return CollarState;
    }

    public void setCollarState(int collarState) {
        CollarState = collarState;
    }
}
