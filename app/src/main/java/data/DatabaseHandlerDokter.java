package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import models.Dokter;

/**
 * Created by erick on 20/11/2017.
 */

public class DatabaseHandlerDokter extends SQLiteOpenHelper {

    public DatabaseHandlerDokter(Context context) {
        super(context, "dokterdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String strSql = "create table doktertable (Nik text primary key,"+
                "Nama text,Spesialisasi text, Alamat text)";
        sqLiteDatabase.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists doktertable");
        onCreate(sqLiteDatabase);
    }

    //menambahkan add dokter
    public void AddDokter(Dokter dokter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nik",dokter.getNik());
        values.put("Nama",dokter.getNama());
        values.put("Alamat",dokter.getAlamat());
        values.put("Spesialisasi",dokter.getSpesialisasi());
        db.insert("doktertable",null,values);
        db.close();
    }

    public void DeletDokter(String Nik){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("doktertable","Nik=?",
                new String[]{Nik});
        db.close();
    }

    public void UpdateDokter(Dokter dokter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nik",dokter.getNik());
        values.put("Nama",dokter.getNama());
        values.put("Alamat",dokter.getAlamat());
        values.put("Spesialisasi",dokter.getSpesialisasi());
        db.update("doktertable",values,"Nik=?",
                new String[]{dokter.getNik()});
        db.close();
    }

    public ArrayList<Dokter> GetAllDokter(){
        ArrayList<Dokter> dokterList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from doktertable order by Nik asc",null);
        if(cursor.moveToFirst()){
            do{
                Dokter dokter = new Dokter();
                dokter.setNik(cursor.getString(cursor.getColumnIndex("Nik")));
                dokter.setNama(cursor.getString(cursor.getColumnIndex("Nama")));
                dokter.setSpesialisasi(cursor.getString(cursor.getColumnIndex("Spesialisasi")));
                dokter.setAlamat(cursor.getString(cursor.getColumnIndex("Alamat")));
                dokterList.add(dokter);
            }while (cursor.moveToNext());
        }
        db.close();
        return dokterList;
    }

    public Dokter GetDokterById(String Nik){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from doktertable where Nik=?",
                new String[]{Nik});
        if(cursor!=null){
            cursor.moveToFirst();
        }

        Dokter dokter = new Dokter();
        dokter.setNik(cursor.getString(cursor.getColumnIndex("Nik")));
        dokter.setNama(cursor.getString(cursor.getColumnIndex("Nama")));
        dokter.setSpesialisasi(cursor.getString(cursor.getColumnIndex("Spesialisasi")));
        dokter.setAlamat(cursor.getString(cursor.getColumnIndex("Alamat")));

        db.close();
        return dokter;
    }

}
