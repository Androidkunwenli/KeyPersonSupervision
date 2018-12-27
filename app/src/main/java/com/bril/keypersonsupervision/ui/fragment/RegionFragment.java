package com.bril.keypersonsupervision.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.ui.activity.MapPositionActivity;
import com.bril.keypersonsupervision.ui.adapter.RegionAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;

public class RegionFragment extends BaseFragment {
    @BindView(R.id.rec_list)
    RecyclerView recList;

    @Override
    public int initView() {
        return R.layout.fragemnt_region;
    }

    @Override
    public void initData() {
        super.initData();
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        RegionAdapter adapter = new RegionAdapter();
        recList.setAdapter(adapter);
        ArrayList<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        adapter.setNewData(data);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MapPositionActivity.start(getActivity());
            }
        });
    }
}
