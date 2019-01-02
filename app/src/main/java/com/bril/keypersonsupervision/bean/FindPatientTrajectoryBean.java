package com.bril.keypersonsupervision.bean;

public class FindPatientTrajectoryBean {

    /**
     * created_time : 2018-12-23T05:03:42.000+0000
     * equip_id : 3929642628
     * latitude : 38.021300
     * longitude : 114.4876467
     */

    private String created_time;
    private String equip_id;
    private String latitude;
    private String longitude;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
