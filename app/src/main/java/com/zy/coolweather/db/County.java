package com.zy.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 赵岩 on 2017/2/19.
 * 这是一个用来存放区、县信息的类
 */

public class County extends DataSupport {

    private int id;
    //区、县名称
    private String countyName;
    //天气ID
    private String weatherId;
    //对应市id
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
