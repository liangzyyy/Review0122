package com.example.lzy.review.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzy.review.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PieFragment extends Fragment {

    private static final String DATA_KEY = "pieframgent_data_key";
    private String mData;
    private PieChart PieChart;


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
        View view = inflater.inflate(R.layout.fragment_pie,null);
        PieChart = view.findViewById(R.id.pieChart);
        initPieChart();
        return view;
    }

    private void initPieChart() {
        PieChart.setUsePercentValues(true);//是否以百分比显示数据
        PieChart.setDrawHoleEnabled(false);//设置是否为饼图或环形图
        PieChart.setEntryLabelTextSize(20f);//实体数据标签字体大小
        PieChart.setEntryLabelColor(Color.BLACK);//设置标签字体颜色
        PieChart.getDescription().setEnabled(false);//图标描述
        PieChart.animateXY(2000,2000);//设置初始动画

        Legend legend = PieChart.getLegend();//获取图例
        legend.setEnabled(true);             //是否显示图例
        legend.setFormSize(20f);             //图例大小
        //legend.setFormToTextSpace(5f);     //图例标签与实体之间的距离
        legend.setXEntrySpace(25f);          //图例之间的距离
        legend.setTextSize(20f);             //图例字体大小
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);//图例的位置显示

        setData();//设置数据
    }

    private void setData() {
        //实体数据
        ArrayList<PieEntry> pieEntries = new ArrayList<>();//实体数据集合
        ArrayList<Integer> colors = new ArrayList<>();//每个块的颜色集合
        pieEntries.add(new PieEntry(13.36f,"有重复违章记录"));
        pieEntries.add(new PieEntry(86.64f,"无重复违章记录"));
        //添加颜色
        for (int c:ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        //实体数据集
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setSliceSpace(5f);//设置饼状Item之间的间隙
        pieDataSet.setSelectionShift(20f);//设置饼状Item被选中时变化的距离
        pieDataSet.setColors(colors);//为DataSet中的数据匹配上颜色集(饼图Item颜色)
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//设置实体数据再外部显示 下列设置才有效
        pieDataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
        pieDataSet.setValueLinePart1Length(0.5f);//数据连接线第一段长度
        pieDataSet.setValueLinePart2Length(0.8f);//数据连接线第二段长度
        pieDataSet.setValueLineColor(Color.BLACK);//设置连接线的颜色

        //最终设置数据
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true);//设置是否显示实体数据 默认为true
        pieData.setValueTextSize(20f);//实体数据值字体大小
        pieData.setValueFormatter(new PercentFormatter(PieChart));//设置数据的百分比百分号 并且精确到小数点后两位
        PieChart.setData(pieData);
        PieChart.invalidate();//刷新数据
    }

    public static PieFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(DATA_KEY,data);
        PieFragment fragment = new PieFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
