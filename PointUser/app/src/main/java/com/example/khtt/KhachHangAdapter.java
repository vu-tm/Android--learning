package com.example.khtt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.khtt.data.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {

    private List<KhachHang> khachHangList = new ArrayList<>();

    public void setData(List<KhachHang> list) {
        khachHangList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.khachhang_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhachHang kh = khachHangList.get(position);
        holder.sdt.setText(kh.getSdt());
        holder.ngaytao.setText("Ngày tạo: " + kh.getCreateDate());
        holder.ngaychinhsua.setText("Ngày sửa: " + kh.getModifyDate());
        holder.note.setText(kh.getNote() != null ? ("Note: " + kh.getNote()) : "Không có note");
        holder.points.setText(String.valueOf(kh.getPoints()));
    }

    @Override
    public int getItemCount() {
        return khachHangList.size();
    }

    public KhachHang getKhachHangAt(int position) {
        return khachHangList.get(position);
    }

    public void removeAt(int position) {
        khachHangList.remove(position);
        notifyItemRemoved(position);
    }

    public List<KhachHang> getKhachHangList() {
        return khachHangList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sdt, ngaytao, ngaychinhsua, note, points;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdt = itemView.findViewById(R.id.sdt);
            ngaytao = itemView.findViewById(R.id.ngaytao);
            ngaychinhsua = itemView.findViewById(R.id.ngaychinhsua);
            note = itemView.findViewById(R.id.note);
            points = itemView.findViewById(R.id.points);
        }
    }
}
