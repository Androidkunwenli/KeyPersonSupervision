package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.FindPatientTrajectoryBean;
import com.bril.keypersonsupervision.bean.SelectPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.PositionAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.bril.keypersonsupervision.widgets.CustomDatePicker;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.TrajectoryPolyline;

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
        TrajectoryPolyline polyline = new TrajectoryPolyline();
        polyline.setWidth(3);
        polyline.setIcon(getResources().getDrawable(R.mipmap.ic_position));
        polyline.setColor(getResources().getColor(R.color.blue_shape));

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
        mCustomDatePicker.setIsLoop(false); // 不允许循环滚动

        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        PositionAdapter adapter = new PositionAdapter();
        recList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelectPatientBean bean = (SelectPatientBean) adapter.getData().get(position);
                HttpUtils.findPatientTrajectory(mActivity,
                        tvStartTime.getText().toString().trim(),
                        bean.getId(),
                        tvStopTime.getText().toString().trim(),
                        new JsonCallback<List<FindPatientTrajectoryBean>>() {
                            @Override
                            public void onSuccess(Response<List<FindPatientTrajectoryBean>> response) {
                                List<FindPatientTrajectoryBean> body = response.body();
                                if (body.size() != 0) {
                                    ArrayList<GeoPoint> geoPoints = new ArrayList<>();
                                    for (FindPatientTrajectoryBean trajectoryBean : body) {
                                        geoPoints.add(new GeoPoint(Double.valueOf(trajectoryBean.getLatitude()), Double.valueOf(trajectoryBean.getLongitude())));
                                    }
                                    polyline.setPoints(geoPoints);
                                    mMapView.getOverlays().add(polyline);
                                    mMapView.getController().setCenter(new GeoPoint(geoPoints.get(0).getLatitude(), geoPoints.get(0).getLongitude()));

                                    lltime2.setVisibility(View.GONE);
                                    recList2.setVisibility(View.GONE);
                                    tvListRegion.setVisibility(View.VISIBLE);
                                } else {
                                    ToastUtils.showShort("没有查询到人员轨迹记录!");
                                    mMapView.getController().setCenter(new GeoPoint(38.040311, 114.495846));

                                    lltime2.setVisibility(View.GONE);
                                    recList2.setVisibility(View.GONE);
                                    tvListRegion.setVisibility(View.GONE);
                                }
                            }
                        });

            }
        });

        HttpUtils.selectPatient(mActivity, "string", new JsonCallback<List<SelectPatientBean>>() {
            @Override
            public void onSuccess(Response<List<SelectPatientBean>> response) {
                adapter.setNewData(response.body());
            }
        });
    }

    @Override
    public void initData() {
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
