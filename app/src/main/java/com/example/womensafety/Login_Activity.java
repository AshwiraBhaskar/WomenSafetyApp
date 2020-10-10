package com.example.womensafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {

    private Button login_t ;
    private EditText mail ;
    private EditText password ;
    private FirebaseAuth firebaseAuth ;
    private Button register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide() ;

        login_t = (Button)findViewById(R.id.login) ;
        mail = (EditText)findViewById(R.id.email_id) ;
        password = (EditText)findViewById(R.id.pass);
        register = (Button)findViewById(R.id.register_btn) ;

        firebaseAuth = FirebaseAuth.getInstance() ;

        login_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim() ;
                String pass = password.getText().toString().trim() ;

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login_Activity.this, "Please enter mail", Toast.LENGTH_LONG).show() ;
                    return ;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Login_Activity.this, "Please enter your password" , Toast.LENGTH_LONG).show() ;
                    return ;
                }


                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_Activity.this, "Login Successfull", Toast.LENGTH_SHORT).show() ;
                            startActivity(new Intent(getApplicationContext(), Activate_page.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show() ;
                        }


                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Register_Activity.class));

            }
        });

    }
}