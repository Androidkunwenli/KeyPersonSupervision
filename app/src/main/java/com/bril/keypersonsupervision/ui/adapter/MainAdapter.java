package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.SelectPatientsLocationBean;
import com.bril.keypersonsupervision.util.KUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.ParseException;

public class MainAdapter extends BaseQuickAdapter<SelectPatientsLocationBean, BaseViewHolder> {
    public MainAdapter() {
        super(R.layout.adapter_main);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectPatientsLocationBean item) {
        try {
            helper.setText(R.id.tv_msg, "您所管辖的" + "\"" + item.getName() + "\"精神患者" + KUtil.dealDateFormat(item.getCreated_time()) + "在石家庄出现。");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
