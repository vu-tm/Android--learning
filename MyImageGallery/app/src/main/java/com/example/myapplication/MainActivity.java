package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.OnItemClickListener;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

  private static final int PERMISSION_REQUEST = 100; // Mã request
  private RecyclerView recyclerView;
  private ArrayList<String> imagePaths;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerView = findViewById(R.id.recyclerView);
    xinCapQuyenVaoThuVien();
  }
  private void xinCapQuyenVaoThuVien() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) // permission xin cấp quyền
        != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
          new String[]{Manifest.permission.READ_MEDIA_IMAGES},
          PERMISSION_REQUEST); // Dialog xin cấp quyêền nếu được cấp trả về 100
      } else {
        loadImages();
      }
    }
  }
  private void loadImages() {
    imagePaths = new ArrayList<>();
    String[] projection = {MediaStore.Images.Media.DATA}; // Chứa tên cột cột này là cột tên của đường dẫn tuyệt đối
    Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
      projection, null, null, MediaStore.Images.Media.DATE_ADDED + " DESC");

    if (cursor != null) {
      while (cursor.moveToNext()) {
        imagePaths.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))); // Lấy cột _data của row
      }
      cursor.close();
    }
    ImageAdapter adapter = new ImageAdapter(this, imagePaths, this);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onItemClick(int position) {
    Intent intent = new Intent(this, FullScreenActivity.class);
    intent.putStringArrayListExtra("images", imagePaths);
    intent.putExtra("position", position);
    startActivity(intent);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    // Được gọi sau khi chọn dialog
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION_REQUEST) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        loadImages();
      } else {
        Toast.makeText(this, "Từ chối cấp quyền truy cập ảnh", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
