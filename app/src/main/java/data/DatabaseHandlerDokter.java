package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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


}
