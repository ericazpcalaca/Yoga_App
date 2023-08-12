package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountSettings extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private Button btnAge;
    private Button btnCurWeight;
    private Button btnTargWeight;
    private Button btnHeight;
    private Button btnGender;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private FirebaseUser userId;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser();
        userRef = database.getReference("users").child(userId.getUid());

        //Set a back to main page button on top
        toolbar = findViewById(R.id.toolbarAccountSettings);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnAge = findViewById(R.id.userAge);
        btnCurWeight = findViewById(R.id.userCurrentWeight);
        btnTargWeight = findViewById(R.id.userTargetWeight);
        btnHeight = findViewById(R.id.userHeight);
        btnGender = findViewById(R.id.userGender);
        getDataFromFirebase();


        btnAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("Insert your age");
                getDataFromFirebase();
            }
        });

    }

    private void showDialog(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setTitle(title);

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_field, null);
        builder.setView(dialogView);

        EditText updateInfo = dialogView.findViewById(R.id.updateInfo);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int isAgeANumber = isNumber(updateInfo.getText().toString());
                if (isAgeANumber >= 0) { // Check if it's a valid number
                    userRef.child("userAge").setValue(isAgeANumber);
                }

            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int isNumber(String toString) {
        try {
            int number = Integer.parseInt(toString);
            return number;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void getDataFromFirebase(){
        // Attach a listener to the "users" node
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    btnAge.setText(String.valueOf(user.getUserAge()));
                    btnCurWeight.setText(String.valueOf(user.getCurrentWeight()));
                    btnTargWeight.setText(String.valueOf(user.getTargetWeight()));
                    btnHeight.setText(String.valueOf(user.getUserHeight()));
                    btnGender.setText(user.getUserGender());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                btnAge.setText(error.getMessage());
            }
        });
    }
}