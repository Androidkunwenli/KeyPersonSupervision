package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import org.osmdroid.library.R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 赵亚坤 on 2018/1/26.
 */

public class MarkerPolygon extends Polygon {
    private List<GeoPoint> points;

    public MarkerPolygon(Context context) {
        super();
        points = new ArrayList<>();
        setFillColor(context.getResources().getColor(R.color.map_red));
        setStrokeColor(context.getResources().getColor(R.color.map_red));
        setStrokeWidth(3);
    }

    @Override
    public void setPoints(List<GeoPoint> points) {
        super.setPoints(points);
        this.points = points;
    }

    @Override
    public void draw(Canvas canvas, MapView mapView, boolean shadow) {
        super.draw(canvas, mapView, shadow);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event, MapView mapView) {
        final Projection pj = mapView.getProjection();
        GeoPoint eventPos = (GeoPoint) pj.fromPixels((int) event.getX(), (int) event.getY());
        points.add(eventPos);
        mOutline.addPoint(eventPos);
        mapView.invalidate();
        return super.onSingleTapConfirmed(event, mapView);
    }
}
