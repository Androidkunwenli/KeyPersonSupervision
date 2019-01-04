package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.SelectPatientBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class PositionAdapter extends BaseQuickAdapter<SelectPatientBean, BaseViewHolder> {
    public PositionAdapter() {
        super(R.layout.adapter_position);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectPatientBean item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_gender, item.getGender() + " " + item.getAge() + "岁 " + item.getCondition_type())
                .setText(R.id.tv_battery, item.getElectricity() + "%电量")
                .setText(R.id.tv_position, "当前位置 : 经度 : " + item.getLongitude() + " 纬度 : " + item.getLatitude());
    }
}
