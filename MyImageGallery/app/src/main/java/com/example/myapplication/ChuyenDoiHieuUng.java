package com.example.myapplication;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class ChuyenDoiHieuUng implements ViewPager2.PageTransformer {

  @Override
  public void transformPage(@NonNull View page, float position) {
    if (position <= 0) {
      page.setTranslationZ(0);
      page.setAlpha(1);
      page.setTranslationX(0);
    } else if (position <= 1) {
      page.setTranslationZ(-position);
      page.setAlpha(1 - position);
      page.setTranslationX(page.getWidth() * -position);
    } else {
      page.setAlpha(0);
      page.setTranslationZ(-1);
    }
  }
}