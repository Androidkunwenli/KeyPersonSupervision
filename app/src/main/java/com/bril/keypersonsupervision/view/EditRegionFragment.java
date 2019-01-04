package com.bril.keypersonsupervision.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.bean.AddVipAreaBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;


/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class EditRegionFragment extends DialogFragment {

    private String mCoordinate;
    private AddVipAreaBean bean = new AddVipAreaBean();
    private String mAreatype;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = inflater.inflate(R.layout.fragment_dialog_coordinate_upload, container, false);
        EditText et_name = v.findViewById(R.id.et_name);
        Spinner sp_vip_level = v.findViewById(R.id.sp_vip_level);
        Spinner sp_area_remake = v.findViewById(R.id.sp_area_remake);
        CheckBox cb_upload_heart_rate = v.findViewById(R.id.cb_upload_heart_rate);
        sp_vip_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setVipLevel(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_area_remake.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setAreaRemake(getActivity().getResources().getStringArray(R.array.frequency)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextView tv_sure = v.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setAreaName(et_name.getText().toString().trim());
                HttpUtils.addVipArea(getContext(), bean, new JsonCallback<Boolean>() {
                    @Override
                    public void onSuccess(Response<Boolean> response) {
                        if (response.body()) {
                            dismiss();
                            getActivity().finish();
                            ToastUtils.showShort("添加区域坐标成功!");
                        }
                    }
                });
            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    public void setCoordinate(String coordinate, String areatype) {
        mCoordinate = coordinate;
        mAreatype = areatype;
        bean.setAreaPosition(mCoordinate);
        bean.setAreatype(areatype);
    }
}
