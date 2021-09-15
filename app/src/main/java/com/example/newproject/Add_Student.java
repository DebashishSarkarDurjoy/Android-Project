package com.example.newproject;
import com.example.newproject.DBHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_Student extends AppCompatActivity {
    EditText studentNumberEditText, firstNameEditText, lastNameEditText, courseEditText, addressEditText, ageEditText;
    Spinner genderSpinner;
    Button addStudentButton;

    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        studentNumberEditText = (EditText) findViewById(R.id.studentNumber);
        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
        courseEditText = (EditText) findViewById(R.id.course);
        ageEditText = (EditText) findViewById(R.id.age);
        addressEditText = (EditText) findViewById(R.id.address);
        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);

        addStudentButton = (Button) findViewById(R.id.addStudentButton);

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent(v);
            }
        });

    }

    public void addStudent(View v) {
        String sNumberString = studentNumberEditText.getText().toString();
//        int sNumber = Integer.parseInt(sNumberString);
        String fName = firstNameEditText.getText().toString();
        String lName = lastNameEditText.getText().toString();
        String course = courseEditText.getText().toString();
        String address = addressEditText.getText().toString();
        String ageString = ageEditText.getText().toString();
//        int age = Integer.parseInt(ageString);
        String gender = genderSpinner.getSelectedItem().toString();

        db = new DatabaseManager(this);

        boolean successInsertion = db.insertDataToStudents(sNumberString, fName, lName, gender, course, ageString, address);

        if (successInsertion == true) {
            String student = sNumberString + fName + lName + course + address + ageString + gender;
            Toast.makeText(Add_Student.this, student, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Add_Student.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}