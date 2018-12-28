package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.SelectPatientsLocationBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class NewsAdapter extends BaseQuickAdapter<SelectPatientsLocationBean,BaseViewHolder> {
    public NewsAdapter() {
        super(R.layout.adapter_news);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectPatientsLocationBean item) {

    }
}
