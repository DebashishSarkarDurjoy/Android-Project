package com.example.newproject;
import com.example.newproject.Add_Task;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_Tasks extends AppCompatActivity {
    private ListView tasksListView;
    private DatabaseManager2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        tasksListView = (ListView) findViewById(R.id.allTasksListView);
        tasksListView.setVisibility(View.VISIBLE);


        if (showRecords() == true) {
            Toast.makeText(View_Tasks.this, "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(View_Tasks.this, "Error", Toast.LENGTH_SHORT).show();
        }

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                updateToDoTask(item);
            }
        });
    }

    public boolean showRecords() {
        db = new DatabaseManager2(this);
        db.openReadable();
        ArrayList<String> tableContent = db.retrieveRowsFromTasks();
        tasksListView.setVisibility(View.VISIBLE);
        //response.setText("The rows in the products table are: \n");
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableContent);
        tasksListView.setAdapter(arrayAdpt);
        return true;
    }

    public void updateToDoTask(String item) {
        Intent intent = new Intent(this, Update_Task.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}