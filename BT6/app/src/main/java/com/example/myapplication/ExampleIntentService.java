package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class ExampleIntentService extends IntentService {
    // Constructor
    public ExampleIntentService() {
        super("ExampleIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("ExampleIntentService", "Task in progress");

        // Giả lập long running = cách cho ngủ 5 giây
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ExampleIntentService", "Task Completed");
    }
}
