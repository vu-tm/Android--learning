package com.example.a3123410437_btthamkhao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";

    public DatabaseHelper(Context context) { // constructor bắt buộc nếu kế thừa SQLiteOpenHelper
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // chạy mới
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AGE + " INTEGER)";
        db.execSQL(createTable); // == execute SQL : chạy lệnh sql
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thao tác thêm mới 1 sinh viên
    public void addStudent(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase(); // mở db chế độ ghi
        ContentValues values = new ContentValues(); // cặp key-value chứa dữ liệu của cột
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Thao tác lấy danh sách sinh viên
    public ArrayList<String> getAllStudents() { // Trả dạng ArrayList để hiện lên ListView
        ArrayList<String> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase(); // db chỉ đọc
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) { // trỏ đến bản ghi đầu tiên
            do {
                int id = cursor.getInt(0);         // gtri cột 0
                String name = cursor.getString(1); // gtri cột 1
                int age = cursor.getInt(2);        // gtri cột 2

                studentList.add(id + " - " + name + " - " + age + " tuổi");
            } while (cursor.moveToNext()); // trỏ đến bản ghi cuối, trả về false nếu đã ở cuối
        }
        cursor.close();
        db.close();
        return studentList;
    }

    // Thao tác xóa một sinh viên (theo mã sinh viên)
    public void deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // DELETE FROM table_name WHERE column_id = ?
        // VD: COLUMN_ID = 5 -> new String[]{"5"})
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
