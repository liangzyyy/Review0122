package com.example.lzy.review.javabeans;

public class CtiyTemp {
    private String cityid;
    private String city;
    private String update_time;
    private String date;
    private String week;
    private String wea;
    private String air_level;
    private String air_tips;
    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    @Override
    public String toString() {
        return "CtiyTemp{" +
                "cityid='" + cityid + '\'' +
                ", city='" + city + '\'' +
                ", update_time='" + update_time + '\'' +
                '}';
    }
}
