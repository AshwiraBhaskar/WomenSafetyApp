package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public Button login ;
    public Button register ;
    FirebaseUser fUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide() ;
        fUser = FirebaseAuth.getInstance().getCurrentUser() ;
        if(fUser != null)
        {
            startActivity(new Intent(MainActivity.this, Activate_page.class));
            finish();
        }



        login = (Button)findViewById(R.id.elogin) ;
        register = (Button)findViewById(R.id.eregister) ;

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, Register_Activity.class) ;
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login_Activity.class) ;
                startActivity(intent);
            }
        });


    }
}