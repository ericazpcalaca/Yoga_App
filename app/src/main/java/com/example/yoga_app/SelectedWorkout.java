package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SelectedWorkout extends AppCompatActivity {

    private int typeofWorkout;
    private TextView workOutTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_workout);

        Intent intent = getIntent();
        typeofWorkout = intent.getIntExtra("type",-1);

        workOutTitle = findViewById(R.id.workOutTitle);
        workOutTitle.setText("O work out eh " + typeofWorkout);


    }
}