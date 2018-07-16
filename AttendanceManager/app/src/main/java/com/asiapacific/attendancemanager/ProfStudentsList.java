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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_students_list);

        //Initializing List
        studentList = new ArrayList<>();

        //Initializing Recycler View
        studentRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStudents);
        studentRecyclerView.setHasFixedSize(false);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Initializing bundle content from an intent
        bundle = getIntent().getExtras();

//        addStudents();
//        setupAdapter();
    }

    //Static data for testing purposes.
//    private void addStudents() {
//        studentList.add(new Student(
//                "Chantal Saldivar",
//                "chantalsaldivar@gmail.com",
//                null,
//                null
//        ));
//    }




    }
}
