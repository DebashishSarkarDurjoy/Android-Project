package com.example.newproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Student_Form extends AppCompatActivity {
    String[] splitString;
    EditText studentNumberEditText;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText courseEditText;
    EditText ageEditText;
    EditText addressEditText;
    String studentNumber, firstName, lastName, gender, course, age, address;
    Button updateStudentButton;
    Button deleteButton;

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student_form);

        String item = getIntent().getExtras().getString("item");
        Toast.makeText(Update_Student_Form.this, item, Toast.LENGTH_SHORT).show();

        splitString = item.split(", ", 7);
        Toast.makeText(Update_Student_Form.this, splitString[0], Toast.LENGTH_SHORT).show();

        studentNumber = splitString[0];
        firstName = splitString[1];
        lastName = splitString[2];
        gender = splitString[3];
        course = splitString[4];
        age = splitString[5];
        address = splitString[6];

        studentNumberEditText = findViewById(R.id.studentNumber);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        courseEditText = findViewById(R.id.course);
        ageEditText = findViewById(R.id.age);
        addressEditText = findViewById(R.id.address);

        studentNumberEditText.setText(studentNumber);
        firstNameEditText.setText(firstName);
        lastNameEditText.setText(lastName);
        courseEditText.setText(course);
        ageEditText.setText(age);
        addressEditText.setText(address);


        updateStudentButton = (Button) findViewById(R.id.updateStudentButton);
        updateStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateStudent(studentNumber);
            }
        });

        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent(studentNumber);
            }
        });
    }

    public void updateStudent(String studentNumber) {
        studentNumberEditText = findViewById(R.id.studentNumber);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        courseEditText = findViewById(R.id.course);
        ageEditText = findViewById(R.id.age);
        addressEditText = findViewById(R.id.address);

        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String course = courseEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String age = ageEditText.getText().toString();

        db = new DatabaseManager(this);
        db.updateRecordStudent(studentNumber, firstName, lastName, gender, course, age, address);

        Toast.makeText(Update_Student_Form.this, "Update Successfull", Toast.LENGTH_SHORT).show();
    }

    public void deleteStudent(String studentNumber) {
        db = new DatabaseManager(this);
        db.deleteStudentRecord(studentNumber);
        Toast.makeText(Update_Student_Form.this, "Delete Successfull", Toast.LENGTH_SHORT).show();
    }

}