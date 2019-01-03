package com.bril.keypersonsupervision.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FindPatientsBean implements Parcelable {

    /**
     * supervise_person : string
     * created_time : 2018-12-17T06:17:57.000+0000
     * updated_time : 2018-12-17T07:33:46.000+0000
     * gender : 男
     * level : 1
     * condition_type : 精神分裂症
     * created_by :
     * identity_card : 130185186512012475
     * patients_type : 1
     * equip_id : 3929642628
     * name : 病人1
     * updated_by :
     * id : 1
     */

    private String supervise_person;
    private String created_time;
    private String updated_time;
    private String gender;
    private String age;
    private String level;
    private String condition_type;
    private String created_by;
    private String identity_card;
    private String patients_type;
    private String equip_id;
    private String name;
    private String updated_by;
    private int id;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

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

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
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

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FindPatientsBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.supervise_person);
        dest.writeString(this.created_time);
        dest.writeString(this.updated_time);
        dest.writeString(this.gender);
        dest.writeString(this.age);
        dest.writeString(this.level);
        dest.writeString(this.condition_type);
        dest.writeString(this.created_by);
        dest.writeString(this.identity_card);
        dest.writeString(this.patients_type);
        dest.writeString(this.equip_id);
        dest.writeString(this.name);
        dest.writeString(this.updated_by);
        dest.writeInt(this.id);
    }

    protected FindPatientsBean(Parcel in) {
        this.supervise_person = in.readString();
        this.created_time = in.readString();
        this.updated_time = in.readString();
        this.gender = in.readString();
        this.age = in.readString();
        this.level = in.readString();
        this.condition_type = in.readString();
        this.created_by = in.readString();
        this.identity_card = in.readString();
        this.patients_type = in.readString();
        this.equip_id = in.readString();
        this.name = in.readString();
        this.updated_by = in.readString();
        this.id = in.readInt();
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
