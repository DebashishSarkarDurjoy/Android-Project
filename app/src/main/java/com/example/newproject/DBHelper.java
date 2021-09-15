package com.example.newproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "StudentData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table StudentDetails(studentNumber TEXT primary key, firstName TEXT, lastName TEXT, gender TEXT, course TEXT, age TEXT, address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists StudentDetails");
    }

    public boolean insertData(String studentNumber, String firstName, String lastName, String gender, String course, String age, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentNumber", studentNumber);
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("gender", gender);
        contentValues.put("course", course);
        contentValues.put("age", age);
        contentValues.put("address", address);

        long result = db.insert("StudentDetails", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
