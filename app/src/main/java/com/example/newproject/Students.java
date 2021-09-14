package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Students extends AppCompatActivity {
    private Button showFormActivityButton;
    private Button showStudentsActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        showFormActivityButton = (Button) findViewById(R.id.showFormButton);
        showStudentsActivityButton = (Button) findViewById(R.id.showStudentButton);

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
    }

    public void showFormActivity(View v) {
        Intent intent = new Intent(this, Add_Student.class);
        startActivity(intent);
    }

    public void showStudentsActivity(View v) {
        Intent intent = new Intent(this, Show_Students.class);
        startActivity(intent);
    }
}