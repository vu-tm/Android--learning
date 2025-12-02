package com.example.a3123410437_btthamkhao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Bai2B extends AppCompatActivity {
    ImageView imgSelected;
    ActivityResultLauncher<Intent> imagePickerlauncher; // Gửi intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai2b);

        imgSelected = findViewById(R.id.imgSelected);
        Button btnSelected = findViewById(R.id.btnSelected);

        // Khởi tạo launcher để nhận kết quả chọn ảnh
        imagePickerlauncher = registerForActivityResult( // cần 2 tham số (contract, callback)
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        // ActivityResult.getData() -> Intent
                        // Intent.getData() -> Uri
                        Uri imageUri = result.getData().getData(); // result -> ActivityResult -> Intent -> Uri
                        imgSelected.setImageURI(imageUri);
                    }
                }
        );

        // Mở trình chọn ảnh khi nhấn nút
        btnSelected.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerlauncher.launch(intent);
        });
    }
}
