package com.bril.keypersonsupervision.util;

import android.content.Context;

import com.bril.keypersonsupervision.bean.AddPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
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
                .params("supervisePerson", "1")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者列表
     */
    public static <T> void findPatients(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findPatients)
                .tag(context)
                .params("param", "string")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者详情
     */
    public static <T> void findPatientById(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.findPatientById)
                .tag(context)
                .params("id", "1")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者详情
     */
    public static <T> void selectCauseRecord(Context context, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.selectCauseRecord)
                .tag(context)
                .params("causeperson", "string")
                .execute(jsonCallback);
    }

    /**
     * 重症精神患者新增
     */
    public static <T> void addPatient(Context context, AddPatientBean addPatientBean, JsonCallback<T> jsonCallback) {
        OkGo.<T>get(ConfigUrl.addPatient)
                .tag(context)
                .params(KUtil.objectToMap(addPatientBean), true)
                .execute(jsonCallback);
    }
}
