package com.example.itubeapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.itubeapp.model.Video;
import com.example.itubeapp.util.UserUtil;
import com.example.itubeapp.util.VideoUtil;

import java.util.ArrayList;

public class PlaylistDatabaseHelper extends SQLiteOpenHelper {

    public PlaylistDatabaseHelper(@Nullable Context context) {
        super(context, VideoUtil.DATABASE_NAME, null, VideoUtil.DATABASE_VERSION);
    }

    public static final String CREATE_TABLE= "CREATE TABLE " + VideoUtil.TABLE_NAME + "(" + VideoUtil.VIDEO_ID + " TEXT PRIMARY KEY , "
            + VideoUtil.VIDEO_URL + " TEXT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + VideoUtil.TABLE_NAME;
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Boolean addVideo(Video video) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(VideoUtil.VIDEO_ID, video.getId());
        values.put(VideoUtil.VIDEO_URL, video.getUrl());
        long insert = db.insert(VideoUtil.TABLE_NAME, null, values);
        db.close();
        if (insert > -1) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Video> getPlaylist() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ VideoUtil.TABLE_NAME,null);
        ArrayList<Video> playlist = new ArrayList<>();
        while (cursor.moveToNext()) {
            playlist.add(new Video(cursor.getString(0), cursor.getString(1)));
        }
        cursor.close();
//        db.close();
        return playlist;
    }

}
