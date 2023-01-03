package com.ceschin.copyyourtag.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseConfig extends SQLiteOpenHelper {
    private static final String DB_NAME = "dbTags";
    private static final int DB_VERSION = 1;

    public static final String TABLE_TAGS = "tbTags";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TAGS = "tags";

    public DatabaseConfig(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_TAGS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TAGS + " TEXT NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
