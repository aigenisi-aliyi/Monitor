package com.monitor.changtian.bean;


public class EquipStateBean {

    /**
     * "id":"183",
     * "EquipmentNumber":"00315900",
     * "names":"多功能声级计（噪声分析仪）",
     * "barCode":"ac45eca1-76e5-43d5-ac4b-748f890df489",
     * "qrcodeurl":"/upload/equips/ac45eca1-76e5-43d5-ac4b-748f890df489.jpg",
     * "FactoryNumber":"",
     * "buyDate":"2018-08-08",
     * "place":"",
     * "roomname":"",
     * "RegulationNumber":"YQ02803",
     * "ExpireDate":"2020-01-01",
     * "remark":"",
     * "IsEnabled":"1",
     * "IsEnabledName":"是",
     * "CollarState":"1",
     * "categoryid":"287",
     * "categoryname":"多功能声级计（噪声分析仪）",
     * "detectionlimit":"",
     * "verificationperiod":"12",
     * "files":"",
     * "custodian":"",
     * "custodianname":""
     */

    private String id;
    private String EquipmentNumber;
    private String names;
    private String barCode;
    private String qrcodeurl;
    private String FactoryNumber;
    private String buyDate;
    private String place;
    private String roomname;
    private String RegulationNumber;
    private String ExpireDate;
    private String remark;
    private String IsEnabled;
    private String IsEnabledName;
    private String CollarState;
    private String categoryid;
    private String categoryname;
    private String detectionlimit;
    private String verificationperiod;
    private String files;
    private String custodian;
    private String custodianname;


    public String getCollarState() {
        return CollarState;
    }

    public void setCollarState(String collarState) {
        CollarState = collarState;
    }
}
