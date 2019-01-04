package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.SelectPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.StatisticsAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StatisticsActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    private StatisticsAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, StatisticsActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_statistics;
    }

    @Override
    public void initView() {
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new StatisticsAdapter();
        recList.setAdapter(mAdapter);

    }

    @Override
    public void initData() {
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.image_choice:
                        SelectPatientBean bean = (SelectPatientBean) adapter.getData().get(position);
                        bean.setChoice(!bean.isChoice());
                        adapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.selectPatient(mActivity, "string", new JsonCallback<List<SelectPatientBean>>() {
            @Override
            public void onSuccess(Response<List<SelectPatientBean>> response) {
                mAdapter.setNewData(response.body());
            }
        });
    }

    @OnClick({R.id.image_return, R.id.image_news, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_confirm:
                AnalysisActivity.start(mActivity);
                break;
        }
    }
}
