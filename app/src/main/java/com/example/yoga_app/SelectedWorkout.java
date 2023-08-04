package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SelectedWorkout extends AppCompatActivity {

    private int typeofWorkout;
    private TextView workOutTitle;
    private TextView workOutDesc;
    private ArrayList<YogaPose> poseList;
    private RequestQueue requestQueue;
    private androidx.appcompat.widget.Toolbar toolbar;

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

        Intent intent = getIntent();
        typeofWorkout = intent.getIntExtra("type", -1);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        fetchYogaWorkout(typeofWorkout);


        poseList = new ArrayList<>();

    }

    private void fetchYogaWorkout(int poseNumber) {
        String url = "https://yoga-api-nzy4.onrender.com/v1/categories";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONObject jsonObject = response.getJSONObject(poseNumber);
                        String categoryName = jsonObject.getString("category_name");
                        String categoryDescription = jsonObject.getString("category_description");
                        
                        workOutTitle.setText(categoryName);
                        workOutDesc.setText(categoryDescription);

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