package com.monitor.changtian.bean;


import java.util.List;

/**
 * Created by ken on 2018/10/11.
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

public class UserPermissionBean {

    private List<ResultBean.RolejarryBean> rolejarry;

    public UserPermissionBean(List<ResultBean.RolejarryBean> rolejarry) {
        this.rolejarry = rolejarry;
    }

    public List<ResultBean.RolejarryBean> getRolejarry() {
        return rolejarry;
    }

    public void setRolejarry(List<ResultBean.RolejarryBean> rolejarry) {
        this.rolejarry = rolejarry;
    }
}
