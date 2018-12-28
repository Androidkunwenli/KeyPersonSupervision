package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.SelectCauseRecordBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class RecordAdapter extends BaseQuickAdapter<SelectCauseRecordBean, BaseViewHolder> {
    public RecordAdapter() {
        super(R.layout.adapter_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectCauseRecordBean item) {
        helper.setText(R.id.tv_cause_person, item.getCause_person())
                .setText(R.id.tv_cause_date, item.getCause_date())
                .setText(R.id.tv_event_passing, "事件经过: " + item.getEvent_passing())
                .setText(R.id.tv_handle_result, "处置结果: " + item.getHandle_result());

    }
}
