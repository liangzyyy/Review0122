package com.example.lzy.review.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    private static RequestManager ourInstance;
    private RequestQueue requestQueue;

    public RequestManager(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RequestManager createInstance(Context context){
        if (context!=null){
            if (ourInstance==null){
                ourInstance = new RequestManager(context);
            }
        }else{
            throw new IllegalArgumentException("context must be set");
        }
        return ourInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
