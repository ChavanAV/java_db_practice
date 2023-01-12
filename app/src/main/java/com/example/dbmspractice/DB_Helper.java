package com.example.dbmspractice;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    public DB_Helper(Context context) {
        super(context, "stdinfo.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table std (name Text,roll_no integer,marks integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists std");
    }

    public  Boolean _insertData(String name,String roll_no,String marks){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("roll_no",roll_no);
        cv.put("marks",marks);
        long result = DB.insert("std",null,cv);
        if(result==-1){
            return  false;
        }
        else {
            return  true;
        }
    }

    public  Boolean updateData(String name,String rn,String mark){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("roll_no",rn);
        cv.put("marks",mark);
        Cursor crsr =DB.rawQuery("select * from std where name=?",new String[]{name});
        if (crsr.getCount()>0){
            long result = DB.update("std",cv,"name=?",new String[]{name});
            if (result ==-1){
                return  false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public  Boolean deleteData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor crsr = DB.rawQuery("select * from std where name=?",new String[]{name});
        if (crsr.getCount()>0){
            long resilt = DB.delete("std","name=?",new String[]{name});
            if (resilt==-1){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public Cursor viewData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor crsr = DB.rawQuery("select * from std ",null);
        return crsr;
    }
}

