package com.example.womensafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register_Activity extends AppCompatActivity {

    private Button register ;
    private EditText mail ;
    private EditText password ;
    private EditText name ;
    private EditText phone ;
    private Button LoginPage ;

    String userID ;
    FirebaseFirestore fstore ;

    FirebaseAuth firebaseauth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide() ;

        register = (Button)findViewById(R.id.register_btn) ;
        mail = (EditText)findViewById(R.id.email_id) ;
        password = (EditText) findViewById(R.id.pass) ;
        name = (EditText) findViewById(R.id.Name_id) ;
        phone = (EditText) findViewById(R.id.Phone_id) ;
        LoginPage = (Button)findViewById(R.id.login_btn) ;


        firebaseauth = FirebaseAuth.getInstance() ;
        fstore = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mail.getText().toString().trim() ;
                String pass = password.getText().toString().trim() ;
                final String fname = name.getText().toString() ;
                final String mob = phone.getText().toString() ;

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register_Activity.this, "Please enter mail", Toast.LENGTH_SHORT).show() ;
                    return ;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(Register_Activity.this, "Please enter your password" , Toast.LENGTH_SHORT).show() ;
                    return ;
                }

                firebaseauth.createUserWithEmailAndPassword(email, pass ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(Register_Activity.this, "registered successfully" , Toast.LENGTH_SHORT).show();
                            userID = firebaseauth.getCurrentUser().getUid() ;
                            DocumentReference documentReference = fstore.collection("users").document(userID) ;
                            Map<String, Object> user = new HashMap<>() ;

                            user.put("Name" , fname) ;
                            user.put("Email" , email) ;
                            user.put("Phone no." , mob) ;

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register_Activity.this, "user profile is created " , Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register_Activity.this, "Failure " , Toast.LENGTH_SHORT).show();
                                }
                            }) ;
                            startActivity(new Intent(getApplicationContext(), Activate_page.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(Register_Activity.this, "error" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
        LoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                finish();
            }
        });


    }




}