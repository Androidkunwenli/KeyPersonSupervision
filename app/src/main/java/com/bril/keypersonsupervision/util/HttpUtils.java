package com.bril.keypersonsupervision.util;

import android.content.Context;

import com.bril.keypersonsupervision.bean.AddPatientBean;
import com.bril.keypersonsupervision.bean.AddVipAreaBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

public class HttpUtils {
    /**
     * 重点监管区域列表查询
     */
    public static <T> void findVipAreaList(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findVipAreaList)
                .tag(context)
                .params("param", "1111")
                .execute(jsonCallback);
    }

    /**
     * 重症精神病患者首页列表实时位置查询
     */
    public static <T> void selectPatientsLocation(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectPatientsLocation)
                .tag(context)
                .params("supervisePerson", "string")
                .execute(jsonCallback);
    }

    /**
     * 重症精神病患者首页图表
     */
    public static <T> void selectHistoryStatistics(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectHistoryStatistics)
                .tag(context)
                .params("buffer", "1")
                .params("supervisePerson", "string")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者列表
     */
    public static <T> void findPatients(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findPatients)
                .tag(context)
                .params("supervisePerson", "string")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者详情
     */
    public static <T> void findPatientById(Context context, String id, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findPatientById)
                .tag(context)
                .params("id", id)
                .execute(jsonCallback);
    }

    /**
     * 违法乱纪列表查询
     */
    public static <T> void selectByPrimaryKey(Context context, String id, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectByPrimaryKey)
                .tag(context)
                .params("id", id)
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者新增
     */
    public static <T> void addPatient(Context context, AddPatientBean addPatientBean, JsonCallback<T> jsonCallback) {
        OkGo.<T>post(ConfigUrl.addPatient)
                .tag(context)
                .upJson(new Gson().toJson(addPatientBean))
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者更新
     */
    public static <T> void updatePatient(Context context, AddPatientBean addPatientBean, JsonCallback<T> jsonCallback) {
        OkGo.<T>post(ConfigUrl.updatePatient)
                .tag(context)
                .upJson(new Gson().toJson(addPatientBean))
                .execute(jsonCallback);
    }

    /**
     * 重点人员实时位置查询
     */
    public static <T> void selectPatients(Context context, String id, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectPatients)
                .tag(context)
                .params("id", id)
                .execute(jsonCallback);
    }

    /**
     * 重症精神病患者实时位置查询
     */
    public static <T> void selectPatient(Context context, String supervisePerson, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectPatient)
                .tag(context)
                .params("supervisePerson", supervisePerson)
                .execute(jsonCallback);
    }

    /**
     * 重点人员实时位置查询
     */
    public static <T> void selectSupervisorsInfo(Context context, String patientsidentitycard,
                                                 JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectSupervisorsInfo)
                .tag(context)
                .params("patientsidentitycard", patientsidentitycard)
                .execute(jsonCallback);
    }

    /**
     * 重点人员实时轨迹
     */
    public static <T> void findPatientTrajectory(Context context, String startTime, int id, String endTime,
                                                 JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findPatientTrajectory)
                .tag(context)
                .params("startTime", startTime)
                .params("id", id)
                .params("endTime", endTime)
                .execute(jsonCallback);
    }

    public static <T> void addVipArea(Context context, AddVipAreaBean bean,
                                      JsonCallback<T> jsonCallback) {
        OkGo.<T>post(ConfigUrl.addVipArea)
                .tag(context).upJson(new Gson().toJson(bean)).execute(jsonCallback);

    }
}
