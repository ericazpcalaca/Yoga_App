package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Workout extends AppCompatActivity {

    private TextView teste;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        Intent intent = getIntent();
        int typeofWorkout = intent.getIntExtra("workoutID", -1);

        teste = findViewById(R.id.teste);
//        ArrayList<Integer> testando = YogaPosesManager.getInstance().getPoseList(typeofWorkout);
//        String hm = "";
//        for(int i = 0; i < testando.size(); i++){
//            hm += " " + testando.get(i);
//        }
        teste.setText("opa "+ typeofWorkout);
    }
}