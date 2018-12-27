package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.adapter.PeopleAdmAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class PeopleAdmActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    @BindView(R.id.tv_add_people)
    TextView tvAddPeople;
    private PeopleAdmAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, PeopleAdmActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_people_adm;
    }

    @Override
    public void initView() {
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new PeopleAdmAdapter();
        recList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PeopleMsgActivity.start(mActivity);
            }
        });
    }

    @Override
    public void initData() {
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        mAdapter.setNewData(data);
    }

    @OnClick({R.id.image_return, R.id.image_news,R.id.tv_add_people})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                break;
            case R.id.tv_add_people:
                AddPeopleActivity.start(mActivity);
                break;
        }
    }

}
