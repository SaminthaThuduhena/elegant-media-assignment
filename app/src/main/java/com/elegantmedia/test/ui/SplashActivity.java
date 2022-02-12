package com.elegantmedia.test.ui;

import android.os.Bundle;

import com.elegantmedia.test.ui.auth.AuthenticationActivity;
import com.elegantmedia.test.R;
import com.elegantmedia.test.base.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;

//This activity decides to login or direct to hotels to list if already logged in
@AndroidEntryPoint
public class SplashActivity extends BaseActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mFirebaseAuth = FirebaseAuth.getInstance();

        activityToActivity(mFirebaseAuth.getCurrentUser() == null ? AuthenticationActivity.class
                : MainActivity.class);
        SplashActivity.this.finish();
    }
}