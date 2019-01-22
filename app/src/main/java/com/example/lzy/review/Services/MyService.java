package com.example.lzy.review.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    int i=0;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                while (true){
//                    i++;
//                    Log.d("TAG","服务正在运行..."+i);
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
        Log.d("TAG","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG","onDestroy");
    }

}
