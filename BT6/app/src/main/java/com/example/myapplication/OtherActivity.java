package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        // Nhận dữ liệu từ intent
        String message = getIntent().getStringExtra("message"); // Lấy String có key là message
        // Hiển thị dữ liệu trên TextView
        TextView textView = findViewById(R.id.text_view);
        textView.setText(message);

        // Tạo nút bấm để quay lại MainActivity
        Button buttonBackMainActivity = findViewById(R.id.btn_back_to_main);
        buttonBackMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kết thúc Activity hiện tại và quay lại MainActivity
                finish();
            }
        });

    }
}
