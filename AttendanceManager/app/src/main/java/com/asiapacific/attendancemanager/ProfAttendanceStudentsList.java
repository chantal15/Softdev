package com.asiapacific.attendancemanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProfAttendanceStudentsList extends AppCompatActivity {
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

    Button buttonRecord;


	}