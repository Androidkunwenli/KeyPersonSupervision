package com.bril.keypersonsupervision.bean;

public class SelectPatientsLocationBean {

    /**
     * created_time : 2018-12-14T22:17:37.000+0000
     * latitude : 38.040713
     * name : 病人1
     * longitude : 114.4958533
     */
    private String id;
    private String created_time;
    private String latitude;
    private String name;
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
