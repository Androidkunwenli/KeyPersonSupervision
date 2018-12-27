package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.ui.adapter.WearAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class EquipmentActivity extends BaseActivity {
    @BindView(R.id.rec_list)
    RecyclerView recList;
    @BindView(R.id.rec_list2)
    RecyclerView recList2;
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    private WearAdapter mAdapter, mAdapter2;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, EquipmentActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_equipment;
    }

    @Override
    public void initView() {
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new WearAdapter();
        recList.setAdapter(mAdapter);

        recList2.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter2 = new WearAdapter();
        recList2.setAdapter(mAdapter2);
    }

    @Override
    public void initData() {
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("");
        mAdapter.setNewData(list);
        mAdapter2.setNewData(list2);
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
