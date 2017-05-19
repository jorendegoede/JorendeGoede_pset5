package com.example.jorendegoede.jorendegoede_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Joren de Goede on 15-5-2017.
 */

public class DBManager {

    private DBHelper dbHelper;
    Context context;
    SQLiteDatabase db;

    public DBManager(Context c) {
        context = c;
    }

    // open database
    public DBManager open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    // close database
    public void close() {
        dbHelper.close();
    }

    // create method, creates a new todo
    public void create(ToDo todo) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        onUpgrade(db, 1, 1);
        ContentValues values = new ContentValues();
        values.put(dbHelper.DATABASE_TASK, todo.getTodo());
        values.put(dbHelper.DATABASE_CHECK, todo.getChecked());
        db.insert(dbHelper.DATABASE_TABLE, null, values);
        db.close();
    }

    // Read
    public ArrayList<ToDo> read() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // A list of custom objects, to store our data.
        ArrayList<ToDo> todos = new ArrayList<>();

        String[] query = new String[] {
                dbHelper.DATABASE_TASK, dbHelper.DATABASE_CHECK, dbHelper.DATABASE_ID };

        Cursor cursor = db.query(dbHelper.DATABASE_TABLE, query, null, null, null, null, null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(dbHelper.DATABASE_ID));
                String todoName = cursor.getString(cursor.getColumnIndex(dbHelper.DATABASE_TASK));
                boolean checkStatus = cursor.getInt(cursor.getColumnIndex(dbHelper.DATABASE_CHECK)) > 0;

                Log.d("read", Boolean.toString(checkStatus));

                // create a todo-object with the newly retrieved data
                ToDo todo = new ToDo(todoName, checkStatus, id);
                todos.add(todo);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return todos;
    }

    // update method, changes element in database.
    public int update(ToDo todo){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.DATABASE_TASK, todo.getTodo());
        values.put(dbHelper.DATABASE_CHECK, todo.getChecked());

        Log.d("update", Boolean.toString(todo.getChecked()));

        return db.update(dbHelper.DATABASE_TABLE, values, dbHelper.DATABASE_ID + " = ? ", new String[] {String.valueOf(todo.getID())});
    }

    // delete method, deletes element from database
    public void delete(ToDo todo) {
        // Make Writable
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Delete task
        db.delete(dbHelper.DATABASE_TABLE, dbHelper.DATABASE_ID + " = ?", new String[]{String.valueOf(todo.getID())});
        db.close();
    }

}
