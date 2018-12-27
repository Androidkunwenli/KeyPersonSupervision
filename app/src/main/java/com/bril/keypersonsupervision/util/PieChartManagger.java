package com.bril.keypersonsupervision.util;

import android.graphics.Color;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.List;

public class PieChartManagger {
    public PieChart mPieChart;

    public PieChartManagger(PieChart pieChart) {
        this.mPieChart = pieChart;
        initPieChart();
    }

    //初始化
    private void initPieChart() {
        mPieChart.setUsePercentValues(true);
        mPieChart.setHighlightPerTapEnabled(false);
        mPieChart.setRotationEnabled(false);
        mPieChart.setDrawEntryLabels(false);
        mPieChart.setDrawSlicesUnderHole(false);
        Legend legend = mPieChart.getLegend();//设置比例图
        legend.setEnabled(false);//图例不显示
        mPieChart.setHoleColor(Color.TRANSPARENT);
        mPieChart.setExtraOffsets(0, 0, 73, 0);
        mPieChart.setRotationAngle(90);
        mPieChart.getDescription().setEnabled(false);
    }

    public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {
        PieDataSet dataSet = new PieDataSet(yvals, "");
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        //圆饼图中子体大小设置
        data.setValueTextSize(0f);
        //圆饼中字体颜色设置
        data.setValueTextColor(Color.TRANSPARENT);
        mPieChart.setData(data);
        mPieChart.highlightValues(null);
        //刷新
        mPieChart.invalidate();
    }
}
