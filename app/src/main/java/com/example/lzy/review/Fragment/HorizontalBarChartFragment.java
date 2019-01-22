package com.example.lzy.review.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.lzy.review.Formatter.MyHorBarFormatter;
import com.example.lzy.review.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HorizontalBarChartFragment extends Fragment {

    private static final String DATA_KEY = "horframgent_data_key";
    private String mData;
    private HorizontalBarChart chart;
    private List<String> Xdata = new ArrayList<>();
    private List<String> Ydata = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mData = arguments.getString(DATA_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_horizontal_bar_chart,container,false);
        initview(view);
        initChart();
        setData();
        return view;
    }

    private void initChart() {
        chart.getDescription().setEnabled(false);//不显示描述
        chart.setDrawGridBackground(false);      //不绘制网格线
        chart.getAxisLeft().setEnabled(false);   //不显示左侧轴线
        chart.animateY(2000);      //Y轴动画

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  //X轴位置
        xAxis.setDrawGridLines(false);                  //X轴网格线
        xAxis.setTextSize(15f);
        xAxis.setLabelCount(3);                         //显示x轴的坐标数量;
        xAxis.setValueFormatter(new MyHorBarFormatter(Xdata));

        YAxis yr = chart.getAxisRight();
        yr.setTextSize(15f);
        yr.setValueFormatter(new ValueFormatter() {
            private DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
            @Override
            public String getFormattedValue(float value) {
                return decimalFormat.format(value)+"%";
            }
        });

        Legend l = chart.getLegend();
        l.setEnabled(false);
    }

    private void setData() {
        Xdata.add("1-2条违章");
        Xdata.add("3-5条违章");
        Xdata.add("5条以上违章");

        final float[] val = {60.51f,26.28f,13.20f};
        float barWidth = 0.5f;
        float spaceForBar = 1f;
        final ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            values.add(new BarEntry(i*spaceForBar, val[i]));
        }

        BarDataSet set1;

        if (chart.getData()!=null&&chart.getData().getDataSetCount()>0){
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        }else{
            set1 = new BarDataSet(values,"");
            set1.setColors(Color.GREEN,Color.BLUE,Color.RED);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(set1);
            data.setBarWidth(barWidth);
            data.setValueTextSize(20f);
            chart.setData(data);
        }
    }
    private void initview(View view) {
        chart = view.findViewById(R.id.HorBarChart);
    }

    public static HorizontalBarChartFragment newInstance() {
        Bundle args = new Bundle();
        HorizontalBarChartFragment fragment = new HorizontalBarChartFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
