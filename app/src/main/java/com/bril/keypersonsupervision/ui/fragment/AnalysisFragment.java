package com.bril.keypersonsupervision.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.util.LineChartManager;
import com.bril.keypersonsupervision.util.PieChartManagger;
import com.bril.keypersonsupervision.widgets.CircularProgressView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class AnalysisFragment extends BaseFragment {
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.pro_view)
    CircularProgressView mProView;
    @BindView(R.id.line_chart)
    LineChart mLineChart;
    private String mOsId;

    //传输数据
    public static AnalysisFragment newInstance(String osId) {
        AnalysisFragment f = new AnalysisFragment();
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
        return R.layout.fragemnt_analysis;
    }

    @Override
    public void initData() {
        super.initData();
        showhodlePieChart();
        mProView.setProgress(0.9f);
        showAlone();
    }

    private void showAlone() {
        LineChartManager lineChartManager = new LineChartManager(mLineChart);
        List<Float> xAxisValues = new ArrayList<>();
        List<Float> yAxisValues = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            xAxisValues.add((float) i);
        }
        for (int i = 0; i < 24; i++) {
            yAxisValues.add((float) new Random().nextInt(100));
        }
        lineChartManager.setDescription("重点区域次数时间浮动");
        lineChartManager.showLineChart(xAxisValues, yAxisValues, "", Color.parseColor("#da6268"));
    }

    private void showhodlePieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        yvals.add(new PieEntry(5));
        yvals.add(new PieEntry(4));
        yvals.add(new PieEntry(2));
        yvals.add(new PieEntry(1));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(mActivity.getResources().getColor(R.color.blue_shape));
        colors.add(mActivity.getResources().getColor(R.color.orange));
        colors.add(mActivity.getResources().getColor(R.color.red));
        colors.add(mActivity.getResources().getColor(R.color.red_key_shape));
        PieChartManagger pieChartManagger = new PieChartManagger(pieChart);
        pieChartManagger.showSolidPieChart(yvals, colors);
    }
}
