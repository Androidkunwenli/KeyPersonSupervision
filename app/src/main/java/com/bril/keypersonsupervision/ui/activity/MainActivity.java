package com.bril.keypersonsupervision.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseActivity;
import com.bril.keypersonsupervision.bean.SelectHistoryStatisticsBean;
import com.bril.keypersonsupervision.bean.SelectPatientsLocationBean;
import com.bril.keypersonsupervision.callback.JsonCallback;
import com.bril.keypersonsupervision.ui.adapter.MainAdapter;
import com.bril.keypersonsupervision.util.HttpUtils;
import com.bril.keypersonsupervision.util.PieChartManagger;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rec_list)
    RecyclerView recList;
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.tv_trajectory)
    TextView tvTrajectory;
    @BindView(R.id.tv_equipment)
    TextView tvEquipment;
    @BindView(R.id.tv_statistics)
    TextView tvStatistics;
    @BindView(R.id.tv_adm)
    TextView tvAdm;
    @BindView(R.id.tv_set)
    TextView tvSet;
    @BindView(R.id.tv_patter)
    TextView tvPatter;
    @BindView(R.id.ll_layout)
    LinearLayout mLlLayout;
    @BindView(R.id.tv_blue)
    TextView tvBlue;
    @BindView(R.id.tv_orange)
    TextView tvOrange;
    @BindView(R.id.tv_red)
    TextView tvRed;
    @BindView(R.id.tv_intrusion)
    TextView tvIntrusion;
    private MainAdapter mAdapter;

    public static void start(BaseActivity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
        activity.finish();
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.image_title).setClickable(false);
        recList.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new MainAdapter();
        recList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelectPatientsLocationBean bean = (SelectPatientsLocationBean) adapter.getData().get(position);
                PeopleMsgActivity.start(mActivity, bean.getId());
            }
        });
    }

    @Override
    public void initData() {
        showhodlePieChart(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpUtils.selectPatientsLocation(mActivity, new JsonCallback<List<SelectPatientsLocationBean>>() {
            @Override
            public void onSuccess(Response<List<SelectPatientsLocationBean>> response) {
                List<SelectPatientsLocationBean> body = response.body();
                mAdapter.setNewData(body);
            }
        });
        HttpUtils.selectHistoryStatistics(mActivity, new JsonCallback<SelectHistoryStatisticsBean>() {
            @Override
            public void onSuccess(Response<SelectHistoryStatisticsBean> response) {
                showhodlePieChart(response.body());
            }
        });
    }

    private void showhodlePieChart(SelectHistoryStatisticsBean body) {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        if (body != null && (body.getBlueArea() != 0 || body.getOrangeArea() != 0 || body.getRedArea() != 0 || body.getIntrusionZone() != 0)) {
            yvals.add(new PieEntry(body.getBlueArea()));
            yvals.add(new PieEntry(body.getOrangeArea()));
            yvals.add(new PieEntry(body.getRedArea()));
            yvals.add(new PieEntry(body.getIntrusionZone()));
            tvBlue.setText("蓝色区域出现" + body.getBlueArea() + "次");
            tvOrange.setText("橙色区域出现" + body.getOrangeArea() + "次");
            tvRed.setText("红色区域出现" + body.getRedArea() + "次");
            tvIntrusion.setText("入侵重点管辖区" + body.getIntrusionZone() + "人");
        } else {
            yvals.add(new PieEntry(1));
            yvals.add(new PieEntry(1));
            yvals.add(new PieEntry(1));
            yvals.add(new PieEntry(1));
        }
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(mActivity.getResources().getColor(R.color.blue_shape));
        colors.add(mActivity.getResources().getColor(R.color.orange));
        colors.add(mActivity.getResources().getColor(R.color.red));
        colors.add(mActivity.getResources().getColor(R.color.red_key_shape));
        PieChartManagger pieChartManagger = new PieChartManagger(pieChart);
        pieChartManagger.showSolidPieChart(yvals, colors);
    }

    @OnClick({R.id.image_news, R.id.tv_map, R.id.tv_trajectory, R.id.tv_equipment,
            R.id.tv_statistics, R.id.tv_adm, R.id.tv_set, R.id.tv_patter, R.id.ll_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_layout:
                RegionActivity.start(mActivity);
                break;
            case R.id.image_news:
                NewsActivity.start(mActivity);
                break;
            case R.id.tv_map://实时位置查询
                PositionActivity.start(mActivity);
                break;
            case R.id.tv_trajectory://历史轨迹
                TrajectoryActivity.start(mActivity);
                break;
            case R.id.tv_equipment://设备信息
                EquipmentActivity.start(mActivity);
                break;
            case R.id.tv_statistics://统计分析
                StatisticsActivity.start(mActivity);
                break;
            case R.id.tv_adm://人员管理
                PeopleAdmActivity.start(mActivity);
                break;
            case R.id.tv_set://边界设备
                SetActivity.start(mActivity);
                break;
            case R.id.tv_patter://模式
                PatterActivity.start(mActivity);
                break;
        }
    }
}
