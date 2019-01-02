package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.bean.FindPatientsBean;
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
    private FindPatientsBean mOsId;
    private SelectSupervisorsInfoBean.HeguardianBean mHeguardian;
    private SelectSupervisorsInfoBean.ComprehensiveBean mComprehensive;

    //传输数据
    public static GuardianMsgFragment newInstance(FindPatientsBean osId) {
        GuardianMsgFragment f = new GuardianMsgFragment();
        Bundle b = new Bundle();
        b.putParcelable("osId", osId);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mOsId = arguments.getParcelable("osId");
        }
    }

    @Override
    public int initView() {
        return R.layout.fragemnt_guardian_msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        HttpUtils.selectSupervisorsInfo(mActivity, mOsId.getIdentity_card(), new JsonCallback<SelectSupervisorsInfoBean>() {
            @Override
            public void onSuccess(Response<SelectSupervisorsInfoBean> response) {
                try {
                    SelectSupervisorsInfoBean body = response.body();
                    mHeguardian = body.getHeguardian();
                    tvSupervisorName.setText("姓名 : " + mHeguardian.getName());
                    tvSupervisorTelephone.setText("联系方式 : " + mHeguardian.getContact());
                    tvSupervisorRelationship.setText("关系 : " + mHeguardian.getRelationship());
                    mComprehensive = body.getComprehensive();
                    String[] sp_type = getResources().getStringArray(R.array.sp_type);
                    tvDepartmentGrade.setText("卫生部门评估等级 : " + mComprehensive.getLevel());
                    tvDepartmentInformation.setText("综治办联系人及信息管理 : " + sp_type[Integer.valueOf(mComprehensive.getType()) - 1]);
                    tvDepartmentName.setText("姓名 : " + mComprehensive.getName());
                    tvDepartmentTelephone.setText("联系方式 : " + mComprehensive.getContact());
                } catch (Exception e) {

                }
            }
        });
    }


    @OnClick(R.id.tv_edit)
    public void onViewClicked() {
        AddPeopleActivity.start(mActivity, mOsId, mHeguardian, mComprehensive);
    }
}
