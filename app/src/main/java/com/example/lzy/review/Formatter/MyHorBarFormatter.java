package com.example.lzy.review.Formatter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;
import java.util.List;

public class MyHorBarFormatter extends ValueFormatter {

    private List<String> mValues;

    public MyHorBarFormatter(List<String> list) {
        mValues = list;
    }
    @Override
    public String getFormattedValue(float value) {
        return mValues.get((int) value);
    }
}
