package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.FindPatientsBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.PeopleMsgPagerAdapter;
import com.bril.keypersonsupervision.ui.fragment.AnalysisFragment;
import com.bril.keypersonsupervision.ui.fragment.GuardianMsgFragment;
import com.bril.keypersonsupervision.ui.fragment.MapPositionFragment;
import com.bril.keypersonsupervision.ui.fragment.RecordFragment;
import com.bril.keypersonsupervision.ui.fragment.StateFragment;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class PeopleMsgActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_gender_condition_type)
    TextView tv_gender_condition_type;
    @BindView(R.id.tv_identity_card)
    TextView tv_identity_card;
    private String mId;
    private FindPatientsBean mBean;
    private PeopleMsgPagerAdapter mAdapter;

    public static void start(BaseActivity activity, String id) {
        Intent intent = new Intent(activity, PeopleMsgActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_people_msg;
    }

    @Override
    public void initView() {
        mId = getIntent().getStringExtra("id");

    }

    @Override
    public void initData() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.findPatientById(mActivity, mId, new JsonCallback<FindPatientsBean>() {
            @Override
            public void onSuccess(Response<FindPatientsBean> response) {
                mBean = response.body();
                tvName.setText(mBean.getName());
                tv_gender_condition_type.setText(mBean.getGender() + "  "
                        + mBean.getAge() + "岁  "
                        + mBean.getCondition_type());
                tv_identity_card.setText("身份证号 : " + mBean.getIdentity_card());
                showFragment();
            }
        });
    }

    private void showFragment() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        ArrayList<String> list_Title = new ArrayList<>();
        fragmentList.add(AnalysisFragment.newInstance(mId, false));
        fragmentList.add(RecordFragment.newInstance(mId));
        fragmentList.add(GuardianMsgFragment.newInstance(mBean));
        fragmentList.add(MapPositionFragment.newInstance(mId));
        fragmentList.add(StateFragment.newInstance(mId, mBean.getEquip_id()));
        list_Title.add("统计分析");
        list_Title.add("违乱记录");
        list_Title.add("监护人信息");
        list_Title.add("位置信息");
        list_Title.add("设备状态");
        /*TabLayout中间分界线*/
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.mipmap.ic_line));
        linearLayout.setDividerPadding(30);
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter = new PeopleMsgPagerAdapter(getSupportFragmentManager(), mActivity, fragmentList, list_Title);
            viewPager.setAdapter(mAdapter);
        }
        tabLayout.setupWithViewPager(viewPager);//此方法就是让tablayout和ViewPager联动
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
