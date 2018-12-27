package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.AddPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, AddPeopleActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_add_people;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.image_return, R.id.image_news, R.id.tv_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_preservation:
                HttpUtils.addPatient(mActivity, new AddPatientBean(), new JsonCallback<Boolean>() {
                    @Override
                    public void onSuccess(Response<Boolean> response) {

                    }
                });
                break;
        }
    }
}
