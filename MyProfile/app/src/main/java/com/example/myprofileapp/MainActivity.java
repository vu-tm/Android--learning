package com.example.myprofileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView avatar;
    TextView txtName, txtEmail;
    Button btnEdit;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        btnEdit = findViewById(R.id.btnEdit);
        avatar = findViewById(R.id.avatar);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        loadUserData();

        btnEdit.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    protected void loadUserData() {
        String savedName = sharedPreferences.getString("name", "Nguyễn Văn A");
        String savedEmail = sharedPreferences.getString("email", "a@gmail.com");
        String savedImgPath = sharedPreferences.getString("img_path", "");
        txtName.setText(savedName);
        txtEmail.setText(savedEmail);

        if(!savedImgPath.isEmpty()) {
            avatar.setImageURI(Uri.parse(savedImgPath));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Mỗi khi Activity được hiển thị lại, cập nhật dữ liệu
        loadUserData();
    }
}