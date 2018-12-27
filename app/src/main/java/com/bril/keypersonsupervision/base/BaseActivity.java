package com.bril.keypersonsupervision.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.ui.activity.MainActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public BaseActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        //设置布局
        setContentView(intiLayout());
        ButterKnife.bind(mActivity);
        findViewById(R.id.image_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(mActivity);
            }
        });
        //初始化控件
        initView();
        //设置数据
        initData();
    }

    /**
     * 设置布局
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

}
