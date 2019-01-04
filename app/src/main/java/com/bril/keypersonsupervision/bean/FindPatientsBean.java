package com.bril.keypersonsupervision.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FindPatientsBean implements Parcelable {


    /**
     * supervise_person : string
     * created_time : 2018-12-29T09:35:05.000+0000
     * gender : 男
     * level : 5
     * condition_type : 癫痫所致精神障碍
     * created_by : string
     * identity_card : 130482100005040013
     * patients_type : 轻微滋事
     * blueArea : 0
     * equip_id : 23
     * name : 钱昆纬
     * orangeArea : 0
     * id : 16
     * redArea : 0
     * age : 1019
     */

    private String supervise_person;
    private String created_time;
    private String gender;
    private String level;
    private String condition_type;
    private String created_by;
    private String identity_card;
    private String patients_type;
    private int blueArea;
    private String equip_id;
    private String name;
    private int orangeArea;
    private int id;
    private int redArea;
    private int age;

    public String getSupervise_person() {
        return supervise_person;
    }

    public void setSupervise_person(String supervise_person) {
        this.supervise_person = supervise_person;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(String condition_type) {
        this.condition_type = condition_type;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getPatients_type() {
        return patients_type;
    }

    public void setPatients_type(String patients_type) {
        this.patients_type = patients_type;
    }

    public int getBlueArea() {
        return blueArea;
    }

    public void setBlueArea(int blueArea) {
        this.blueArea = blueArea;
    }

    public String getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrangeArea() {
        return orangeArea;
    }

    public void setOrangeArea(int orangeArea) {
        this.orangeArea = orangeArea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRedArea() {
        return redArea;
    }

    public void setRedArea(int redArea) {
        this.redArea = redArea;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.supervise_person);
        dest.writeString(this.created_time);
        dest.writeString(this.gender);
        dest.writeString(this.level);
        dest.writeString(this.condition_type);
        dest.writeString(this.created_by);
        dest.writeString(this.identity_card);
        dest.writeString(this.patients_type);
        dest.writeInt(this.blueArea);
        dest.writeString(this.equip_id);
        dest.writeString(this.name);
        dest.writeInt(this.orangeArea);
        dest.writeInt(this.id);
        dest.writeInt(this.redArea);
        dest.writeInt(this.age);
    }

    public FindPatientsBean() {
    }

    protected FindPatientsBean(Parcel in) {
        this.supervise_person = in.readString();
        this.created_time = in.readString();
        this.gender = in.readString();
        this.level = in.readString();
        this.condition_type = in.readString();
        this.created_by = in.readString();
        this.identity_card = in.readString();
        this.patients_type = in.readString();
        this.blueArea = in.readInt();
        this.equip_id = in.readString();
        this.name = in.readString();
        this.orangeArea = in.readInt();
        this.id = in.readInt();
        this.redArea = in.readInt();
        this.age = in.readInt();
    }

    public static final Creator<FindPatientsBean> CREATOR = new Creator<FindPatientsBean>() {
        @Override
        public FindPatientsBean createFromParcel(Parcel source) {
            return new FindPatientsBean(source);
        }

        @Override
        public FindPatientsBean[] newArray(int size) {
            return new FindPatientsBean[size];
        }
    };
}
