package com.example.khtt.ui.xaidiem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.khtt.databinding.FragmentSudungdiemBinding;

public class XaiDiemFragment extends Fragment {

    private FragmentSudungdiemBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        XaiDiemViewModel xaiDiemViewModel =
                new ViewModelProvider(this).get(XaiDiemViewModel.class);

        binding = FragmentSudungdiemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        xaiDiemViewModel.getKhachHang().observe(getViewLifecycleOwner(), kh -> {
            if (kh != null) {
                binding.currentPoint.setText(String.valueOf(kh.getPoints()));
                binding.note.setText(kh.getNote());
            } else {
                binding.currentPoint.setText("0");
                binding.note.setText("");
            }
        });

        binding.customerPhone.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                xaiDiemViewModel.setPhone(binding.customerPhone.getText().toString().trim());
            }
        });

        binding.save.setOnClickListener(v -> save(xaiDiemViewModel));
        binding.saveandnext.setOnClickListener(v -> {
            save(xaiDiemViewModel);
            binding.customerPhone.setText("");
            binding.currentPoint.setText("0");
            binding.usePoint.setText("");
            binding.note.setText("");
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (binding != null) {
            binding.customerPhone.setText("");
            binding.usePoint.setText("");
            binding.note.setText("");
            binding.currentPoint.setText("0");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (binding != null) {
            binding.customerPhone.setText("");
            binding.usePoint.setText("");
            binding.note.setText("");
            binding.currentPoint.setText("0");
        }
    }

    void save(XaiDiemViewModel xaiDiemViewModel){
        String phone = binding.customerPhone.getText().toString().trim();

        if (phone.isEmpty()) {
            binding.customerPhone.setError("Chưa nhập số điện thoại");
            return;
        }
        else if (phone.length() != 10) {
            binding.customerPhone.setError("Số điện thoại phải chứa 10 số");
            return;
        }

        int points = 0;
        try {
            points = Integer.parseInt(binding.usePoint.getText().toString().trim());
        } catch (NumberFormatException ignored) {}

        if (points > Integer.parseInt(binding.currentPoint.getText().toString()))
        {
            binding.usePoint.setError("Không thể xài nhiều hơn điểm hiện có");
            return;
        }
        String note = binding.note.getText().toString().trim();

        xaiDiemViewModel.upDateKhachHang(phone, points, note);
        Toast.makeText(getContext(),"Lưu thành công", Toast.LENGTH_SHORT).show();

        binding.customerPhone.setText("");
        binding.currentPoint.setText("");
        binding.usePoint.setText("");
        binding.note.setText("");
    }
}