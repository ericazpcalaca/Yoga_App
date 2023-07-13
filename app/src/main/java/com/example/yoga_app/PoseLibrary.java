package com.example.yoga_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PoseLibrary extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private RequestQueue requestQueue;
    private ArrayList<YogaPose> poseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pose_library);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        poseList = new ArrayList<>();
        fetchMovie();
    }

    private void fetchMovie() {
        String url = "https://yoga-api-nzy4.onrender.com/v1/poses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject jsonObject = response.getJSONObject(i);
                            String poseName = jsonObject.getString("english_name");
                            String urlImage = jsonObject.getString("url_png");
                            YogaPose yogaPose = new YogaPose(poseName, urlImage);
                            poseList.add(yogaPose);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter = new RecyclerAdapter(PoseLibrary.this, poseList);
                    recyclerView.setAdapter(adapter);

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Handle error
                        Toast.makeText(PoseLibrary.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, response -> {
//                    try {
//                        JSONArray jsonArray = response.getJSONArray("0");
//
//
//                        Toast.makeText(PoseLibrary.this, "Entroooo", Toast.LENGTH_LONG).show();
//
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            String title = jsonObject.getString("english_name");
//                            String imgUrl = jsonObject.getString("url_png");
//
//                            YogaPose yogaPose = new YogaPose(title, imgUrl);
//                            poseList.add(yogaPose);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    adapter = new RecyclerAdapter(PoseLibrary.this, poseList);
//                    recyclerView.setAdapter(adapter);
//
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //TODO: Handle error
//                        Toast.makeText(PoseLibrary.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//        requestQueue.add(jsonObjectRequest);
    }
}