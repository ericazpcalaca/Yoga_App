package com.example.yoga_app;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataRetriever {

    private RequestQueue requestQueue;

    public DataRetriever(Context context){
        requestQueue = VolleySingleton.getInstance(context).getRequestQueue();

        //Retrieve yoga poses
        if (YogaPosesManager.getInstance().getNumberOfPoses() == 0) {
            fetchAllYogaPose();
        }

        //Retrieve yoga categories
        if (YogaPosesManager.getInstance().getNumberOfCategories() == 0) {
            fetchAllYogaWorkout();
        }
    }

    private void fetchAllYogaPose() {
        String url = "https://yoga-api-nzy4.onrender.com/v1/poses";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int poseId = jsonObject.getInt("id");
                            String poseName = jsonObject.getString("english_name");
                            String poseDescription = jsonObject.getString("pose_description");
                            String poseBenefits = jsonObject.getString("pose_benefits");
                            String urlImage = jsonObject.getString("url_png");
                            YogaPose yogaPose = new YogaPose(poseId, poseName, poseDescription, poseBenefits, urlImage);
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
        requestQueue.add(jsonArrayRequest);
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

    private void checkDifficulty(String level, int categoryID) {
        String url = "https://yoga-api-nzy4.onrender.com/v1/poses?level="+ level;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {
                    try {
                            JSONObject jsonObject = response.getJSONObject(0);
                            ArrayList<Integer> poseIDList = new ArrayList<>();
                            try {
                                JSONArray jsonArray = jsonObject.getJSONArray("poses");
                                int poseId = -1;
                                for (int j = 0; j < jsonArray.length(); j++) {
                                    JSONObject jsonObjectPose = jsonArray.getJSONObject(j);
                                    poseId = jsonObjectPose.getInt("id");
                                    String difficultyLevel = jsonObjectPose.getString("difficulty_level");
//                                    YogaPose yogaPose
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
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
}
