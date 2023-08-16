package com.example.yoga_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

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
                showDialogBtnAge("age");
                getDataFromFirebase();
            }
        });

        btnCurWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBtnWeight("current");
                getDataFromFirebase();
            }
        });

        btnTargWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBtnWeight("target");
                getDataFromFirebase();

            }
        });

        btnHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBtnAge("height");
                getDataFromFirebase();
            }
        });

        btnGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogGender();
                getDataFromFirebase();
            }
        });

    }

    private void showDialogGender() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
            builder.setTitle("Insert your gender");

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_radiobtn, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroupGender);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                switch (selectedRadioButtonId){
                    case R.id.female:
                        userRef.child("userGender").setValue("Female");
                        break;
                    case R.id.male:
                        userRef.child("userGender").setValue("Male");
                        break;
                    case R.id.noBinary:
                        userRef.child("userGender").setValue("No-binary");
                        break;
                    case R.id.notSay:
                        userRef.child("userGender").setValue("Prefer not to say");
                        break;
                    default:
                        break;
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showDialogBtnWeight(String userWeight){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        if(userWeight.equals("current")){
            builder.setTitle("Current weight in kg");
        } else if (userWeight.equals("target")){
            builder.setTitle("Target weight in kg");
        }

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_field, null);
        builder.setView(dialogView);

        EditText updateInfo = dialogView.findViewById(R.id.updateInfo);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double weight = isNumberDouble(updateInfo.getText().toString());
                if (weight >= 0) { // Check if it's a valid number
                    if(userWeight.equals("current")){
                        userRef.child("currentWeight").setValue(weight);
                    }else if(userWeight.equals("target")){
                        userRef.child("targetWeight").setValue(weight);
                    }
                }else{
                    Toast.makeText(AccountSettings.this,"Please insert a number",Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDialogBtnAge(String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        if(type.equals("age")){
            builder.setTitle("Insert your age");
        } else if(type.equals("height")){
            builder.setTitle("Insert your height");
        }

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_field, null);
        builder.setView(dialogView);

        EditText updateInfo = dialogView.findViewById(R.id.updateInfo);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int isThisANumber = isNumber(updateInfo.getText().toString());
                if (isThisANumber >= 0) { // Check if it's a valid number
                    if(type.equals("age")){
                        userRef.child("userAge").setValue(isThisANumber);
                    } else if(type.equals("height")){
                        userRef.child("userHeight").setValue(isThisANumber);
                    }
                }else{
                    Toast.makeText(AccountSettings.this,"Please insert a number",Toast.LENGTH_SHORT).show();
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

    private double isNumberDouble(String toString) {
        try {
            double number = Double.parseDouble(toString);
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