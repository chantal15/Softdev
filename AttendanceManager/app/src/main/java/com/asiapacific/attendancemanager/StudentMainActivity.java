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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        //Getting data from intent
        bundle = getIntent().getExtras();

        textViewJoinClass = (TextView) findViewById(R.id.textViewJoinClass);
        textViewDisplayName = (TextView) findViewById(R.id.textViewDisplayName);
        textViewSignOut = (TextView) findViewById(R.id.textViewSignOut);

        recyclerViewJoinedClass = (RecyclerView) findViewById(R.id.recyclerViewJoinedClasses);
        recyclerViewJoinedClass.setHasFixedSize(false);
        recyclerViewJoinedClass.setLayoutManager(new LinearLayoutManager(this));

        classList = new ArrayList<>();

        textViewJoinClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(StudentMainActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_join_class, null);

                editTextAccessCode = (EditText) dialogView.findViewById(R.id.editTextAccessCode);

                buttonJoin = (Button) dialogView.findViewById(R.id.buttonJoin);
                buttonCancel = (Button) dialogView.findViewById(R.id.buttonCancel);

                dialogBuilder.setView(dialogView);
                final AlertDialog joinClassDialog = dialogBuilder.create();
                joinClassDialog.show();

                buttonJoin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String accessCode = editTextAccessCode.getText().toString().trim();
                        if(!accessCode.equals("") )
                        {
                            joinClass(accessCode);
                            joinClassDialog.dismiss();
                        }
                        else
                        {
                            editTextAccessCode.setError("Required Field");
                            editTextAccessCode.setFocusable(true);
                        }

                    }
                });

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        joinClassDialog.dismiss();
                    }
                });

            }
        });

        textViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        startActivity(new Intent(StudentMainActivity.this, LoginStudent.class));
                    }
                });
            }
        });

        displayName = bundle.getString("NAME");
        emailAddress = bundle.getString("EMAIL");

        student = new Student(displayName, emailAddress, null, null);

        setupUI();
    }

    
    }
}
