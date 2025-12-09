package com.example.khtt.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {KhachHang.class}, version = 1, exportSchema = false)
public abstract class KhachHangDatabase extends RoomDatabase {

    // Abstract method to get DAO
    public abstract KhachHangDao khachHangDao();

    // Singleton instance
    private static volatile KhachHangDatabase INSTANCE;

    public static synchronized KhachHangDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KhachHangDatabase.class, "users.db")
                    .fallbackToDestructiveMigration(false)
                    .build();
        }
        return INSTANCE;
    }
}
