package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.bean.SelectPatientsBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import org.osmdroid.views.MapView;

import butterknife.BindView;

public class MapPositionFragment extends BaseFragment {
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tv_createdtime)
    TextView tvCreatedtime;
    @BindView(R.id.tv_latitude_longitude)
    TextView tvLatitudeLongitude;
    @BindView(R.id.tv_heart_rate)
    TextView tvHeartRate;
    @BindView(R.id.tv_electricity)
    TextView tvElectricity;
    private String mOsId;

    //传输数据
    public static MapPositionFragment newInstance(String osId) {
        MapPositionFragment f = new MapPositionFragment();
        Bundle b = new Bundle();
        b.putString("osId", osId);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOsId = arguments.getString("osId");
        }
    }

    @Override
    public int initView() {
        return R.layout.fragment_map_position;
    }

    @Override
    public void initData() {
        HttpUtils.selectPatients(mActivity, mOsId, new JsonCallback<SelectPatientsBean>() {
            @Override
            public void onSuccess(Response<SelectPatientsBean> response) {
                SelectPatientsBean body = response.body();
                tvCreatedtime.setText(body.getCreatedtime());
                tvLatitudeLongitude.setText("经度:" + body.getLatitude() + " " + "纬度:" + body.getLongitude());
                tvElectricity.setText("剩余电量:" + body.getElectricity() + "%");
                tvHeartRate.setText("心率:" + body.getHeartrate() + "次/S");
            }
        });
    }
}
