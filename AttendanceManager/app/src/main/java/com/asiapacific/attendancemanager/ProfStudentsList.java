package com.asiapacific.attendancemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfStudentsList extends AppCompatActivity {
    //Declaring Views
    RecyclerView studentRecyclerView;
    StudentAdapter studentAdapter;

    //Student List object
    List<Student> studentList;

    //Firebase Database
    DatabaseReference databaseStudents;

    //Bundle
    Bundle bundle;

    //Chosen access code
    String accessCode;


    }
}
