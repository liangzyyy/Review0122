package com.example.lzy.review;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lzy.review.javabeans.CtiyTemp;
import com.example.lzy.review.javabeans.UrlBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView city_tv,city_id_tv,temp_tv,wd_tv,ws_tv;
    EditText inputCity_edt;
    Button submit_btn,chart_btn,calender_btn,volley_btn,evn_btn;

    CtiyTemp ctiyTemp = new CtiyTemp();
    UrlBean urlBean = new UrlBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiview();
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.volley_btn:
                 intent = new Intent(MainActivity.this,VolleyActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.calender_btn:
                 intent = new Intent(MainActivity.this,CalenderActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.chart_btn:
                intent = new Intent(MainActivity.this,ChartActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.submit_btn:
                urlBean.setCity(inputCity_edt.getText().toString());
                inputCity_edt.setText("");
                HttpRequestWeather(urlBean);
                break;
            case R.id.env_btn:
                intent = new Intent(MainActivity.this,EvnActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
    private void HttpRequestWeather(UrlBean urlBean) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlBean.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("TAG",jsonObject.toString());
                        ctiyTemp.setCity(jsonObject.optString("city"));
                        setVALUE();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("TAG",volleyError.getMessage(),volleyError);
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }


    private void setVALUE() {
        Log.d("TAG",ctiyTemp.toString());
        city_tv.setText(ctiyTemp.getCity());
    }

    private void intiview() {
        city_tv = findViewById(R.id.city);
        city_id_tv = findViewById(R.id.cityID_tv);
        temp_tv = findViewById(R.id.tempValue_tv);
        wd_tv = findViewById(R.id.WD_value_tv);
        ws_tv = findViewById(R.id.WS_value_tv);
        submit_btn = findViewById(R.id.submit_btn);
        inputCity_edt = findViewById(R.id.inputCity_edt);
        chart_btn = findViewById(R.id.chart_btn);
        calender_btn = findViewById(R.id.calender_btn);
        volley_btn = findViewById(R.id.volley_btn);
        evn_btn = findViewById(R.id.env_btn);
        evn_btn.setOnClickListener(this);
        volley_btn.setOnClickListener(this);
        chart_btn.setOnClickListener(this);
        submit_btn.setOnClickListener(this);
        calender_btn.setOnClickListener(this);
    }

}
