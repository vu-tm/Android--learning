package com.example.khtt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.khtt.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    private final DatabaseHelper dbHelper;
    public LoginDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public Result<LoggedInUser> login(String username, String password) {
        try (SQLiteDatabase db = dbHelper.getReadableDatabase(); Cursor cursor = db.query(
                DatabaseHelper.TABLE_USERS,
                new String[]{DatabaseHelper.COL_USER_ID, DatabaseHelper.COL_USERNAME, DatabaseHelper.COL_PASSWORD},
                DatabaseHelper.COL_USERNAME + "=? AND " + DatabaseHelper.COL_PASSWORD + "=?",
                new String[]{username, password},
                null, null, null
        )) {

            if (cursor.moveToFirst()) {
                // User exists
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USER_ID));
                String uname = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USERNAME));
                String upass = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_PASSWORD));
                LoggedInUser loggedInUser = new LoggedInUser(userId, uname, upass);
                return new Result.Success<>(loggedInUser);
            } else {
                // No matching user
                return new Result.Error<>(new IOException("Invalid username or password"));
            }
        } catch (Exception e) {
            return new Result.Error<>(new IOException("Error logging in", e));
        }
    }

    public void changePassword(String currentId, String newPassword) {
        try (SQLiteDatabase db = dbHelper.getWritableDatabase()) {

            ContentValues values = new ContentValues();
            values.put("password", newPassword);

            db.update(
                    "users",                     // table name
                    values,                      // new values
                    "id_user = ?",              // where clause
                    new String[]{currentId}       // where args
            );
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}