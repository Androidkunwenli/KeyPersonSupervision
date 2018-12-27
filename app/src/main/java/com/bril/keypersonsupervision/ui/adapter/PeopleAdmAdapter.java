package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.FindPatientsBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class PeopleAdmAdapter extends BaseQuickAdapter<FindPatientsBean, BaseViewHolder> {
    public PeopleAdmAdapter() {
        super(R.layout.adapter_people_adm);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindPatientsBean item) {
        helper.setText(R.id.tv_name, item.getName());
    }
}
