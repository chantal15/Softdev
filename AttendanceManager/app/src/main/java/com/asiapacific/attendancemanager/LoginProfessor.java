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

public class LoginProfessor extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void goToMainActivity(View view) {
//        if(authenticated()) {
            //This will open Professor's activity activity.
            Intent intent = new Intent(this, ProfMainActivity.class);
            startActivity(intent);
//        }
    }

     //Incomplete
    public boolean authenticated() {
        if(editTextUsername.getText().equals("admin") && editTextPassword.getText().equals("admin")) {
            Toast.makeText(this, editTextUsername.getText()+" "+editTextPassword.getText(), Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }
}
