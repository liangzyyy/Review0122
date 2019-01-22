package com.example.lzy.review;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EvnActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout menu;
    TextView tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evn);
        menu = findViewById(R.id.action_menu_divider);
        tv6 = findViewById(R.id.textView6);
        tv6.setOnClickListener(this);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menu.getVisibility()==View.VISIBLE){
                    menu.setVisibility(View.GONE);
                }else{
                    menu.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView6:
                Intent intent = new Intent(EvnActivity.this,MainActivity.class);
                startActivity(intent);
                fileList();
        }
    }
}
