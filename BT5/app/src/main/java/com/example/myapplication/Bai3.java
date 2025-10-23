package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bai3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        // 1. Mở google
        Button btnOpenWeb = (Button) findViewById(R.id.btnOpenWeb);
        btnOpenWeb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn"));
                startActivity(intent);
            }
        });

        // 2. Gọi điện
        Button btnGoiDien = (Button) findViewById(R.id.btnGoiDien);
        btnGoiDien.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+84)123456789"));
                startActivity(intent);
            }
        });

        // 3. Xem danh bạ
        Button btnViewContacts = (Button) findViewById(R.id.btnViewContacts);
        btnViewContacts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
            }
        });
    }
}
