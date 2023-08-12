package com.example.yoga_app;

import android.content.Context;

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

public class DataRetriever {

    private RequestQueue requestQueue;

    public DataRetriever(Context context) {
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();

        //Retrieve yoga poses
        if (YogaPosesManager.getInstance().getNumberOfPoses() == 0) {
            checkDifficulty("beginner");
            checkDifficulty("intermediate");
            checkDifficulty("expert");
        }

        //Retrieve yoga categories
        if (YogaPosesManager.getInstance().getNumberOfCategories() == 0) {
            fetchAllYogaWorkout();
        }
    }

    private void fetchAllYogaWorkout() {
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
                                int poseId;
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
//                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void checkDifficulty(String level) {

        String url = "https://yoga-api-nzy4.onrender.com/v1/poses?level=" + level;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("poses");

                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject jsonObjectPose = jsonArray.getJSONObject(j);
                            int poseId = jsonObjectPose.getInt("id");
                            String poseName = jsonObjectPose.getString("english_name");
                            String poseDescription = jsonObjectPose.getString("pose_description");
                            String poseBenefits = jsonObjectPose.getString("pose_benefits");
                            String urlImage = jsonObjectPose.getString("url_png");
                            YogaPose yogaPose = new YogaPose(poseId, poseName, poseDescription, poseBenefits, urlImage, level);
                            YogaPosesManager.getInstance().addPose(yogaPose);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Handle error
//                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void checkDifficultyLevel(String level) {

        String url = "https://yoga-api-nzy4.onrender.com/v1/poses?level=" + level;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("poses");

                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject jsonObjectPose = jsonArray.getJSONObject(j);
                            int poseId = jsonObjectPose.getInt("id");
                            String poseName = jsonObjectPose.getString("english_name");
                            String poseDescription = jsonObjectPose.getString("pose_description");
                            String poseBenefits = jsonObjectPose.getString("pose_benefits");
                            String urlImage = jsonObjectPose.getString("url_png");
                            YogaPose yogaPose = new YogaPose(poseId, poseName, poseDescription, poseBenefits, urlImage, level);
                            YogaPosesManager.getInstance().addPose(yogaPose);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //TODO: Handle error
//                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }
}
