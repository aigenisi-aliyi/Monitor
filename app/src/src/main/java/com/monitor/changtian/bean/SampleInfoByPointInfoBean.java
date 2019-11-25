package com.monitor.changtian.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/10/17.
 */

public class SampleInfoByPointInfoBean implements Serializable {

    /**
     * onedaysamplecount : 6
     * details : [{"name":"容器材质：G， 保护剂：H2SO4","factors":[{"fid":"532","factorname":"pH值","DaysNumber":"3","frequency":"3"}]},{"name":"容器材质：P， 保护剂：硫酸锰","factors":[{"fid":"533","factorname":"氨氮","DaysNumber":"3","frequency":"3"}]}]
     */

    private int onedaysamplecount;
    private List<DetailsBean> details;

    public int getOnedaysamplecount() {
        return onedaysamplecount;
    }

    public void setOnedaysamplecount(int onedaysamplecount) {
        this.onedaysamplecount = onedaysamplecount;
    }

    public List<DetailsBean> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsBean> details) {
        this.details = details;
    }

    public static class DetailsBean implements Serializable, IPickerViewData

    {
        /**
         * name : 容器材质：G， 保护剂：H2SO4
         * factors : [{"fid":"532","factorname":"pH值","DaysNumber":"3","frequency":"3"}]
         */

        private String name;
        private String id;
        private boolean isSelected; //自定义列表是否选中的标识

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isSelected() {
            return isSelected;
        }
        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        private List<FactorsBean> factors;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<FactorsBean> getFactors() {
            return factors;
        }

        public void setFactors(List<FactorsBean> factors) {
            this.factors = factors;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }

        public static class FactorsBean implements Serializable {
            /**
             * fid : 532
             * factorname : pH值
             * DaysNumber : 3
             * frequency : 3
             */

            private boolean isChoice_save;

            private boolean isChoice;
            private String fid;
            private String factorname;
            private String DaysNumber;
            private String frequency;


            private boolean isSelected; //自定义列表是否选中的标识

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

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

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getFactorname() {
                return factorname;
            }

            public void setFactorname(String factorname) {
                this.factorname = factorname;
            }

            public String getDaysNumber() {
                return DaysNumber;
            }

            public void setDaysNumber(String DaysNumber) {
                this.DaysNumber = DaysNumber;
            }

            public String getFrequency() {
                return frequency;
            }

            public void setFrequency(String frequency) {
                this.frequency = frequency;
            }
        }
    }
}
