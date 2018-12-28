package com.bril.keypersonsupervision.bean;

public class SelectCauseRecordBean {

    /**
     * cause_place : 1
     * event_passing : 1
     * handle_result : 1
     */

    private String cause_place;
    private String event_passing;
    private String handle_result;
    /**
     * cause_person : 司务长
     * cause_date : 2018-12-22 ：24:00
     * id : 2
     */

    private String cause_person;
    private String cause_date;
    private int id;

    public String getCause_place() {
        return cause_place;
    }

    public void setCause_place(String cause_place) {
        this.cause_place = cause_place;
    }

    public String getEvent_passing() {
        return event_passing;
    }

    public void setEvent_passing(String event_passing) {
        this.event_passing = event_passing;
    }

    public String getHandle_result() {
        return handle_result;
    }

    public void setHandle_result(String handle_result) {
        this.handle_result = handle_result;
    }

    public String getCause_person() {
        return cause_person;
    }

    public void setCause_person(String cause_person) {
        this.cause_person = cause_person;
    }

    public String getCause_date() {
        return cause_date;
    }

    public void setCause_date(String cause_date) {
        this.cause_date = cause_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
