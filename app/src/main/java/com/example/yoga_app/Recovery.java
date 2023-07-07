package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;

public class Recovery extends AppCompatActivity {

    private Button btnReset;
    private EditText txtEmail;
    private FirebaseAuth mAuth;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recovery);

        txtEmail = findViewById(R.id.rEmail);
        btnReset = findViewById(R.id.btnRecoverPassword);
        toolbar = findViewById(R.id.toolbarRecovery);
        mAuth = FirebaseAuth.getInstance();

        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Send an email to the user to recovery the password
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Password reset email sent successfully
                                // Display a success message to the user or redirect to another screen
                                Toast.makeText(Recovery.this,"Email sent",Toast.LENGTH_SHORT).show();
                            } else {
                                try {
                                    throw task.getException();
                                } catch (FirebaseAuthInvalidUserException e) {
                                    // User does not exist
                                    Toast.makeText(Recovery.this,"User doesn't exist",Toast.LENGTH_SHORT).show();
                                } catch (FirebaseAuthActionCodeException e) {
                                    // Invalid action code
                                } catch (FirebaseAuthRecentLoginRequiredException e) {
                                    // Recent login required
                                } catch (Exception e) {
                                    // Other errors
                                }
                            }
                        });
            }
        });
    }
}