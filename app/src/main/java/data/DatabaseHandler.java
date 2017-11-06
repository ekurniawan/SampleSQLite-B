package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public void DeleteWish(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("wishtable","itemId=?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void UpdateWish(MyWish wish){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",wish.getTitle());
        values.put("content",wish.getContent());
        values.put("recordDate",java.lang.System.currentTimeMillis());
        db.update("wishtable",values,"itemId=?",
                new String[]{String.valueOf(wish.getItemId())});
        db.close();
    }

    public MyWish GetWishById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from wishtable where itemId=?",
                new String[]{String.valueOf(id)});
        if(cursor!=null){
            cursor.moveToFirst();
        }

        MyWish wish = new MyWish();
        wish.setItemId(cursor.getInt(cursor.getColumnIndex("itemId")));
        wish.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        wish.setContent(cursor.getString(cursor.getColumnIndex("content")));
        DateFormat dateFormat = DateFormat.getDateInstance();
        String dataDate = dateFormat.format(
                new Date(cursor.getLong(cursor.getColumnIndex("recordDate"))).getTime());
        wish.setRecordDate(dataDate);

        db.close();
        return wish;
    }

    public ArrayList<MyWish> GetAllWish(){
        ArrayList<MyWish> wishList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from wishtable order by title asc",null);
        if(cursor.moveToFirst()){
            do{
                MyWish wish = new MyWish();
                wish.setItemId(cursor.getInt(cursor.getColumnIndex("itemId")));
                wish.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                wish.setContent(cursor.getString(cursor.getColumnIndex("content")));
                DateFormat dateFormat = DateFormat.getDateInstance();
                String dataDate = dateFormat.format(
                        new Date(cursor.getLong(cursor.getColumnIndex("recordDate"))).getTime());
                wish.setRecordDate(dataDate);
                wishList.add(wish);
            }while (cursor.moveToNext());
        }
        db.close();
        return wishList;
    }
}
