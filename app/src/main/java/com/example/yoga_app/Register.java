package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private TextView btnRegister;
    private EditText textEmail;
    private EditText textPassword;
    private FirebaseAuth mAuth;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        textEmail = findViewById(R.id.inputEmail);
        textPassword = findViewById(R.id.inputPassword);
        btnRegister = findViewById(R.id.btnRegister);
        toolbar = findViewById(R.id.toolbarRegister);

        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //Register the user using firebase
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String password;
                email = textEmail.getText().toString();
                password = textPassword.getText().toString();

                //Check if the fields aren't empty
                // MAIS TARDE COLOCAR EM TEXT VIEW
                if(email.isEmpty()){
                    Toast.makeText(Register.this,"Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.isEmpty()){
                    Toast.makeText(Register.this,"Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Firebase code to create new account
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Register.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}