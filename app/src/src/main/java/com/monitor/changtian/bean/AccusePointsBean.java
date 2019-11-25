package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/8/2.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class AccusePointsBean implements Serializable, IPickerViewData {

    private String id;
    private String name;
    private String typeid;

    private String DaysNumber;
    private String frequency;

    public String getDaysNumber() {
        return DaysNumber;
    }

    public void setDaysNumber(String daysNumber) {
        DaysNumber = daysNumber;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private boolean isChoice_save;

    private boolean isChoice;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<FactorsBean> factorsBeans;

    public List<FactorsBean> getFactorsBeans() {
        return factorsBeans;
    }

    public void setFactorsBeans(List<FactorsBean> factorsBeans) {
        this.factorsBeans = factorsBeans;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    public static class FactorsBean implements Serializable {
        /**
         * roleid : 7
         * RoleName : 质控人员
         */

        private String factorsid;
        private String factorsName;

        private boolean isChoice_save;
        private boolean isChoice;

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

        public String getFactorsid() {
            return factorsid;
        }

        public void setFactorsid(String factorsid) {
            this.factorsid = factorsid;
        }

        public String getFactorsName() {
            return factorsName;
        }

        public void setFactorsName(String factorsName) {
            this.factorsName = factorsName;
        }
    }
}
