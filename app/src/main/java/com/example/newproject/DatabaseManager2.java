package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager2 {
    private DBHelper2 helper;
    private SQLiteDatabase db;
    private Context context;
    private String tasksTable = "Tasks";
    private String studentsTable = "Students";

    public DatabaseManager2(Context c) {
        this.context = c;
        helper = new DBHelper2(c);
        this.db = helper.getWritableDatabase();
    }

    public DatabaseManager2 openReadable() throws android.database.SQLException {
        helper = new DBHelper2(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    // Tasks Table Functions ------ START
    public boolean insertDataToTasks(String taskName, String location, String status) {
        synchronized(this.db) {
            db = helper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Name", taskName);
            contentValues.put("Location", location);
            contentValues.put("Status", status);

            long result = db.insert(tasksTable, null, contentValues);
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    public ArrayList<String> retrieveRowsFromTasks() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"name", "location", "status"};
        Cursor cursor = db.query(tasksTable, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return productRows;
    }

    // Tasks Table functions ------ END



    public void clearRecordsTasks()
    {
        db = helper.getWritableDatabase();
        db.delete(tasksTable, null, null);
        //db.delete(studentsTable, null, null);
    }

    public class DBHelper2 extends SQLiteOpenHelper {
        public DBHelper2(@Nullable Context context) {
            super(context, "TasksDatabase.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create Table Tasks(name TEXT, location TEXT, status TEXT)");
            //db.execSQL("create Table Students(String studentNumber, String firstName, String lastName, String gender, String course, String age, String address)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop Table if exists Tasks");
            //db.execSQL("drop Table if exists Students");
        }
    }

}
