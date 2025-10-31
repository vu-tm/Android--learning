package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Tạo nút bấm để chuyển sang activity khác
        Button btnOpenOtherActivity = findViewById(R.id.btn_open_other_activity);
        btnOpenOtherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo intent để chuyển sang Activity khác
                // (getApplicationContext == this) là đầu, (OtherActivity.class) là đích
                Intent intent = new Intent(getApplicationContext(), OtherActivity.class);

                // Truyền dữ liệu qua Intent
                intent.putExtra("message", "Xin chào từ MainActivity");
                // Khởi chạy Activity mới
                startActivity(intent);
            }
        });

    }
}