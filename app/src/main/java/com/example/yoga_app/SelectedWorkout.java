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
//    private ArrayList<YogaPose> poseList;
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

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        //Check if the categories are filled up
        if (YogaPosesManager.getInstance().getNumberOfCategories() == 0) {
            fetchYogaWorkout();
        }

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

    private void fetchYogaWorkout() {
        String url = "https://yoga-api-nzy4.onrender.com/v1/categories";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int categoryID = jsonObject.getInt("id");
                            String categoryName = jsonObject.getString("category_name");
                            String categoryDescription = jsonObject.getString("category_description");
                            ArrayList<Integer> poseIDList = new ArrayList<>();
                            try {
                                JSONArray jsonArray = jsonObject.getJSONArray("poses");
                                int poseId = -1;
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObjectPose = jsonArray.getJSONObject(j);
                                    poseId = jsonObjectPose.getInt("id");
                                    poseIDList.add(poseId);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            YogaCategories yogaCategory = new YogaCategories(categoryID, categoryName, categoryDescription, poseIDList);
                            YogaPosesManager.getInstance().addCategories(yogaCategory);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Handle error
                        Toast.makeText(SelectedWorkout.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}