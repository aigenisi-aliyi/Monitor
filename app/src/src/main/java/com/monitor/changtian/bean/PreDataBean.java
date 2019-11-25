package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/14.
 */

public class PreDataBean  implements Serializable {


    /**
     * col1 : 编号
     * col2 : 标准物质名称
     * col3 : 标准物质证书号
     * col4 : 购进日期
     * col5 : 规格
     * col6 : 购进数量（瓶）
     * col7 : 标准物质有效期
     * col8 : 浓度/纯度
     * col9 : 批号
     * col10 : 添加时间
     * col11 : 录入用户账号
     * id : 主键id
     * T_SS_MS : 标准物质管理【数据库表名】
     * DataList : [{"col1":"6666","col2":"66666","col3":"66666","col4":"","col5":"","col6":"10000100","col7":"","col8":"","col9":"","col10":"","col11":"","id":1},{"col1":"66","col2":"酒精","col3":"1016165","col4":"2018-11-08","col5":"1","col6":"20000","col7":"2018-11-10","col8":"3612","col9":"10","col10":"","col11":"","id":2},{"col1":"1","col2":"12","col3":"3","col4":"","col5":"","col6":"5","col7":"","col8":"","col9":"","col10":"","col11":"","id":3},{"col1":"66","col2":"酒精","col3":"1016165","col4":"2018-11-08","col5":"1","col6":"200","col7":"2018-11-10","col8":"3612","col9":"10","col10":"","col11":"","id":4},{"col1":"6","col2":"6","col3":"6","col4":"1899-12-14","col5":"6","col6":"9","col7":"2018-11-10","col8":"9","col9":"6","col10":"","col11":"","id":5},{"col1":"001","col2":"酒精","col3":"101243","col4":"","col5":"","col6":"555","col7":"","col8":"","col9":"","col10":"","col11":"","id":6},{"col1":"1","col2":"12","col3":"3","col4":"","col5":"","col6":"5555555","col7":"","col8":"","col9":"","col10":"","col11":"","id":7},{"col1":"1","col2":"12","col3":"3","col4":"","col5":"","col6":"666","col7":"","col8":"","col9":"","col10":"","col11":"","id":8},{"col1":"","col2":"","col3":"","col4":"","col5":"","col6":"106069","col7":"","col8":"","col9":"","col10":"","col11":"","id":9},{"col1":"测试物质1","col2":"","col3":"","col4":"","col5":"","col6":"1016168808","col7":"","col8":"","col9":"","col10":"","col11":"","id":10},{"col1":"2","col2":"","col3":"","col4":"","col5":"","col6":"775266","col7":"","col8":"","col9":"","col10":"","col11":"","id":11},{"col1":"测试数据","col2":"盐溶液","col3":"104565","col4":"2018-12-01","col5":"10","col6":"1300","col7":"2018-12-02","col8":"01","col9":"10452","col10":"","col11":"","id":12},{"col1":"1212121212","col2":"222","col3":"111","col4":"2018-11-28","col5":"xq","col6":"300","col7":"2019-12-01","col8":"0.02","col9":"111111","col10":"","col11":"","id":13},{"col1":"BW20004-30-W-20","col2":"112","col3":"1","col4":"2018-12-06","col5":"1","col6":"1","col7":"2018-12-15","col8":"1","col9":"1","col10":"","col11":"","id":14},{"col1":"2","col2":"去","col3":"BW20004-30-W-20","col4":"2018-12-02","col5":"1","col6":"1","col7":"2018-12-09","col8":"1","col9":"1","col10":"","col11":"","id":15},{"col1":"231","col2":"2323","col3":"BW20004-30-W-20","col4":"2018-12-06","col5":"333","col6":"2","col7":"2018-12-29","col8":"2","col9":"2323553","col10":"","col11":"","id":16},{"col1":"24234","col2":"23423423","col3":"2423423423","col4":"2018-12-06","col5":"24","col6":"234","col7":"2018-12-29","col8":"234","col9":"2323553","col10":"","col11":"","id":17}]
     */

    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String col8;
    private String col9;
    private String col10;
    private String col11;
    private String id;
    private String T_SS_MS;
    private List<DataListBean> DataList;

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getCol8() {
        return col8;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public String getCol9() {
        return col9;
    }

    public void setCol9(String col9) {
        this.col9 = col9;
    }

    public String getCol10() {
        return col10;
    }

    public void setCol10(String col10) {
        this.col10 = col10;
    }

    public String getCol11() {
        return col11;
    }

    public void setCol11(String col11) {
        this.col11 = col11;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getT_SS_MS() {
        return T_SS_MS;
    }

    public void setT_SS_MS(String T_SS_MS) {
        this.T_SS_MS = T_SS_MS;
    }

    public List<DataListBean> getDataList() {
        return DataList;
    }

    public void setDataList(List<DataListBean> DataList) {
        this.DataList = DataList;
    }

    public static class DataListBean implements Serializable{
        /**
         * col1 : 6666
         * col2 : 66666
         * col3 : 66666
         * col4 :
         * col5 :
         * col6 : 10000100
         * col7 :
         * col8 :
         * col9 :
         * col10 :
         * col11 :
         * id : 1
         */

        private boolean isSelected; //自定义列表是否选中的标识
        private String col1;
        private String col2;
        private String col3;
        private String col4;
        private String col5;
        private String col6;
        private String col7;
        private String col8;
        private String col9;
        private String col10;
        private String col11;
        private int id;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getCol1() {
            return col1;
        }

        public void setCol1(String col1) {
            this.col1 = col1;
        }

        public String getCol2() {
            return col2;
        }

        public void setCol2(String col2) {
            this.col2 = col2;
        }

        public String getCol3() {
            return col3;
        }

        public void setCol3(String col3) {
            this.col3 = col3;
        }

        public String getCol4() {
            return col4;
        }

        public void setCol4(String col4) {
            this.col4 = col4;
        }

        public String getCol5() {
            return col5;
        }

        public void setCol5(String col5) {
            this.col5 = col5;
        }

        public String getCol6() {
            return col6;
        }

        public void setCol6(String col6) {
            this.col6 = col6;
        }

        public String getCol7() {
            return col7;
        }

        public void setCol7(String col7) {
            this.col7 = col7;
        }

        public String getCol8() {
            return col8;
        }

        public void setCol8(String col8) {
            this.col8 = col8;
        }

        public String getCol9() {
            return col9;
        }

        public void setCol9(String col9) {
            this.col9 = col9;
        }

        public String getCol10() {
            return col10;
        }

        public void setCol10(String col10) {
            this.col10 = col10;
        }

        public String getCol11() {
            return col11;
        }

        public void setCol11(String col11) {
            this.col11 = col11;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
