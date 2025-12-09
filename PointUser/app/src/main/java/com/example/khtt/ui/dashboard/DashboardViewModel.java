package com.example.khtt.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.khtt.data.KhachHang;
import com.example.khtt.data.KhachHangDatabase;
import com.example.khtt.data.LoginRepository;

import java.util.List;
import java.util.concurrent.Executors;

public class DashboardViewModel extends AndroidViewModel {
    private final KhachHangDatabase db;
    private final int current_user_id = LoginRepository.getInstance(null).getLoggedInUserId();
    private final LiveData<List<KhachHang>> khachHangList;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        db = KhachHangDatabase.getInstance(application);
        khachHangList = db.khachHangDao().getKhachHangForUser(current_user_id);
    }

    public LiveData<List<KhachHang>> getKhachHangList() {
        return khachHangList;
    }

    public void deleteKhachHang(KhachHang kh)
    {
        Executors.newSingleThreadExecutor().execute(() -> {
            db.khachHangDao().delete(kh);
        });
    }

    public void saveOrUpdateKhachHang(String phoneNumber, int points, String ngaytao, String ngaychinhsua, String note) {
        Executors.newSingleThreadExecutor().execute(()-> {
            KhachHang kh = db.khachHangDao().getKhachHangByPhoneAndIdQuanLySync(phoneNumber,current_user_id);
            if (kh != null) {
                kh.setPoints(points);
                kh.setNote(note);
                kh.setCreateDate(ngaytao);
                kh.setModifyDate(ngaychinhsua);
                db.khachHangDao().update(kh);
            } else {
                KhachHang newKh = new KhachHang(
                        phoneNumber,
                        points,
                        current_user_id,
                        ngaytao,
                        ngaychinhsua,
                        note
                );
                db.khachHangDao().insert(newKh);
            }
        });
    }
}