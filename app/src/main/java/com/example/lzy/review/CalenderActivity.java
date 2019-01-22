package com.example.lzy.review;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import com.github.airsaid.calendarview.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class CalenderActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView curTime,selectTime_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView = findViewById(R.id.mCalenderView);
        curTime = findViewById(R.id.curTime_tv);
        selectTime_tv = findViewById(R.id.disSelect_tv);

        calendarView.setSelectDate(initData());
        calendarView.setChangeDateStatus(true);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, boolean select, int year, int month, int day) {
                if (select){
                    Toast.makeText(getApplicationContext(),"选择"+year+"年"+(month+1)+"月"+day+"日",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"取消选择"+year+"年"+(month+1)+"月"+day+"日",Toast.LENGTH_SHORT).show();
                }
                displayTime();
            }
        });
        setCurDate();

    }

    private void displayTime() {
        StringBuffer selectTime_Str = new StringBuffer();
        for (String s:calendarView.getSelectDate()) {
            selectTime_Str.append(s+"\n");
        }
        selectTime_tv.setText(selectTime_Str);
    }

    private List<String> initData() {
        List<String> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendarView.setDateFormatPattern("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat(calendarView.getDateFormatPattern(),Locale.CHINA);
        dates.add(sdf.format(calendar.getTime()));
        return dates;
    }

    private void setCurDate() {
        curTime.setText(calendarView.getYear()+"年"+(calendarView.getMonth()+1)+"月");
    }
}
