package com.bril.keypersonsupervision.bean;

public class SelectPatientBean {

    /**
     * created_time : 2018-12-28T19:19:15.000+0000
     * gender : 女
     * name : 张子昂
     * condition_type : 精神分裂症
     * data_type : 1
     */
    private String id;
    private String created_time;
    private String gender;
    private String name;
    private String condition_type;
    private String data_type;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }
}
