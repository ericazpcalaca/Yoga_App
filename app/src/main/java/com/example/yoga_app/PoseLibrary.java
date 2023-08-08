package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PoseLibrary extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RequestQueue requestQueue;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_library);

        toolbar = findViewById(R.id.toolbarPoseLibrary);

        //Set a back to main page button on top
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24);
        toolbar.setTitle("Poses");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        if(YogaPosesManager.getInstance().getNumberOfPoses() == 0){
            fetchYogaPose();
        }
        adapter = new RecyclerAdapter(PoseLibrary.this);
        recyclerView.setAdapter(adapter);
    }

    private void fetchYogaPose() {
        String url = "https://yoga-api-nzy4.onrender.com/v1/poses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            String poseName = jsonObject.getString("english_name");
                            String poseDescription = jsonObject.getString("pose_description");
                            String poseBenefits = jsonObject.getString("pose_benefits");
                            String urlImage = jsonObject.getString("url_png");
                            YogaPose yogaPose = new YogaPose(poseName, poseDescription, poseBenefits, urlImage);
                            YogaPosesManager.getInstance().addPose(yogaPose);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Handle error
                        Toast.makeText(PoseLibrary.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}