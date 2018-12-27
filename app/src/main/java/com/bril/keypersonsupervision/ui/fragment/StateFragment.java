package com.bril.keypersonsupervision.ui.fragment;

import android.graphics.Color;

import com.bril.keypersonsupervision.R;
import com.bril.keypersonsupervision.base.BaseFragment;
import com.bril.keypersonsupervision.util.BarChartManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StateFragment extends BaseFragment {
    @BindView(R.id.bar_chart)
    BarChart barChart;
    private BarChartManager barChartManager1;

    @Override
    public int initView() {
        return R.layout.fragemnt_state;
    }

    @Override
    public void initData() {
        super.initData();
        barChartManager1 = new BarChartManager(barChart);
        setBarChart();

    }

    private void setBarChart() {
        //设置X轴数据
        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            xValues.add((float) i);
        }
        //设置Y轴数据
        List<List<Float>> yValues = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Float> yValue = new ArrayList<>();
            //一条柱状图模拟数据
            for (int j = 0; j < 24; j++) {
                yValue.add(j + 30f);
            }
            yValues.add(yValue);
        }
        //颜色集合
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("柱状图");
        names.add("柱状图2");
        names.add("柱状图3");

        //X轴显示字符串------->>
        final List<String> mList = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            mList.add(i + 1 + "点");
        }
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mList.get((int) value); //mList为存有月份的集合
            }
        });
        //X轴显示字符串-------<<
        //显示单条柱状图
        barChartManager1.showBarChart(xValues, yValues.get(0), names.get(0), colors.get(0));
        barChartManager1.setYAxis(100, 0, 11);
        //横向柱状图
//        barChartManager3.showHorizontalBarChart(xValues, yValues.get(0), "横向柱状图", colors.get(0));
//        barChartManager3.setXAxis(6f, 0f, 6);
//        barChartManager3.setYAxis(100f, 0f, 11);
//        barChartManager3.setDescription("温度");

    }
}
