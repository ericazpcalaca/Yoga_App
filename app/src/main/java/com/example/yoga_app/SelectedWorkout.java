package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectedWorkout extends AppCompatActivity {

    private int typeofWorkout;
    private Button btnStart;
    private TextView workOutTitle;
    private TextView workOutDesc;
    private RequestQueue requestQueue;
    private androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_workout);

        toolbar = findViewById(R.id.toolbarSelectedWorkout);
        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        workOutTitle = findViewById(R.id.workOutTitle);
        workOutDesc = findViewById(R.id.txtDescWorkout);

        //Get the number of the workout selected by the user
        Intent intent = getIntent();
        typeofWorkout = intent.getIntExtra("type", -1);

        recyclerView = findViewById(R.id.posesWorkoutRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataRetriever dataRetriever = new DataRetriever(this);

        YogaCategories yogaCategory = YogaPosesManager.getInstance().getYogaCategoryByIndex(typeofWorkout);
        workOutTitle.setText(yogaCategory.getNameCategory());
        workOutDesc.setText(yogaCategory.getDescriptionCategory());

        adapter = new RecyclerAdapterSimpleList(SelectedWorkout.this, YogaPosesManager.getInstance().getPoseList(typeofWorkout));
        recyclerView.setAdapter(adapter);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedWorkout.this, Workout.class);
                intent.putExtra("workoutID",typeofWorkout);
                startActivity(intent);
            }
        });
    }


}