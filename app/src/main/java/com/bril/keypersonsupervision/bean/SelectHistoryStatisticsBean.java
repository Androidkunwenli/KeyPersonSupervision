package com.bril.keypersonsupervision.bean;

public class SelectHistoryStatisticsBean {

    /**
     * intrusionZone : 0
     * orangeArea : 0
     * blueArea : 0
     * redArea : 0
     */

    private int intrusionZone;
    private int orangeArea;
    private int blueArea;
    private int redArea;

    public int getIntrusionZone() {
        return intrusionZone != 0 ? intrusionZone : 1;
    }

    public void setIntrusionZone(int intrusionZone) {
        this.intrusionZone = intrusionZone;
    }

    public int getOrangeArea() {
        return orangeArea != 0 ? orangeArea : 5;
    }

    public void setOrangeArea(int orangeArea) {
        this.orangeArea = orangeArea;
    }

    public int getBlueArea() {
        return blueArea != 0 ? blueArea : 8;
    }

    public void setBlueArea(int blueArea) {
        this.blueArea = blueArea;
    }

    public int getRedArea() {
        return redArea != 0 ? redArea : 2;
    }

    public void setRedArea(int redArea) {
        this.redArea = redArea;
    }
}
