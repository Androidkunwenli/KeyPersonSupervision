package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.fragment.AnalysisFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class AnalysisActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.fl_layout)
    FrameLayout flLayout;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, AnalysisActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_analysis;
    }

    @Override
    public void initView() {
        getSupportFragmentManager()    //
                .beginTransaction()
                .add(R.id.fl_layout, AnalysisFragment.newInstance(null, true))   // 此处的R.id.fragment_container是要盛放fragment的父容器
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
