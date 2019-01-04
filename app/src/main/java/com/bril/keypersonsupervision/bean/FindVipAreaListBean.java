package com.bril.keypersonsupervision.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FindVipAreaListBean implements Parcelable {

    /**
     * area_name : string
     * created_time : 2018-12-25T06:37:09.000+0000
     * updated_time : 2018-12-25T06:37:09.000+0000
     * updated_by : string
     * vip_Level : 1
     * id : 9
     * area_remake : string
     * area_position : MTE0LjUwNTg2MzE4OTY5NzI3LDM4LjAzMzYyNTEzNzEwNjE5LDExNC41MjEyMjY4ODI5MzQ1NywxMTQuNTIxMjI2ODgyOTM0NTcsMzguMDMzNjI1MTM3MTA2MTk=
     * created_by : string
     */

    private String area_name;
    private String created_time;
    private String updated_time;
    private String updated_by;
    private int vip_Level;
    private int id;
    private String area_remake;
    private String area_position;
    private String created_by;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public int getVip_Level() {
        return vip_Level;
    }

    public void setVip_Level(int vip_Level) {
        this.vip_Level = vip_Level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea_remake() {
        return area_remake;
    }

    public void setArea_remake(String area_remake) {
        this.area_remake = area_remake;
    }

    public String getArea_position() {
        return area_position;
    }

    public void setArea_position(String area_position) {
        this.area_position = area_position;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.area_name);
        dest.writeString(this.created_time);
        dest.writeString(this.updated_time);
        dest.writeString(this.updated_by);
        dest.writeInt(this.vip_Level);
        dest.writeInt(this.id);
        dest.writeString(this.area_remake);
        dest.writeString(this.area_position);
        dest.writeString(this.created_by);
    }

    public FindVipAreaListBean() {
    }

    protected FindVipAreaListBean(Parcel in) {
        this.area_name = in.readString();
        this.created_time = in.readString();
        this.updated_time = in.readString();
        this.updated_by = in.readString();
        this.vip_Level = in.readInt();
        this.id = in.readInt();
        this.area_remake = in.readString();
        this.area_position = in.readString();
        this.created_by = in.readString();
    }

    public static final Parcelable.Creator<FindVipAreaListBean> CREATOR = new Parcelable.Creator<FindVipAreaListBean>() {
        @Override
        public FindVipAreaListBean createFromParcel(Parcel source) {
            return new FindVipAreaListBean(source);
        }

        @Override
        public FindVipAreaListBean[] newArray(int size) {
            return new FindVipAreaListBean[size];
        }
    };
}
