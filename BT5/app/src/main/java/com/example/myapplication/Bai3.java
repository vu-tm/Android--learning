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

        // 4. Gửi tin SMS
        Button btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        btnSendSMS.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"));
                intent.putExtra("sms_body", "Thu bay nay di choi khong?");
                startActivity(intent);
            }
        });

        // 5. Xem ảnh
        Button btnViewImage = (Button) findViewById(R.id.btnViewImage);
        btnViewImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent();
                myIntent.setType("image/pictures/*");
                myIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivity(myIntent);
            }
        });

        // 6. Nghe nhạc
        Button btnMusicPlayer = (Button) findViewById(R.id.btnMusicPlayer);
        btnMusicPlayer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
                startActivity(intent);
            }
        });

        // 7. Mở bản đồ
        Button btnOpenMap = (Button) findViewById(R.id.btnOpenMap);
        btnOpenMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String url = "http://maps.google.com/maps?saddr=9.938083,-84.054430&daddr=9.926392,-84.055964";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
