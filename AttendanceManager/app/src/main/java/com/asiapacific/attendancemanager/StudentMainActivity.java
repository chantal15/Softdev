package com.asiapacific.attendancemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentMainActivity extends AppCompatActivity {

    //Google APT Client
    GoogleApiClient googleApiClient;
    GoogleSignInOptions googleSignInOptions;

    //Declaring users information
    String displayName;
    String emailAddress;

    //List
    List<Class> classList;

    //Object
    Student student;

    //Getting information from intents
    Bundle bundle;

    //Declaring views
    TextView textViewJoinClass, textViewDisplayName, textViewSignOut;
    EditText editTextAccessCode;
    Button buttonJoin, buttonCancel;
    RecyclerView recyclerViewJoinedClass;

    //Declaring class adapter
    JoinedClassAdapter joinedClassAdapter;

    //Firebase Database Instance
    DatabaseReference mDatabase;

    //Progress Dialog
    ProgressDialog progressDialog;

    
    }
}
