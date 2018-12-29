package com.bril.keypersonsupervision.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.AddPatientBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseActivity {
    @BindView(R.id.image_return)
    ImageView imageReturn;
    @BindView(R.id.image_news)
    ImageView imageNews;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.rg_gender_male)
    RadioButton rgGenderMale;
    @BindView(R.id.rg_gender_female)
    RadioButton rgGenderFemale;
    @BindView(R.id.rg_gender)
    RadioGroup rgGender;
    @BindView(R.id.et_identity_card)
    EditText etIdentityCard;
    @BindView(R.id.sp_patients_type)
    Spinner spPatientsType;
    @BindView(R.id.sp_condition_type)
    Spinner spConditionType;
    @BindView(R.id.et_supervise_name)
    EditText etSuperviseName;
    @BindView(R.id.et_supervise_telephone)
    EditText etSuperviseTelephone;
    @BindView(R.id.et_supervise_relationship)
    EditText etSuperviseRelationship;
    @BindView(R.id.sp_level)
    Spinner spLevel;
    @BindView(R.id.sp_office_type)
    Spinner spOfficeType;
    @BindView(R.id.et_name_other)
    EditText etNameOther;
    @BindView(R.id.et_contact_telephone)
    EditText etContactTelephone;
    @BindView(R.id.tv_preservation)
    TextView tvPreservation;
    private AddPatientBean mAddPatientBean = new AddPatientBean();

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, AddPeopleActivity.class));
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_add_people;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_gender_male:
                        mAddPatientBean.setGender("男");
                        break;
                    case R.id.rg_gender_female:
                        mAddPatientBean.setGender("女");
                        break;
                }
            }
        });
        spPatientsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAddPatientBean.setPatientsType(getResources().getStringArray(R.array.sp_patients_type)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spConditionType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAddPatientBean.setConditionType(getResources().getStringArray(R.array.sp_condition_type)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAddPatientBean.setLevel(getResources().getStringArray(R.array.sp_level)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spOfficeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAddPatientBean.setOthertype(String.valueOf(position + 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @OnClick({R.id.image_return, R.id.image_news, R.id.tv_preservation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_return:
                finish();
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_preservation:
                mAddPatientBean.setPatientsname(etName.getText().toString().trim());
                mAddPatientBean.setIdentityCard(etIdentityCard.getText().toString().trim());
                mAddPatientBean.setName(etSuperviseName.getText().toString().trim());
                mAddPatientBean.setContact(etSuperviseTelephone.getText().toString().trim());
                mAddPatientBean.setRelationship(etSuperviseRelationship.getText().toString().trim());
                mAddPatientBean.setOthername(etNameOther.getText().toString().trim());
                mAddPatientBean.setOthercontact(etContactTelephone.getText().toString().trim());
                HttpUtils.addPatient(mActivity, mAddPatientBean, new JsonCallback<Boolean>() {
                    @Override
                    public void onSuccess(Response<Boolean> response) {
                        if (response.body()) {
                            ToastUtils.showShort("添加患者成功!");
                            finish();
                        }
                    }
                });
                break;
        }
    }
}
