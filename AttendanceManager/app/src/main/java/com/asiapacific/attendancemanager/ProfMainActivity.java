package com.asiapacific.attendancemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class ProfMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    //Google APT Client
    GoogleApiClient googleApiClient;
    GoogleSignInOptions googleSignInOptions;

    boolean pressedOnce = true;
    boolean inClassesUI;

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

            }
        }).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

        inClassesUI = true;

        fragment = new FragmentProfClasses();

        loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if(inClassesUI) {
            if(pressedOnce) {
                Toast.makeText(this, "Please click once more to exit", Toast.LENGTH_SHORT).show();
                pressedOnce = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pressedOnce = true;
                    }
                }, 1000);
            } else {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
//                        startActivity(new Intent(getApplicationContext(), LoginStudent.class));
                        finish();
                    }
                });
            }
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_classes:
                inClassesUI = true;
                fragment = new FragmentProfClasses();
                break;
            case R.id.navigation_calendar:
                inClassesUI = false;
                fragment = new FragmentProfAttendance();
                break;
            case R.id.navigation_reports:
                inClassesUI = false;
                fragment = new FragmentProfReports();
                break;
//            case R.id.navigation_students:
//                fragment = new FragmentProfStudents();
//                break;
        }
        return loadFragment(fragment);
    }


}
