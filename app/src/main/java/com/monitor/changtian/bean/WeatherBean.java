package com.monitor.changtian.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ken on 2018/4/24.
 * <p>
 * Version:     1.0
 * E-Mail:      iauhsil
 * <p>
 * Function:
 */

public class WeatherBean implements Serializable{

    /**
     * date : 20180424
     * message : Success !
     * status : 200
     * city : 西安市
     * count : 495
     * data : {"shidu":"88%","pm25":59,"pm10":87,"quality":"良","wendu":"15","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"23日星期一","sunrise":"06:05","high":"高温 22.0℃","low":"低温 14.0℃","sunset":"19:21","aqi":54,"fx":"南风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"},"forecast":[{"date":"24日星期二","sunrise":"06:04","high":"高温 17.0℃","low":"低温 12.0℃","sunset":"19:22","aqi":76,"fx":"西南风","fl":"3-4级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"25日星期三","sunrise":"06:03","high":"高温 18.0℃","low":"低温 11.0℃","sunset":"19:23","aqi":72,"fx":"南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"26日星期四","sunrise":"06:02","high":"高温 28.0℃","low":"低温 13.0℃","sunset":"19:24","aqi":71,"fx":"西南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"27日星期五","sunrise":"06:01","high":"高温 30.0℃","low":"低温 14.0℃","sunset":"19:25","aqi":78,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28日星期六","sunrise":"06:00","high":"高温 29.0℃","low":"低温 14.0℃","sunset":"19:25","aqi":77,"fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements  Serializable{
        /**
         * shidu : 88%
         * pm25 : 59
         * pm10 : 87
         * quality : 良
         * wendu : 15
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"23日星期一","sunrise":"06:05","high":"高温 22.0℃","low":"低温 14.0℃","sunset":"19:21","aqi":54,"fx":"南风","fl":"3-4级","type":"阴","notice":"不要被阴云遮挡住好心情"}
         * forecast : [{"date":"24日星期二","sunrise":"06:04","high":"高温 17.0℃","low":"低温 12.0℃","sunset":"19:22","aqi":76,"fx":"西南风","fl":"3-4级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"25日星期三","sunrise":"06:03","high":"高温 18.0℃","low":"低温 11.0℃","sunset":"19:23","aqi":72,"fx":"南风","fl":"<3级","type":"小雨","notice":"雨虽小，注意保暖别感冒"},{"date":"26日星期四","sunrise":"06:02","high":"高温 28.0℃","low":"低温 13.0℃","sunset":"19:24","aqi":71,"fx":"西南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"27日星期五","sunrise":"06:01","high":"高温 30.0℃","low":"低温 14.0℃","sunset":"19:25","aqi":78,"fx":"东南风","fl":"<3级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},{"date":"28日星期六","sunrise":"06:00","high":"高温 29.0℃","low":"低温 14.0℃","sunset":"19:25","aqi":77,"fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 23日星期一
             * sunrise : 06:05
             * high : 高温 22.0℃
             * low : 低温 14.0℃
             * sunset : 19:21
             * aqi : 54
             * fx : 南风
             * fl : 3-4级
             * type : 阴
             * notice : 不要被阴云遮挡住好心情
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean implements Serializable{
            /**
             * date : 24日星期二
             * sunrise : 06:04
             * high : 高温 17.0℃
             * low : 低温 12.0℃
             * sunset : 19:22
             * aqi : 76
             * fx : 西南风
             * fl : 3-4级
             * type : 阵雨
             * notice : 阵雨来袭，出门记得带伞
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
