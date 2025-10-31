package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Bai3 extends AppCompatActivity implements View.OnClickListener {
    public static class Key {
        public static final String ACTION_PLUS_NUMBER = "com.example.action_add_number";
        public static final String NUMBER_A = "number_a";
        public static final String NUMBER_B = "number_b";
    }

    private EditText edtA, edtB;
    private Button btn;
    private CalculatorReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai3);

        edtA = (EditText) findViewById(R.id.editText1);
        edtB = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        receiver = new CalculatorReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Key.ACTION_PLUS_NUMBER);

        registerReceiver(receiver, filter, Context.RECEIVER_NOT_EXPORTED);
    }

    @Override
    public void onClick(View v) {
        try {
            int a = Integer.parseInt(edtA.getText().toString());
            int b = Integer.parseInt(edtB.getText().toString());
            Intent intent = new Intent();
            intent.setAction(Key.ACTION_PLUS_NUMBER);

            intent.setPackage(getPackageName());

            intent.putExtra(Key.NUMBER_A, a);
            intent.putExtra(Key.NUMBER_B, b);
            sendBroadcast(intent);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}