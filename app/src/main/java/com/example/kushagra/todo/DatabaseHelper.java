package com.example.kushagra.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TODOTASKS.db";
    public static final String TABLE_NAME = "TaskRecord_table";
    public static final String ID = "ID";
    public static final String TASK_COL = "TASK";
    public static final String DATE_COL = "DATE";
    public static final String TIME_COL = "TIME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, TASK STRING, DATE STRING, TIME STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String task, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_COL,task);
        contentValues.put(DATE_COL,date);
        contentValues.put(TIME_COL,time);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from "+TABLE_NAME,null);
        return result;
    }
}
