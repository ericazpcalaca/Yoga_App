package com.example.yoga_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YogaPosesManager {

    private HashMap<Integer, YogaPose> poseList;
    private HashMap<Integer, YogaCategories> categoriesList;

    private static YogaPosesManager instance;

    // Private constructor to prevent instantiation from outside
    private YogaPosesManager() {
        poseList = new HashMap<>();
        categoriesList = new HashMap<>();
    }

    // Static method to get the Singleton instance
    public static YogaPosesManager getInstance() {
        if (instance == null) {
            instance = new YogaPosesManager();
        }
        return instance;
    }

    public void addPose(YogaPose pose, int poseID) {
        poseList.put(poseID, pose);
    }

    public void addCategories(YogaCategories category, int categoryID) {
        categoriesList.put(categoryID, category);
    }

    public YogaPose getYogaPoseByIndex(int index) {
        return poseList.get(index);
    }

    public YogaCategories getYogaCategoryByIndex(int index) {
        return categoriesList.get(index);
    }

    public ArrayList<Integer> getPoseList(int index) {
        return categoriesList.get(index).getPosesID();
    }

    public int getNumberOfPoses() {
        return poseList.size();
    }

    public int getNumberOfCategories() {
        return categoriesList.size();
    }

    public String getLevelOfCategory(int index) {
        Map<String, Integer> mapping = new HashMap<>();
        ArrayList<Integer> listOfPoses = getPoseList(index);

        for (int i = 0; i < listOfPoses.size(); i++) {
            String key = getYogaPoseByIndex(listOfPoses.get(i)).getDifficulty();
            if (mapping.containsKey(key)) {
                int currentValue = mapping.get(key);
                mapping.put(key, currentValue + 1);
            } else {
                // If the key is not present, add it with a value of 1
                mapping.put(key, 1);
            }
        }

        int max = Integer.MIN_VALUE;
        String keyWithMaxValue = null;

        for (Map.Entry<String, Integer> entry : mapping.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                keyWithMaxValue = entry.getKey();
            }
        }
        return keyWithMaxValue;
    }
}

