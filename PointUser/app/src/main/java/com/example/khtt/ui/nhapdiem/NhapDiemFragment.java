package com.example.khtt.ui.nhapdiem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.khtt.databinding.FragmentNhapdiemBinding;

public class NhapDiemFragment extends Fragment {

    private FragmentNhapdiemBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NhapDiemViewModel nhapDiemViewModel =
                new ViewModelProvider(this).get(NhapDiemViewModel.class);

        binding = FragmentNhapdiemBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        nhapDiemViewModel.getKhachHang().observe(getViewLifecycleOwner(), kh -> {
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
                nhapDiemViewModel.setPhone(binding.customerPhone.getText().toString().trim());
            }
        });

        binding.save.setOnClickListener(v -> save(nhapDiemViewModel));
        binding.saveandnext.setOnClickListener(v -> {
            save(nhapDiemViewModel);
            binding.customerPhone.setText("");
            binding.currentPoint.setText("0");
            binding.newPoint.setText("");
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
            binding.newPoint.setText("");
            binding.note.setText("");
            binding.currentPoint.setText("0");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if (binding != null) {
            binding.customerPhone.setText("");
            binding.newPoint.setText("");
            binding.note.setText("");
            binding.currentPoint.setText("0");
        }
    }

    void save(NhapDiemViewModel nhapDiemViewModel)
    {
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
            points = Integer.parseInt(binding.newPoint.getText().toString().trim());
        } catch (NumberFormatException ignored) {}
        String note = binding.note.getText().toString().trim();

        nhapDiemViewModel.saveOrUpdateKhachHang(phone, points, note);
        Toast.makeText(getContext(),"Lưu thành công", Toast.LENGTH_SHORT).show();
    }
}