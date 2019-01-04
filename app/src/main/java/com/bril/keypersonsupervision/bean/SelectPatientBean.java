package com.bril.keypersonsupervision.bean;

public class SelectPatientBean {


    /**
     * created_time : 2018-12-28 02:25:12.0
     * equip_id : 3929642628
     * gender : 女
     * heartrate : 79
     * latitude : 38.021300
     * name : 张子昂
     * condition_type : 精神分裂症
     * electricity : 1
     * id : 2
     * identity_card : 130482199605040011
     * age : 23
     * longitude : 114.4876467
     */

    private String created_time;
    private String equip_id;
    private String gender;
    private String heartrate;
    private String latitude;
    private String name;
    private String condition_type;
    private String electricity;
    private int id;
    private String identity_card;
    private int age;
    private String longitude;
    private boolean isChoice;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(String equip_id) {
        this.equip_id = equip_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(String condition_type) {
        this.condition_type = condition_type;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
