package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;

public class FullScreenActivity extends AppCompatActivity {

  private ViewPager2 viewPager;
  private ArrayList<String> dsImage;
  private int startPosition;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_full_screen);

    viewPager = findViewById(R.id.viewPager);
    dsImage = getIntent().getStringArrayListExtra("images");
    startPosition = getIntent().getIntExtra("position", 0);

    FullScreenAdapter adapter = new FullScreenAdapter(this, dsImage, viewPager);
    viewPager.setAdapter(adapter);
    viewPager.setCurrentItem(startPosition, false);
    viewPager.setPageTransformer(new ChuyenDoiHieuUng());
    viewPager.setUserInputEnabled(false);

    // Xử lý nút Back
    Button btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(v -> finish());
  }
}