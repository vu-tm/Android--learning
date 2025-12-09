package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class FullScreenAdapter extends RecyclerView.Adapter<FullScreenAdapter.ViewHolder> {

  private Context context;
  private ArrayList<String> images;
  private ViewPager2 viewPager; // Tham chiếu đến ViewPager2

  public FullScreenAdapter(Context context, ArrayList<String> images, ViewPager2 viewPager) {
    this.context = context;
    this.images = images;
    this.viewPager = viewPager;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_fullscreen_image, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Glide.with(context).load(images.get(position)).into(holder.photoView);

    // Thiết lập listener cho cử chỉ 3 ngón
    holder.photoView.setOnThreeFingerSwipeListener(new CustomPhotoView.OnThreeFingerSwipeListener() {
      @Override
      public void onSwipeLeft() {
        // Vuốt trái = ảnh tiếp theo
        int currentItem = viewPager.getCurrentItem();
        if (currentItem < images.size() - 1) {
          viewPager.setCurrentItem(currentItem + 1, true);
        }
      }

      @Override
      public void onSwipeRight() {
        // Vuốt phải = ảnh trước đó
        int currentItem = viewPager.getCurrentItem();
        if (currentItem > 0) {
          viewPager.setCurrentItem(currentItem - 1, true);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return images.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    CustomPhotoView photoView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      photoView = itemView.findViewById(R.id.photoView);
    }
  }
}