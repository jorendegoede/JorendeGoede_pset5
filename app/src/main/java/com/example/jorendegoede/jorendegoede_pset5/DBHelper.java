package com.example.jorendegoede.jorendegoede_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 12-5-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    // Static Strings
    private static final String DATABASE_NAME = "TodoDB";
    public static final int DATABASE_VERSION = 1;

    // Table
    public static final String DATABASE_TABLE = "TodoTable";
    // columns
    public static final String DATABASE_ID = "_id";
    public static final String DATABASE_TASK = "Task";
    public static final String DATABASE_CHECK = "Checked";

    // constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB = "CREATE TABLE " + DATABASE_TABLE + " ( " +
                DATABASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATABASE_TASK + " TEXT NOT NULL, " +
                DATABASE_CHECK + " BOOLEAN " + ");";

        db.execSQL(CREATE_DB);
//        Log.d("log", "DBHelper.onCreate: success");
    }

    // Upgrade database when helper object is made and there is one already
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
