package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.bean.SelectCauseRecordBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.RecordAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;

public class RecordFragment extends BaseFragment {

    @BindView(R.id.rec_list)
    RecyclerView recList;
    private String mOsId;

    //传输数据
    public static RecordFragment newInstance(String osId) {
        RecordFragment f = new RecordFragment();
        Bundle b = new Bundle();
        b.putString("osId", osId);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOsId = arguments.getString("osId");
        }
    }

    @Override
    public int initView() {
        return R.layout.activity_record;
    }

    @Override
    public void initData() {
        super.initData();
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        RecordAdapter adapter = new RecordAdapter();
        recList.setAdapter(adapter);

        HttpUtils.selectByPrimaryKey(mActivity, mOsId, new JsonCallback<List<SelectCauseRecordBean>>() {
            @Override
            public void onSuccess(Response<List<SelectCauseRecordBean>> response) {
                adapter.setNewData(response.body());
            }
        });
    }
}
