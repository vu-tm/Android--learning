package com.example.khtt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COL_USER_ID = "id_user";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    public static final String TABLE_KHACHHANG = "khachhang";
    public static final String COL_KHACHANG_ID = "id_khachhang";
    public static final String COL_SDT = "sdt";
    public static final String COL_USER_QUANLY = "id_user_quanly";
    public static final String COL_POINTS = "diem";
    public static final String COL_CREATE_DATE = "ngaytao";
    public static final String COL_MODIFY_DATE = "ngaysua";
    public static final String COL_NOTE = "note";

    public DatabaseHelper (Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USERNAME + " TEXT UNIQUE, " +
                COL_PASSWORD + " TEXT);";

        String createKhachHangTable = "CREATE TABLE " + TABLE_KHACHHANG + " (" +
                COL_KHACHANG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SDT + " TEXT UNIQUE, " +
                COL_POINTS + " INTEGER, " +
                COL_USER_QUANLY + " INTEGER, " +
                COL_CREATE_DATE + " TEXT, " +
                COL_MODIFY_DATE + " TEXT," +
                COL_NOTE + " TEXT);";

        db.execSQL(createUserTable);
        db.execSQL(createKhachHangTable);

        //Chèn dữ liệu cho lần đầu tiên chạy
        //Chèn Users
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, "cuspoint@gmail.com");
        values.put(COL_PASSWORD, "Password123");
        db.insert(TABLE_USERS, null, values);

        values.clear();

        values.put(COL_USERNAME, "tuankhoi@gmail.com");
        values.put(COL_PASSWORD, "Password123");
        db.insert(TABLE_USERS, null, values);

        values.clear();

        //Chèn khách hàng
        values.put(COL_SDT, "3456789012");
        values.put(COL_POINTS, 9);
        values.put(COL_USER_QUANLY, 1);
        values.put(COL_CREATE_DATE, "2024-07-31 08:14:01");
        values.put(COL_MODIFY_DATE, "2024-07-31 08:52:10");
        values.put(COL_NOTE, "def");
        db.insert(TABLE_KHACHHANG, null, values);

        values.clear();

        values.put(COL_SDT, "4567890123");
        values.put(COL_POINTS, 4);
        values.put(COL_USER_QUANLY, 1);
        values.put(COL_CREATE_DATE, "2024-07-31 08:14:42");
        values.put(COL_MODIFY_DATE, "2024-07-31 08:17:31");
        values.put(COL_NOTE, "jjo");
        db.insert(TABLE_KHACHHANG, null, values);

        values.clear();

        values.put(COL_SDT, "5678901234");
        values.put(COL_POINTS, 3);
        values.put(COL_USER_QUANLY, 1);
        values.put(COL_CREATE_DATE, "2024-07-31 08:14:59");
        values.put(COL_MODIFY_DATE, "2024-07-31 08:16:23");
        values.put(COL_NOTE, "dnck");
        db.insert(TABLE_KHACHHANG, null, values);

        values.clear();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KHACHHANG);
        onCreate(db);
    }

}
