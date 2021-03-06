package com.bril.keypersonsupervision.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.FindVipAreaListBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.RegionListAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Polygon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tv_add_region)
    TextView tvAddRegion;
    @BindView(R.id.tv_list_region)
    TextView tvListRegion;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    private RegionListAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, SetActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        mapView.getController().setCenter(new GeoPoint(38.04487619383014, 114.47731912136078));
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new RegionListAdapter();
        recList.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FindVipAreaListBean bean = (FindVipAreaListBean) adapter.getData().get(position);
                switch (view.getId()) {
                    case R.id.tv_delete:
                        AlertDialog.Builder normalDialog =
                                new AlertDialog.Builder(mActivity);
                        normalDialog.setTitle("温馨提示");
                        normalDialog.setMessage("确定要删除" + bean.getArea_name() + "区域吗？");
                        normalDialog.setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HttpUtils.delVipArea(mActivity, bean.getId(), new JsonCallback<Boolean>() {
                                            @Override
                                            public void onSuccess(Response<Boolean> response) {
                                                if (response.body()) {
                                                    ToastUtils.showShort("删除区域成功！");
                                                    onResume();
                                                }
                                            }
                                        });
                                        dialog.dismiss();
                                    }
                                });
                        normalDialog.setNegativeButton("取消", null);
                        // 显示
                        normalDialog.show();
                        break;
                    case R.id.tv_edit:
                        AddRegionActivity.start(mActivity,bean);
                        break;
                    case R.id.tv_see:
                        String[] split = bean.getArea_position().split(",");
                        String la = null;
                        String lo = null;
                        for (int i1 = 0; i1 < split.length; i1++) {
                            if ((i1 & 1) != 1) {
                                lo = split[i1];
                            } else {
                                la = split[i1];
                            }
                            if (lo != null && la != null) {
                                recList.setVisibility(View.GONE);
                                mapView.getController().setCenter(new GeoPoint(Double.valueOf(la), Double.valueOf(lo)));
                            }
                        }
                        break;
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.findVipAreaList(mActivity, new JsonCallback<List<FindVipAreaListBean>>() {
            @Override
            public void onSuccess(Response<List<FindVipAreaListBean>> response) {
                List<FindVipAreaListBean> body = response.body();
                mAdapter.setNewData(body);
                showMap(body);
            }

        });
    }

    private void showMap(List<FindVipAreaListBean> body) {
        mapView.getOverlays().clear();
        for (FindVipAreaListBean areaListBean : body) {
            String[] split = areaListBean.getArea_position().split(",");
            Polygon polygon = new Polygon();
            polygon.setStrokeWidth(1);
            switch (areaListBean.getVip_Level()) {
                case 1:
                    polygon.setFillColor(getResources().getColor(R.color.map_blue));
                    polygon.setStrokeColor(getResources().getColor(R.color.map_blue));
                    break;
                case 2:
                    polygon.setFillColor(getResources().getColor(R.color.map_orange));
                    polygon.setStrokeColor(getResources().getColor(R.color.map_orange));
                    break;
                case 3:
                    polygon.setFillColor(getResources().getColor(R.color.map_red));
                    polygon.setStrokeColor(getResources().getColor(R.color.map_red));
                    break;
                default:
                    polygon.setFillColor(getResources().getColor(R.color.map_blue));
                    polygon.setStrokeColor(getResources().getColor(R.color.map_blue));
                    break;
            }
            ArrayList<GeoPoint> points = new ArrayList<>();
            String la = null;
            String lo = null;
            for (int i1 = 0; i1 < split.length; i1++) {
                if ((i1 & 1) != 1) {
                    lo = split[i1];
                } else {
                    la = split[i1];
                }
                if (!StringUtils.isEmpty(la) && !StringUtils.isEmpty(lo)) {
                    points.add(new GeoPoint(Double.valueOf(la), Double.valueOf(lo)));
                    la = null;
                    lo = null;
                }
            }
            polygon.setPoints(points);
            mapView.getOverlays().add(polygon);
            mapView.postInvalidate();
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.image_return, R.id.image_news, R.id.tv_add_region, R.id.tv_list_region})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_add_region:
                AddRegionActivity.start(mActivity,null);
                break;
            case R.id.tv_list_region:
                recList.setVisibility(View.VISIBLE == recList.getVisibility() ? View.GONE : View.VISIBLE);
                break;
        }
    }
}
