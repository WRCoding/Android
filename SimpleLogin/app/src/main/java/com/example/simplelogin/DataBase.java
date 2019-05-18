package com.example.simplelogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private static final String STUDENT = "create table student("
            +"id integer primary key autoincrement,"
            +"name text, "
            +"password text,"
            +"age integer,"
            +"birth text,"
            +"gender integer,"
            +"email text,"
            +"tel text)";
    private Context mContext;

    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
