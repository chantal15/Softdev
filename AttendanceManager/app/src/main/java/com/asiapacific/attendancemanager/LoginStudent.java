package com.asiapacific.attendancemanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginStudent extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    //Declaring Google API Client for Google Sign In Service
    private GoogleApiClient googleApiClient;

    private GoogleSignInOptions googleSignInOptions;

    //Declaring Google Sign In Button
    private SignInButton signInButton;

    //Request code for API Client
    private final int REQ_CODE =  1001;


        }
    }

}
