package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.DrawBaseModel;
import com.bril.keypersonsupervision.view.EditRegionFragment;
import com.bril.keypersonsupervision.widgets.SectorPathOverlay;
import com.orhanobut.logger.Logger;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;

import butterknife.BindView;
import butterknife.OnClick;

public class AddRegionActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tv_circle)
    TextView tvCircle;
    @BindView(R.id.tv_rect)
    TextView tvRect;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_sure)
    TextView tvSure;
    private SectorPathOverlay mPathOverlay;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, AddRegionActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_add_region;
    }

    @Override
    public void initView() {
        mPathOverlay = new SectorPathOverlay(mActivity, new SectorPathOverlay.drawListener() {
            @Override
            public void drawCircleListener(IGeoPoint circularGeoPoint, double mapRadius) {
                Logger.i("getLatitude " + circularGeoPoint.getLatitude()
                        + "\ngetLongitude" + circularGeoPoint.getLongitude()
                        + "\nmapRadius" + mapRadius);
            }

            @Override
            public void drawRectangleListener() {

            }
        });
        mPathOverlay.setType(DrawBaseModel.TPEY_RECT);
        mapView.getOverlays().add(mPathOverlay);
    }

    private static final String TAG = "AddRegionActivity";

    @Override
    public void initData() {

    }

    @OnClick({R.id.image_return, R.id.image_news, R.id.tv_circle, R.id.tv_rect,
            R.id.tv_delete, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                mPathOverlay.clean();
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_circle:
                mPathOverlay.setType(DrawBaseModel.TPEY_CIRCLE);
                break;
            case R.id.tv_rect:
                mPathOverlay.setType(DrawBaseModel.TPEY_RECT);
                break;
            case R.id.tv_delete:
                mPathOverlay.clean();
                break;
            case R.id.tv_sure:
                EditRegionFragment editRegionFragment = new EditRegionFragment();
                editRegionFragment.show(getSupportFragmentManager(), "editRegionFragment");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
