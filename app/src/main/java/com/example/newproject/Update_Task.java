package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Update_Task extends AppCompatActivity {
    String item;
    String taskName, location, status;
    String[] taskInfo;
    TextView taskNameTextView;
    TextView locationTextView;
    Spinner statusSpinner;
    Button updateTaskButton;

    DatabaseManager2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        taskNameTextView = (TextView) findViewById(R.id.taskName);
        locationTextView = (TextView) findViewById(R.id.locationName);
        statusSpinner = (Spinner) findViewById(R.id.statusSpinner);

        item = getIntent().getExtras().getString("item");
        taskInfo = item.split(", ", 3);

        taskNameTextView.setText(taskInfo[0]);
        locationTextView.setText(taskInfo[1]);

        updateTaskButton = (Button) findViewById(R.id.updateThisTaskButton);
        updateTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask();
            }
        });

    }

    public void updateTask() {
        taskName = taskNameTextView.getText().toString();
        location = locationTextView.getText().toString();
        status = statusSpinner.getSelectedItem().toString();

        db = new DatabaseManager2(this);
        db.updateTask(taskName, location, status);
    }

}