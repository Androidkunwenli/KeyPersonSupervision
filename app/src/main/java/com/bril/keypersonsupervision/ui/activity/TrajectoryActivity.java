package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.adapter.PeopleChoiceAdapter;
import com.bril.keypersonsupervision.ui.adapter.TrajectoryListAdapter;
import com.bril.keypersonsupervision.widgets.CustomDatePicker;
import com.chad.library.adapter.base.BaseQuickAdapter;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.simplefastpoint.LabelledGeoPoint;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlayOptions;
import org.osmdroid.views.overlay.simplefastpoint.SimplePointTheme;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class TrajectoryActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_stop_time)
    TextView tvStopTime;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_time)
    LinearLayout lltime;
    @BindView(R.id.ll_time2)
    LinearLayout lltime2;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    @BindView(R.id.rec_list2)
    RecyclerView recList2;
    @BindView(R.id.tv_list_region)
    TextView tvListRegion;
    @BindView(R.id.tv_confirm2)
    TextView tvConfirm2;
    private CustomDatePicker mCustomDatePicker;

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Configuration.getInstance().save(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, TrajectoryActivity.class));
    }

    @Override
    public int intiLayout() {
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        return R.layout.activity_trajectory;
    }

    private boolean isStart = true;

    @Override
    public void initView() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvStartTime.setText(now);
        tvStopTime.setText(now);
        // 回调接口，获得选中的时间
        // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mCustomDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                if (isStart) {
                    tvStartTime.setText(time);
                } else {
                    tvStopTime.setText(time);
                }
            }
        }, "2018-01-01 00:00", now);
        mCustomDatePicker.showSpecificTime(true); // 不显示时和分
        mCustomDatePicker.setIsLoop(true); // 不允许循环滚动

        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        PeopleChoiceAdapter adapter = new PeopleChoiceAdapter();
        recList.setAdapter(adapter);
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");
        adapter.setNewData(data);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                lltime2.setVisibility(View.GONE);
                recList2.setVisibility(View.VISIBLE);
                tvListRegion.setVisibility(View.VISIBLE);
            }
        });

        recList2.setLayoutManager(new LinearLayoutManager(mActivity));
        TrajectoryListAdapter trajectoryListAdapter = new TrajectoryListAdapter();
        recList2.setAdapter(trajectoryListAdapter);
        ArrayList<String> newData = new ArrayList<>();
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        newData.add("");
        trajectoryListAdapter.setNewData(newData);
    }

    @Override
    public void initData() {
        mMapView.setActivity(mActivity);
        Polyline polyline = new Polyline();
        polyline.setWidth(6);
        polyline.setColor(mActivity.getResources().getColor(R.color.blue_shape));
        ArrayList<GeoPoint> geoPoints = new ArrayList<>();
        geoPoints.add(new GeoPoint(38.040311, 114.495846));
        geoPoints.add(new GeoPoint(38.0472973501, 114.4794505572));
        geoPoints.add(new GeoPoint(38.0360743031, 114.4813347780));
        geoPoints.add(new GeoPoint(38.0439213002, 114.5058844477));
        geoPoints.add(new GeoPoint(38.0456756612, 114.5151556211));
        polyline.setPoints(geoPoints);
        mMapView.getOverlays().add(polyline);

        // in most cases, there will be no problems of displaying >100k points, feel free to try
        List<IGeoPoint> points = new ArrayList<>();
        for (GeoPoint geoPoint : geoPoints) {
            points.add(new LabelledGeoPoint(geoPoint.getLatitude(), geoPoint.getLongitude()));
        }

        // wrap them in a theme
        SimplePointTheme pt = new SimplePointTheme(points, true);

        // create label style
        Paint textStyle = new Paint();
        textStyle.setStyle(Paint.Style.FILL);
        textStyle.setColor(Color.parseColor("#0000ff"));
        textStyle.setTextAlign(Paint.Align.CENTER);
        textStyle.setTextSize(24);

        // set some visual options for the overlay
        // we use here MAXIMUM_OPTIMIZATION algorithm, which works well with >100k points
        SimpleFastPointOverlayOptions opt = SimpleFastPointOverlayOptions.getDefaultStyle()
                .setAlgorithm(SimpleFastPointOverlayOptions.RenderingAlgorithm.MAXIMUM_OPTIMIZATION)
                .setRadius(10).setIsClickable(true).setCellSize(15).setTextStyle(textStyle);

        // create the overlay with the theme
        final SimpleFastPointOverlay sfpo = new SimpleFastPointOverlay(pt, opt);
        sfpo.setOnClickListener(new SimpleFastPointOverlay.OnClickListener() {
            @Override
            public void onClick(SimpleFastPointOverlay.PointAdapter points, Integer point) {
                Log.e("点击", points.get(0).getLatitude() + "," + points.get(0).getLongitude());
            }
        });
        // add overlay
        mMapView.getOverlays().add(sfpo);


        Polygon polygon = new Polygon();
        polygon.setStrokeWidth(10);
        polygon.setStrokeColor(0xffff0000);
        polygon.setFillColor(0x550000ff);
        polygon.setPoints(Polygon.pointsAsCircle(new GeoPoint(38.0439678925254, 114.49238777160645), 134));
//        List<GeoPoint> points = Polygon.pointsAsRect(new GeoPoint(38.0, 114.0), 50000, 50000);
//        polygon.setPoints(points);
        mMapView.getOverlays().add(polygon);
    }

    private static final String TAG = "TrajectoryActivity";

    @OnClick({R.id.image_return, R.id.image_news,
            R.id.tv_start_time, R.id.tv_stop_time,
            R.id.tv_confirm,
            R.id.tv_confirm2, R.id.tv_list_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_start_time:
                isStart = true;
                mCustomDatePicker.show(tvStartTime.getText().toString());
                break;
            case R.id.tv_stop_time:
                isStart = false;
                mCustomDatePicker.show(tvStartTime.getText().toString());
                break;
            case R.id.tv_confirm:
                lltime.setVisibility(View.GONE);
                lltime2.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_confirm2:

                break;
            case R.id.tv_list_region://轨迹列表
                RegionActivity.start(mActivity);
                break;
        }
    }

}
