package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/7/4.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class SamplingAssistDataBean implements Serializable {

    private List<AssistBean> assist;
    private List<SamplingBean> sampling;

    public List<AssistBean> getAssist() {
        return assist;
    }

    public void setAssist(List<AssistBean> assist) {
        this.assist = assist;
    }

    public List<SamplingBean> getSampling() {
        return sampling;
    }

    public void setSampling(List<SamplingBean> sampling) {
        this.sampling = sampling;
    }

    public static class AssistBean implements Serializable {
        /**
         * id : 1
         * loginName : admin
         * userName : 赵 涛
         */

        private int id;
        private String loginName;
        private String userName;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }

    public static class SamplingBean implements Serializable {
        /**
         * id : 1
         * loginName : admin
         * userName : 赵 涛
         */
        private boolean isChoice_save;

        public boolean isChoice_save() {
            return isChoice_save;
        }

        public void setChoice_save(boolean choice_save) {
            isChoice_save = choice_save;
        }

        private boolean isChoice;

        public boolean isChoice() {
            return isChoice;
        }

        public void setChoice(boolean choice) {
            isChoice = choice;
        }
        private int id;
        private String loginName;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
