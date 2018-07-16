package com.asiapacific.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void openProfessorLogin(View view) {
        //This will open Professor's login page.
        Intent professorLoginIntent = new Intent(this, LoginProfessor.class);
        startActivity(professorLoginIntent);
    }

    public void openStudentLogin(View view) {
        //This will open student's login page.
        Intent studentLoginIntent = new Intent(this, LoginStudent.class);
        startActivity(studentLoginIntent);
    }

}
