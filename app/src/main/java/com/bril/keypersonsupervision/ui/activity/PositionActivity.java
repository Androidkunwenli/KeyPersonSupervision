package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.SelectPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.PositionAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PositionActivity extends BaseActivity {
    private static final String TAG = "PositionActivity";
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.mapView)
    MapView mMapView;
    @BindView(R.id.rec_list)
    RecyclerView mRecList;
    private PositionAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, PositionActivity.class));
    }

    @Override
    public int intiLayout() {
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        return R.layout.activity_position;
    }

    @Override
    public void initView() {
        mRecList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new PositionAdapter();
        mRecList.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (mMapView != null) {
            mMapView.onResume();
        }
        HttpUtils.selectPatient(mActivity, "string", new JsonCallback<List<SelectPatientBean>>() {
            @Override
            public void onSuccess(Response<List<SelectPatientBean>> response) {
                mAdapter.setNewData(response.body());
            }
        });
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

    @OnClick({R.id.image_return, R.id.image_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
        }
    }

    private class GeoUpdateListener implements LocationListener {
        public GeoUpdateListener(MapController pMapController) {
        }

        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
