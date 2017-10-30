package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import models.MyWish;

/**
 * Created by erick on 30/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, "wishdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //membuat database
        String strSql = "create table wishtable (itemId integer primary key autoincrement,"+
                "title text,content text, recordDate long)";
        sqLiteDatabase.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists wishtable");
        onCreate(sqLiteDatabase);
    }

    public void AddWish(MyWish wish){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",wish.getTitle());
        values.put("content",wish.getContent());
        values.put("recordDate",java.lang.System.currentTimeMillis());
        db.insert("wishtable",null,values);
        db.close();
    }
}
