package com.example.lzy.review;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lzy.review.Fragment.HorizontalBarChartFragment;
import com.example.lzy.review.Fragment.PieFragment;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initView();
    }

    private void initView() {

        viewPager = findViewById(R.id.viewPage);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
               Fragment f=null;
               switch (i){
                   case 0:
                       f = PieFragment.newInstance(i+"");
                       break;
                   case 1:
                       f = HorizontalBarChartFragment.newInstance();
                       break;
               }
               return f;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
