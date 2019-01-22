package com.example.lzy.review.javabeans;

import android.widget.TextView;

public class UrlBean {
    private String url="https://www.tianqiapi.com/api/";
    private String version = "v1";
    private String city;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return url+"?version="+version+"&city="+city;
    }
}
