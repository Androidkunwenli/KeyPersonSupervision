package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;

import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.OnClick;

public class MapPositionActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.mapView)
    MapView mapView;

    public static void start(FragmentActivity activity) {
        activity.startActivity(new Intent(activity, MapPositionActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_map_position;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
}
