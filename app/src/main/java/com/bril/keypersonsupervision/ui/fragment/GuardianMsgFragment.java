package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;

public class GuardianMsgFragment extends BaseFragment {
    private String mOsId;

    //传输数据
    public static GuardianMsgFragment newInstance(String osId) {
        GuardianMsgFragment f = new GuardianMsgFragment();
        Bundle b = new Bundle();
        b.putString("osId", osId);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOsId = arguments.getString("osId");
        }
    }

    @Override
    public int initView() {
        return R.layout.fragemnt_guardian_msg;
    }
}
