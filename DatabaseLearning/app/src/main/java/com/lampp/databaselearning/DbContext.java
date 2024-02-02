package com.lampp.databaselearning;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbContext extends SQLiteOpenHelper {
    //tạo file db tên SE1715.db trong internal storage
    private static final String DB_NAME = "SE1715.db";
    private static final int DB_VERSION = 1;
    private static final String TB_DEFINITION_NAME = "dictionary";

    public DbContext(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE dictionary (id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, definition TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNewWord(String word, String definition) {
        String sql = "INSERT INTO dictionary (word, definition) VALUES ('" + word + "', '" + definition + "')";
        getWritableDatabase().execSQL(sql);
    }

    public long insertNewWord2(String word, String definition) {
        ContentValues cv = new ContentValues();
        cv.put("word", word);
        cv.put("definition", definition);
        return getWritableDatabase().insert(TB_DEFINITION_NAME, null, cv);
    }

    public Cursor getAllWord() {
        String sql = "SELECT * FROM dictionary";
        //selection args: thay cho dấu hỏi chấm ở câu sql
        return getReadableDatabase().rawQuery(sql, null);
    }
}
