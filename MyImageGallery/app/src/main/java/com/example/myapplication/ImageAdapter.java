package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.OnItemClickListener;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

  private Context context;
  private ArrayList<String> imagePaths;
  private OnItemClickListener listener;


  public ImageAdapter(Context context, ArrayList<String> imagePaths, OnItemClickListener listener) {
    this.context = context;
    this.imagePaths = imagePaths;
    this.listener = listener;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int sizeAnh= parent.getWidth() / 3;
    View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
    view.setLayoutParams(new ViewGroup.LayoutParams(sizeAnh,sizeAnh));
    return new ViewHolder(view, listener);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Glide.with(context).load(imagePaths.get(position)).centerCrop().into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return imagePaths.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
      super(itemView);
      imageView = itemView.findViewById(R.id.imageView);
      itemView.setOnClickListener(v -> {
        if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
          listener.onItemClick(getAdapterPosition());
//          Toast.makeText(itemView.getContext(), getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
      });
    }
  }
}
