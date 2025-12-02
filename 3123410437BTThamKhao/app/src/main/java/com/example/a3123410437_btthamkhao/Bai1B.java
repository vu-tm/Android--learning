package com.example.a3123410437_btthamkhao;

import android.app.Activity;
import android.content.Intent; // Mở camera
import android.graphics.Bitmap; // Kiểu dữ liệu ảnh
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Bai1B extends AppCompatActivity {
    ImageView imgPreview;
    ActivityResultLauncher<Intent> cameraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai1b);

        imgPreview = findViewById(R.id.imgPreview);
        Button btnCapture = findViewById(R.id.btnCapture);

        // Đăng ký launcher để xử lý kết quả trả về từ camera
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    // if người dùng bấm chụp xong và có kết quả trả về
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        // Lấy thumbnail từ key "data" trong getExtras (ở trong getData)
                        Bitmap imageBitmap = (Bitmap) result.getData().getExtras().get("data");
                        imgPreview.setImageBitmap(imageBitmap);
                    }
                }
        );

        // Mở camera khi nhấn nút
        btnCapture.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Mở camera
            cameraLauncher.launch(intent);
        });


    }
}
