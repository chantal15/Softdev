package com.asiapacific.attendancemanager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProfStudentsList extends AppCompatActivity {
    //Declaring Views
    RecyclerView studentRecyclerView;
    StudentAdapter studentAdapter;
    TextView textViewClassName, textViewTotalStudents ;

    //Student List object
    List<Student> studentList;

    //Firebase Database
    DatabaseReference mDatabase;

    //Bundle
    Bundle bundle;

    //Chosen access code
    String accessCode;

    //Progress Dialog
    ProgressDialog progressDialog;

    String professorsEmail;
    String professorsName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_students_list);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(this);

        //Initializing List
        studentList = new ArrayList<>();

        //Initializing Recycler View
        studentRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStudents);
        studentRecyclerView.setHasFixedSize(false);
        studentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewClassName = (TextView) findViewById(R.id.textViewClassName);
        textViewTotalStudents = (TextView) findViewById(R.id.textViewTotalStudents);

        //Initializing bundle content from an intent
        bundle = getIntent().getExtras();

        setupUI();
    }

    private void setupUI() {
        String className =bundle.getString("CLASS_NAME");
        String totalStudents = bundle.getString("TOTAL_STUDENTS");
       textViewClassName.setText(className);
       textViewTotalStudents.setText(String.format("Total: %s", totalStudents));
    }


    @Override
    protected void onStart() {
        super.onStart();
        showProgressDialog("Loading Classes...");
        accessCode = bundle.getString("ACCESS_CODE");
        Query retrieveStudents = mDatabase.child("Classes").child(accessCode).child("Students");

        retrieveStudents.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot studentSnapshot : dataSnapshot.getChildren()) {
                    String key = studentSnapshot.getKey().toString();
                    if(!key.equals("totalStudents")) {
                        Student student = studentSnapshot.getValue(Student.class);
                        studentList.add(student);
                        //Setting Adapter
                        studentAdapter = new StudentAdapter(ProfStudentsList.this, studentList);
                        studentRecyclerView.setAdapter(studentAdapter);
                    }
                }
                dismissProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void showProgressDialog(String message) {
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

}

   