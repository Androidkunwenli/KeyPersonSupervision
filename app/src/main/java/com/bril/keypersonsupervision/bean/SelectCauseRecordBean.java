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
}
