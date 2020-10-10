package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Activate_page extends AppCompatActivity {

    private Button Logout ;
    FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_page);
        getSupportActionBar().hide() ;

        Logout = (Button)findViewById(R.id.SignOut_btn) ;
        mAuth = FirebaseAuth.getInstance() ;

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                //AuthUI.getInstance().signOut(this) ;
                startActivity(new Intent(Activate_page.this, MainActivity.class));
                finish();
            }
        });

    }
}