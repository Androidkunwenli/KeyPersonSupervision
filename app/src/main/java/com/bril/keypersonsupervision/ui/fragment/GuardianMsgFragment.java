package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.bean.AddPatientBean;
import com.bril.keypersonsupervision.bean.SelectSupervisorsInfoBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.activity.AddPeopleActivity;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

public class GuardianMsgFragment extends BaseFragment {
    @BindView(R.id.tv_supervisor_name)
    TextView tvSupervisorName;
    @BindView(R.id.tv_supervisor_telephone)
    TextView tvSupervisorTelephone;
    @BindView(R.id.tv_supervisor_relationship)
    TextView tvSupervisorRelationship;
    @BindView(R.id.tv_department_grade)
    TextView tvDepartmentGrade;
    @BindView(R.id.tv_department_information)
    TextView tvDepartmentInformation;
    @BindView(R.id.tv_department_name)
    TextView tvDepartmentName;
    @BindView(R.id.tv_department_telephone)
    TextView tvDepartmentTelephone;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
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
    private AddPatientBean mAddPatientBean = new AddPatientBean();
    @Override
    public void initData() {
        HttpUtils.selectSupervisorsInfo(mActivity, mOsId, new JsonCallback<SelectSupervisorsInfoBean>() {
            @Override
            public void onSuccess(Response<SelectSupervisorsInfoBean> response) {
                try {
                    SelectSupervisorsInfoBean body = response.body();
                    SelectSupervisorsInfoBean.HeguardianBean heguardian = body.getHeguardian();
                    tvSupervisorName.setText("姓名 : " + heguardian.getName());
                    tvSupervisorTelephone.setText("联系方式 : " + heguardian.getContact());
                    tvSupervisorRelationship.setText("关系 : " + heguardian.getRelationship());
                    SelectSupervisorsInfoBean.ComprehensiveBean comprehensive = body.getComprehensive();
                    String[] sp_type = getResources().getStringArray(R.array.sp_type);
                    tvDepartmentGrade.setText("卫生部门评估等级 : " + comprehensive.getLevel());
                    tvDepartmentInformation.setText("综治办联系人及信息管理 : " + sp_type[Integer.valueOf(comprehensive.getType())]);
                    tvDepartmentName.setText("姓名 : " + comprehensive.getName());
                    tvDepartmentTelephone.setText("联系方式 : " + comprehensive.getContact());
                } catch (Exception e) {

                }
            }
        });
    }

    @OnClick(R.id.tv_edit)
    public void onViewClicked() {
        AddPeopleActivity.start(mActivity);
    }
}
