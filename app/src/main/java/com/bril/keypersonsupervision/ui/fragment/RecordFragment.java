package com.bril.keypersonsupervision.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.ui.adapter.RecordAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class RecordFragment extends BaseFragment {

    @BindView(R.id.rec_list)
    RecyclerView recList;

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
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        adapter.setNewData(data);
    }
}
