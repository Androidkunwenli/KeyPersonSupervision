package com.bril.keypersonsupervision.util;

public class ConfigUrl {
    private final static String URl = "http://192.168.66.94:30000";
    //重点监管区域列表查询
    public static String findVipAreaList = URl + "/ipmp/area/findVipAreaList";
    //重点监管区域新增
    public static String addVipArea = URl + "/ipmp/area/addVipArea";
    //重点监管区域删除
    public static String delVipArea = URl + "/ipmp/area/delVipArea";
    //重点监管区域修改
    public static String updateVipArea = URl + "/ipmp/area/updateVipArea";
    //重症精神病患者首页列表实时位置查询
    public static String selectPatientsLocation = URl + "/ipmp/patients/selectPatientsLocation";
    //重症精神病患者首页图表
    public static String selectHistoryStatistics = URl + "/ipmp/patients/selectHistoryStatistics";
}
