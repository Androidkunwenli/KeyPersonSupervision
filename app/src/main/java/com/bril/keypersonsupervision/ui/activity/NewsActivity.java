package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.SelectPatientsLocationBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.NewsAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.rec_list)
    RecyclerView recList;
    private NewsAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, NewsActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        imageNews.setVisibility(View.GONE);
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new NewsAdapter();
        recList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelectPatientsLocationBean bean = (SelectPatientsLocationBean) adapter.getData().get(position);
                PeopleMsgActivity.start(mActivity, bean.getId());
            }
        });
    }

    @Override
    public void initData() {
        HttpUtils.selectPatientsLocation(mActivity, new JsonCallback<List<SelectPatientsLocationBean>>() {
            @Override
            public void onSuccess(Response<List<SelectPatientsLocationBean>> response) {
                List<SelectPatientsLocationBean> body = response.body();
                mAdapter.setNewData(body);
            }
        });

    }


    @OnClick(R.id.image_return)
    public void onViewClicked() {
        finish();
    }
}
