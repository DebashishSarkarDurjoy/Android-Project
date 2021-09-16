package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_Student extends AppCompatActivity {
    private ListView studentsListView;
    private DatabaseManager dbStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        studentsListView = (ListView) findViewById(R.id.studentsListView);
        studentsListView.setVisibility(View.VISIBLE);


        if (showRecords() == true) {
            Toast.makeText(Edit_Student.this, "Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Edit_Student.this, "Error", Toast.LENGTH_SHORT).show();
        }


        studentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Edit_Student.this,  String.valueOf(position)+ " Selected!", Toast.LENGTH_SHORT).show();
                updateStudent(item);
            }
        });

    }

    /*
        Retrieve all the rows from the Student Database using the
        DatabaseManager and display them using ListView
    */
    public boolean showRecords() {
        dbStudents = new DatabaseManager(this); //open database connection
        dbStudents.openReadable(); //open for reading
        ArrayList<String> tableContent = dbStudents.retrieveRowsFromStudents(); // get the values as an array of String
        studentsListView.setVisibility(View.VISIBLE); //set the ListView to visible
        // using ArrayAdapter for the ListView
        ArrayAdapter<String> arrayAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableContent);
        studentsListView.setAdapter(arrayAdpt);
        return true; // return true upon completion
    }

    public void updateStudent(String item) {
        Intent intent = new Intent(this, Update_Student_Form.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }
}