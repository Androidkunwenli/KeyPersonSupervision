package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PatterActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    @BindView(R.id.rb_commonly)
    RadioButton rbCommonly;
    @BindView(R.id.rb_vigilance)
    RadioButton rbVigilance;
    @BindView(R.id.rg_patter)
    RadioGroup rgPatter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, PatterActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_patter;
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
