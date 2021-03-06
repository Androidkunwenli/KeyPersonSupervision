package com.bril.keypersonsupervision.util;

import android.content.Context;

import com.bril.keypersonsupervision.R;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class KUtil {
    /**
     * 日期格式转换yyyy-MM-dd‘T‘HH:mm:ss.SSSXXX  TO  yyyy-MM-dd HH:mm:ss
     *
     * @throws ParseException
     */
    public static String dealDateFormat(String oldDateStr) throws ParseException {
        //此格式只有  jdk 1.7才支持  yyyy-MM-dd‘T‘HH:mm:ss.SSSXXX
        //这个后面的.SSSXXX写了的话这一行就直接抛异常了，所以我去掉了，还有前面的T  一定要用英文的单引号包裹起来
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = df.parse(oldDateStr);
        SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date date1 = df1.parse(date.toString());
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df2.format(date1);
    }

    /***
     * 将对象转换为map对象
     * @param thisObj 对象
     * @return map
     */
    public static Map<String, String> objectToMap(Object thisObj) {
        Map<String, String> map = new HashMap();
        Class c;
        try {
            c = Class.forName(thisObj.getClass().getName());
            //获取所有的方法
            Method[] m = c.getMethods();
            for (Method aM : m) {   //获取方法名
                String method = aM.getName();
                //获取get开始的方法名
                if (method.startsWith("get") && !method.contains("getClass")) {
                    try {
                        //获取对应对应get方法的value值
                        Object value = aM.invoke(thisObj);
                        if (value != null) {
                            //截取get方法除get意外的字符 如getUserName-->UserName
                            String key = method.substring(3);
                            //将属性的第一个值转为小写
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                            //将属性key,value放入对象
                            map.put(key, String.valueOf(value));
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("error:" + method);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 添加相关的marker
     *
     * @param currentLocation
     */
    public static void addCurrentMarker(Context context, GeoPoint currentLocation, MapView mapView) {
        OverlayItem myLocationOverlayItem = new OverlayItem("", "", currentLocation);
        myLocationOverlayItem.setMarker(context.getResources().getDrawable(R.mipmap.ic_position));
        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        items.add(myLocationOverlayItem);
//        ItemizedIconOverlay<OverlayItem> currentLocationOverlay = new ItemizedIconOverlay<OverlayItem>(items,
        ItemizedOverlayWithFocus<OverlayItem> currentLocationOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                null, context);
        currentLocationOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(currentLocationOverlay);
        mapView.getController().setCenter(currentLocation);
    }

    /**
     * 矩形字符串
     */
    public static String getPointStr(IGeoPoint upper, IGeoPoint lower, IGeoPoint righter, IGeoPoint lefter) {
        //        double[] upperDoubles = CoordinateConverter.transformWgs2Gcj(upper.getLatitude(), upper.getLongitude());
//        double[] lowerDoubles = CoordinateConverter.transformWgs2Gcj(lower.getLatitude(), lower.getLongitude());
//        double[] righterrDoubles = CoordinateConverter.transformWgs2Gcj(righter.getLatitude(), righter.getLongitude());
//        double[] lefterDoubles = CoordinateConverter.transformWgs2Gcj(lefter.getLatitude(), lefter.getLongitude());
        return String.valueOf(upper.getLongitude()) + "," + upper.getLatitude() + "," +
                lower.getLongitude() + "," + lower.getLatitude() + "," +
                righter.getLongitude() + "," + righter.getLatitude() + "," +
                lefter.getLongitude() + "," + lefter.getLatitude() + "," + upper.getLongitude() + "," + upper.getLatitude();

    }
}
