package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.fragment.MapPositionFragment;

import butterknife.OnClick;

public class MapPositionActivity extends BaseActivity {

    public static void start(FragmentActivity activity) {
        activity.startActivity(new Intent(activity, MapPositionActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_map_position;
    }

    @Override
    public void initView() {
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fl_layout, new MapPositionFragment())   // 此处的R.id.fragment_container是要盛放fragment的父容器
                .commit();
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
