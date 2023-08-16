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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private TextView btnRegister;
    private TextView btnRecovery;
    private TextView txtNotification;
    private TextView txtNotificationPassword;
    private EditText textEmail;
    private EditText textPassword;
    private Button btnLogin;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.signUp);
        btnRecovery = findViewById(R.id.resetPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textEmail = findViewById(R.id.inputEmail);
        textPassword = findViewById(R.id.inputPassword);
        txtNotification = findViewById(R.id.txtNotification);
        txtNotificationPassword = findViewById(R.id.txtNotificationPassword);

        DataRetriever dataRetriever = new DataRetriever(this);

        //Open the Register activity
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        //Open the Login Activity
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String password;
                email = textEmail.getText().toString();
                password = textPassword.getText().toString();

                //Check if the fields aren't empty
                //Display message error accordingly
                if (email.isEmpty()) {
                    txtNotification.setVisibility(View.VISIBLE);
                    txtNotification.setText("Please, enter the e-mail");
                    return;
                } else {
                    txtNotification.setVisibility(View.GONE);
                }

                if (password.isEmpty()) {
                    txtNotificationPassword.setVisibility(View.VISIBLE);
                    txtNotificationPassword.setText("Please, enter the password");
                    return;
                } else {
                    txtNotification.setVisibility(View.GONE);
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    txtNotificationPassword.setVisibility(View.VISIBLE);
                                    txtNotificationPassword.setText("Incorrect username or password.");
                                }
                            }
                        });
            }
        });

        //Open the Recovery Password Activity
        btnRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Recovery.class));
            }
        });
    }
}