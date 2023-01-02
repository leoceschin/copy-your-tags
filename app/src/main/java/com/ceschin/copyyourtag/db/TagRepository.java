package com.ceschin.copyyourtag.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ceschin.copyyourtag.models.TagModel;

import java.util.ArrayList;
import java.util.List;

public class TagRepository {
    private DatabaseConfig databaseConfig;

    public TagRepository(Context ctx) {
       databaseConfig = new DatabaseConfig(ctx);
    }

    public void insert(TagModel tagModel){
        SQLiteDatabase db = databaseConfig.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseConfig.COLUMN_TAGS,
                tagModel.getTags());

        db.insert(DatabaseConfig.TABLE_TAGS, null, cv);
        db.close();
    }

    public List<TagModel> getAllTags(){
        SQLiteDatabase db = databaseConfig.getReadableDatabase();
        String sql = "SELECT * FROM "+DatabaseConfig.TABLE_TAGS;

        Cursor cursor = db.rawQuery(sql, null);
        List<TagModel> tags = new ArrayList<TagModel>();

        while(cursor.moveToNext()){
            @SuppressLint("Range")
            long id = cursor.getLong(cursor.getColumnIndex(DatabaseConfig.COLUMN_ID));
            @SuppressLint("Range")
            String tag = cursor.getString(cursor.getColumnIndex(DatabaseConfig.COLUMN_TAGS));

            TagModel tagModel = new TagModel(id, tag);
            tags.add(tagModel);
        }
        cursor.close();
        db.close();

        return tags;

    }
}
