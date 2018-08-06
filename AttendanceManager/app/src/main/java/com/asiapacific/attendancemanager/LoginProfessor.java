package com.asiapacific.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginProfessor extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    //Declaring Google API Client for Google Sign In Service
    private GoogleApiClient googleApiClient;

    private GoogleSignInOptions googleSignInOptions;

    //Declaring Google Sign In Button
    private SignInButton signInButton;

    //Request code for API Client
    private final int REQ_CODE =  1001;



     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        signInButton = (SignInButton) findViewById(R.id.googleSignInButton);

        signInButton.setOnClickListener(this);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleSignInButton:
                signInToGoogle();
                break;
        }
    }

    private void signInToGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, REQ_CODE);
    }

    private void holdGoogleResult(GoogleSignInResult googleSignInResult) {
        if(googleSignInResult.isSuccess()) {
            GoogleSignInAccount googleAccount = googleSignInResult.getSignInAccount();
            String name = googleAccount.getDisplayName();
            String email = googleAccount.getEmail();

             //Initializing shared preferences that will use for storing professors information.
            SharedPreferences.Editor sharedPreferencesProfessorGoogleInfo = getSharedPreferences("AMS_PREFERENCE",MODE_PRIVATE).edit();

            sharedPreferencesProfessorGoogleInfo.putString("NAME", name);
            sharedPreferencesProfessorGoogleInfo.putString("EMAIL", email);
            sharedPreferencesProfessorGoogleInfo.apply();

            Intent intent = new Intent(this, ProfMainActivity.class);

            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            holdGoogleResult(googleSignInResult);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Override back button.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Login.class));
        finish();
    }
}
