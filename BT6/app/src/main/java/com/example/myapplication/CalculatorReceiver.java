package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Bai3.Key.ACTION_PLUS_NUMBER:
                int a = intent.getIntExtra(Bai3.Key.NUMBER_A, 0);
                int b = intent.getIntExtra(Bai3.Key.NUMBER_B, 0);
                Toast.makeText(context, "Result: "+(a+b), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
