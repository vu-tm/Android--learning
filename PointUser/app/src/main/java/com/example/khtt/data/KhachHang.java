package com.example.khtt.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "KhachHang")
public class KhachHang {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_khachhang")
    private Integer id;

    @ColumnInfo(name = "sdt")
    private String sdt;

    @ColumnInfo(name = "diem")
    private Integer points;
    @ColumnInfo(name = "id_user_quanly")
    private Integer userQuanly;

    @ColumnInfo(name = "ngaytao")
    private String createDate;

    @ColumnInfo(name = "ngaysua")
    private String modifyDate;

    @ColumnInfo(name = "note")
    private String note;

    public KhachHang() {}
    // Constructor
    public KhachHang(String sdt, int points, int userQuanly, String createDate, String modifyDate, String note) {
        this.sdt = sdt;
        this.points = points;
        this.userQuanly = userQuanly;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.note = note;
    }

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public Integer getPoints()
    {
        return points;
    }
    public void setPoints(int points)
    {
        this.points = points;
    }
    public int getUserQuanly() { return userQuanly; }
    public void setUserQuanly(int userQuanly) { this.userQuanly = userQuanly; }

    public String getCreateDate() { return createDate; }
    public void setCreateDate(String createDate) { this.createDate = createDate; }

    public String getModifyDate() { return modifyDate; }
    public void setModifyDate(String modifyDate) { this.modifyDate = modifyDate; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
