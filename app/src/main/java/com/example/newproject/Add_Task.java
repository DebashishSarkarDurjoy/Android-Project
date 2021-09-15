package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Task extends AppCompatActivity {
    private EditText taskNameEditText, locationEditText;
    private Spinner statusSpinner;
    private Button addThisTaskButton;
    private DatabaseManager2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskNameEditText = (EditText) findViewById(R.id.taskName);
        locationEditText = (EditText) findViewById(R.id.locationName);
        statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
        addThisTaskButton = (Button) findViewById(R.id.addThisTaskButton);

        addThisTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTask(v);
            }
        });
    }

    public void insertTask(View v) {
        String taskName = taskNameEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String status = statusSpinner.getSelectedItem().toString();

        db = new DatabaseManager2(this);
        boolean result = db.insertDataToTasks(taskName, location, status);

        if (result == true) {
            Toast.makeText(Add_Task.this, "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Add_Task.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }


}