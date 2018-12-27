package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.FindVipAreaListBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.RegionListAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import org.osmdroid.views.MapView;

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
