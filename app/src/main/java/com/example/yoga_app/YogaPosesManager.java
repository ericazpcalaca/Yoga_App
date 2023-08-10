package com.example.yoga_app;

import java.util.ArrayList;

public class YogaPosesManager {

    private ArrayList<YogaPose> poseList;
    private ArrayList<YogaCategories> categoriesList;

    private static YogaPosesManager instance;

    // Private constructor to prevent instantiation from outside
    private YogaPosesManager() {
        poseList = new ArrayList<>();
        categoriesList = new ArrayList<>();
    }

    // Static method to get the Singleton instance
    public static YogaPosesManager getInstance() {
        if (instance == null) {
            instance = new YogaPosesManager();
        }
        return instance;
    }

    public void addPose(YogaPose pose){
        poseList.add(pose);
    }

    public void addCategories(YogaCategories category){
        categoriesList.add(category);
    }

    public YogaPose getYogaPoseByIndex(int index){
        return poseList.get(index);
    }

    public YogaCategories getYogaCategoryByIndex(int index){
        return categoriesList.get(index);
    }

    public ArrayList<Integer> getPoseList(int index){
        return categoriesList.get(index).getPosesID();
    }
    public int getNumberOfPoses(){
        return poseList.size();
    }

    public int getNumberOfCategories(){
        return categoriesList.size();
    }
}

