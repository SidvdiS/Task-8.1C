package com.example.itubeapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.itubeapp.model.User;
import com.example.itubeapp.util.UserUtil;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    public UserDatabaseHelper(@Nullable Context context) {
        super(context, UserUtil.DATABASE_NAME, null, UserUtil.DATABASE_VERSION);
    }

    public static final String CREATE_TABLE= "CREATE TABLE " + UserUtil.TABLE_NAME + "(" + UserUtil.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + UserUtil.FULL_NAME + " TEXT , " + UserUtil.USERNAME + " TEXT UNIQUE, " + UserUtil.PASSWORD+" TEXT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + UserUtil.TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserUtil.FULL_NAME, user.getFullname());
        contentValues.put(UserUtil.USERNAME, user.getUsername());
        contentValues.put(UserUtil.PASSWORD, user.getPassword());
        long insert = db.insert(UserUtil.TABLE_NAME, null, contentValues);
        return insert;
    }

    public boolean isValidLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + UserUtil.TABLE_NAME +
                        " WHERE " + UserUtil.USERNAME + " = ? AND " + UserUtil.PASSWORD + " = ?",
                new String[]{username, password});

        boolean isValidLogin = false;
        if (cursor != null && cursor.moveToFirst()) {
            // The cursor contains at least one row, indicating a successful login
            isValidLogin = true;
            cursor.close();
        }

        return isValidLogin;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = getReadableDatabase();

        try (Cursor cursor = db.rawQuery(
                "SELECT * FROM " + UserUtil.TABLE_NAME +
                        " WHERE " + UserUtil.USERNAME + " = ?",
                new String[]{username})) {

            boolean isUserRegistered = false;
            if (cursor != null && cursor.moveToFirst()) {
                // The cursor contains at least one row, indicating username already exixts
                isUserRegistered = true;
            }
            return isUserRegistered;
        }
    }
}
