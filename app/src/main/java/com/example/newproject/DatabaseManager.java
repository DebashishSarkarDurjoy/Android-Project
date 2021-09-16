package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseManager {
    private String studentsTable = "Students";
    private DBHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DatabaseManager(Context c) {
        this.context = c;
        helper = new DatabaseManager.DBHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DatabaseManager openReadable() throws android.database.SQLException {
        helper = new DatabaseManager.DBHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public boolean insertDataToStudents(String studentNumber, String firstName, String lastName, String gender, String course, String age, String address) {
        synchronized (this.db) {
            db = helper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("studentNumber", studentNumber);
            contentValues.put("firstName", firstName);
            contentValues.put("lastName", lastName);
            contentValues.put("gender", gender);
            contentValues.put("course", course);
            contentValues.put("age", age);
            contentValues.put("address", address);

            long result = db.insert(studentsTable, null, contentValues);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    public ArrayList<String> retrieveRowsFromStudents() {
        ArrayList<String> productRows = new ArrayList<String>();
        String[] columns = new String[] {"studentNumber", "firstName", "lastName", "gender", "course", "age", "address"};
        Cursor cursor = db.query(studentsTable, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            productRows.add(cursor.getString(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2)
                    + ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", " + cursor.getString(5)
                    + ", " + cursor.getString(6));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return productRows;
    }

    public boolean updateRecordStudent(String studentNumber, String firstName, String lastName, String gender, String course, String age, String address) {
        synchronized (this.db) {
            ContentValues value = new ContentValues();
            value.put("studentNumber", studentNumber);
            value.put("firstName", firstName);
            value.put("lastName", lastName);
            value.put("gender", gender);
            value.put("course", course);
            value.put("age", age);
            value.put("address", address);

            db.update(studentsTable, value, "studentNumber = " + studentNumber, null);
            return true;
        }
    }

    public void deleteStudentRecord(String studentNumber) {
        synchronized (this.db) {
            db.delete(studentsTable, "studentNumber = " + studentNumber, null);
        }
    }

    public void clearRecordsStudents()
    {
        db = helper.getWritableDatabase();
        db.delete(studentsTable, null, null);
    }

    public class DBHelper extends SQLiteOpenHelper {
        public DBHelper(@Nullable Context context) {
            super(context, "StudentsDatabase.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //db.execSQL("create Table Tasks(name TEXT, location TEXT, status TEXT)");
            db.execSQL("create Table Students(studentNumber TEXT, firstName TEXT,lastName TEXT, gender TEXT, course TEXT, age TEXT, address TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("drop Table if exists Tasks");
            db.execSQL("drop Table if exists Students");
        }
    }
}
