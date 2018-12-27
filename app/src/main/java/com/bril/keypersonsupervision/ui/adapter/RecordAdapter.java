package com.bril.keypersonsupervision.ui.adapter;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.SelectCauseRecordBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class RecordAdapter extends BaseQuickAdapter<SelectCauseRecordBean,BaseViewHolder> {
    public RecordAdapter() {
        super(R.layout.adapter_record);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectCauseRecordBean item) {

    }
}
