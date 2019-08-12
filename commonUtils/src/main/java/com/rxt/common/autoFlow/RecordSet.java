package com.rxt.common.autoFlow;

import java.util.ArrayList;
import java.util.List;

public class RecordSet {
    private Header header;    //记录头
    private Body body;

    public RecordSet() {
        this.header = new Header();
        this.body = new Body();
    }

    public RecordSet(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public RecordSet setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Body getBody() {
        return body;
    }

    public RecordSet setBody(Body body) {
        this.body = body;
        return this;
    }

    @Override
    public String toString() {
        return "RecordSet{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}

class Header {
    private String userid;         //乘客ID
    private String line_name;      //线路
    private String station_name;   //站点
    private String forward;        //方向：进站/出站

    public Header() {
    }

    public Header(String userid, String line_name, String station_name, String forward) {
        this.userid = userid;
        this.line_name = line_name;
        this.station_name = station_name;
        this.forward = forward;
    }

    public String getLine_name() {
        return line_name;
    }

    public Header setLine_name(String line_name) {
        this.line_name = line_name;
        return this;
    }

    public String getStation_name() {
        return station_name;
    }

    public Header setStation_name(String station_name) {
        this.station_name = station_name;
        return this;
    }

    public String getForward() {
        return forward;
    }

    public Header setForward(String forward) {
        this.forward = forward;
        return this;
    }

    public String getUserid() {
        return userid;
    }

    public Header setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    @Override
    public String toString() {
        return "Header{" +
                "userid='" + userid + '\'' +
                ", line_name='" + line_name + '\'' +
                ", station_name='" + station_name + '\'' +
                ", forward='" + forward + '\'' +
                '}';
    }
}

class Body {
    private List<DetailsRecord> detailsRecordList = new ArrayList<>();

    public Body() {
    }

    public Body(List<DetailsRecord> detailsRecordList) {
        this.detailsRecordList = detailsRecordList;
    }

    public List<DetailsRecord> getDetailsRecordList() {
        return detailsRecordList;
    }

    public Body setDetailsRecordList(List<DetailsRecord> detailsRecordList) {
        this.detailsRecordList = detailsRecordList;
        return this;
    }

    @Override
    public String toString() {
        return "Body{" +
                "detailsRecordList=" + detailsRecordList +
                '}';
    }
}