package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.FindVipAreaListBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.RegionListAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
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

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, SetActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        mapView.setActivity(mActivity);
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        RegionListAdapter adapter = new RegionListAdapter();
        recList.setAdapter(adapter);
        HttpUtils.findVipAreaList(mActivity, new JsonCallback<List<FindVipAreaListBean>>() {
            @Override
            public void onSuccess(Response<List<FindVipAreaListBean>> response) {
                List<FindVipAreaListBean> body = response.body();
                adapter.setNewData(body);
            }

        });

        String str = "38.06126775920033,114.50136243102523,38.04782101399793,114.50136087092922,38.047772608790204,114.51751571253834,38.06121935430018,114.51751726059254";
        String str2 = "38.01550489521216,114.47650909423828,38.00610494529178,114.47650909423828,38.00610494529178,114.49298858642578,38.01550489521216,114.49298858642578";
        String str3 = "38.06025645956686,114.48921203613281, 38.03038005008319,114.51556205749512, 38.02463318912693,114.48809623718262, 38.04045287153958,114.48483467102051, 38.068703458828686,114.48483467102051, 38.06464902088959,114.47376251220703, 38.03828969783536,114.47547912597656, 38.0138819683678,114.48637962341309, 38.01591062130842,114.50517654418945, 38.052484358967995,114.52448844909668, 38.07796024983941,114.50783729553223, 38.04025007671922,114.49221611022949, 38.0813383489067,114.48346138000488, 38.03403076287582,114.46320533752441, 38.03862769793864,114.49324607849121";
        for (int i = 0; i < 3; i++) {
            Polygon polygon = new Polygon();
            polygon.setStrokeWidth(1);
            polygon.setFillColor(getResources().getColor(R.color.map_red));
            polygon.setStrokeColor(getResources().getColor(R.color.map_red));
            ArrayList<GeoPoint> points = new ArrayList<>();
            String[] split;
            if (i == 0) {
                split = str.split(",");
            } else if (i == 1) {
                split = str2.split(",");
            } else {
                split = str3.split(",");
            }
            String la = null;
            String lo = null;
            for (int i1 = 0; i1 < split.length; i1++) {
                if ((i1 & 1) != 1) {
                    la = split[i1];
                } else {
                    lo = split[i1];
                }
                if (!StringUtils.isEmpty(la) && !StringUtils.isEmpty(lo)) {
                    points.add(new GeoPoint(Double.valueOf(la), Double.valueOf(lo)));
                    la = null;
                    lo = null;
                }
            }
            polygon.setPoints(points);
            mapView.getOverlays().add(polygon);
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
                AddRegionActivity.start(mActivity);
                break;
            case R.id.tv_list_region:
                recList.setVisibility(View.VISIBLE == recList.getVisibility() ? View.GONE : View.VISIBLE);
                break;
        }
    }
}
