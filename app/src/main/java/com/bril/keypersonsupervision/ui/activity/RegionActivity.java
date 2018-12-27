package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.adapter.PeopleMsgPagerAdapter;
import com.bril.keypersonsupervision.ui.fragment.RegionFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class RegionActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.spacer)
    Spinner spacer;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, RegionActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_region;
    }

    @Override
    public void initView() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> list_Title = new ArrayList<>();
        fragmentList.add(new RegionFragment());
        fragmentList.add(new RegionFragment());
        fragmentList.add(new RegionFragment());
        list_Title.add("红色区域");
        list_Title.add("橙色区域");
        list_Title.add("蓝色区域");
        /*TabLayout中间分界线*/
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.mipmap.ic_line));
        linearLayout.setDividerPadding(30);
        viewPager.setAdapter(new PeopleMsgPagerAdapter(getSupportFragmentManager(), mActivity, fragmentList, list_Title));
        tabLayout.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动
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
