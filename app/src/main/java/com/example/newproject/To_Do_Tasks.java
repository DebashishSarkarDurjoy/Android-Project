package com.example.newproject;
import com.example.newproject.DatabaseManager2;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class To_Do_Tasks extends AppCompatActivity {

    private Button addTaskButton;
    private Button updateTaskButton;
    private Button viewTaskButton;
    private Button clearTasksButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_tasks);

        addTaskButton = (Button) findViewById(R.id.addTaskButton);
        updateTaskButton = (Button) findViewById(R.id.updateTaskButton);
        viewTaskButton = (Button) findViewById(R.id.viewAllTasksButton);
        clearTasksButton = (Button) findViewById(R.id.clearTasks);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskActivity(v);
            }
        });

        updateTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllTaskActivity(v);
            }
        });

        viewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllTaskActivity(v);
            }
        });

        clearTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTasks(v);
            }
        });
    }


    public void showAddTaskActivity(View v) {
        Intent intent = new Intent(this, Add_Task.class);
        startActivity(intent);
    }

    public void showUpdateTaskActivity(View v) {
        Intent intent = new Intent(this, Update_Task.class);
        startActivity(intent);
    }

    public void showAllTaskActivity(View v) {
        Intent intent = new Intent(this, View_Tasks.class);
        startActivity(intent);
    }

    public void clearTasks(View v) {
        DatabaseManager2 db = new DatabaseManager2(this);
        db.clearRecordsTasks();
        Toast.makeText(To_Do_Tasks.this, "Tasks Table Cleared", Toast.LENGTH_SHORT).show();
        db.close();
    }

}