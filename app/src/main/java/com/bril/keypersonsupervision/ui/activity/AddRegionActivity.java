package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.DrawBaseModel;
import com.bril.keypersonsupervision.bean.FindVipAreaListBean;
import com.bril.keypersonsupervision.view.EditRegionFragment;
import com.bril.keypersonsupervision.widgets.SectorPathOverlay;
import com.orhanobut.logger.Logger;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MarkerPolygon;

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
    private MarkerPolygon mPolygon;
    private FindVipAreaListBean mBean;

    public static void start(BaseActivity activity, FindVipAreaListBean bean) {
        Intent intent = new Intent(activity, AddRegionActivity.class);
        intent.putExtra("bean", bean);
        activity.startActivity(intent);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_add_region;
    }

    @Override
    public void initView() {
        mBean = getIntent().getParcelableExtra("bean");
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
                if (mPathOverlay != null) {
                    mPathOverlay.clean(mapView);
                    mapView.getOverlays().remove(mPathOverlay);
                    mapView.invalidate();
                    mPathOverlay = null;
                }
                if (mPolygon != null) {
                    mPolygon.getPoints().clear();
                    mapView.getOverlays().remove(mPolygon);
                    mapView.invalidate();
                    mPolygon = null;
                }
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_circle:
                mPolygon = new MarkerPolygon(mActivity);
                mapView.getOverlays().add(mPolygon);
                if (mPathOverlay != null) {
                    mPathOverlay.clean(mapView);
                    mapView.getOverlays().remove(mPathOverlay);
                    mapView.invalidate();
                    mPathOverlay = null;
                    coordinate = null;
                }
                break;
            case R.id.tv_rect:
                mPathOverlay = new SectorPathOverlay(mActivity, new SectorPathOverlay.drawListener() {
                    @Override
                    public void drawCircleListener(IGeoPoint circularGeoPoint, double mapRadius) {
                        Logger.i("getLatitude " + circularGeoPoint.getLatitude()
                                + "\ngetLongitude" + circularGeoPoint.getLongitude()
                                + "\nmapRadius" + mapRadius);
                    }

                    @Override
                    public void drawRectangleListener(String pointStr) {
                        Logger.i("pointStr = " + pointStr);
                        coordinate = pointStr;
                    }
                });
                mPathOverlay.setType(DrawBaseModel.TPEY_RECT);
                mapView.getOverlays().add(mPathOverlay);
                if (mPolygon != null) {
                    mPolygon.getPoints().clear();
                    mapView.getOverlays().remove(mPolygon);
                    mapView.invalidate();
                    mPolygon = null;
                    coordinate = null;
                }
                break;
            case R.id.tv_delete:
                if (mPathOverlay != null) {
                    mPathOverlay.clean(mapView);
                    mapView.getOverlays().remove(mPathOverlay);
                    mapView.invalidate();
                    mPathOverlay = null;
                }
                if (mPolygon != null) {
                    mPolygon.getPoints().clear();
                    mapView.getOverlays().remove(mPolygon);
                    mapView.invalidate();
                    mPolygon = null;
                }
                break;
            case R.id.tv_sure:
                if (mPolygon == null && mPathOverlay == null) {
                    coordinate = null;
                    ToastUtils.showShort("请绘制重点区域!");
                    return;
                }
                if (mPolygon != null && !mPolygon.getPoints().isEmpty()) {
                    StringBuilder buffer = new StringBuilder();
                    for (GeoPoint point : mPolygon.getPoints()) {
                        buffer.append(point.getLongitude()).append(",").append(point.getLatitude()).append(",");
                    }
                    GeoPoint geoPoint = mPolygon.getPoints().get(0);
                    buffer.append(geoPoint.getLongitude()).append(",").append(geoPoint.getLatitude());
                    coordinate = buffer.toString();
                    areatype = "3";
                }
                if (mPathOverlay != null) {
                    areatype = "2";
                }
                EditRegionFragment editRegionFragment = new EditRegionFragment();
                editRegionFragment.setCoordinate(coordinate, areatype);
                if (mBean != null) {
                    editRegionFragment.setId(mBean.getId(), mBean.getArea_name());
                }
                editRegionFragment.show(getSupportFragmentManager(), "editRegionFragment");
                break;
        }
    }

    private String coordinate;
    private String areatype;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
