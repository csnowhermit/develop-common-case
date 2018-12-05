/**
 * FileName: People
 * Date:     2018/12/3 21:11
 */

package com.rxt.common.es.model;

public class People {
    private Double lat;        //纬度
    private Double lon;        //经度
    private String wxNo;    //微信号
    private String nickName;//昵称
    private String sex;        //性别

    public People(Double lat, Double lon, String wxNo, String nickName, String sex) {
        this.lat = lat;
        this.lon = lon;
        this.wxNo = wxNo;
        this.nickName = nickName;
        this.sex = sex;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
