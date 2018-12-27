package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.FindVipAreaListBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class RegionListAdapter extends BaseQuickAdapter<FindVipAreaListBean, BaseViewHolder> {
    public RegionListAdapter() {
        super(R.layout.adapter_list_region);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindVipAreaListBean item) {
        helper.setText(R.id.tv_describe, item.getArea_name() + " " + item.getArea_remake());
    }
}