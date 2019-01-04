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
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_gender_condition_type, item.getGender() + "  "
                        + item.getAge() + "岁  " + item.getCondition_type())
                .setProgress(R.id.bar_blue, item.getBlueArea())
                .setProgress(R.id.bar_orange, item.getOrangeArea())
                .setProgress(R.id.bar_red, +item.getRedArea());
    }
}
