package com.zy.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 赵岩 on 2017/2/19.
 * 这是一个用来存放省份信息的类
 */

public class Province extends DataSupport {

    private int id;
    //省份名称
    private String provinceName;
    //省份代号
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
