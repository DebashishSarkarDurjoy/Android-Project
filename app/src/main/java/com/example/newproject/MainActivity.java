package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button showStudentsActivityButton;
    private Button showToDoTasksActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showStudentsActivityButton = (Button) findViewById(R.id.students);
        showStudentsActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStudentsActivity(v);
            }
        });

        showToDoTasksActivityButton = (Button) findViewById(R.id.toDoTasks);
        showToDoTasksActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToDoTasksActivity(v);
            }
        });
    }

    public void showStudentsActivity(View v) {
        Intent intent = new Intent(this, Students.class);
        startActivity(intent);
    }

    public void showToDoTasksActivity(View v) {
        Intent intent = new Intent(this, To_Do_Tasks.class);
        startActivity(intent);
    }
}