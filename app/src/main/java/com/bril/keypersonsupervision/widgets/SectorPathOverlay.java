package com.bril.keypersonsupervision.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.CircleModel;
import com.bril.keypersonsupervision.bean.DrawBaseModel;
import com.bril.keypersonsupervision.bean.LineModel;
import com.bril.keypersonsupervision.bean.PathModel;
import com.bril.keypersonsupervision.bean.RectModel;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;
import java.util.List;

public class SectorPathOverlay extends Overlay {

    private final drawListener mListener;
    private MapView mMapView;

    @Override
    public void draw(Canvas canvas, MapView osmv, boolean shadow) {
        mMapView = osmv;
        // TODO Auto-generated method stub
        canvas.save();
        //绘制之前的图形
        for (DrawBaseModel model : list) {
            drawGraph(canvas, model, osmv);
        }
        //绘制当前的图形
        if (!isPointUp)
            drawGraph(canvas, drawBaseModel, osmv);
        canvas.restore();
    }

    //声明路径
    private Path path;
    //声明画笔
    private Paint paint;

    //记录所有画过的轨迹
    private List<List<PointF>> linePath = new ArrayList<>();
    //记录画过的所有的内容
    private List<DrawBaseModel> list = new ArrayList<>();
    //记录撤销过的所有的内容
    private List<DrawBaseModel> delectList = new ArrayList<>();
    //现在所画的图形是什么
    private DrawBaseModel drawBaseModel;
    //记录画过的所有的直线
    private List<LineModel> lineList = new ArrayList<>();
    //记录画过的所有的圆
    private List<CircleModel> circleList = new ArrayList<>();
    //记录画过的所有的矩形
    private List<RectModel> RectList = new ArrayList<>();
    //记录画过的所有的轨迹
    private List<DrawBaseModel> pathList = new ArrayList<>();
    //绘制图形的Type
    private int type;
    private boolean isPointUp = true;
    private Context context;

    public SectorPathOverlay(Context context, drawListener drawListener) {
        super(context);
        initView(context);
        mListener = drawListener;
    }

    private void initView(Context context) {
        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        drawBaseModel = new DrawBaseModel();
        type = DrawBaseModel.TPEY_CIRCLE;
        this.context = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
        // TODO Auto-generated method stub
        //获取拖动事件发生的位置
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                down(event);
                break;
            case MotionEvent.ACTION_MOVE:
                move(event);
                break;
            case MotionEvent.ACTION_UP:
                up(event);
                break;
        }
        //返回true表明处理方法已经处理该事件
        return true;
    }

    //手指按下操作
    private void down(MotionEvent event) {
        isPointUp = false;
        switch (type) {
            case DrawBaseModel.TPEY_LINE:
//                LineIsDownOn(event);
                drawBaseModel = new LineModel();
                ((LineModel) drawBaseModel).setStartx(event.getX());
                ((LineModel) drawBaseModel).setStarty(event.getY());
                break;
            case DrawBaseModel.TPEY_PATH:
                drawBaseModel = new PathModel();
                ((PathModel) drawBaseModel).startPath(event.getX(), event.getY());
                break;
            case DrawBaseModel.TPEY_CIRCLE:
                drawBaseModel = new CircleModel();
                ((CircleModel) drawBaseModel).setCenterx(event.getX());
                ((CircleModel) drawBaseModel).setCentery(event.getY());
                break;
            case DrawBaseModel.TPEY_RECT:
                drawBaseModel = new RectModel();
                ((RectModel) drawBaseModel).startRectF(event.getX(), event.getY());
                break;
        }
        drawBaseModel.setType(type);
        drawBaseModel.setColor(context.getResources().getColor(R.color.map_red));
        Log.i("PaintView", "down");
    }

    //手指移动时的操作
    private void move(MotionEvent event) {
        switch (type) {
            case DrawBaseModel.TPEY_LINE:
                ((LineModel) drawBaseModel).setEndx(event.getX());
                ((LineModel) drawBaseModel).setEndy(event.getY());
//                IsChangePoint(downState,event);
                break;
            case DrawBaseModel.TPEY_PATH:
                ((PathModel) drawBaseModel).movePath(event.getX(), event.getY());
                break;
            case DrawBaseModel.TPEY_CIRCLE:
                float x = ((CircleModel) drawBaseModel).getCenterx();
                float y = ((CircleModel) drawBaseModel).getCentery();
                ((CircleModel) drawBaseModel).setRadis((float) Math.sqrt(
                        (x - event.getX()) * (x - event.getX())
                                + (y - event.getY()) * (y - event.getY())));
                ((CircleModel) drawBaseModel).setMovex(event.getX());
                ((CircleModel) drawBaseModel).setMovey(event.getY());
                break;
            case DrawBaseModel.TPEY_RECT:
                ((RectModel) drawBaseModel).toRectF(event.getX(), event.getY());
                break;
        }
        Log.i("PaintView", "move");
        mMapView.postInvalidate();
    }

    //手指抬起的操作
    private void up(MotionEvent event) {
        move(event);
        switch (type) {
            case DrawBaseModel.TPEY_LINE:
                lineList.clear();
                lineList.add((LineModel) drawBaseModel);
                break;
            case DrawBaseModel.TPEY_CIRCLE:
                circleList.clear();
                circleList.add((CircleModel) drawBaseModel);
                break;
            case DrawBaseModel.TPEY_PATH:
                pathList.clear();
                pathList.add(drawBaseModel);
                break;
            case DrawBaseModel.TPEY_RECT:
                RectList.clear();
                RectList.add((RectModel) drawBaseModel);
                break;
            default:
                break;
        }
        list.clear();
        list.add(drawBaseModel);
        isPointUp = true;
        Log.i("PaintView", "up");
    }


    //绘制图形
    private void drawGraph(Canvas canvas, DrawBaseModel model, MapView osmv) {
        paint.setColor(model.getColor());

        switch (model.getType()) {
            case DrawBaseModel.TPEY_LINE:
                drawLine(canvas, (LineModel) model);
                break;
            case DrawBaseModel.TPEY_PATH:
                drawPath(canvas, (PathModel) model);
                break;
            case DrawBaseModel.TPEY_CIRCLE:
                drawCircle(canvas, (CircleModel) model, osmv);
                break;
            case DrawBaseModel.TPEY_RECT:
                drawRectangle(canvas, (RectModel) model, osmv);
                break;
        }

    }

    //画直线
    private void drawLine(Canvas canvas, LineModel model) {

        //对直线进行操作时，不再绘制该直线
        /*if (chickLine!=-1&&model.equals((LineModel)drawBaseModel)){
            return;
        }*/
        canvas.drawLine(model.getStartx(), model.getStarty()
                , model.getEndx(), model.getEndy(), paint);
    }

    //画圆
    private void drawCircle(Canvas canvas, CircleModel model, MapView osmv) {
        float x = model.getCenterx() - (model.getCenterx() - model.getMovex()) / 2;
        float y = model.getCentery() - (model.getCentery() - model.getMovey()) / 2;
        float radius = model.getRadis() / 2;
        Log.i(TAG, "drawCircle: x =" + x + "y =" + y + "radius=" + radius);
        //中心点对应坐标
        IGeoPoint circularGeoPoint = osmv.getProjection().fromPixels(x, y);
        //起点对应坐标
        IGeoPoint radiusGeoPoint = osmv.getProjection().fromPixels(model.getCenterx(), model.getCentery());
        double distanceToAsDouble = new GeoPoint(circularGeoPoint).distanceToAsDouble(radiusGeoPoint);
        if (mListener != null) {
            mListener.drawCircleListener(circularGeoPoint, distanceToAsDouble);
        }
        canvas.drawCircle(x, y, radius, paint);
    }

    public interface drawListener {
        void drawCircleListener(IGeoPoint circularGeoPoint, double mapRadius);

        void drawRectangleListener();
    }

    //画矩形
    private void drawRectangle(Canvas canvas, RectModel model, MapView osmv) {
        // TODO Auto-generated method stub
        float left = Math.min(model.getLeft(), model.getRight());
        float right = Math.max(model.getLeft(), model.getRight());
        float top = Math.min(model.getTop(), model.getBottom());
        float bottom = Math.max(model.getTop(), model.getBottom());
        canvas.drawRect(left, top, right, bottom, paint);

    }

    private static final String TAG = "DrawPicView";

    //画轨迹
    private void drawPath(Canvas canvas, PathModel model) {
        canvas.drawPath(model.getPath(), paint);
    }

    //清空
    public void clean() {
        path.reset();
        list.clear();
        delectList.clear();
        type = drawBaseModel.TPEY_CIRCLE;
        mMapView.postInvalidate();
    }

    //撤销
    public void cancel() {
        if (list.size() > 0) {
            DrawBaseModel delect = list.get(list.size() - 1);
            list.remove(delect);
            delectList.add(delect);
            mMapView.postInvalidate();
        } else {
            Toast.makeText(context, "无内容可撤销！", Toast.LENGTH_SHORT).show();
        }

    }

    //设置绘制图形类型
    public void setType(int type) {
        this.type = type;
    }

    //还原
    public void restore() {
        // TODO Auto-generated method stub
        if (delectList.size() > 0) {
            //将删除的路径列表中的最后一个，也就是最顶端路径取出（栈）,并加入路径保存列表中
            DrawBaseModel dp = delectList.get(delectList.size() - 1);
            list.add(dp);
            delectList.remove(delectList.size() - 1);
            mMapView.invalidate();
        } else {
            Toast.makeText(context, "无内容可还原！", Toast.LENGTH_SHORT).show();
        }
    }
}
