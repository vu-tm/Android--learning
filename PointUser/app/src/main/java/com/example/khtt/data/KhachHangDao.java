package com.example.khtt.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface KhachHangDao {

    // Insert a new record
    @Insert
    void insert(KhachHang khachHang);

    // Update an existing record
    @Update
    void update(KhachHang khachHang);

    // Delete a record
    @Delete
    void delete(KhachHang khachHang);

    @Query("SELECT * FROM KhachHang WHERE id_user_quanly = :current_user_id ORDER BY id_khachhang DESC")
    LiveData<List<KhachHang>> getKhachHangForUser(int current_user_id);

    @Query("SELECT * FROM KhachHang WHERE sdt = :phoneNumber AND id_user_quanly = :current_user_id")
    LiveData<KhachHang> getKhachHangByPhoneAndIdQuanLy(String phoneNumber, int current_user_id);

    @Query("SELECT * FROM KhachHang WHERE sdt = :phoneNumber AND id_user_quanly = :current_user_id")
    KhachHang getKhachHangByPhoneAndIdQuanLySync(String phoneNumber, int current_user_id);
}
