package com.example.lzy.review;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lzy.review.Services.MyService;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class VolleyActivity extends AppCompatActivity {
    TextView data_tv;
    String url_Sence="http://192.168.1.121:8080/transportservice/type/jason/action/GetAllSense.do";
    String url_GetCarSpeed="http://192.168.1.121:8080/transportservice/type/jason/action/GetCarSpeed.do";
    Button startServices_btn,stopServices_btn;

    private Timer timer = new Timer();
    private TimerTask timerTask;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    requestVolley(msg.what);
                    break;
                case 1:
                    requestVolley(msg.what);
                    break;
                default:
                    break;
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        data_tv = findViewById(R.id.data_tv);
        startServices_btn = findViewById(R.id.startService_btn);
        stopServices_btn = findViewById(R.id.stopService_btn);

        final Intent intent = new Intent(VolleyActivity.this,MyService.class);

        startServices_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startService(intent);
                timer.schedule(getTimeTask(1),1000,1000);
            }
        });
        stopServices_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // stopService(intent);
                timer.cancel();
            }
        });
    }


    private TimerTask getTimeTask(final int i){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what=i;
                handler.sendMessage(msg);
            }
        };
        return timerTask;
    }

    private void requestVolley(int i) {
        RequestQueue queue = Volley.newRequestQueue(VolleyActivity.this);
        switch (i){
            case 0:
                GetAllSense(queue);
                break;
            case 1:
                GetCarSpeed(queue);
                break;
            default:
                break;
        }
    }

    private void GetAllSense(RequestQueue queue){
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.POST, url_Sence, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("TAG",jsonObject.toString());
                data_tv.setText(jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(jor);
    }

    private void GetCarSpeed(RequestQueue queue){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("CarId",1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jor = new JsonObjectRequest(1, url_GetCarSpeed, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONObject data = new JSONObject(jsonObject.optString("serverinfo"));
                    data_tv.setText(data.optString("CarSpeed"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(jor);
    }
}
