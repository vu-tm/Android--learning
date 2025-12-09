package com.example.khtt.ui.nhapdiem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.khtt.data.KhachHang;
import com.example.khtt.data.KhachHangDatabase;
import com.example.khtt.data.LoginRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NhapDiemViewModel extends AndroidViewModel {
    private KhachHangDatabase db;

    private final int current_user_id = LoginRepository.getInstance(null).getLoggedInUserId();
    private final Executor executor = Executors.newSingleThreadExecutor();

    private final MutableLiveData<String> phoneLiveData = new MutableLiveData<>();

    private final LiveData<KhachHang> khachHangLiveData =
            Transformations.switchMap(phoneLiveData, phone -> {
                if (phone == null || phone.isEmpty()) {
                    return new MutableLiveData<>(null);
                }
                return db.khachHangDao().getKhachHangByPhoneAndIdQuanLy(phone, current_user_id);
            });

    public NhapDiemViewModel(@NonNull Application application) {
        super(application);
        db = KhachHangDatabase.getInstance(application);
    }

    public LiveData<KhachHang> getKhachHang() {
        return khachHangLiveData;
    }

    public void setPhone(String phone) {
        phoneLiveData.setValue(phone);
    }

    public void saveOrUpdateKhachHang(String phoneNumber, int points, String note) {
        executor.execute(()-> {
            String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .format(new Date());

            KhachHang kh = db.khachHangDao().getKhachHangByPhoneAndIdQuanLySync(phoneNumber,current_user_id);
            if (kh != null) {
                kh.setPoints(points);
                kh.setNote(note);
                kh.setModifyDate(currentDateTime);
                db.khachHangDao().update(kh);
            } else {
                KhachHang newKh = new KhachHang(
                        phoneNumber,
                        points,
                        current_user_id,
                        currentDateTime,
                        currentDateTime,
                        note
                );
                db.khachHangDao().insert(newKh);
            }
        });
    }
}