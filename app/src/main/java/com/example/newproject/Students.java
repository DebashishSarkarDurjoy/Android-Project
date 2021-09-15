package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Students extends AppCompatActivity {
    private Button showFormActivityButton;
    private Button showStudentsActivityButton;
    private Button clearStudentsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        showFormActivityButton = (Button) findViewById(R.id.showFormButton);
        showStudentsActivityButton = (Button) findViewById(R.id.editStudentButton);
        clearStudentsButton = (Button) findViewById(R.id.clearStudents);

        showFormActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFormActivity(v);
            }
        });

        showStudentsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStudentsActivity(v);
            }
        });

        clearStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStudentsTable(v);
            }
        });
    }

    public void showFormActivity(View v) {
        Intent intent = new Intent(this, Add_Student.class);
        startActivity(intent);
    }

    public void showStudentsActivity(View v) {
        Intent intent = new Intent(this, Edit_Student.class);
        startActivity(intent);
    }

    public void clearStudentsTable(View v) {
        DatabaseManager db = new DatabaseManager(this);
        db.clearRecordsStudents();
        Toast.makeText(Students.this, "Students Database Cleared", Toast.LENGTH_SHORT).show();
        db.close();
    }
}