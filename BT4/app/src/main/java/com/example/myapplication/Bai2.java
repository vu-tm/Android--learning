package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;


public class Bai2 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2);
        getSupportActionBar().hide();

        final Button button = (Button) findViewById(R.id.button);
        final AlertDialog ad = new AlertDialog.Builder(this).create();

        // Xử lý sự kiện cho button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date t = new Date();
                String mess = "Thời gian hiện hành " + t.toString();
                ad.setMessage(mess);
                ad.show();
            }
        });
    }
}
