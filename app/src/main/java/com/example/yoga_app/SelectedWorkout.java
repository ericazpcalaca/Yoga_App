package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Locale;

public class SelectedWorkout extends AppCompatActivity {

    private int typeofWorkout;
    private Button btnStart;
    private ImageButton btnSetTimer;
    private TextView workOutTitle;
    private TextView workOutDesc;
    private TextView minutesForSelected;
    private androidx.appcompat.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private int totalMin = 0;
    private final int DEFAULT_TIME = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_workout);

        //Set a back to main page button on top
        toolbar = findViewById(R.id.toolbarSelectedWorkout);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        workOutTitle = findViewById(R.id.workOutTitle);
        workOutDesc = findViewById(R.id.txtDescWorkout);
        minutesForSelected = findViewById(R.id.timeWorkout);

        //Get the number of the workout selected by the user
        Intent intent = getIntent();
        typeofWorkout = intent.getIntExtra("type", -1);

        recyclerView = findViewById(R.id.posesWorkoutRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataRetriever dataRetriever = new DataRetriever(this);
        YogaCategories yogaCategory = YogaPosesManager.getInstance().getYogaCategoryByIndex(typeofWorkout);
        workOutTitle.setText(yogaCategory.getNameCategory());
        workOutDesc.setText(yogaCategory.getDescriptionCategory());

        adapter = new RecyclerAdapterSimpleList(SelectedWorkout.this,YogaPosesManager.getInstance().getPoseList(typeofWorkout));
        recyclerView.setAdapter(adapter);

        convertTime(DEFAULT_TIME);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedWorkout.this, Workout.class);
                intent.putExtra("workoutID",typeofWorkout);
                startActivity(intent);
            }
        });

        btnSetTimer = findViewById(R.id.btnSetTimer);
        btnSetTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionDialog();
            }
        });
    }
    
    private void showOptionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomAlertDialog);
        builder.setTitle("Workout Settings");

        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_timer, null);
        builder.setView(dialogView);

        final RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroup);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                switch (selectedRadioButtonId){
                    case R.id.option1:
                        convertTime(30);
                        break;
                    case R.id.option2:
                        convertTime(45);
                        break;
                    case R.id.option3:
                        convertTime(60);
                        break;
                    case R.id.option4:
                        convertTime(75);
                        break;
                    case R.id.option5:
                        convertTime(90);
                        break;
                    case R.id.option6:
                        convertTime(120);
                        break;
                    case R.id.option7:
                        convertTime(180);
                        break;
                    case R.id.option8:
                        convertTime(240);
                        break;
                    case R.id.option9:
                        convertTime(300);
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

    private void convertTime(int time) {
        totalMin = YogaPosesManager.getInstance().getPoseList(typeofWorkout).size() * time;

        int minutes = totalMin / 60;
        int seconds = totalMin % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        minutesForSelected.setText(timeLeftFormatted);
    }

}